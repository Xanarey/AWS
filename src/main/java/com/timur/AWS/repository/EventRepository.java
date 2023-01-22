package com.timur.AWS.repository;

import com.timur.AWS.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByCreated(String created);
}
