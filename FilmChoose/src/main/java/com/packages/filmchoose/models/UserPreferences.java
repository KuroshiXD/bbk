package com.packages.filmchoose.models;

import lombok.Data;
import java.util.List;

@Data
public class UserPreferences {
    private List<String> platforms;
    private List<String> genres;
    private List<String> settings;
    private String rating;
    private String price;
    private List<String> movieTypes;
    private String duration;
    private List<String> graphics;
    private Integer minYear;
    private Integer maxYear;
    private Double minRating;
}