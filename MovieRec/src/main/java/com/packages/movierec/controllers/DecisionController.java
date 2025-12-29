package com.packages.movierec.controllers;

import com.packages.movierec.dtos.AnswerDto;
import com.packages.movierec.services.MovieDecisionEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class DecisionController {

    private final MovieDecisionEngine decisionEngine;

    @Autowired
    public DecisionController(MovieDecisionEngine decisionEngine) {
        this.decisionEngine = decisionEngine;
    }

    @PostMapping("/start")
    public ResponseEntity<?> startSession() {
        decisionEngine.reset();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Сессия начата");
        response.put("status", "ready");
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/next")
    public ResponseEntity<?> getNextQuestion() {
        Map<String, Object> response = decisionEngine.getNextQuestion();

        // Добавляем общую информацию о прогрессе
        if (!(Boolean) response.get("finished")) {
            Map<String, Object> systemInfo = decisionEngine.getSystemInfo();
            response.put("systemInfo", systemInfo);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/answer")
    public ResponseEntity<?> submitAnswer(@RequestBody AnswerDto answerDto) {
        if (answerDto.getRating() == null || answerDto.getRating() < 1 || answerDto.getRating() > 5) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Рейтинг должен быть от 1 до 5")
            );
        }

        if (answerDto.getEvidenceId() == null) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "ID свидетельства обязательно")
            );
        }

        try {
            Map<String, Object> response = decisionEngine.updateProbabilities(
                    answerDto.getEvidenceId(),
                    answerDto.getRating()
            );

            // Добавляем информацию о текущем состоянии
            if (!(Boolean) response.get("finished")) {
                response.put("systemInfo", decisionEngine.getSystemInfo());
            }

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }

    @GetMapping("/probabilities")
    public ResponseEntity<?> getProbabilities() {
        return ResponseEntity.ok(decisionEngine.getCurrentProbabilities());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMovies() {
        Map<String, Object> response = new HashMap<>();
        response.put("movies", decisionEngine.getAllMovies());
        response.put("systemInfo", decisionEngine.getSystemInfo());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getSystemInfo() {
        return ResponseEntity.ok(decisionEngine.getSystemInfo());
    }

    @GetMapping("/answers")
    public ResponseEntity<?> getAnswers() {
        return ResponseEntity.ok(decisionEngine.getEvidenceAnswers());
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetSession() {
        decisionEngine.reset();
        return ResponseEntity.ok(Map.of(
                "message", "Сессия сброшена",
                "status", "reset"
        ));
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "Movie Recommendation System",
                "version", "1.0.0"
        ));
    }
}