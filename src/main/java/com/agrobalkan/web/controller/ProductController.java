package com.agrobalkan.web.controller;

import com.agrobalkan.config.layout.Layout;
import com.agrobalkan.model.PriceRange;
import com.agrobalkan.model.Product;
import com.agrobalkan.model.Rating;
import com.agrobalkan.model.User;
import com.agrobalkan.model.dto.ProductDto;
import com.agrobalkan.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    private Boolean compareError = false;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Layout("layout/default")
    @GetMapping
    public String products(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "rangeMin", required = false) Integer rangeMin,
            @RequestParam(value = "rangeMax", required = false) Integer rangeMax,
            Model model) {

        List<Product> typeFilteredProducts = new ArrayList<>();
        List<Product> countryFilteredProducts = new ArrayList<>();

        if(type != null) {

            Long[] types = filterProductParameterHelper(type);

            for(int i=0; i<types.length; i++) {
                typeFilteredProducts.addAll( productService.findProductsByProductTypeId(types[i]) );
            }

            model.addAttribute("typeFilteredProducts", mapProductToProductDto(typeFilteredProducts));
        }

        if(country != null) {

            Long[] countries = filterProductParameterHelper(country);

            for(int i=0; i<countries.length; i++) {
                countryFilteredProducts.addAll( productService.findProductsByCountryId(countries[i]) );
            }

            model.addAttribute("countryFilteredProducts", mapProductToProductDto(countryFilteredProducts));
        }

        if(type != null && country != null) {

            Set<Product> bothFiltered = new HashSet<Product>();

            Long[] types = filterProductParameterHelper(type);
            Long[] countries = filterProductParameterHelper(country);

            for(int i=0; i<types.length; i++) {
                for(int j=0; j<countries.length; j++) {
                    bothFiltered.addAll(productService.findProductsByProductTypeIdAndCountryId(types[i], countries[j]));
                }
            }

            model.addAttribute("bothFiltered", mapProductToProductDto(new ArrayList<>(bothFiltered)));
        }

        if(type == null && country == null) {

            List<Product> products = (List<Product>) productService.findAll();

            model.addAttribute("products", mapProductToProductDto(products));
        }

        Integer minPrice = productService.findMinProductPrice();
        Integer maxPrice = productService.findMaxProductPrice();
        if(rangeMin != null && rangeMax != null) {
            List<Product> products = productService.findByPriceBetween(rangeMin, rangeMax);
            model.addAttribute("products", mapProductToProductDto(products));
            model.addAttribute("dataSliderValue", "[" + rangeMin +"," + rangeMax + "]");
        } else{
            model.addAttribute("dataSliderValue", "[" + minPrice +"," + maxPrice + "]");
        }

        if(this.compareError) {
            model.addAttribute("compareError", "Selected products are not comparable. Please select products from the same product type!");
            this.compareError = false;
        }

        model.addAttribute("activeNav", "products");
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("priceRangeMin", minPrice);
        model.addAttribute("priceRangeMax", maxPrice);
        return "products";
    }

    private Long[] filterProductParameterHelper(String number) {

        String[] numbers = number.split(",");
        Long[] result = new Long[numbers.length];

        for(int i=0; i<numbers.length; i++) {
            result[i] = Long.parseLong(numbers[i]);
        }

        return result;
    }

    private List<ProductDto> mapProductToProductDto(List<Product> products) {
        List<ProductDto> productDtos = products.stream().map(product ->
                {
                    ProductDto productDto = new ProductDto();
                    productDto.id = product.getId();
                    productDto.country = product.getCountry();
                    productDto.date = product.getDate();
                    productDto.imageName = product.getImageName();
                    productDto.name = product.getName();
                    productDto.photoData = product.getPhotoData();
                    productDto.price = product.getPrice();
                    productDto.productType = product.getProductType();
                    productDto.specs = product.getSpecs();

                    return productDto;
                }
        ).collect(Collectors.toList());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            String username = ((UserDetails)principal).getUsername();
            User user = userService.findByUsername(username);
            List<Rating> ratings = ratingService.getAllRatingsByUser(user);
            for(ProductDto productDto : productDtos){
                Optional<Rating> result = ratings.stream().filter(rating -> rating.getProduct().getId() == productDto.id).findFirst();
                if(result.isPresent()){
                    Rating r = result.get();
                    productDto.stars = r.getStars();
                } else {
                    productDto.stars = 0;
                }
            }
        }

        return productDtos;
    }

    @Layout("layout/default")
    @GetMapping("/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model) {

        Optional<Product> optProduct= productService.findById(id);

        Product noProduct= new Product();
        noProduct.setName("No product with id: " + id);

        model.addAttribute("activeNav", "products");
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("product", optProduct.orElse(noProduct));
        return "product_details";
    }

    @Layout("layout/default")
    @GetMapping("/compare/{firstId}/{secondId}")
    @Transactional
    public String compareProducts(@PathVariable("firstId") Long firstId, @PathVariable("secondId") Long secondId, Model model) {

        Product firstProduct = productService.findOne(firstId);
        Product secondProduct = productService.findOne(secondId);

        if(firstProduct.getProductType().getId() == secondProduct.getProductType().getId()) {
            model.addAttribute("activeNav", "products");
            model.addAttribute("productTypes", productTypeService.findAll());
            model.addAttribute("firstProduct", firstProduct);
            model.addAttribute("secondProduct", secondProduct);
            return "compare";
        }

        this.compareError = true;
        return "redirect:/products";
    }
}
