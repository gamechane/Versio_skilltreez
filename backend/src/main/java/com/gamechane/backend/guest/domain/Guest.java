package com.gamechane.backend.guest.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gamePin;

    @OneToMany(
            mappedBy = "guest",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Session> session;
}
