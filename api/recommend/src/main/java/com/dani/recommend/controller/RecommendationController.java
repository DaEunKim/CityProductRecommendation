package com.dani.recommend.controller;

import com.dani.recommend.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.dani.recommend.controller
 * fileName       : RecommendationController
 * author         : kim-daeun
 * date           : 2024/06/15
 * description    :
 */
@RestController
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/recommendations")
    public List<Integer> getRecommendations(@RequestParam String city) {
        return recommendationService.getRecommendations(city);
    }
}
