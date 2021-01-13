package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Pet;

import java.util.List;

public interface PetDao {

    List<Pet> findAllPetOwnerHas(Long ownerId);
    List<Pet> findAllPets();
    Pet findPetById(Long id);
    Long savePet(Pet pet);
}
