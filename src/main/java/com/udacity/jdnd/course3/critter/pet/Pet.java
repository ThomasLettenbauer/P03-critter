package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private PetType type;
    private String name;

    @OneToOne(targetEntity = Customer.class)
    private Long ownerId;

    private LocalDate birthDate;
    private String notes;

}
