package com.agrobalkan.model.dto;

import com.agrobalkan.model.Country;
import com.agrobalkan.model.ProductType;

import java.util.Date;

public class ProductDto {
    public Long id;
    public String name;
    public String imageName;
    public byte[] photoData;
    public String specs;
    public Double price;
    public Date date = new Date();
    public ProductType productType;
    public Country country;
    public float stars;
}
