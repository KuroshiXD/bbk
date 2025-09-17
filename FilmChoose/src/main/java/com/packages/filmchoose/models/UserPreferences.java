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
    private Boolean happyEnding;
    private Boolean famousActors;
    private Boolean epicScenes;
    private Boolean basedOnTrueStory;
    private Boolean goodMusic;
    private Boolean plotTwists;
}