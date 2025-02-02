package com.inventory.repositories;

import com.inventory.models.Ticket;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @NonNull
    List<Ticket> findAll();  // Retrieve all tickets
    Page<Ticket> findAllByUserUsername(String username, Pageable pageable);  // Find tickets by user's username
}