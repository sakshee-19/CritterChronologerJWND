package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServices {

    @Autowired
    PetDao petDao;

    private static final Logger logger = LoggerFactory.getLogger(PetServices.class);

    public List<Pet> petsByOwnerId(Long ownerId) {
        logger.info("**START** petsByOwnerId ownerId={}", ownerId);
        return petDao.findAllPetOwnerHas(ownerId);
    }

    public List<Pet> findAllPets(){
        logger.info("**START** findALLPets");
        return petDao.findAllPets();
    }

    public Pet findPetById(Long id){
        logger.info("**START** findPetById={}", id);
        return petDao.findPetById(id);
    }

    public Long save(Pet pet){
        logger.info("**START** save() ={}", pet);
        return petDao.savePet(pet);
    }
}
