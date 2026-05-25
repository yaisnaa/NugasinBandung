package com.nugasinbandung.backend.repository;

import com.nugasinbandung.backend.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByCoffeeshopId(Long coffeeshopId);
    Optional<Rating> findByUserIdAndCoffeeshopId(Long userId, Long coffeeshopId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.coffeeshop.id = :coffeeshopId")
    Double findAverageScoreByCoffeeshopId(Long coffeeshopId);
}