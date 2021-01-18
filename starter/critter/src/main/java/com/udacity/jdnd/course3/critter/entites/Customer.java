package com.udacity.jdnd.course3.critter.entites;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@NamedQuery(
//        name = "Customer.hasPet",
//        query = "select * from customer c where :petId in (c.petIds)"
//)
@Entity
public class Customer extends User{

    @Column(name = "phone_number")
    private String phoneNumber;
    private String notes;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> petIds;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Pet> petIds) {
        this.petIds = petIds;
    }

    public void addPet(Pet pet) {
        if(this.petIds == null)
            this.petIds = new ArrayList<>();
        this.petIds.add(pet);
    }
}
