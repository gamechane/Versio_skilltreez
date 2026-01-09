package com.gamechane.backend.skilltree.presentation.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionDTO {
    private Long source;
    private Long destination;
    private String startPoint;
    private String endPoint;
}
