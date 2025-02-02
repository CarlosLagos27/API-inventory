package com.inventory.interfaces;


import com.inventory.models.dtos.TicketDTO;
import com.inventory.models.dtos.requestDTOs.UpdateStatusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITicketService {
    Page<TicketDTO> getAllTickets(Pageable pageable);  // Get all tickets with pagination
    TicketDTO createTicket(TicketDTO ticketDTO, String username);  // Create a new ticket
    TicketDTO updateTicketStatus(Long id, UpdateStatusRequest updateStatusRequest);  // Update ticket status (ADMIN only)
    Page<TicketDTO> getTicketsByUser(String username, Pageable pageable);  // Get tickets by user
    String deleteTicket(Long id);  // Delete a ticket by ID

}