package com.gamechane.backend.game.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    private String name;
    private String description;

    private Boolean cardsOpen;
    private Integer setSize;

    @Column(unique = true)
    private String sharePin;

    private Integer mistakeTolerance;

    @Column(columnDefinition = "jsonb")
    @Type(type = "com.gamechane.backend.game.persistence.JsonbType")
    private String information;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rubric> rubrics;


    public Game() {
        this.cards = new ArrayList<>();
        this.rubrics = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
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

    public Boolean getCardsOpen() {
        return cardsOpen;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Integer getSetSize() {
        return setSize;
    }

    public void setSetSize(Integer setSize) {
        this.setSize = setSize;
    }

    public String getSharePin() {
        return sharePin;
    }

    public void setSharePin(String sharePin) {
        this.sharePin = sharePin;
    }

    public Integer getMistakeTolerance(){ return mistakeTolerance;}

    public void setMistakeTolerance(Integer mistakeTolerance) {this.mistakeTolerance = mistakeTolerance;}

    public void setInformation(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Rubric> getRubrics() {
        return rubrics;
    }

    public void setRubrics(List<Rubric> rubrics) {
        this.rubrics = rubrics;
    }

}
