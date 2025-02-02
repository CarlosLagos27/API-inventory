package com.inventory.models.dtos.responseDTOs;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;  // HTTP status code
    private String message;  // Error message
    private String details;  // Additional details (optional)

    public ErrorResponse(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }
}