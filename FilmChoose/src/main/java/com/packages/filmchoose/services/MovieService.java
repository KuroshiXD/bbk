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

            // Оценка зрителей (важный - 15 баллов)
            if (preferences.getMinRating() != null) {
                if (movie.getViewerRating() >= preferences.getMinRating()) {
                    score += 15;
                    reasons.add("✓ Высокая оценка зрителей");
                } else {
                    reasons.add("~ Оценка зрителей ниже желаемой");
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

            // Хэппи-энд (важный - 10 баллов)
            if (preferences.getHappyEnding() != null) {
                if (preferences.getHappyEnding() && movie.getTitle().toLowerCase().contains("хэппи-энд")) {
                    score += 10;
                    reasons.add("✓ Фильм с хэппи-эндом");
                } else if (!preferences.getHappyEnding() && !movie.getTitle().toLowerCase().contains("хэппи-энд")) {
                    score += 10;
                    reasons.add("✓ Фильм без хэппи-энда");
                }
            }

            // Известные актеры (важный - 8 баллов)
            if (preferences.getFamousActors() != null) {
                if (preferences.getFamousActors() && movie.getDirector().equalsIgnoreCase("Стивен Спилберг")) {
                    score += 8;
                    reasons.add("✓ Фильм с известными актерами");
                }
            }

            // Эпические сцены (важный - 8 баллов)
            if (preferences.getEpicScenes() != null) {
                if (preferences.getEpicScenes() && movie.getGraphics().equalsIgnoreCase("Эпическая")) {
                    score += 8;
                    reasons.add("✓ Эпические сцены");
                }
            }

            // Основано на реальных событиях (важный - 10 баллов)
            if (preferences.getBasedOnTrueStory() != null) {
                if (preferences.getBasedOnTrueStory() && movie.getSetting().equalsIgnoreCase("Исторический")) {
                    score += 10;
                    reasons.add("✓ Основано на реальных событиях");
                }
            }

            // Хорошая музыка (менее важный - 5 баллов)
            if (preferences.getGoodMusic() != null) {
                if (preferences.getGoodMusic() && movie.getTitle().toLowerCase().contains("музыка")) {
                    score += 5;
                    reasons.add("✓ Хорошая музыка");
                }
            }

            // Неожиданные повороты сюжета (важный - 10 баллов)
            if (preferences.getPlotTwists() != null) {
                if (preferences.getPlotTwists() && movie.getGenre().equalsIgnoreCase("Триллер")) {
                    score += 10;
                    reasons.add("✓ Неожиданные повороты сюжета");
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
