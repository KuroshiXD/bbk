package com.packages.filmchoose.services;

import com.packages.filmchoose.models.Movie;
import com.packages.filmchoose.models.MovieRecommendation;
import com.packages.filmchoose.models.UserPreferences;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final List<Movie> moviesDatabase;
    @Getter
    private final List<String> availablePlatforms;
    @Getter
    private final List<String> availableGenres;
    @Getter
    private final List<String> availableSettings;

    public MovieService(List<Movie> moviesDatabase,
                        List<String> availablePlatforms,
                        List<String> availableGenres,
                        List<String> availableSettings) {
        this.moviesDatabase = moviesDatabase;
        this.availablePlatforms = availablePlatforms;
        this.availableGenres = availableGenres;
        this.availableSettings = availableSettings;
    }

    public List<MovieRecommendation> findMatchingMovies(UserPreferences preferences) {
        List<MovieRecommendation> scoredMovies = new ArrayList<>();

        for (Movie movie : moviesDatabase) {
            int score = 0;
            List<String> reasons = new ArrayList<>();

            // Платформа (важный критерий - 15 баллов)
            if (preferences.getPlatforms() != null && !preferences.getPlatforms().isEmpty()) {
                boolean platformMatch = movie.getPlatforms().stream()
                        .anyMatch(platform -> preferences.getPlatforms().contains(platform));
                if (platformMatch) {
                    score += 15;
                    reasons.add("✓ Доступно на выбранной платформе");
                } else {
                    reasons.add("✗ Недоступно на выбранных платформах");
                    continue; // Обязательный критерий
                }
            }

            // Жанр (очень важный - 20 баллов)
            if (preferences.getGenres() != null && !preferences.getGenres().isEmpty()) {
                if (preferences.getGenres().contains(movie.getGenre())) {
                    score += 20;
                    reasons.add("✓ Идеально по жанру");
                } else {
                    reasons.add("✗ Не подходит по жанру");
                }
            }

            // Рейтинг (важный - 12 баллов)
            if (preferences.getRating() != null) {
                if (movie.getRating().equals(preferences.getRating())) {
                    score += 12;
                    reasons.add("✓ Подходит по возрастному рейтингу");
                } else {
                    reasons.add("~ Другой возрастной рейтинг");
                }
            }

            // Год выпуска (важный - 10 баллов)
            if (preferences.getMinYear() != null && preferences.getMaxYear() != null) {
                if (movie.getYear() >= preferences.getMinYear() &&
                        movie.getYear() <= preferences.getMaxYear()) {
                    score += 10;
                    reasons.add("✓ Подходит по году выпуска");
                } else {
                    reasons.add("~ Не входит в выбранный период");
                }
            }

            // Рейтинг IMDB (важный - 15 баллов)
            if (preferences.getMinRating() != null) {
                if (movie.getImdbRating() >= preferences.getMinRating()) {
                    score += 15;
                    reasons.add("✓ Высокий рейтинг IMDB");
                } else {
                    reasons.add("~ Рейтинг ниже желаемого");
                }
            }

            // Тип просмотра (важный - 8 баллов)
            if (preferences.getMovieTypes() != null && !preferences.getMovieTypes().isEmpty()) {
                boolean typeMatch = movie.getMovieType().stream()
                        .anyMatch(type -> preferences.getMovieTypes().contains(type));
                if (typeMatch) {
                    score += 8;
                    reasons.add("✓ Подходит для выбранного типа просмотра");
                } else {
                    reasons.add("~ Не совсем подходит для просмотра");
                }
            }

            // Длительность (менее важный - 5 баллов)
            if (preferences.getDuration() != null &&
                    preferences.getDuration().equals(movie.getDuration())) {
                score += 5;
                reasons.add("✓ Подходит по длительности");
            }

            // Сеттинг (менее важный - 5 баллов)
            if (preferences.getSettings() != null && !preferences.getSettings().isEmpty()) {
                if (preferences.getSettings().contains(movie.getSetting())) {
                    score += 5;
                    reasons.add("✓ Подходит по сеттингу");
                }
            }

            scoredMovies.add(new MovieRecommendation(movie, score, reasons));
        }

        return scoredMovies.stream()
                .sorted(Comparator.comparingInt(MovieRecommendation::getScore).reversed())
                .collect(Collectors.toList());
    }

    public List<Movie> getAllMovies() {
        return moviesDatabase;
    }
}
