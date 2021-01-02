package com.agrobalkan.repository.jpa;

import com.agrobalkan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.specs FROM Product p WHERE p.productType.id = :id")
    public Collection<Product> findAllSpecsByProductType(@Param("id") Long id);

    public List<Product> findProductsByProductTypeId(Long id);

    public List<Product> findProductsByCountryId(Long id);

    public List<Product> findProductsByProductTypeIdAndCountryId(Long pId, Long cId);

    public List<Product> findByNameContaining(String query);

    public List<Product> findByProductTypeNameLike(String query);

    public List<Product> findByCountryNameLike(String query);

    public List<Product> findByPriceBetween(Double from, Double to);

    @Query("SELECT min(price) FROM Product")
    public Integer findMinProductPrice();

    @Query("SELECT max(price) FROM Product")
    public Integer findMaxProductPrice();
}
