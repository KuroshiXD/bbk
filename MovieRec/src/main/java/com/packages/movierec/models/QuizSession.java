package com.packages.movierec.models;

import java.util.HashMap;
import java.util.Map;

public class QuizSession {
    private double[] probabilities;
    private Map<Integer, Boolean> observedEvidence = new HashMap<>();
    private String currentConclusion;

    public QuizSession() {}

    public QuizSession(int hypothesisCount) {
        this.probabilities = new double[hypothesisCount];
    }

    public double[] getProbabilities() { return probabilities; }
    public void setProbabilities(double[] probabilities) {
        this.probabilities = probabilities;
    }

    public Map<Integer, Boolean> getObservedEvidence() { return observedEvidence; }
    public void setObservedEvidence(Map<Integer, Boolean> observedEvidence) {
        this.observedEvidence = observedEvidence;
    }

    public String getCurrentConclusion() { return currentConclusion; }
    public void setCurrentConclusion(String currentConclusion) {
        this.currentConclusion = currentConclusion;
    }
}