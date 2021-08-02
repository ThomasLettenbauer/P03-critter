package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PetService petService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) throws ParseException {
        System.out.println(petDTO);
        Pet pet = convertPetDTOToPetEntity(petDTO);
        System.out.println(pet);
        Pet savedPet = petService.savePet(pet);
        return convertPetToPetDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return (convertPetToPetDTO(pet));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOList =
        petService.getAllPets().stream()
            .map(p -> convertPetToPetDTO(p))
            .collect(Collectors.toList());
        return petDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getPetsByOwner(ownerId);

        List<PetDTO> petDTOList = petList.stream()
                .map(p -> convertPetToPetDTO(p))
                .collect(Collectors.toList());

        return petDTOList;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    private Pet convertPetDTOToPetEntity(PetDTO petDTO) throws ParseException {
        Pet pet = modelMapper.map(petDTO, Pet.class);
        Customer customer = userService.getCustomer(petDTO.getOwnerId());
        pet.setCustomer(customer);
        return pet;
    }

}
