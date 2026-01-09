package com.gamechane.backend.guest.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class GameScore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer time;
    private Integer score;
    private Long gameId;

    @ManyToOne
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;
}
