package com.inventory.models.dtos.responseDTOs;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class LoginResponseDTO {
    @Schema(description = "The JWT token for authenticated requests", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;


    // Constructor to accept the token
    public LoginResponseDTO(String token) {
        this.token = token;
    }
}