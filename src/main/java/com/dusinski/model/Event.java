package com.dusinski.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonIdentityReference
    private Owner owner;
    @JsonProperty("title")
    private EventType eventType;
    private String description;
    @JsonProperty("start")
    private LocalDate startDate;
    @JsonProperty("end")
    private LocalDate endDate;

    public Event() {
    }

    public Event(Owner owner, EventType eventType, String description, LocalDate startDate, LocalDate endDate) {
        this.owner = owner;
        this.eventType = eventType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Owner getUser() {
        return owner;
    }

    public void setUser(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", user=" + owner + ", eventType=" + eventType + ", description='" + description + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
}
