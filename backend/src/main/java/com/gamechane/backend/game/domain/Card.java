package com.gamechane.backend.game.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String set;
    private int placeInOrder;
    private String rubric;

    private String textOnFront;
    private String imageOnFront;
    private String textOnBack;
    private String imageOnBack;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
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

    public void setImageOnFront(String imageOnFront) {
        this.imageOnFront = imageOnFront;
    }

    public String getTextOnBack() {
        return textOnBack;
    }

    public void setTextOnBack(String textOnBack) {
        this.textOnBack = textOnBack;
    }

    public String getImageOnBack() {
        return imageOnBack;
    }

    public void setImageOnBack(String imageOnBack) {
        this.imageOnBack = imageOnBack;
    }
}
