package com.agrobalkan.service.impl;

import com.agrobalkan.model.Country;
import com.agrobalkan.repository.jpa.CountryRepository;
import com.agrobalkan.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repo;

    @Override
    public List<Country> findAll() { return repo.findAll(); }
}
