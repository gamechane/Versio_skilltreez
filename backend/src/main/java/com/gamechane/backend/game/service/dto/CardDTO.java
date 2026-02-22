package com.gamechane.backend.game.service.dto;

import java.util.List;

public class CardDTO {
    private Long id;
    private String set;
    private int placeInOrder;
    private String rubric;
    private String textOnFront;
    private String imageOnFront;
    private String textOnBack;
    private String imageOnBack;
    private List<AnswerDTO> answers;

    public CardDTO() {
    }

    public CardDTO(Long id, String textOnFront, String imageOnFront, String textOnBack, String imageOnBack) {
        this.id = id;
        this.textOnFront = textOnFront;
        this.imageOnFront = imageOnFront;
        this.textOnBack = textOnBack;
        this.imageOnBack = imageOnBack;
    }

    public CardDTO(String textOnFront, String imageOnFront, String textOnBack, String imageOnBack) {
        this.textOnFront = textOnFront;
        this.imageOnFront = imageOnFront;
        this.textOnBack = textOnBack;
        this.imageOnBack = imageOnBack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public int getPlaceInOrder() {
        return placeInOrder;
    }

    public void setPlaceInOrder(int placeInOrder) {
        this.placeInOrder = placeInOrder;
    }

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
    }

    public String getTextOnFront() {
        return textOnFront;
    }

    public void setTextOnFront(String textOnFront) {
        this.textOnFront = textOnFront;
    }

    public String getImageOnFront() {
        return imageOnFront;
    }

    public void setImageOnFrontfbeelding(String imageOnFront) {
        this.imageOnFront = imageOnFront;
    }

    public String getTextOnBackt() {
        return textOnBack;
    }

    public void setTextOnBack(String textOnBack) {
        this.textOnBack = textOnBack;
    }

    public String getImageOnBack() {
        return imageOnBack;
    }

    public void setImageOnBack(String imageOnBacktAfbeelding) {
        this.imageOnBack = imageOnBack;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
