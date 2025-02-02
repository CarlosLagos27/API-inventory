package com.inventory.services;

import com.inventory.models.dtos.TicketDTO;
import com.inventory.interfaces.ITicketService;
import com.inventory.models.Ticket;
import com.inventory.models.User;
import com.inventory.exceptions.custom.*;
import com.inventory.models.dtos.requestDTOs.UpdateStatusRequest;
import com.inventory.repositories.TicketRepository;
import com.inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<TicketDTO> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable)
                .map(ticket -> new TicketDTO(
                        ticket.getId(),
                        ticket.getTitle(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getUser().getUsername()
                ));
    }
    @Override
    public Page<TicketDTO> getTicketsByUser(String username, Pageable pageable) {
        // Retrieve all tickets created by the user
        Page<Ticket> tickets = ticketRepository.findAllByUserUsername(username, pageable);

        // Map the tickets to TicketDTO objects
        return tickets.map(ticket -> new TicketDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getUser().getUsername()
        ));
    }
    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setTitle(ticketDTO.getTitle());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setStatus("pending");  // Default status
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUser(user);

        ticketRepository.save(ticket);

        return new TicketDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getUser().getUsername()
        );
    }

    @Override
    public TicketDTO updateTicketStatus(Long id, UpdateStatusRequest updateStatusRequest) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));;

        ticket.setStatus(updateStatusRequest.getStatus());
        ticketRepository.save(ticket);

        return new TicketDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getUser().getUsername()
        );
    }
    @Override
    public String deleteTicket(Long id) {
        // Check if the ticket exists
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));

        // Delete the ticket
        ticketRepository.delete(ticket);
        // Return a confirmation message
        return "Ticket " + id + " deleted";
    }
}