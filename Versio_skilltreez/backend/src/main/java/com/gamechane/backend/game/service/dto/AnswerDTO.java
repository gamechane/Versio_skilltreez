package com.gamechane.backend.game.service.dto;

public class AnswerDTO {
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String tekst) {
        this.text = text;
    }
}
