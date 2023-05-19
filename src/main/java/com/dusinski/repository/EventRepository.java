package com.dusinski.repository;

import com.dusinski.model.Event;
import com.dusinski.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByOwnerId(Long id);
    Long removeByOwnerId(Long id);
}
