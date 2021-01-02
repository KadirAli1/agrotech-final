package com.agrobalkan.service;

import com.agrobalkan.model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public void saveFile(Product product, MultipartFile file);
}
