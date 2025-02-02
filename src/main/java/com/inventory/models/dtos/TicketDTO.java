package com.inventory.models.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
public class TicketDTO {
    @Schema(description = "The ID of the  ticket (Auto Generated)", example = "3")
    private Long id;
    @Schema(description = "The title of the ticket", example = "Broken product")
    @NotBlank(message = "Title is required")
    private String title;
    @Schema(description = "The description of the ticket", example = "The product arrived damaged.")
    @NotBlank(message = "Description is required")
    private String description;
    @Schema(description = "The status of the ticket", example = "pending")
    private String status;
    @Schema(description = "The username of the user who created the ticket", example = "admin")
    private String user;  // Only include the username of the user

    public TicketDTO(Long id, String title, String description, String status, String user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
    }
}