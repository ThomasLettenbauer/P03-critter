package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet getPet(Long id) {
        Optional<Pet> optionalPet;
        optionalPet = petRepository.findById(id);
        return optionalPet.orElse(null);
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        petRepository.findAll().forEach(petList::add);

        return petList;
    }

    @Override
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
}
