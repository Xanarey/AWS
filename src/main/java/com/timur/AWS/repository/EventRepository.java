package com.timur.AWS.repository;

import com.timur.AWS.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByCreated(String created);
}
