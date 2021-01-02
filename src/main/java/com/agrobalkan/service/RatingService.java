package com.agrobalkan.service;

import com.agrobalkan.model.Product;
import com.agrobalkan.model.Rating;
import com.agrobalkan.model.User;

import java.util.List;


public interface RatingService extends BaseEntityService<Rating> {
    public List<Rating> getAllRatingsByUser(User user);
    public Rating findByUserIdAndProductId(Long userId, Long productId);
}
