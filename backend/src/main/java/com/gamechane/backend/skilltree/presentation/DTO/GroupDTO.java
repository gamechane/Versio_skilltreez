package com.gamechane.backend.skilltree.presentation.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private String name;
    private String gamePin;
    private Long skillTreeId;


    @Override
    public String toString() {
        return "GroepDTO{" +
                "naam='" + name + '\'' +
                ", gamePin='" + gamePin + '\'' +
                ", skilltree_id=" + skillTreeId +
                '}';
    }
}
