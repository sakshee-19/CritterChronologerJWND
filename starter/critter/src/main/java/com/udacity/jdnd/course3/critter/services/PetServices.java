package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.exceptions.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.PetDao;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServices {

//    @Autowired
//    PetDao petDao;

    @Autowired
    PetRepository petRepository;

    private static final Logger logger = LoggerFactory.getLogger(PetServices.class);

    public List<Pet> petsByOwnerId(Long ownerId) {
        logger.info("**START** petsByOwnerId ownerId={}", ownerId);
//        return petDao.findAllPetOwnerHas(ownerId);
        return petRepository.findAllByOwner_id(ownerId);
    }

    public List<Pet> findAllPets(){
        logger.info("**START** findALLPets");
//        return petDao.findAllPets();
        return petRepository.findAll();
    }

    public Pet findPetById(Long id){
        logger.info("**START** findPetById={}", id);
//        return petDao.findPetById(id);
        Optional<Pet> petOptional = petRepository.findById(id);
        if(petOptional.isPresent()){
            return petOptional.get();
        }
        throw new NotFoundException("Pet Not found");
    }

    public Pet save(Pet pet){
        logger.info("**START** save() ={}", pet);
//        return petDao.savePet(pet, ownerId);
        return petRepository.save(pet);
    }
}
