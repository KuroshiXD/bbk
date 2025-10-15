package com.packages.filmchoose.models;

import lombok.Data;

@Data
public class UserPreferences {
    // === ЖАНРЫ (вопросы да/нет) ===
    private Boolean likesAction;        // Боевик
    private Boolean likesSuperheroes;   // Супергерои
    private Boolean likesFantasy;       // Фэнтези
    private Boolean likesSciFi;         // Научная фантастика
    private Boolean likesDrama;         // Драма
    private Boolean likesComedy;        // Комедия
    private Boolean likesHorror;        // Ужасы
    private Boolean likesRomance;       // Романтика
    private Boolean likesAnimation;     // Анимация
    private Boolean likesDocumentary;   // Документальный
    private Boolean likesThriller;      // Триллер
    private Boolean likesSpaceOpera;    // Космическая опера
    private Boolean likesAdventure;     // Приключения

    // === СЕТТИНГИ (вопросы да/нет) ===
    private Boolean settingModern;      // Современный
    private Boolean settingFantasy;     // Фэнтези
    private Boolean settingSpace;       // Космос
    private Boolean settingCyberpunk;   // Киберпанк
    private Boolean settingHistorical;  // Исторический
    private Boolean settingNature;      // Природа
    private Boolean settingPsychological; // Психологический
    private Boolean settingCrime;       // Криминальный
    private Boolean settingPostApocalypse; // Постапокалипсис
    private Boolean settingMagicWorld;  // Волшебный мир
    private Boolean settingMystic;      // Мистика

    // === ОСТАЛЬНЫЕ ПРЕДПОЧТЕНИЯ ===
    private String rating;
    private String price;
    private String duration;
    private Integer minYear;
    private Integer maxYear;
    private Double minRating;
    private Boolean happyEnding;
    private Boolean famousActors;
    private Boolean epicScenes;
    private Boolean basedOnTrueStory;
    private Boolean goodMusic;
    private Boolean plotTwists;

    // === Тип просмотра (оставим как есть) ===
    private java.util.List<String> movieTypes;
}