package com.inventory.controllers;

import com.inventory.models .dtos.TicketDTO;
import com.inventory.interfaces.ITicketService;
import com.inventory.models.dtos.requestDTOs.UpdateStatusRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Tickets", description = "Endpoints for managing tickets")
@SecurityRequirement(name = "bearerAuth")  // Require JWT token for all endpoints
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @GetMapping("/getAll")
    @Operation(summary = "Get all tickets", description = "Retrieve all tickets (paginated)")
    public ResponseEntity<Page<TicketDTO>> getAllTickets(Pageable pageable) {
        Page<TicketDTO> tickets = ticketService.getAllTickets(pageable);
        return ResponseEntity.ok(tickets);
    }
    @GetMapping("/myTickets")
    @Operation(summary = "Get tickets by user", description = "Retrieve all tickets created by the authenticated user")
    public ResponseEntity<Page<TicketDTO>> getTicketsByUser(@AuthenticationPrincipal String username, Pageable pageable) {
        Page<TicketDTO> tickets = ticketService.getTicketsByUser(username, pageable);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    @Operation(summary = "Create a ticket", description = "Create a new ticket")
    public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketDTO ticketDTO, @AuthenticationPrincipal String username) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO, username);
        return ResponseEntity.ok(createdTicket);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update ticket status", description = "Update the status of a ticket (ADMIN only)")
    public ResponseEntity<TicketDTO> updateTicketStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest updateStatusRequest) {
        TicketDTO updatedTicket = ticketService.updateTicketStatus(id, updateStatusRequest);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ticket", description = "Delete a ticket by its ID (ADMIN only)")
    public ResponseEntity<Map<String, String>> deleteTicket(@PathVariable Long id) {
        String message = ticketService.deleteTicket(id);
        return ResponseEntity.ok(Collections.singletonMap("message", message));  // Return JSON response
    }
}