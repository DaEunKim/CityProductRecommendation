package com.dani.recommend.service;

import com.dani.recommend.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.dani.recommend.service
 * fileName       : RecommendationService
 * author         : kim-daeun
 * date           : 2024/06/15
 * description    :
 */
@Service
public class RecommendationService {
    @Autowired
    private RecommendationRepository recommendationRepository;

    public List<Integer> getRecommendations(String city) {
        return recommendationRepository.findTop20ByCityOrderByScoreDesc(city);
    }
}
