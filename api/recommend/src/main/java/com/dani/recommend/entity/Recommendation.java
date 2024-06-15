package com.dani.recommend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * packageName    : com.dani.recommend.entity
 * fileName       : Recommendation
 * author         : kim-daeun
 * date           : 2024/06/15
 * description    :
 */
@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private Integer item_id;
    private Float score;
}
