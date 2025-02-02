package com.inventory.models.dtos.requestDTOs;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UserRequestDTO {
    @Schema(description = "The username of the user", example = "admin")
    private String username;
    @Schema(description = "The password of the user", example = "admin123")
    private String password;
    @Schema(description = "The role of the user", example = "ADMIN")
    private String role;

    public UserRequestDTO(String username, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
