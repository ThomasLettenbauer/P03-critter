package com.udacity.jdnd.course3.critter.pet;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PetService {

    public Pet savePet(Pet pet);

    public Pet getPet(Long id);

    public List<Pet> getAllPets();

    public List<Pet>getPetsByOwner(Long ownerId);

}
