package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;

import java.util.List;

public interface PetDao {

    List<PetDTO> findAllPetOwnerHas(Long ownerId);
    List<PetDTO> findAllPets();
    PetDTO findPetById(Long id);
    Long savePet(Pet pet, Long ownerId);
}
