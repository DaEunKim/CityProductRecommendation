package com.dani.recommend.repository;

import com.dani.recommend.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName    : com.dani.recommend.repository
 * fileName       : RecommendationRepository
 * author         : kim-daeun
 * date           : 2024/06/15
 * description    :
 */
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

    @Query("SELECT r.item_id FROM Recommendation r WHERE r.city = :city ORDER BY r.score DESC")
    List<Integer> findTop20ByCityOrderByScoreDesc(@Param("city") String city);
}