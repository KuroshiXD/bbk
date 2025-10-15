
package com.packages.filmchoose.services;

import com.packages.filmchoose.models.Movie;
import com.packages.filmchoose.models.MovieRecommendation;
import com.packages.filmchoose.models.UserPreferences;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final List<Movie> moviesDatabase;

    public MovieService(List<Movie> moviesDatabase) {
        this.moviesDatabase = moviesDatabase;
    }

    public List<MovieRecommendation> findMatchingMovies(UserPreferences preferences) {
        List<MovieRecommendation> scoredMovies = new ArrayList<>();

        for (Movie movie : moviesDatabase) {
            int score = 0;
            List<String> reasons = new ArrayList<>();

            // === ПРОВЕРКА ПО ЖАНРАМ (на основе ответов "да/нет") ===
            boolean genreMatch = false;
            if (preferences.getLikesAction() != null && preferences.getLikesAction() && movie.getGenre().equals("Боевик")) {
                score += 20;
                reasons.add("✓ Нравится экшен — идеально подходит");
                genreMatch = true;
            }
            if (preferences.getLikesSuperheroes() != null && preferences.getLikesSuperheroes() && movie.getGenre().equals("Супергерои")) {
                score += 20;
                reasons.add("✓ Любите супергероев — отличный выбор");
                genreMatch = true;
            }
            if (preferences.getLikesFantasy() != null && preferences.getLikesFantasy() && movie.getGenre().equals("Фэнтези")) {
                score += 20;
                reasons.add("✓ Нравится магия и драконы — подходит");
                genreMatch = true;
            }
            if (preferences.getLikesSciFi() != null && preferences.getLikesSciFi() && movie.getGenre().equals("Научная фантастика")) {
                score += 20;
                reasons.add("✓ Любите технологии и космос — идеально");
                genreMatch = true;
            }
            if (preferences.getLikesDrama() != null && preferences.getLikesDrama() && movie.getGenre().equals("Драма")) {
                score += 20;
                reasons.add("✓ Нравятся глубокие истории — подходит");
                genreMatch = true;
            }
            if (preferences.getLikesComedy() != null && preferences.getLikesComedy() && movie.getGenre().equals("Комедия")) {
                score += 20;
                reasons.add("✓ Любите шутки — отличный выбор");
                genreMatch = true;
            }
            if (preferences.getLikesHorror() != null && preferences.getLikesHorror() && movie.getGenre().equals("Ужасы")) {
                score += 20;
                reasons.add("✓ Нравится напряжение и страх — подходит");
                genreMatch = true;
            }
            if (preferences.getLikesRomance() != null && preferences.getLikesRomance() && movie.getGenre().equals("Романтика")) {
                score += 20;
                reasons.add("✓ Любите истории о любви — идеально");
                genreMatch = true;
            }
            if (preferences.getLikesAnimation() != null && preferences.getLikesAnimation() && movie.getGenre().equals("Анимация")) {
                score += 20;
                reasons.add("✓ Нравятся мультфильмы — подходит");
                genreMatch = true;
            }
            if (preferences.getLikesDocumentary() != null && preferences.getLikesDocumentary() && movie.getGenre().equals("Документальный")) {
                score += 20;
                reasons.add("✓ Любите реальные факты — отлично");
                genreMatch = true;
            }
            if (preferences.getLikesThriller() != null && preferences.getLikesThriller() && movie.getGenre().equals("Триллер")) {
                score += 20;
                reasons.add("✓ Нравятся неожиданные повороты — подходит");
                genreMatch = true;
            }
            if (preferences.getLikesSpaceOpera() != null && preferences.getLikesSpaceOpera() && movie.getGenre().equals("Космическая опера")) {
                score += 20;
                reasons.add("✓ Любите эпические космические саги — идеально");
                genreMatch = true;
            }
            if (preferences.getLikesAdventure() != null && preferences.getLikesAdventure() && movie.getGenre().equals("Приключения")) {
                score += 20;
                reasons.add("✓ Нравятся путешествия и сокровища — подходит");
                genreMatch = true;
            }

            if (!genreMatch && (anyGenreSelected(preferences))) {
                reasons.add("✗ Не совпадает с выбранными жанрами");
            }

            // === ПРОВЕРКА ПО СЕТТИНГУ ===
            boolean settingMatch = false;
            if (preferences.getSettingModern() != null && preferences.getSettingModern() && movie.getSetting().equals("Современный")) {
                score += 5;
                reasons.add("✓ Действие в наше время — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingFantasy() != null && preferences.getSettingFantasy() && movie.getSetting().equals("Фэнтези")) {
                score += 5;
                reasons.add("✓ Волшебный мир — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingSpace() != null && preferences.getSettingSpace() && movie.getSetting().equals("Космос")) {
                score += 5;
                reasons.add("✓ Действие в космосе — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingCyberpunk() != null && preferences.getSettingCyberpunk() && movie.getSetting().equals("Киберпанк")) {
                score += 5;
                reasons.add("✓ Мрачный футуризм — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingHistorical() != null && preferences.getSettingHistorical() && movie.getSetting().equals("Исторический")) {
                score += 5;
                reasons.add("✓ Реальные исторические события — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingNature() != null && preferences.getSettingNature() && movie.getSetting().equals("Природа")) {
                score += 5;
                reasons.add("✓ Действие среди природы — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingPsychological() != null && preferences.getSettingPsychological() && movie.getSetting().equals("Психологический")) {
                score += 5;
                reasons.add("✓ Важна психология героев — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingCrime() != null && preferences.getSettingCrime() && movie.getSetting().equals("Криминальный")) {
                score += 5;
                reasons.add("✓ Мафия и преступления — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingPostApocalypse() != null && preferences.getSettingPostApocalypse() && movie.getSetting().equals("Постапокалипсис")) {
                score += 5;
                reasons.add("✓ Выживание после катастрофы — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingMagicWorld() != null && preferences.getSettingMagicWorld() && movie.getSetting().equals("Волшебный мир")) {
                score += 5;
                reasons.add("✓ Сказочная атмосфера — подходит");
                settingMatch = true;
            }
            if (preferences.getSettingMystic() != null && preferences.getSettingMystic() && movie.getSetting().equals("Мистика")) {
                score += 5;
                reasons.add("✓ Загадки и тайны — подходит");
                settingMatch = true;
            }

            if (!settingMatch && (anySettingSelected(preferences))) {
                reasons.add("~ Не совпадает с выбранными сеттингами");
            }

            // Возрастной рейтинг
            if (preferences.getRating() != null) {
                if (movie.getRating().equals(preferences.getRating())) {
                    score += 12;
                    reasons.add("✓ Подходит по возрастному рейтингу");
                } else {
                    reasons.add("~ Другой возрастной рейтинг");
                }
            }

            // Год выпуска
            if (preferences.getMinYear() != null && preferences.getMaxYear() != null) {
                if (movie.getYear() >= preferences.getMinYear() &&
                        movie.getYear() <= preferences.getMaxYear()) {
                    score += 10;
                    reasons.add("✓ Подходит по году выпуска");
                } else {
                    reasons.add("~ Не входит в выбранный период");
                }
            }

            // Оценка зрителей
            if (preferences.getMinRating() != null) {
                if (movie.getViewerRating() >= preferences.getMinRating()) {
                    score += 15;
                    reasons.add("✓ Высокая оценка зрителей");
                } else {
                    reasons.add("~ Оценка зрителей ниже желаемой");
                }
            }

            // Тип просмотра
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

            // Длительность
            if (preferences.getDuration() != null &&
                    preferences.getDuration().equals(movie.getDuration())) {
                score += 5;
                reasons.add("✓ Подходит по длительности");
            }

            scoredMovies.add(new MovieRecommendation(movie, score, reasons));
        }

        return scoredMovies.stream()
                .sorted(Comparator.comparingInt(MovieRecommendation::getScore).reversed())
                .collect(Collectors.toList());
    }

    private boolean anyGenreSelected(UserPreferences p) {
        return (p.getLikesAction() != null && p.getLikesAction()) ||
                (p.getLikesSuperheroes() != null && p.getLikesSuperheroes()) ||
                (p.getLikesFantasy() != null && p.getLikesFantasy()) ||
                (p.getLikesSciFi() != null && p.getLikesSciFi()) ||
                (p.getLikesDrama() != null && p.getLikesDrama()) ||
                (p.getLikesComedy() != null && p.getLikesComedy()) ||
                (p.getLikesHorror() != null && p.getLikesHorror()) ||
                (p.getLikesRomance() != null && p.getLikesRomance()) ||
                (p.getLikesAnimation() != null && p.getLikesAnimation()) ||
                (p.getLikesDocumentary() != null && p.getLikesDocumentary()) ||
                (p.getLikesThriller() != null && p.getLikesThriller()) ||
                (p.getLikesSpaceOpera() != null && p.getLikesSpaceOpera()) ||
                (p.getLikesAdventure() != null && p.getLikesAdventure());
    }

    private boolean anySettingSelected(UserPreferences p) {
        return (p.getSettingModern() != null && p.getSettingModern()) ||
                (p.getSettingFantasy() != null && p.getSettingFantasy()) ||
                (p.getSettingSpace() != null && p.getSettingSpace()) ||
                (p.getSettingCyberpunk() != null && p.getSettingCyberpunk()) ||
                (p.getSettingHistorical() != null && p.getSettingHistorical()) ||
                (p.getSettingNature() != null && p.getSettingNature()) ||
                (p.getSettingPsychological() != null && p.getSettingPsychological()) ||
                (p.getSettingCrime() != null && p.getSettingCrime()) ||
                (p.getSettingPostApocalypse() != null && p.getSettingPostApocalypse()) ||
                (p.getSettingMagicWorld() != null && p.getSettingMagicWorld()) ||
                (p.getSettingMystic() != null && p.getSettingMystic());
    }

    public List<Movie> getAllMovies() {
        return moviesDatabase;
    }
}