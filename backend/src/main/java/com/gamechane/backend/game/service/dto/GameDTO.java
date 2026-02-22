package com.gamechane.backend.game.service.dto;

import java.util.ArrayList;
import java.util.List;

public class GameDTO {
    private Long id;
    private String type;
    private String user;
    private String name;
    private String description;
    private String information;
    private Boolean cardsOpen;
    private Integer setSize;
    private Integer mistakeTolerance;
    private List<CardDTO> cards;
    private List<RubricDTO> rubrics;

    public GameDTO() {
        this.cards = new ArrayList<>();
        this.rubrics = new ArrayList<>();
    }

    public GameDTO(String user, String name, String description, String information) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.information = information;
        this.cards = new ArrayList<>();
        this.rubrics = new ArrayList<>();
    }

    public Integer getMistakeTolerance(){ return mistakeTolerance;}

    public void setMistakeTolerance(Integer mistakeTolerance){ this.mistakeTolerance = mistakeTolerance;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Boolean getCardsOpen() {
        return cardsOpen;
    }

    public void setCardsOpen(Boolean cardsOpen) {
        this.cardsOpen = cardsOpen;
    }

    public Integer getSetSize() {
        return setSize;
    }

    public void setSetSize(Integer setSize) {
        this.setSize = setSize;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    public List<RubricDTO> getRubrics() {
        return rubrics;
    }

    public void setRubrics(List<RubricDTO> rubrics) {
        this.rubrics = rubrics;
    }

}
