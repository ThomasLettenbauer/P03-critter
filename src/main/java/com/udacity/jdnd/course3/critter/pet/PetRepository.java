package com.udacity.jdnd.course3.critter.pet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

    @Query("select p from Pet p where p.customer.id = ?1")
    public List<Pet> findByOwnerId(Long id);
}
