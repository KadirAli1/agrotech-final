package com.agrobalkan.repository.jpa;

import com.agrobalkan.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
