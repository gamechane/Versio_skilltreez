package com.gamechane.backend.skilltree.domain;

import com.gamechane.backend.game.domain.Game;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="game_block", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "skill_tree_id", "block_id" })
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameBlock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Nullable
    private int requiredPoints;
    @Nullable
    private int valueInPoints;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isMystery = false;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Nullable
    private Game game;
    @Column(name="block_id")
    private Long blockId;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "connection_from")
    private List<Connection> connections;

    public void addConnection(Connection connection){
        this.connections.add(connection);
    }
}
