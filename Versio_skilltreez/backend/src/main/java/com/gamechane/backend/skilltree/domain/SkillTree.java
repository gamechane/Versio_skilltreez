package com.gamechane.backend.skilltree.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.*;

@Entity
@Table(name="skill_tree")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillTree {
    // region attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int points;
    private String description;
    private String finalMessage;
    private Boolean isPublic;
    private String username;


    @OneToMany(mappedBy = "skillTree", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkillTreeGroup> groups = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_tree_id")
    private List<GameBlock> gameBlocks = new ArrayList<>();
    // endregion

    public void addGameBlock(GameBlock gameBlock){
        this.gameBlocks.add(gameBlock);
    }

    public void addGroup(SkillTreeGroup group){
        this.groups.add(group);
    }

    public List<String> getAllGamePins(){
        return groups.stream()
                .map(SkillTreeGroup::getGamePin)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Boolean hasGamePin(String gamePin){
        return getAllGamePins().contains(gamePin);
    }

    public Long getId() {
        return id;
    }
}
