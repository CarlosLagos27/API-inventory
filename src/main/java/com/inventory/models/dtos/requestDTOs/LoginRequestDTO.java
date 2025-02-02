package com.inventory.models.dtos.requestDTOs;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class LoginRequestDTO {
    @Schema(description = "The username for login", example = "admin")
    private String username;
    @Schema(description = "The password for login", example = "admin123")
    private String password;
}