package com.udacity.jdnd.course3.critter.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Customer owner;
    private LocalDate birthDate;
    private String notes;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment")
    private Schedule appointment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType  getType() {
        return type;
    }

    public void setType(PetType type) { this.type = type; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer ownerId) {
        this.owner = ownerId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Schedule getAppointment() {
        return appointment;
    }

    public void setAppointment(Schedule appointment) {
        this.appointment = appointment;
    }

//    @Override
//    public String toString() {
//        return "Pet{" +
//                "id=" + id +
//                ", type=" + type +
//                ", name='" + name + '\'' +
//                ", owner=" + owner +
//                ", birthDate=" + birthDate +
//                ", notes='" + notes + '\'' +
//                ", appointment=" + appointment +
//                '}';
//    }
}
