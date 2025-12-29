package com.packages.movierec.dtos;

public class AnswerDto {
    private Integer evidenceId;
    private Integer rating; // 1-5

    public AnswerDto() {}

    public AnswerDto(Integer evidenceId, Integer rating) {
        this.evidenceId = evidenceId;
        this.rating = rating;
    }

    public Integer getEvidenceId() { return evidenceId; }
    public void setEvidenceId(Integer evidenceId) { this.evidenceId = evidenceId; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}