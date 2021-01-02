package com.agrobalkan.service.impl;

import com.agrobalkan.model.Product;
import com.agrobalkan.service.ProductService;
import com.agrobalkan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private ProductService productService;

    public void saveFile(Product product, MultipartFile file) {

        try {
            product.setPhotoData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.save(product);
    }
}
