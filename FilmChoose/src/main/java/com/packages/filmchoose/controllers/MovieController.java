// MovieController.java
package com.packages.filmchoose.controllers;

import com.packages.filmchoose.models.MovieRecommendation;
import com.packages.filmchoose.models.UserPreferences;
import com.packages.filmchoose.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("preferences", new UserPreferences());
        return "index";
    }

    @PostMapping("/recommend")
    public String getRecommendations(@ModelAttribute UserPreferences preferences,
                                     Model model) {
        List<MovieRecommendation> recommendations = movieService.findMatchingMovies(preferences);
        model.addAttribute("recommendations", recommendations);
        model.addAttribute("preferences", preferences);
        return "result";
    }

    @GetMapping("/all")
    public String getAllMovies(Model model) {
        model.addAttribute("allMovies", movieService.getAllMovies());
        return "all";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Система подбора фильмов работает!";
    }
}