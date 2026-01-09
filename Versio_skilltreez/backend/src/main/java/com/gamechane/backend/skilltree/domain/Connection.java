package com.gamechane.backend.skilltree.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Connection")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Connection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long destination;

    private String startPoint;

    private String endPoint;

}
