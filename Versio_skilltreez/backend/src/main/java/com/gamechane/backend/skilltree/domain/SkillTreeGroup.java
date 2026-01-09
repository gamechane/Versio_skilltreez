package com.gamechane.backend.skilltree.domain;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="skill_tree_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillTreeGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String gamePin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "skill_tree_id")
    private SkillTree skillTree;

    @Override
    public String toString() {
        String s = "";
        s+= "id: " + this.id + "name: " + this.name;
        return s;
    }

}
