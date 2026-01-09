package com.gamechane.backend.student.presentation.DTO;

import com.gamechane.backend.skilltree.presentation.DTO.GameBlockDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkilltreeDTO {
    private Long id;
    private int punten;
    private String naam;
    private String omschrijving;
    private String eindbericht;
    private Boolean openbaar;

    private List<GameBlockDTO> spelBlokken = new ArrayList<>();
}
