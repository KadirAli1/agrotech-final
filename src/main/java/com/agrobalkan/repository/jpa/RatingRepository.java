package com.agrobalkan.repository.jpa;


import com.agrobalkan.model.Product;
import com.agrobalkan.model.Rating;
import com.agrobalkan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByUser(User user);

    Rating findAllByUserIdAndProductId(Long userId, Long productId);
}
