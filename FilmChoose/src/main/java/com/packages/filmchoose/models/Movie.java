package com.packages.filmchoose.models;

import lombok.Data;
import java.util.List;

@Data
public class Movie {
    private Integer id;
    private String title;
    private List<String> platforms;
    private String genre;
    private String setting;
    private String rating;
    private String price;
    private List<String> movieType;
    private String duration;
    private String graphics;
    private Integer year;
    private String director;
    private Double viewerRating;
}