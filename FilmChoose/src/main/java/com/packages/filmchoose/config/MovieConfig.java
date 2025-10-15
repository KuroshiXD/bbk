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
                    setGenre("Боевик");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(2023);
                    setDirector("Чад Стахелски");
                    setViewerRating(8.2);
                }},

                new Movie() {{
                    setId(2);
                    setTitle("Миссия невыполнима: Смертельная расплата");
                    setGenre("Боевик");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С друзьями"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(2023);
                    setDirector("Кристофер Маккуорри");
                    setViewerRating(8.1);
                }},

                // === МАРВЕЛ (СУПЕРГЕРОИ) ===
                new Movie() {{
                    setId(3);
                    setTitle("Мстители: Финал");
                    setGenre("Супергерои");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("С друзьями", "С семьей", "Вечеринка"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(2019);
                    setDirector("Руссо");
                    setViewerRating(8.4);
                }},

                new Movie() {{
                    setId(4);
                    setTitle("Человек-паук: Вдали от дома");
                    setGenre("Супергерои");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми", "Веселье"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2019);
                    setDirector("Джон Уоттс");
                    setViewerRating(8.1);
                }},

                new Movie() {{
                    setId(5);
                    setTitle("Железный человек");
                    setGenre("Супергерои");
                    setSetting("Современный");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С друзьями"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Эпическая");
                    setYear(2008);
                    setDirector("Джон Фавро");
                    setViewerRating(7.9);
                }},

                // === ЗВЁЗДНЫЕ ВОЙНЫ (КОСМИЧЕСКАЯ ОПЕРА) ===
                new Movie() {{
                    setId(6);
                    setTitle("Звёздные войны: Эпизод IV — Новая надежда");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С друзьями", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(1977);
                    setDirector("Джордж Лукас");
                    setViewerRating(8.6);
                }},

                new Movie() {{
                    setId(7);
                    setTitle("Звёздные войны: Последние джедаи");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "Одиночный просмотр", "Для размышлений"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2019);
                    setDirector("Райан Джонсон");
                    setViewerRating(6.9);
                }},

                new Movie() {{
                    setId(8);
                    setTitle("Изгой один: История Звёздных войн");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2016);
                    setDirector("Гарет Эдвардс");
                    setViewerRating(7.8);
                }},

                // === ФЭНТЕЗИ ===
                new Movie() {{
                    setId(9);
                    setTitle("Властелин колец: Братство кольца");
                    setGenre("Фэнтези");
                    setSetting("Фэнтези");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С семьей"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(2001);
                    setDirector("Питер Джексон");
                    setViewerRating(8.8);
                }},

                // === НАУЧНАЯ ФАНТАСТИКА ===
                new Movie() {{
                    setId(10);
                    setTitle("Интерстеллар");
                    setGenre("Научная фантастика");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Реалистичная");
                    setYear(2014);
                    setDirector("Кристофер Нолан");
                    setViewerRating(8.6);
                }},

                // === ДРАМЫ ===
                new Movie() {{
                    setId(11);
                    setTitle("Зеленая книга");
                    setGenre("Драма");
                    setSetting("Исторический");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С партнером", "Для размышлений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2018);
                    setDirector("Питер Фаррелли");
                    setViewerRating(8.2);
                }},

                new Movie() {{
                    setId(12);
                    setTitle("Форрест Гамп");
                    setGenre("Драма");
                    setSetting("Исторический");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С партнером", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(1994);
                    setDirector("Роберт Земекис");
                    setViewerRating(8.8);
                }},

                // === КОМЕДИИ ===
                new Movie() {{
                    setId(13);
                    setTitle("Мальчишник в Вегасе");
                    setGenre("Комедия");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Вечеринка"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2009);
                    setDirector("Тодд Филлипс");
                    setViewerRating(7.7);
                }},

                // === УЖАСЫ ===
                new Movie() {{
                    setId(14);
                    setTitle("Прочь");
                    setGenre("Ужасы");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для острых ощущений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2017);
                    setDirector("Джордан Пил");
                    setViewerRating(7.7);
                }},

                // === РОМАНТИКА ===
                new Movie() {{
                    setId(15);
                    setTitle("Титаник");
                    setGenre("Романтика");
                    setSetting("Исторический");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С партнером", "Мелодрама"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(1997);
                    setDirector("Джеймс Кэмерон");
                    setViewerRating(7.9);
                }},

                // === АНИМАЦИЯ ===
                new Movie() {{
                    setId(16);
                    setTitle("Тайна Коко");
                    setGenre("Анимация");
                    setSetting("Фэнтези");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми"));
                    setDuration("Короткий (1.5 часа)");
                    setGraphics("Анимационная");
                    setYear(2017);
                    setDirector("Ли Анкрич");
                    setViewerRating(8.4);
                }},

                new Movie() {{
                    setId(17);
                    setTitle("Король Лев");
                    setGenre("Анимация");
                    setSetting("Природа");
                    setRating("G");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми", "Классика"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Анимационная");
                    setYear(1994);
                    setDirector("Роджер Аллерс");
                    setViewerRating(8.5);
                }},

                // === ДОКУМЕНТАЛЬНЫЕ ===
                new Movie() {{
                    setId(18);
                    setTitle("Моя планета");
                    setGenre("Документальный");
                    setSetting("Природа");
                    setRating("G");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Документальная");
                    setYear(2019);
                    setDirector("Альберт Хьюз");
                    setViewerRating(9.3);
                }},

                // === ТРИЛЛЕРЫ ===
                new Movie() {{
                    setId(19);
                    setTitle("Игра на понижение");
                    setGenre("Триллер");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2015);
                    setDirector("Адам МакКей");
                    setViewerRating(7.8);
                }},

                new Movie() {{
                    setId(20);
                    setTitle("Семь");
                    setGenre("Триллер");
                    setSetting("Криминальный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для острых ощущений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Мрачная");
                    setYear(1995);
                    setDirector("Дэвид Финчер");
                    setViewerRating(8.6);
                }},

                // === ДОПОЛНИТЕЛЬНЫЕ ===
                new Movie() {{
                    setId(21);
                    setTitle("Хоббит: Неожиданное путешествие");
                    setGenre("Фэнтези");
                    setSetting("Фэнтези");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2012);
                    setDirector("Питер Джексон");
                    setViewerRating(7.8);
                }},

                new Movie() {{
                    setId(22);
                    setTitle("Гарри Поттер и философский камень");
                    setGenre("Фэнтези");
                    setSetting("Фэнтези");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Волшебная");
                    setYear(2001);
                    setDirector("Крис Коламбус");
                    setViewerRating(7.6);
                }},

                new Movie() {{
                    setId(23);
                    setTitle("Бегущий по лезвию 2049");
                    setGenre("Научная фантастика");
                    setSetting("Киберпанк");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Мрачная");
                    setYear(2017);
                    setDirector("Дени Вильнёв");
                    setViewerRating(8.0);
                }},

                new Movie() {{
                    setId(24);
                    setTitle("Матрица");
                    setGenre("Научная фантастика");
                    setSetting("Киберпанк");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Одиночный просмотр", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(1999);
                    setDirector("Лана и Лилли Вачовски");
                    setViewerRating(8.7);
                }},

                new Movie() {{
                    setId(25);
                    setTitle("Безумный Макс: Дорога ярости");
                    setGenre("Боевик");
                    setSetting("Постапокалипсис");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("С друзьями", "Для острых ощущений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2015);
                    setDirector("Джордж Миллер");
                    setViewerRating(8.1);
                }},

                new Movie() {{
                    setId(26);
                    setTitle("Паразиты");
                    setGenre("Драма");
                    setSetting("Современный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С партнером", "Для размышлений"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(2019);
                    setDirector("Пон Джун-хо");
                    setViewerRating(8.6);
                }},

                new Movie() {{
                    setId(27);
                    setTitle("Остров проклятых");
                    setGenre("Триллер");
                    setSetting("Психологический");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Мрачная");
                    setYear(2010);
                    setDirector("Мартин Скорсезе");
                    setViewerRating(8.2);
                }},

                new Movie() {{
                    setId(28);
                    setTitle("Сияние");
                    setGenre("Ужасы");
                    setSetting("Мистика");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для острых ощущений"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Мрачная");
                    setYear(1980);
                    setDirector("Стэнли Кубрик");
                    setViewerRating(8.4);
                }},

                new Movie() {{
                    setId(29);
                    setTitle("Джокер");
                    setGenre("Драма");
                    setSetting("Криминальный");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Мрачная");
                    setYear(2019);
                    setDirector("Тодд Филлипс");
                    setViewerRating(8.4);
                }},

                new Movie() {{
                    setId(30);
                    setTitle("Начало");
                    setGenre("Научная фантастика");
                    setSetting("Психологический");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С друзьями", "Для размышлений"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2010);
                    setDirector("Кристофер Нолан");
                    setViewerRating(8.8);
                }},

                new Movie() {{
                    setId(31);
                    setTitle("Хроники Нарнии: Лев, колдунья и платяной шкаф");
                    setGenre("Фэнтези");
                    setSetting("Волшебный мир");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С детьми"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Волшебная");
                    setYear(2005);
                    setDirector("Эндрю Адамсон");
                    setViewerRating(6.9);
                }},

                new Movie() {{
                    setId(32);
                    setTitle("Облачный атлас");
                    setGenre("Научная фантастика");
                    setSetting("Исторический");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(2012);
                    setDirector("Том Тыквер, Лана и Лилли Вачовски");
                    setViewerRating(7.4);
                }},

                new Movie() {{
                    setId(33);
                    setTitle("Гравитация");
                    setGenre("Научная фантастика");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Реалистичная");
                    setYear(2013);
                    setDirector("Альфонсо Куарон");
                    setViewerRating(7.7);
                }},

                new Movie() {{
                    setId(34);
                    setTitle("Терминатор 2: Судный день");
                    setGenre("Научная фантастика");
                    setSetting("Киберпанк");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(1991);
                    setDirector("Джеймс Кэмерон");
                    setViewerRating(8.6);
                }},

                new Movie() {{
                    setId(35);
                    setTitle("Список Шиндлера");
                    setGenre("Драма");
                    setSetting("Исторический");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Реалистичная");
                    setYear(1993);
                    setDirector("Стивен Спилберг");
                    setViewerRating(9.0);
                }},

                new Movie() {{
                    setId(36);
                    setTitle("Побег из Шоушенка");
                    setGenre("Драма");
                    setSetting("Криминальный");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Реалистичная");
                    setYear(1994);
                    setDirector("Фрэнк Дарабонт");
                    setViewerRating(9.3);
                }},

                new Movie() {{
                    setId(37);
                    setTitle("Дюна");
                    setGenre("Научная фантастика");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("С друзьями", "Одиночный просмотр"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(2021);
                    setDirector("Дени Вильнёв");
                    setViewerRating(8.1);
                }},

                new Movie() {{
                    setId(38);
                    setTitle("Ведьмак");
                    setGenre("Фэнтези");
                    setSetting("Фэнтези");
                    setRating("R");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("Одиночный просмотр", "С друзьями"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Мрачная");
                    setYear(2019);
                    setDirector("Алик Сахаров и др.");
                    setViewerRating(8.2);
                }},

                new Movie() {{
                    setId(39);
                    setTitle("Донни Дарко");
                    setGenre("Триллер");
                    setSetting("Мистика");
                    setRating("R");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("Одиночный просмотр", "Для размышлений"));
                    setDuration("Средний (2 часа)");
                    setGraphics("Мрачная");
                    setYear(2001);
                    setDirector("Ричард Келли");
                    setViewerRating(8.0);
                }},

                new Movie() {{
                    setId(40);
                    setTitle("Аватар");
                    setGenre("Научная фантастика");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Премиум");
                    setMovieType(Arrays.asList("С друзьями", "С семьей"));
                    setDuration("Очень длинный (3+ часа)");
                    setGraphics("Эпическая");
                    setYear(2009);
                    setDirector("Джеймс Кэмерон");
                    setViewerRating(7.8);
                }},

                new Movie() {{
                    setId(41);
                    setTitle("Звёздные войны: Эпизод I — Скрытая угроза");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С друзьями"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(1999);
                    setDirector("Джордж Лукас");
                    setViewerRating(6.5);
                }},

                new Movie() {{
                    setId(42);
                    setTitle("Звёздные войны: Эпизод II — Атака клонов");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2002);
                    setDirector("Джордж Лукас");
                    setViewerRating(6.6);
                }},

                new Movie() {{
                    setId(43);
                    setTitle("Звёздные войны: Эпизод III — Месть ситхов");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2005);
                    setDirector("Джордж Лукас");
                    setViewerRating(7.6);
                }},

                new Movie() {{
                    setId(44);
                    setTitle("Звёздные войны: Эпизод V — Империя наносит ответный удар");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "С друзьями", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(1980);
                    setDirector("Ирвин Кершнер");
                    setViewerRating(8.7);
                }},

                new Movie() {{
                    setId(45);
                    setTitle("Звёздные войны: Эпизод VI — Возвращение джедая");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С семьей", "Классика"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(1983);
                    setDirector("Ричард Маркуанд");
                    setViewerRating(8.3);
                }},

                new Movie() {{
                    setId(46);
                    setTitle("Звёздные войны: Эпизод VII — Пробуждение силы");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Семейный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2015);
                    setDirector("Дж. Дж. Абрамс");
                    setViewerRating(7.8);
                }},

                new Movie() {{
                    setId(47);
                    setTitle("Звёздные войны: Эпизод IX — Скайуокер. Восход");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2019);
                    setDirector("Дж. Дж. Абрамс");
                    setViewerRating(6.5);
                }},

                new Movie() {{
                    setId(48);
                    setTitle("Хан Соло: Звёздные войны. Истории");
                    setGenre("Космическая опера");
                    setSetting("Космос");
                    setRating("PG-13");
                    setPrice("Стандарт");
                    setMovieType(Arrays.asList("С друзьями", "Одиночный просмотр"));
                    setDuration("Длинный (2.5+ часа)");
                    setGraphics("Эпическая");
                    setYear(2018);
                    setDirector("Рон Ховард");
                    setViewerRating(6.9);
                }}
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