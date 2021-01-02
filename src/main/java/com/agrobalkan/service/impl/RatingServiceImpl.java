package com.agrobalkan.service.impl;

import com.agrobalkan.model.Product;
import com.agrobalkan.model.Rating;
import com.agrobalkan.model.User;
import com.agrobalkan.repository.jpa.RatingRepository;
import com.agrobalkan.repository.jpa.UserRepository;
import com.agrobalkan.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Optional<Rating> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Rating findOne(Long id) {
        return null;
    }

    @Override
    public Collection<Rating> findAll() {
        return null;
    }

    @Override
    public Rating save(Rating entity) {
        return ratingRepository.save(entity);
    }

    @Override
    public List<Rating> getAllRatingsByUser(User user) {
        return ratingRepository.findAllByUser(user);
    }

    @Override
    public Rating findByUserIdAndProductId(Long userId, Long productId) {
        return ratingRepository.findAllByUserIdAndProductId(userId, productId);
    }
}
