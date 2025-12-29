package com.packages.movierec.models;

import java.util.Map;

public class Hypothesis {
    private String name;
    private double prior;
    private Map<Integer, EvidenceData> evidenceMap;

    // Конструкторы, геттеры, сеттеры
    public Hypothesis() {}

    public Hypothesis(String name, double prior, Map<Integer, EvidenceData> evidenceMap) {
        this.name = name;
        this.prior = prior;
        this.evidenceMap = evidenceMap;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrior() { return prior; }
    public void setPrior(double prior) { this.prior = prior; }

    public Map<Integer, EvidenceData> getEvidenceMap() { return evidenceMap; }
    public void setEvidenceMap(Map<Integer, EvidenceData> evidenceMap) {
        this.evidenceMap = evidenceMap;
    }
}
