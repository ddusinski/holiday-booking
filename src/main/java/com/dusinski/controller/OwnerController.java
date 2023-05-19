package com.dusinski.controller;

import com.dusinski.model.Owner;
import com.dusinski.repository.EventRepository;
import com.dusinski.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/owners")
public class OwnerController {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    EventRepository eventRepository;

    @GetMapping(path = "/get")
    public Iterable<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @GetMapping(path = "/get/{id}")
    public String getOwner(@PathVariable Long id) {
        return ownerRepository.findById(id).toString();
    }

    @PostMapping(path = "/add")
    public Owner addOwner(@RequestBody Owner newOwner) {
        return ownerRepository.save(newOwner);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteOwner(@PathVariable Long id) {
//        List<Event> eventList = eventRepository.findByOwnerId(id);
//        eventList.forEach(event -> event.setUser(null));
        eventRepository.removeByOwnerId(id);
        ownerRepository.deleteById(id);
    }
}
