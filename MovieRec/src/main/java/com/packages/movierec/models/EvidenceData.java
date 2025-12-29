package com.packages.movierec.models;

public class EvidenceData {
    private double pPlus; // P(E|H)
    private double pMinus; // P(E|¬H)

    public EvidenceData() {}

    public EvidenceData(double pPlus, double pMinus) {
        this.pPlus = pPlus;
        this.pMinus = pMinus;
    }

    public double getPPlus() { return pPlus; }
    public void setPPlus(double pPlus) { this.pPlus = pPlus; }

    public double getPMinus() { return pMinus; }
    public void setPMinus(double pMinus) { this.pMinus = pMinus; }
}
