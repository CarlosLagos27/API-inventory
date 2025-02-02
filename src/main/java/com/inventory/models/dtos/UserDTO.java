package com.inventory.models.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {
    @Schema(description = "The username of the user", example = "admin")
    private String username;
    @Schema(description = "The role of the user", example = "ADMIN")
    private String role;


    // Constructor to map from User entity
    public UserDTO(String username, String role) {
        this.username = username;
        this.role = role;
    }
}