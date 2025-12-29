package com.packages.movierec.services;

import com.packages.movierec.models.EvidenceData;
import com.packages.movierec.models.Hypothesis;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieDecisionEngine {

    private List<Hypothesis> hypotheses;
    private double[] probabilities;
    private final Map<Integer, Boolean> observedEvidence = new HashMap<>();
    private final Map<Integer, Integer> evidenceAnswers = new HashMap<>(); // Сохраняем рейтинги
    private final Set<Integer> allEvidenceIds = new HashSet<>();

    private static final double PM = 0.65;    // Высокая уверенность
    private static final double M1 = 0.40;    // Все гипотезы вероятны
    private static final double M2 = 0.05;    // Низкая уверенность

    // Минимальное количество вопросов перед остановом
    private static final int MIN_QUESTIONS = 5;
    // Максимальное количество вопросов
    private static final int MAX_QUESTIONS = 30;

    // Группы взаимоисключающих вопросов
    private final Map<String, List<Integer>> exclusiveGroups = Map.of(
            "genre", Arrays.asList(1, 2, 3, 4, 5),      // Жанры
            "mood", Arrays.asList(6, 7, 8),            // Настроение
            "time", Arrays.asList(9, 10, 11),          // Время просмотра
            "company", Arrays.asList(12, 13, 14, 15),  // Компания
            "year", Arrays.asList(16, 17, 18)          // Год выпуска
    );

    @PostConstruct
    public void init() {
        loadHypotheses();
        reset();
    }

    private void loadHypotheses() {
        hypotheses = new ArrayList<>();

        // Приоры: 29 фильмов (было 30, убрали "Дневник памяти")
        double prior = 1.0 / 29.0; // примерно 0.0345

        // 1. Боевик - Миссия невыполнима
        hypotheses.add(new Hypothesis(
                "Миссия невыполнима: Смертельная расплата",
                prior,
                Map.of(
                        1, new EvidenceData(0.95, 0.1),   // Драки и погони
                        9, new EvidenceData(0.85, 0.3),   // Легкое и развлекательное
                        13, new EvidenceData(0.7, 0.4),   // Вечер после работы
                        18, new EvidenceData(0.8, 0.25),  // С друзьями
                        22, new EvidenceData(0.9, 0.15),  // Современные
                        31, new EvidenceData(0.9, 0.2),   // Спецэффекты
                        34, new EvidenceData(0.95, 0.1)   // Экшн-сцены
                )
        ));

        // 2. Комедия - Один дома
        hypotheses.add(new Hypothesis(
                "Один дома",
                prior,
                Map.of(
                        3, new EvidenceData(0.95, 0.1),   // Смешные и веселые
                        9, new EvidenceData(0.9, 0.15),   // Легкое и развлекательное
                        14, new EvidenceData(0.85, 0.2),  // На выходных
                        19, new EvidenceData(0.8, 0.25),  // С семьей
                        21, new EvidenceData(0.7, 0.35),  // С детьми
                        23, new EvidenceData(0.7, 0.35),  // Классика
                        12, new EvidenceData(0.9, 0.15)   // Посмеяться
                )
        ));

        // 3. Научная фантастика - Интерстеллар
        hypotheses.add(new Hypothesis(
                "Интерстеллар",
                prior,
                Map.of(
                        4, new EvidenceData(0.9, 0.15),   // Космос и технологии
                        10, new EvidenceData(0.95, 0.1),  // Серьезное и заставляющее задуматься
                        14, new EvidenceData(0.7, 0.35),  // На выходных
                        17, new EvidenceData(0.65, 0.4),  // Один
                        22, new EvidenceData(0.8, 0.25),  // Современные
                        31, new EvidenceData(0.9, 0.15),  // Спецэффекты
                        35, new EvidenceData(0.95, 0.1)   // Философия
                )
        ));

        // 4. Ужасы - Сияние
        hypotheses.add(new Hypothesis(
                "Сияние",
                prior,
                Map.of(
                        5, new EvidenceData(0.9, 0.15),   // Страшные и напряженные
                        11, new EvidenceData(0.85, 0.2),  // Сильные эмоции
                        18, new EvidenceData(0.75, 0.3),  // С друзьями
                        15, new EvidenceData(0.6, 0.45),  // В кинотеатре
                        23, new EvidenceData(0.7, 0.35),  // Классика
                        32, new EvidenceData(0.9, 0.15),  // Атмосферный
                        29, new EvidenceData(0.8, 0.25)   // Неожиданная концовка
                )
        ));

        // 5. Marvel - Человек-паук: Через вселенные
        hypotheses.add(new Hypothesis(
                "Человек-паук: Через вселенные",
                prior,
                Map.of(
                        1, new EvidenceData(0.85, 0.2),   // Драки и погони
                        3, new EvidenceData(0.8, 0.25),   // Смешные и веселые
                        6, new EvidenceData(0.95, 0.1),   // Супергерои
                        9, new EvidenceData(0.9, 0.15),   // Легкое и развлекательное
                        18, new EvidenceData(0.85, 0.2),  // С друзьями
                        22, new EvidenceData(0.95, 0.1),  // Современные
                        33, new EvidenceData(0.9, 0.15)   // Анимация
                )
        ));

        // 6. Marvel - Мстители: Финал
        hypotheses.add(new Hypothesis(
                "Мстители: Финал",
                prior,
                Map.of(
                        1, new EvidenceData(0.95, 0.1),   // Драки и погони
                        4, new EvidenceData(0.8, 0.25),   // Космос и технологии
                        6, new EvidenceData(0.95, 0.1),   // Супергерои
                        11, new EvidenceData(0.9, 0.15),  // Сильные эмоции
                        18, new EvidenceData(0.9, 0.15),  // С друзьями
                        22, new EvidenceData(0.9, 0.15),  // Современные
                        31, new EvidenceData(0.95, 0.1)   // Спецэффекты
                )
        ));

        // 7. Marvel - Железный человек
        hypotheses.add(new Hypothesis(
                "Железный человек",
                prior,
                Map.of(
                        1, new EvidenceData(0.9, 0.15),   // Драки и погони
                        4, new EvidenceData(0.95, 0.1),   // Космос и технологии
                        6, new EvidenceData(0.9, 0.15),   // Супергерои
                        9, new EvidenceData(0.85, 0.2),   // Легкое и развлекательное
                        18, new EvidenceData(0.8, 0.25),  // С друзьями
                        22, new EvidenceData(0.8, 0.25),  // Современные
                        34, new EvidenceData(0.9, 0.15)   // Экшн-сцены
                )
        ));

        // 8. Звездные войны - Империя наносит ответный удар
        hypotheses.add(new Hypothesis(
                "Звездные войны: Эпизод V - Империя наносит ответный удар",
                prior,
                Map.of(
                        1, new EvidenceData(0.85, 0.2),   // Драки и погони
                        4, new EvidenceData(0.95, 0.1),   // Космос и технологии
                        7, new EvidenceData(0.8, 0.25),   // Фэнтези/магия
                        10, new EvidenceData(0.8, 0.25),  // Серьезное
                        18, new EvidenceData(0.8, 0.25),  // С друзьями
                        23, new EvidenceData(0.85, 0.2),  // Классика
                        31, new EvidenceData(0.7, 0.35)   // Спецэффекты (для своего времени)
                )
        ));

        // 9. Драма - Зеленая миля
        hypotheses.add(new Hypothesis(
                "Зеленая миля",
                prior,
                Map.of(
                        2, new EvidenceData(0.9, 0.15),   // Отношения и эмоции
                        10, new EvidenceData(0.95, 0.1),  // Серьезное и заставляющее задуматься
                        11, new EvidenceData(0.9, 0.15),  // Сильные эмоции
                        17, new EvidenceData(0.7, 0.35),  // Один
                        23, new EvidenceData(0.8, 0.25),  // Классика
                        35, new EvidenceData(0.9, 0.15),  // Философия
                        36, new EvidenceData(0.6, 0.45)   // Основано на книге
                )
        ));

        // 10. Фэнтези - Властелин колец: Братство кольца
        hypotheses.add(new Hypothesis(
                "Властелин колец: Братство кольца",
                prior,
                Map.of(
                        4, new EvidenceData(0.8, 0.25),   // Технологии/магия
                        7, new EvidenceData(0.95, 0.1),   // Фэнтези и магия
                        10, new EvidenceData(0.85, 0.2),  // Серьезное
                        14, new EvidenceData(0.9, 0.15),  // На выходных
                        18, new EvidenceData(0.8, 0.25),  // С друзьями
                        23, new EvidenceData(0.85, 0.2),  // Классика
                        27, new EvidenceData(0.7, 0.35)   // Длинный фильм
                )
        ));

        // 11. Приключения - Индиана Джонс
        hypotheses.add(new Hypothesis(
                "Индиана Джонс: В поисках утраченного ковчега",
                prior,
                Map.of(
                        1, new EvidenceData(0.85, 0.2),   // Драки и погони
                        9, new EvidenceData(0.9, 0.15),   // Легкое и развлекательное
                        14, new EvidenceData(0.8, 0.25),  // На выходных
                        18, new EvidenceData(0.85, 0.2),  // С друзьями
                        23, new EvidenceData(0.75, 0.3),  // Классика
                        34, new EvidenceData(0.9, 0.15),  // Экшн-сцены
                        36, new EvidenceData(0.7, 0.35)   // Приключения
                )
        ));

        // 12. Триллер - Молчание ягнят
        hypotheses.add(new Hypothesis(
                "Молчание ягнят",
                prior,
                Map.of(
                        5, new EvidenceData(0.85, 0.2),   // Страшные и напряженные
                        10, new EvidenceData(0.9, 0.15),  // Серьезное и заставляющее задуматься
                        13, new EvidenceData(0.7, 0.35),  // Вечер после работы
                        17, new EvidenceData(0.75, 0.3),  // Один
                        23, new EvidenceData(0.8, 0.25),  // Классика
                        29, new EvidenceData(0.9, 0.15),  // Неожиданная концовка
                        35, new EvidenceData(0.85, 0.2)   // Психология
                )
        ));

        // 13. Мультфильм - Холодное сердце
        hypotheses.add(new Hypothesis(
                "Холодное сердце",
                prior,
                Map.of(
                        3, new EvidenceData(0.9, 0.15),   // Смешные и веселые
                        9, new EvidenceData(0.95, 0.1),   // Легкое и развлекательное
                        14, new EvidenceData(0.8, 0.25),  // На выходных
                        19, new EvidenceData(0.9, 0.15),  // С семьей
                        21, new EvidenceData(0.95, 0.1),  // С детьми
                        22, new EvidenceData(0.85, 0.2),  // Современные
                        30, new EvidenceData(0.9, 0.15)   // Музыкальные номера
                )
        ));

        // 14. Драма - Форрест Гамп
        hypotheses.add(new Hypothesis(
                "Форрест Гамп",
                prior,
                Map.of(
                        2, new EvidenceData(0.9, 0.15),   // Отношения и эмоции
                        10, new EvidenceData(0.85, 0.2),  // Серьезное
                        11, new EvidenceData(0.8, 0.25),  // Сильные эмоции
                        17, new EvidenceData(0.7, 0.35),  // Один
                        23, new EvidenceData(0.75, 0.3),  // Классика
                        28, new EvidenceData(0.8, 0.25),  // Хэппи-энд
                        36, new EvidenceData(0.7, 0.35)   // Исторический контекст
                )
        ));

        // 15. Marvel - Стражи Галактики
        hypotheses.add(new Hypothesis(
                "Стражи Галактики",
                prior,
                Map.of(
                        1, new EvidenceData(0.85, 0.2),   // Драки и погони
                        3, new EvidenceData(0.9, 0.15),   // Смешные и веселые
                        4, new EvidenceData(0.95, 0.1),   // Космос и технологии
                        6, new EvidenceData(0.9, 0.15),   // Супергерои
                        9, new EvidenceData(0.9, 0.15),   // Легкое и развлекательное
                        18, new EvidenceData(0.9, 0.15),  // С друзьями
                        30, new EvidenceData(0.8, 0.25)   // Музыка
                )
        ));

        // 16. Marvel - Чёрная пантера
        hypotheses.add(new Hypothesis(
                "Чёрная пантера",
                prior,
                Map.of(
                        1, new EvidenceData(0.9, 0.15),   // Драки и погони
                        6, new EvidenceData(0.95, 0.1),   // Супергерои
                        10, new EvidenceData(0.85, 0.2),  // Серьезное
                        11, new EvidenceData(0.9, 0.15),  // Сильные эмоции
                        18, new EvidenceData(0.8, 0.25),  // С друзьями
                        22, new EvidenceData(0.9, 0.15),  // Современные
                        35, new EvidenceData(0.8, 0.25)   // Социальные темы
                )
        ));

        // 17. Звездные войны - Пробуждение силы
        hypotheses.add(new Hypothesis(
                "Звездные войны: Пробуждение силы",
                prior,
                Map.of(
                        1, new EvidenceData(0.9, 0.15),   // Драки и погони
                        4, new EvidenceData(0.95, 0.1),   // Космос и технологии
                        7, new EvidenceData(0.8, 0.25),   // Фэнтези/магия
                        9, new EvidenceData(0.8, 0.25),   // Легкое и развлекательное
                        18, new EvidenceData(0.85, 0.2),  // С друзьями
                        22, new EvidenceData(0.9, 0.15),  // Современные
                        31, new EvidenceData(0.9, 0.15)   // Спецэффекты
                )
        ));

        // 18. Драма - Побег из Шоушенка
        hypotheses.add(new Hypothesis(
                "Побег из Шоушенка",
                prior,
                Map.of(
                        2, new EvidenceData(0.85, 0.2),   // Отношения и эмоции
                        10, new EvidenceData(0.95, 0.1),  // Серьезное и заставляющее задуматься
                        11, new EvidenceData(0.9, 0.15),  // Сильные эмоции
                        17, new EvidenceData(0.75, 0.3),  // Один
                        23, new EvidenceData(0.85, 0.2),  // Классика
                        28, new EvidenceData(0.9, 0.15),  // Хэппи-энд
                        35, new EvidenceData(0.9, 0.15)   // Надежда, свобода
                )
        ));

        // 19. Marvel - Тор: Рагнарёк
        hypotheses.add(new Hypothesis(
                "Тор: Рагнарёк",
                prior,
                Map.of(
                        1, new EvidenceData(0.85, 0.2),   // Драки и погони
                        3, new EvidenceData(0.95, 0.1),   // Смешные и веселые
                        4, new EvidenceData(0.9, 0.15),   // Космос и технологии
                        6, new EvidenceData(0.9, 0.15),   // Супергерои
                        7, new EvidenceData(0.8, 0.25),   // Фэнтези/магия
                        12, new EvidenceData(0.9, 0.15),  // Посмеяться
                        18, new EvidenceData(0.9, 0.15)   // С друзьями
                )
        ));

        // 20. Научная фантастика - Матрица
        hypotheses.add(new Hypothesis(
                "Матрица",
                prior,
                Map.of(
                        1, new EvidenceData(0.9, 0.15),   // Драки и погони
                        4, new EvidenceData(0.95, 0.1),   // Космос и технологии
                        10, new EvidenceData(0.9, 0.15),  // Серьезное и заставляющее задуматься
                        18, new EvidenceData(0.8, 0.25),  // С друзьями
                        23, new EvidenceData(0.8, 0.25),  // Классика
                        31, new EvidenceData(0.9, 0.15),  // Спецэффекты
                        35, new EvidenceData(0.95, 0.1)   // Философия
                )
        ));

        // 21. Анимация - Корпорация монстров
        hypotheses.add(new Hypothesis(
                "Корпорация монстров",
                prior,
                Map.of(
                        3, new EvidenceData(0.9, 0.15),   // Смешные и веселые
                        9, new EvidenceData(0.95, 0.1),   // Легкое и развлекательное
                        14, new EvidenceData(0.8, 0.25),  // На выходных
                        19, new EvidenceData(0.9, 0.15),  // С семьей
                        21, new EvidenceData(0.9, 0.15),  // С детьми
                        22, new EvidenceData(0.8, 0.25),  // Современные
                        33, new EvidenceData(0.9, 0.15)   // Анимация
                )
        ));

        // 22. Marvel - Доктор Стрэндж
        hypotheses.add(new Hypothesis(
                "Доктор Стрэндж",
                prior,
                Map.of(
                        4, new EvidenceData(0.95, 0.1),   // Космос и технологии
                        6, new EvidenceData(0.9, 0.15),   // Супергерои
                        7, new EvidenceData(0.9, 0.15),   // Магия
                        10, new EvidenceData(0.85, 0.2),  // Серьезное
                        17, new EvidenceData(0.7, 0.35),  // Один
                        22, new EvidenceData(0.9, 0.15),  // Современные
                        31, new EvidenceData(0.95, 0.1)   // Спецэффекты
                )
        ));

        // 23. Звездные войны - Новая надежда
        hypotheses.add(new Hypothesis(
                "Звездные войны: Новая надежда",
                prior,
                Map.of(
                        1, new EvidenceData(0.85, 0.2),   // Драки и погони
                        4, new EvidenceData(0.95, 0.1),   // Космос и технологии
                        7, new EvidenceData(0.8, 0.25),   // Фэнтези/магия
                        9, new EvidenceData(0.8, 0.25),   // Легкое и развлекательное
                        18, new EvidenceData(0.8, 0.25),  // С друзьями
                        23, new EvidenceData(0.9, 0.15),  // Классика
                        31, new EvidenceData(0.7, 0.35)   // Спецэффекты (для своего времени)
                )
        ));

        // 24. Фэнтези - Гарри Поттер и философский камень
        hypotheses.add(new Hypothesis(
                "Гарри Поттер и философский камень",
                prior,
                Map.of(
                        7, new EvidenceData(0.95, 0.1),   // Фэнтези и магия
                        9, new EvidenceData(0.9, 0.15),   // Легкое и развлекательное
                        14, new EvidenceData(0.85, 0.2),  // На выходных
                        19, new EvidenceData(0.8, 0.25),  // С семьей
                        21, new EvidenceData(0.85, 0.2),  // С детьми
                        22, new EvidenceData(0.8, 0.25),  // Современные
                        32, new EvidenceData(0.85, 0.2)   // Атмосферный
                )
        ));

        // 25. Комедия - Маска
        hypotheses.add(new Hypothesis(
                "Маска",
                prior,
                Map.of(
                        3, new EvidenceData(0.95, 0.1),   // Смешные и веселые
                        6, new EvidenceData(0.7, 0.35),   // Супергерои (в некотором роде)
                        9, new EvidenceData(0.9, 0.15),   // Легкое и развлекательное
                        12, new EvidenceData(0.95, 0.1),  // Посмеяться
                        18, new EvidenceData(0.85, 0.2),  // С друзьями
                        23, new EvidenceData(0.8, 0.25),  // Классика
                        33, new EvidenceData(0.7, 0.35)   // Анимационные элементы
                )
        ));

        // 26. Драма - 1+1
        hypotheses.add(new Hypothesis(
                "1+1 (Неприкасаемые)",
                prior,
                Map.of(
                        2, new EvidenceData(0.9, 0.15),   // Отношения и эмоции
                        3, new EvidenceData(0.8, 0.25),   // Смешные и веселые
                        9, new EvidenceData(0.85, 0.2),   // Легкое и развлекательное
                        10, new EvidenceData(0.8, 0.25),  // Серьезное
                        11, new EvidenceData(0.9, 0.15),  // Сильные эмоции
                        22, new EvidenceData(0.85, 0.2),  // Современные
                        28, new EvidenceData(0.9, 0.15)   // Хэппи-энд
                )
        ));

        // 27. Аниме - Унесенные призраками
        hypotheses.add(new Hypothesis(
                "Унесенные призраками",
                prior,
                Map.of(
                        7, new EvidenceData(0.9, 0.15),   // Фэнтези и магия
                        10, new EvidenceData(0.85, 0.2),  // Серьезное
                        19, new EvidenceData(0.8, 0.25),  // С семьей
                        21, new EvidenceData(0.8, 0.25),  // С детьми
                        23, new EvidenceData(0.8, 0.25),  // Классика
                        32, new EvidenceData(0.95, 0.1),  // Красивая картинка
                        33, new EvidenceData(0.95, 0.1)   // Аниме
                )
        ));

        // 28. Боевик - Джон Уик
        hypotheses.add(new Hypothesis(
                "Джон Уик",
                prior,
                Map.of(
                        1, new EvidenceData(0.95, 0.1),   // Драки и погони
                        13, new EvidenceData(0.8, 0.25),  // Вечер после работы
                        15, new EvidenceData(0.7, 0.35),  // В кинотеатре
                        18, new EvidenceData(0.85, 0.2),  // С друзьями
                        22, new EvidenceData(0.9, 0.15),  // Современные
                        31, new EvidenceData(0.9, 0.15),  // Спецэффекты
                        34, new EvidenceData(0.95, 0.1)   // Экшн-сцены
                )
        ));

        // 29. Исторический - Гладиатор
        hypotheses.add(new Hypothesis(
                "Гладиатор",
                prior,
                Map.of(
                        1, new EvidenceData(0.9, 0.15),   // Драки и погони
                        8, new EvidenceData(0.85, 0.2),   // Исторический
                        10, new EvidenceData(0.9, 0.15),  // Серьезное
                        11, new EvidenceData(0.95, 0.1),  // Сильные эмоции
                        17, new EvidenceData(0.7, 0.35),  // Один
                        23, new EvidenceData(0.85, 0.2),  // Классика
                        36, new EvidenceData(0.8, 0.25)   // Исторический контекст
                )
        ));

        // Собираем все ID свидетельств
        for (Hypothesis h : hypotheses) {
            allEvidenceIds.addAll(h.getEvidenceMap().keySet());
        }

        // Инициализируем все свидетельства как неотвеченные
        for (Integer id : allEvidenceIds) {
            observedEvidence.put(id, null);
            evidenceAnswers.put(id, 0); // 0 = не отвечено
        }
    }

    public void reset() {
        this.probabilities = new double[hypotheses.size()];
        for (int i = 0; i < hypotheses.size(); i++) {
            this.probabilities[i] = hypotheses.get(i).getPrior();
        }
        for (Integer key : observedEvidence.keySet()) {
            observedEvidence.put(key, null);
            evidenceAnswers.put(key, 0);
        }
    }

    private double calculateEvidenceCost(int evidenceId) {
        double totalCost = 0.0;

        for (int i = 0; i < hypotheses.size(); i++) {
            Hypothesis h = hypotheses.get(i);
            double pH = probabilities[i];
            double pNotH = 1.0 - pH;

            EvidenceData ev = h.getEvidenceMap().get(evidenceId);
            double pPlus = (ev != null) ? ev.getPPlus() : 0.01;
            double pMinus = (ev != null) ? ev.getPMinus() : 0.99;

            double pE = pPlus * pH + pMinus * pNotH;
            double pNotE = 1.0 - pE;

            double pHGivenE = (pE > 1e-9) ? (pPlus * pH) / pE : 0.0;
            double pHGivenNotE = (pNotE > 1e-9) ? ((1.0 - pPlus) * pH) / pNotE : 0.0;

            totalCost += Math.abs(pHGivenE - pHGivenNotE);
        }

        return totalCost;
    }

    public Map<String, Object> getNextQuestion() {

        Map<String, Object> stopCheck = checkStoppingCondition();
        if ((Boolean) stopCheck.get("finished")) {
            return stopCheck;
        }

        // Проверяем, достигнут ли максимум вопросов (обработанных, включая пропущенные)
        if (getProcessedQuestionsCount() >= MAX_QUESTIONS) {
            Map<String, Object> response = new HashMap<>();
            response.put("finished", true);
            response.put("conclusion", "Достигнут максимум вопросов. Рекомендация на основе ответов.");
            addBestMovieToResponse(response);
            return response;
        }

        // Проверяем, достигнут ли максимум вопросов
        if (getAnsweredCount() >= MAX_QUESTIONS) {
            Map<String, Object> response = new HashMap<>();
            response.put("finished", true);
            response.put("conclusion", "Достигнут максимум вопросов. Рекомендация на основе ответов.");
            addBestMovieToResponse(response);
            return response;
        }

        List<Integer> unasked = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> entry : observedEvidence.entrySet()) {
            if (entry.getValue() == null) {
                unasked.add(entry.getKey());
            }
        }

        if (unasked.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("finished", true);
            response.put("conclusion", "Все вопросы заданы.");
            addBestMovieToResponse(response);
            return response;
        }

        // Выбираем следующий вопрос
        Integer bestEvidenceId = selectNextQuestion(unasked);

        Map<String, Object> response = new HashMap<>();
        response.put("evidenceId", bestEvidenceId);
        response.put("question", getQuestionText(bestEvidenceId));
        response.put("finished", false);

        // Добавляем номер вопроса (для фронтенда)
        response.put("questionNumber", getAnsweredCount() + 1);

        // Информация о прогрессе (поддерживаем два формата для совместимости)
        int answered = getAnsweredCount();
        response.put("answeredCount", answered); // для бэкенд-логики
        response.put("answeredQuestions", answered); // для фронтенда
        response.put("totalQuestions", MAX_QUESTIONS); // общее количество (максимум)

        // Для совместимости со старым фронтендом
        response.put("totalAnswered", answered);

        // Прогресс в процентах
        double progress = (double) answered / MAX_QUESTIONS * 100;
        response.put("progress", progress);

        return response;
    }

    private Integer selectNextQuestion(List<Integer> unasked) {
        // Сначала проверяем, нужно ли задавать вопрос из группы взаимоисключающих
        for (List<Integer> group : exclusiveGroups.values()) {
            int answeredInGroup = 0;
            Integer unansweredInGroup = null;

            for (Integer questionId : group) {
                if (observedEvidence.get(questionId) != null) {
                    answeredInGroup++;
                } else if (unasked.contains(questionId)) {
                    unansweredInGroup = questionId;
                }
            }

            // Если в группе уже есть ответ, пропускаем другие вопросы этой группы
            if (answeredInGroup > 0 && unansweredInGroup != null) {
                // Помечаем остальные вопросы в группе как отвеченные (пропущенными)
                for (Integer questionId : group) {
                    if (questionId != unansweredInGroup && observedEvidence.get(questionId) == null) {
                        observedEvidence.put(questionId, false); // false = пропущено
                        evidenceAnswers.put(questionId, 3); // нейтральный ответ
                    }
                }
                return unansweredInGroup;
            }
        }

        // Если нет групп с ответами, выбираем самый информативный вопрос
        Integer bestEvidenceId = unasked.get(0);
        double maxCost = calculateEvidenceCost(bestEvidenceId);

        for (int i = 1; i < unasked.size(); i++) {
            double cost = calculateEvidenceCost(unasked.get(i));
            if (cost > maxCost) {
                maxCost = cost;
                bestEvidenceId = unasked.get(i);
            }
        }

        return bestEvidenceId;
    }

    private String getQuestionText(int evidenceId) {
        Map<Integer, String> questions = Map.ofEntries(
                // Жанры (1-8)
                Map.entry(1, "Вам нравятся фильмы с драками и погонями?"),
                Map.entry(2, "Вам нравятся фильмы про отношения и эмоции?"),
                Map.entry(3, "Вам нравятся смешные и веселые фильмы?"),
                Map.entry(4, "Вам нравятся фильмы про космос и технологии?"),
                Map.entry(5, "Вам нравятся страшные и напряженные фильмы?"),
                Map.entry(6, "Любите супергеройские фильмы?"),
                Map.entry(7, "Вам нравятся фэнтези и магия?"),
                Map.entry(8, "Любите исторические фильмы или биографии?"),

                // Настроение (9-12)
                Map.entry(9, "Хотите посмотреть что-то легкое и развлекательное?"),
                Map.entry(10, "Хотите посмотреть что-то серьезное и заставляющее задуматься?"),
                Map.entry(11, "Хотите испытать сильные эмоции?"),
                Map.entry(12, "Хотите посмеяться от души?"),

                // Время просмотра (13-16)
                Map.entry(13, "Смотрите фильм вечером после работы?"),
                Map.entry(14, "Смотрите фильм на выходных?"),
                Map.entry(15, "Смотрите фильм в кинотеатре?"),
                Map.entry(16, "Смотрите фильм днем?"),

                // Компания (17-21)
                Map.entry(17, "Смотрите фильм один?"),
                Map.entry(18, "Смотрите фильм с друзьями?"),
                Map.entry(19, "Смотрите фильм с семьей?"),
                Map.entry(20, "Смотрите фильм с парой?"),
                Map.entry(21, "Смотрите фильм с детьми?"),

                // Год выпуска (22-24)
                Map.entry(22, "Предпочитаете современные фильмы (после 2010)?"),
                Map.entry(23, "Предпочитаете классику кино (до 2000)?"),
                Map.entry(24, "Вам все равно когда снят фильм?"),

                // Длительность (25-27)
                Map.entry(25, "Предпочитаете короткие фильмы (до 1.5 часа)?"),
                Map.entry(26, "Предпочитаете фильмы средней длины (2-2.5 часа)?"),
                Map.entry(27, "Не против длинных фильмов (3+ часа)?"),

                // Рейтинг и атмосфера (28-32)
                Map.entry(28, "Важен ли хэппи-энд?"),
                Map.entry(29, "Любите фильмы с неожиданной концовкой?"),
                Map.entry(30, "Нравится когда в фильме есть музыкальные номера?"),
                Map.entry(31, "Важны ли спецэффекты и графика?"),
                Map.entry(32, "Любите атмосферные фильмы с красивой картинкой?"),

                // Стиль (33-36)
                Map.entry(33, "Нравятся фильмы в стиле аниме или анимации?"),
                Map.entry(34, "Любите экшн-сцены и трюки?"),
                Map.entry(35, "Важен ли глубокий сюжет и философия?"),
                Map.entry(36, "Любите фильмы, основанные на реальных событиях?")
        );

        return questions.getOrDefault(evidenceId, "Вопрос не найден");
    }

    private int getAnsweredCount() {
        int count = 0;
        for (Map.Entry<Integer, Boolean> entry : observedEvidence.entrySet()) {
            // Считаем только те вопросы, которые пользователь действительно ответил
            // (где ответ не был установлен системой автоматически как пропущенный)
            if (Boolean.TRUE.equals(entry.getValue())) {
                count++;
            }
        }
        return count;
    }

    private int getProcessedQuestionsCount() {
        int count = 0;
        for (Boolean processed : observedEvidence.values()) {
            if (processed != null) {
                count++;
            }
        }
        return count;
    }

    public Map<String, Object> updateProbabilities(int evidenceId, int userRating) {
        if (!observedEvidence.containsKey(evidenceId) || observedEvidence.get(evidenceId) != null) {
            throw new IllegalArgumentException("Свидетельство уже обработано");
        }

        // Сохраняем ответ
        observedEvidence.put(evidenceId, true);
        evidenceAnswers.put(evidenceId, userRating);

        // Обрабатываем взаимоисключающие группы
        handleExclusiveGroups(evidenceId, userRating);

        // Пересчитываем все вероятности на основе ВСЕХ ответов
        recalculateAllProbabilities();

        // Убедимся, что вероятности не слишком малы
        ensureMinimumProbabilities();

        return checkStoppingCondition();
    }

    private void ensureMinimumProbabilities() {
        double minProbability = 1e-6; // Минимальная вероятность
        boolean hasZeroProbability = false;

        for (int i = 0; i < probabilities.length; i++) {
            if (probabilities[i] < minProbability) {
                probabilities[i] = minProbability;
                hasZeroProbability = true;
            }
        }

        // Если были нулевые вероятности - пересчитываем
        if (hasZeroProbability) {
            double total = Arrays.stream(probabilities).sum();
            for (int i = 0; i < probabilities.length; i++) {
                probabilities[i] = probabilities[i] / total;
            }
        }
    }

    private void handleExclusiveGroups(int answeredEvidenceId, int rating) {
        // Находим группу, к которой принадлежит ответ
        for (List<Integer> group : exclusiveGroups.values()) {
            if (group.contains(answeredEvidenceId)) {
                // Для взаимоисключающих вопросов:
                // Если выбрали высокий рейтинг (4-5), другие варианты в группе менее вероятны
                // Если выбрали низкий рейтинг (1-2), другие варианты могут быть более вероятны

                for (Integer questionId : group) {
                    if (questionId != answeredEvidenceId && observedEvidence.get(questionId) == null) {
                        observedEvidence.put(questionId, false); // помечаем как обработанный

                        // Устанавливаем рейтинг для других вопросов в группе
                        // Логика: если выбран один вариант, другие менее вероятны, но не невозможны
                        int otherRating;
                        if (rating >= 4) {
                            otherRating = 1; // Если выбрано "очень нравится", другие "не нравятся"
                        } else if (rating <= 2) {
                            otherRating = 3; // Если выбрано "не нравится", другие нейтральны
                        } else {
                            otherRating = 2; // Нейтральный выбор
                        }

                        evidenceAnswers.put(questionId, otherRating);
                    }
                }
                break;
            }
        }
    }

    private void recalculateAllProbabilities() {
        // Используем логарифмы чтобы избежать проблемы с очень малыми числами
        double[] logProbs = new double[hypotheses.size()];

        // Инициализируем логарифмические вероятности
        for (int i = 0; i < hypotheses.size(); i++) {
            logProbs[i] = Math.log(hypotheses.get(i).getPrior());
        }

        // Применяем все ответы в логарифмической форме
        for (Map.Entry<Integer, Integer> entry : evidenceAnswers.entrySet()) {
            int evidenceId = entry.getKey();
            int rating = entry.getValue();

            if (rating == 0) continue; // Пропускаем неотвеченные

            double observedLikelihood = (rating - 1) / 4.0;

            for (int i = 0; i < hypotheses.size(); i++) {
                Hypothesis h = hypotheses.get(i);
                EvidenceData ev = h.getEvidenceMap().get(evidenceId);
                double pPlus = (ev != null) ? ev.getPPlus() : 0.01;
                double pMinus = (ev != null) ? ev.getPMinus() : 0.99;

                double likelihood = observedLikelihood * pPlus + (1 - observedLikelihood) * pMinus;

                // Избегаем нулевых вероятностей
                if (likelihood < 1e-10) likelihood = 1e-10;

                logProbs[i] += Math.log(likelihood);
            }
        }

        // Преобразуем обратно из логарифмов и нормализуем
        // Сначала находим максимальное логарифмическое значение для стабильности
        double maxLogProb = logProbs[0];
        for (int i = 1; i < logProbs.length; i++) {
            if (logProbs[i] > maxLogProb) {
                maxLogProb = logProbs[i];
            }
        }

        // Вычитаем максимальное значение для стабильности вычислений
        double total = 0.0;
        for (int i = 0; i < logProbs.length; i++) {
            probabilities[i] = Math.exp(logProbs[i] - maxLogProb);
            total += probabilities[i];
        }

        // Нормализация с проверкой на нулевую сумму
        if (total > 1e-10) {
            for (int i = 0; i < probabilities.length; i++) {
                probabilities[i] = probabilities[i] / total;

                // Гарантируем минимальную вероятность для всех гипотез
                if (probabilities[i] < 1e-6) {
                    probabilities[i] = 1e-6;
                }
            }

            // Пересчитываем сумму после добавления минимальных вероятностей
            total = Arrays.stream(probabilities).sum();
            for (int i = 0; i < probabilities.length; i++) {
                probabilities[i] = probabilities[i] / total;
            }
        } else {
            // Если все вероятности нулевые - равномерное распределение
            double uniform = 1.0 / hypotheses.size();
            Arrays.fill(probabilities, uniform);
        }
    }

    private Map<String, Object> checkStoppingCondition() {
        int answeredCount = getAnsweredCount(); // Только реальные ответы
        int processedCount = getProcessedQuestionsCount(); // Все обработанные

        // Минимальное количество вопросов должно быть задано (реальных ответов)
        if (answeredCount < MIN_QUESTIONS) {
            Map<String, Object> response = new HashMap<>();
            response.put("finished", false);
            response.put("needMoreQuestions", MIN_QUESTIONS - answeredCount);
            response.put("message", "Нужно ответить еще как минимум на " + (MIN_QUESTIONS - answeredCount) + " вопросов");
            response.put("answeredQuestions", answeredCount);
            response.put("processedQuestions", processedCount);
            response.put("totalQuestions", MAX_QUESTIONS);
            return response;
        }

        // Проверяем максимальное количество вопросов (всех обработанных)
        if (processedCount >= MAX_QUESTIONS) {
            Map<String, Object> response = new HashMap<>();
            response.put("finished", true);
            response.put("reason", "max_questions");
            response.put("message", "Достигнут максимум вопросов");
            response.put("totalAnswered", answeredCount);
            response.put("totalProcessed", processedCount);
            response.put("totalQuestions", MAX_QUESTIONS);
            addBestMovieToResponse(response);
            return response;
        }

        // Находим максимальную и минимальную вероятности
        double pMax = probabilities[0];
        double pMin = probabilities[0];
        int bestIndex = 0;

        for (int i = 1; i < probabilities.length; i++) {
            if (probabilities[i] > pMax) {
                pMax = probabilities[i];
                bestIndex = i;
            }
            if (probabilities[i] < pMin) {
                pMin = probabilities[i];
            }
        }

        Map<String, Object> response = new HashMap<>();

        // Правило 1: Высокая уверенность И достаточно вопросов задано
        if (pMax > PM && answeredCount >= MIN_QUESTIONS) {
            response.put("finished", true);
            response.put("conclusion", String.format(
                    "Рекомендуем фильм: %s (уверенность: %.0f%%, отвечено вопросов: %d)",
                    hypotheses.get(bestIndex).getName(), pMax * 100, answeredCount
            ));
            response.put("totalAnswered", answeredCount);
            response.put("totalQuestions", MAX_QUESTIONS);
            addBestMovieToResponse(response);
            response.put("confidence", pMax);
            response.put("answeredCount", answeredCount);
            response.put("reason", "high_confidence");
            return response;
        }

        // Правило 2: Все гипотезы достаточно вероятны И достаточно вопросов
        if (pMin > M1 && answeredCount >= MIN_QUESTIONS) {
            response.put("finished", true);
            response.put("conclusion", String.format(
                    "Рекомендуем фильм: %s (все варианты хороши, выбираем лучший)",
                    hypotheses.get(bestIndex).getName()
            ));
            response.put("totalAnswered", answeredCount);
            response.put("totalQuestions", MAX_QUESTIONS);
            addBestMovieToResponse(response);
            response.put("reason", "all_probable");
            return response;
        }

        // Правило 3: Низкая уверенность даже после максимального количества вопросов
        if (pMax < M2 && answeredCount >= MAX_QUESTIONS) {
            response.put("finished", true);
            response.put("conclusion", "Не удалось подобрать фильм. Попробуйте ответить более определенно.");
            response.put("totalAnswered", answeredCount);
            response.put("totalQuestions", MAX_QUESTIONS);
            response.put("reason", "low_confidence");
            return response;
        }

        // Продолжаем задавать вопросы
        response.put("finished", false);
        response.put("currentConfidence", pMax);
        response.put("answeredCount", answeredCount);
        response.put("answeredQuestions", answeredCount); // Для фронтенда
        response.put("totalQuestions", MAX_QUESTIONS); // Для фронтенда
        response.put("needMoreQuestions", Math.max(MIN_QUESTIONS - answeredCount, 1));

        // Прогресс для фронтенда
        double progress = (double) answeredCount / MAX_QUESTIONS * 100;
        response.put("progress", progress);

        return response;
    }

    private int findBestHypothesisIndex() {
        int bestIndex = 0;
        for (int i = 1; i < probabilities.length; i++) {
            if (probabilities[i] > probabilities[bestIndex]) {
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    private void addBestMovieToResponse(Map<String, Object> response) {
        int bestIndex = findBestHypothesisIndex();
        String bestMovie = hypotheses.get(bestIndex).getName();
        double confidence = probabilities[bestIndex];

        response.put("movie", bestMovie);
        response.put("confidence", confidence);
        response.put("movieInfo", getMovieInfo(bestMovie));
    }

    public Map<String, Double> getCurrentProbabilities() {
        Map<String, Double> result = new LinkedHashMap<>();
        for (int i = 0; i < hypotheses.size(); i++) {
            result.put(hypotheses.get(i).getName(), probabilities[i]);
        }
        return result;
    }

    public Map<String, Object> getSystemInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("totalHypotheses", hypotheses.size());
        info.put("totalEvidence", allEvidenceIds.size());
        info.put("answeredQuestions", getAnsweredCount());
        info.put("processedQuestions", getProcessedQuestionsCount());
        info.put("exclusiveGroups", exclusiveGroups);
        return info;
    }

    private Map<String, Object> getMovieInfo(String movieName) {
        Map<String, String> movieDetails = new HashMap<>();

        // Добавляем 29 фильмов (без "Дневник памяти")
        movieDetails.put("Миссия невыполнима: Смертельная расплата", "Боевик • 2023 • 7.1/10");
        movieDetails.put("Один дома", "Комедия • 1990 • 7.7/10");
        movieDetails.put("Интерстеллар", "Фантастика • 2014 • 8.6/10");
        movieDetails.put("Сияние", "Ужасы • 1980 • 8.4/10");
        movieDetails.put("Человек-паук: Через вселенные", "Мультфильм/Супергерои • 2018 • 8.4/10");
        movieDetails.put("Мстители: Финал", "Супергерои • 2019 • 8.4/10");
        movieDetails.put("Железный человек", "Супергерои • 2008 • 7.9/10");
        movieDetails.put("Звездные войны: Эпизод V - Империя наносит ответный удар", "Фантастика • 1980 • 8.7/10");
        movieDetails.put("Зеленая миля", "Драма/Фэнтези • 1999 • 8.6/10");
        movieDetails.put("Властелин колец: Братство кольца", "Фэнтези • 2001 • 8.9/10");
        movieDetails.put("Индиана Джонс: В поисках утраченного ковчега", "Приключения • 1981 • 8.4/10");
        movieDetails.put("Молчание ягнят", "Триллер • 1991 • 8.6/10");
        movieDetails.put("Холодное сердце", "Мультфильм • 2013 • 7.4/10");
        movieDetails.put("Форрест Гамп", "Драма • 1994 • 8.8/10");
        movieDetails.put("Стражи Галактики", "Фантастика/Комедия • 2014 • 8.0/10");
        movieDetails.put("Чёрная пантера", "Супергерои • 2018 • 7.3/10");
        movieDetails.put("Звездные войны: Пробуждение силы", "Фантастика • 2015 • 7.8/10");
        movieDetails.put("Побег из Шоушенка", "Драма • 1994 • 9.3/10");
        movieDetails.put("Тор: Рагнарёк", "Супергерои/Комедия • 2017 • 7.9/10");
        movieDetails.put("Матрица", "Фантастика • 1999 • 8.7/10");
        movieDetails.put("Корпорация монстров", "Мультфильм • 2001 • 8.0/10");
        movieDetails.put("Доктор Стрэндж", "Супергерои • 2016 • 7.5/10");
        movieDetails.put("Звездные войны: Новая надежда", "Фантастика • 1977 • 8.6/10");
        movieDetails.put("Гарри Поттер и философский камень", "Фэнтези • 2001 • 7.6/10");
        movieDetails.put("Маска", "Комедия • 1994 • 6.9/10");
        movieDetails.put("1+1 (Неприкасаемые)", "Драма/Комедия • 2011 • 8.5/10");
        movieDetails.put("Унесенные призраками", "Аниме/Фэнтези • 2001 • 8.6/10");
        movieDetails.put("Джон Уик", "Боевик • 2014 • 7.4/10");
        movieDetails.put("Гладиатор", "Исторический/Боевик • 2000 • 8.5/10");

        Map<String, Object> info = new HashMap<>();
        String details = movieDetails.getOrDefault(movieName, "Фильм не найден");
        info.put("description", details);

        String[] parts = details.split(" • ");
        if (parts.length >= 3) {
            info.put("genre", parts[0]);
            info.put("year", parts[1]);
            info.put("rating", parts[2]);
        }

        return info;
    }

    public List<Map<String, Object>> getAllMovies() {
        List<Map<String, Object>> movies = new ArrayList<>();

        for (int i = 0; i < hypotheses.size(); i++) {
            Map<String, Object> movie = new HashMap<>();
            movie.put("name", hypotheses.get(i).getName());
            movie.put("probability", probabilities[i]);
            movie.put("info", getMovieInfo(hypotheses.get(i).getName()));
            movies.add(movie);
        }

        movies.sort((a, b) -> Double.compare((Double) b.get("probability"), (Double) a.get("probability")));

        return movies;
    }

    public Map<Integer, Integer> getEvidenceAnswers() {
        return new HashMap<>(evidenceAnswers);
    }
}