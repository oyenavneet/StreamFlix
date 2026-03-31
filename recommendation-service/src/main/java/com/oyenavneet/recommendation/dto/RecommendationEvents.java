package com.oyenavneet.recommendation.dto;

public class RecommendationEvents {
    public record NewMovieEvent(Integer movieId) {

    }

    public record PersonalizedEvent(Integer customerId) {

    }
}
