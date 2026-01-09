// leerling <-> progressie - OneToMany
// progressie -> skilltree - OneToOne

package com.gamechane.backend.student.domain;

import com.gamechane.backend.security.user.domain.User;
import com.gamechane.backend.skilltree.domain.SkillTree;
import com.gamechane.backend.skilltree.domain.GameBlock;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="progress")
@NoArgsConstructor
@Data
public class Progress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "progress_unlocked_blocks",
            joinColumns = {@JoinColumn( name="progress_id")},
            inverseJoinColumns = {@JoinColumn( name="unlocked_blocks_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = { "progress_id", "unlocked_blocks_id" })}
    )
    private List<GameBlock> unlockedBlocks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private SkillTree skillTree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User user;

    public Progress(SkillTree skillTree){
        this.skillTree = skillTree;
    }

    public void addUnlockedBlock(GameBlock block){
        unlockedBlocks.add(block);
    }

}
