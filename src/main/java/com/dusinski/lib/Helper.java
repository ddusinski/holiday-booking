package com.dusinski.lib;

import com.dusinski.model.Event;
import com.dusinski.model.EventType;
import com.dusinski.model.Owner;
import com.dusinski.repository.EventRepository;
import com.dusinski.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class Helper {
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    EventRepository eventRepository;

    @Transactional
    @Bean
    public void initData() {
        Owner owner1 = new Owner("Dominik", "111");
        Owner owner2 = new Owner("Zuzia", "222");

        Event event1 = new Event(owner1, EventType.HOLIDAY, "Baltic holidays", LocalDate.of(2023, 6, 5), LocalDate.of(2023, 6, 16));
        Event event2 = new Event(owner1, EventType.TRAINING, "Java training", LocalDate.of(2023, 5, 8), LocalDate.of(2023, 5, 12));
        Event event3 = new Event(owner2, EventType.SICKNESS, "Pregnancy", LocalDate.of(2022, 12, 1), LocalDate.of(2023, 8, 15));

        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);

    }
}
