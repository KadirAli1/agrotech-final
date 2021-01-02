package com.agrobalkan.service.impl;

import com.agrobalkan.model.ProductType;
import com.agrobalkan.repository.jpa.ProductTypeRepository;
import com.agrobalkan.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository repo;

    @Override
    public Optional<ProductType> findById(Long id) { return repo.findById(id); }

    @Override
    public ProductType findOne(Long id) { return repo.getOne(id); }

    @Override
    public Collection<ProductType> findAll() { return repo.findAll(); }

    @Override
    public ProductType save(ProductType entity) { return repo.save(entity); }

    @Override
    public Long count() { return repo.count(); }
}
