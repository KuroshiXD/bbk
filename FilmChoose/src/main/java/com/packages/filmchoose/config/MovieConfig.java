package com.packages.filmchoose.config;

import com.packages.filmchoose.models.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MovieConfig {

    @Bean
    public List<Movie> moviesDatabase() {
        return Arrays.asList(

                // === БОЕВИКИ ===
                new Movie() {{
                    setId(1);
                    setTitle("Джон Уик 4");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko", "Start"));
                    setGenre("Боевик");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(2023);
                    setDirector("Чад Стахелски");
                    setViewerRating(8.2); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(2);
                    setTitle("Миссия невыполнима: Смертельная расплата");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko", "MoreTV"));
                    setGenre("Боевик");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С друзьями"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(2023);
                    setDirector("Кристофер Маккуорри");
                    setViewerRating(8.1); // Заменено с imdbRating
                }},

                // === МАРВЕЛ (СУПЕРГЕРОИ) ===
                new Movie() {{
                    setId(3);
                    setTitle("Мстители: Финал");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Супергерои");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("С друзьями", "С семьей", "Вечеринка"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(2019);
                    setDirector("Руссо");
                    setViewerRating(8.4); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(4);
                    setTitle("Человек-паук: Вдали от дома");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Start"));
                    setGenre("Супергерои");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми", "Веселье"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2019);
                    setDirector("Джон Уоттс");
                    setViewerRating(8.1); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(5);
                    setTitle("Железный человек");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Супергерои");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С друзьями"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Эпическая");
                    setYear(2008);
                    setDirector("Джон Фавро");
                    setViewerRating(7.9); // Заменено с imdbRating
                }},

                // === ЗВЁЗДНЫЕ ВОЙНЫ (КОСМИЧЕСКАЯ ОПЕРА) ===
                new Movie() {{
                    setId(6);
                    setTitle("Звёздные войны: Эпизод IV — Новая надежда");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С друзьями", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(1977);
                    setDirector("Джордж Лукас");
                    setViewerRating(8.6); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(7);
                    setTitle("Звёздные войны: Последние джедаи");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "Одиночный просмотр", "Для размышлений"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2019);
                    setDirector("Райан Джонсон");
                    setViewerRating(6.9); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(8);
                    setTitle("Изгой один: История Звёздных войн");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2016);
                    setDirector("Гарет Эдвардс");
                    setViewerRating(7.8); // Заменено с imdbRating
                }},

                // === ФЭНТЕЗИ ===
                new Movie() {{
                    setId(9);
                    setTitle("Властелин колец: Братство кольца");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Фэнтези");
                    setSetting("Фэнтези");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С семьей"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(2001);
                    setDirector("Питер Джексон");
                    setViewerRating(8.8); // Заменено с imdbRating
                }},

                // === НАУЧНАЯ ФАНТАСТИКА ===
                new Movie() {{
                    setId(10);
                    setTitle("Интерстеллар");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko", "Start"));
                    setGenre("Научная фантастика");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Реалистичная");
                    setYear(2014);
                    setDirector("Кристофер Нолан");
                    setViewerRating(8.6); // Заменено с imdbRating
                }},

                // === ДРАМЫ ===
                new Movie() {{
                    setId(11);
                    setTitle("Зеленая книга");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Драма");
                    setSetting("Исторический");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С партнером", "Для размышлений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2018);
                    setDirector("Питер Фаррелли");
                    setViewerRating(8.2); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(12);
                    setTitle("Форрест Гамп");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko", "Start"));
                    setGenre("Драма");
                    setSetting("Исторический");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С партнером", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(1994);
                    setDirector("Роберт Земекис");
                    setViewerRating(8.8); // Заменено с imdbRating
                }},

                // === КОМЕДИИ ===
                new Movie() {{
                    setId(13);
                    setTitle("Мальчишник в Вегасе");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Комедия");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Вечеринка"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2009);
                    setDirector("Тодд Филлипс");
                    setViewerRating(7.7); // Заменено с imdbRating
                }},

                // === УЖАСЫ ===
                new Movie() {{
                    setId(14);
                    setTitle("Прочь");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Ужасы");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для острых ощущений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2017);
                    setDirector("Джордан Пил");
                    setViewerRating(7.7); // Заменено с imdbRating
                }},

                // === РОМАНТИКА ===
                new Movie() {{
                    setId(15);
                    setTitle("Титаник");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Романтика");
                    setSetting("Исторический");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С партнером", "Мелодрама"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(1997);
                    setDirector("Джеймс Кэмерон");
                    setViewerRating(7.9); // Заменено с imdbRating
                }},

                // === АНИМАЦИЯ ===
                new Movie() {{
                    setId(16);
                    setTitle("Тайна Коко");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Анимация");
                    setSetting("Фэнтези");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми"));
                    setDuration("Короткий (1.5 часа)");
                    setGraphics("Анимационная");
                    setYear(2017);
                    setDirector("Ли Анкрич");
                    setViewerRating(8.4); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(17);
                    setTitle("Король Лев");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Анимация");
                    setSetting("Природа");
                    setRating("G");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми", "Классика"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Анимационная");
                    setYear(1994);
                    setDirector("Роджер Аллерс");
                    setViewerRating(8.5); // Заменено с imdbRating
                }},

                // === ДОКУМЕНТАЛЬНЫЕ ===
                new Movie() {{
                    setId(18);
                    setTitle("Моя планета");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Документальный");
                    setSetting("Природа");
                    setRating("G");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Документальная");
                    setYear(2019);
                    setDirector("Альберт Хьюз");
                    setViewerRating(9.3); // Заменено с imdbRating
                }},

                // === ТРИЛЛЕРЫ ===
                new Movie() {{
                    setId(19);
                    setTitle("Игра на понижение");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Триллер");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2015);
                    setDirector("Адам МакКей");
                    setViewerRating(7.8); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(20);
                    setTitle("Семь");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Триллер");
                    setSetting("Криминальный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для острых ощущений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Мрачная");
                    setYear(1995);
                    setDirector("Дэвид Финчер");
                    setViewerRating(8.6); // Заменено с imdbRating
                }},

                // === ДОПОЛНИТЕЛЬНЫЕ ===
                new Movie() {{
                    setId(21);
                    setTitle("Хоббит: Неожиданное путешествие");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Фэнтези");
                    setSetting("Фэнтези");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2012);
                    setDirector("Питер Джексон");
                    setViewerRating(7.8); // Заменено с imdbRating
                }},

                new Movie() {{
                    setId(22);
                    setTitle("Гарри Поттер и философский камень");
                    setPlatforms(Arrays.asList("Кинопоиск", "IVI", "Okko"));
                    setGenre("Фэнтези");
                    setSetting("Фэнтези");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Волшебная");
                    setYear(2001);
                    setDirector("Крис Коламбус");
                    setViewerRating(7.6); // Заменено с imdbRating
                }}
        );
    }

    @Bean
    public List<String> availablePlatforms() {
        return Arrays.asList(
                "Кинопоиск",
                "IVI",
                "Okko",
                "Start",
                "MoreTV",
                "YouTube",
                "Кинотеатр"
        );
    }

    @Bean
    public List<String> availableGenres() {
        return Arrays.asList(
                "Боевик",
                "Супергерои",
                "Фэнтези",
                "Научная фантастика",
                "Драма",
                "Комедия",
                "Ужасы",
                "Романтика",
                "Анимация",
                "Документальный",
                "Триллер",
                "Космическая опера",
                "Приключения"
        );
    }

    @Bean
    public List<String> availableSettings() {
        return Arrays.asList(
                "Современный",
                "Фэнтези",
                "Космос",
                "Киберпанк",
                "Исторический",
                "Природа",
                "Психологический",
                "Криминальный",
                "Постапокалипсис",
                "Волшебный мир",
                "Мистика"
        );
    }
}