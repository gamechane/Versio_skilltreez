package com.gamechane.backend.guest.presentation.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GuestDTO implements Serializable {
    private Long id;
    private String name;
    private String gamePin;
    private List<SessionDTO> session;
}
