package com.inventory.models.dtos.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
public class UpdateStatusRequest {
    @Schema(description = "The new status of the ticket", example = "in_progress")
    @NotBlank(message = "Status is required")
    private String status;
    public UpdateStatusRequest(String status) {

        this.status = status;

    }
}