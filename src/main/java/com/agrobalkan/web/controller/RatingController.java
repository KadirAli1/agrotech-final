package com.agrobalkan.web.controller;

import com.agrobalkan.model.Product;
import com.agrobalkan.model.Rating;
import com.agrobalkan.model.dto.RatingDto;
import com.agrobalkan.model.User;
import com.agrobalkan.service.ProductService;
import com.agrobalkan.service.RatingService;
import com.agrobalkan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
//@RequestMapping({"/index" })
@RequestMapping({"/rating" })
public class RatingController {

//    @GetMapping
//    public String main(Model model) {
//        model.addAttribute("rating", new Rating());
//        return "index";
//    }
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public String save(RatingDto ratingDto, Model model) {
//        model.addAttribute("rating", ratingDto);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = ((UserDetails)principal).getUsername();
        User user = userService.findByUsername(username);
        Product product = productService.findById(ratingDto.getProductId()).get();

        Rating rating = ratingService.findByUserIdAndProductId(user.getId(), product.getId());
        if(rating != null){
            rating.setStars(ratingDto.getStars());
            ratingService.save(rating);
        } else {
            Rating newRating = new Rating();
            newRating.setUser(user);
            newRating.setProduct(product);
            newRating.setStars(ratingDto.getStars());
            ratingService.save(newRating);
        }
        return "redirect:/products";
    }
}
