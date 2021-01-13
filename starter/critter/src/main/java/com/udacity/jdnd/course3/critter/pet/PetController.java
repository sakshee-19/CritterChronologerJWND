package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.services.PetServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetServices petServices;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
//        throw new UnsupportedOperationException();
        Long petId = petServices.save(convertPetDTOToPet(petDTO));
        Pet pet = petServices.findPetById(petId);
        return convertPetsToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petServices.findPetById(petId);
        return convertPetsToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = petServices.findAllPets();
        return convertPetToPetDTOList(petList);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petServices.petsByOwnerId(ownerId);
        return convertPetToPetDTOList(petList);
    }

    private PetDTO convertPetsToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }

    private List<PetDTO> convertPetToPetDTOList(List<Pet> petList){
        List<PetDTO> petDTOList = new ArrayList<>();
        for(Pet pet : petList){
            petDTOList.add(convertPetsToPetDTO(pet));
        }
        return petDTOList;
    }

    private Pet convertPetDTOToPet(PetDTO petDto){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDto, pet);
        return pet;
    }
}
