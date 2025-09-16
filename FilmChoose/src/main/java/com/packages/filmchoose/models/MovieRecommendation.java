package com.packages.filmchoose.models;

import lombok.Data;
import java.util.List;

@Data
public class MovieRecommendation {
    private Movie movie;
    private Integer score;
    private List<String> reasons;

    public MovieRecommendation(Movie movie, Integer score, List<String> reasons) {
        this.movie = movie;
        this.score = score;
        this.reasons = reasons;
    }
}
