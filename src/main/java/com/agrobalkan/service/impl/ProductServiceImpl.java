package com.agrobalkan.service.impl;

import com.agrobalkan.model.Product;
import com.agrobalkan.repository.jpa.ProductRepository;
import com.agrobalkan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Product findOne(Long id) {
        return repo.getOne(id);
    }

    @Override
    public Collection<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Product save(Product entity) { return repo.save(entity); }

    @Override
    public Collection<Product> findAllSpecsByProductType(Long id) { return repo.findAllSpecsByProductType(id); }

    @Override
    public List<Product> findProductsByProductTypeId(Long id) { return repo.findProductsByProductTypeId(id); }

    @Override
    public List<Product> findProductsByCountryId(Long id) { return repo.findProductsByCountryId(id); }

    @Override
    public List<Product> findProductsByProductTypeIdAndCountryId(Long pId, Long cId) { return repo.findProductsByProductTypeIdAndCountryId(pId, cId); }

    @Override
    public Long count() { return repo.count(); }

    @Override
    public List<Product> search(String query) {
        Set<Product> searchResult = new HashSet<Product>();
        searchResult.addAll(repo.findByNameContaining(query));
        searchResult.addAll(repo.findByProductTypeNameLike(query));
        searchResult.addAll(repo.findByCountryNameLike(query));
        return new ArrayList<>(searchResult);
    }

    @Override
    public List<Product> findByPriceBetween(int from, int to){
        return repo.findByPriceBetween(Double.valueOf(from), Double.valueOf(to));
    }

    @Override
    public Integer findMinProductPrice() {
        return repo.findMinProductPrice();
    }

    @Override
    public Integer findMaxProductPrice() {
        return repo.findMaxProductPrice();
    }
}
