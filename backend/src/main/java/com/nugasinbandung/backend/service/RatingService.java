package com.nugasinbandung.backend.service;

import com.nugasinbandung.backend.entity.Coffeeshop;
import com.nugasinbandung.backend.entity.Rating;
import com.nugasinbandung.backend.entity.User;
import com.nugasinbandung.backend.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;
    private final CoffeeshopService coffeeshopService;

    public List<Rating> findByCoffeeshopId(Long coffeeshopId) {
        return ratingRepository.findByCoffeeshopId(coffeeshopId);
    }

    public Double getAverageScore(Long coffeeshopId) {
        return ratingRepository.findAverageScoreByCoffeeshopId(coffeeshopId);
    }

    public Rating submitOrUpdate(Long coffeeshopId, String email, Integer score, String comment) {
        User user = userService.findByEmail(email);
        Coffeeshop coffeeshop = coffeeshopService.findById(coffeeshopId);

        Rating rating = ratingRepository
                .findByUserIdAndCoffeeshopId(user.getId(), coffeeshopId)
                .orElse(new Rating());

        rating.setUser(user);
        rating.setCoffeeshop(coffeeshop);
        rating.setScore(score);
        rating.setComment(comment);

        return ratingRepository.save(rating);
    }
}