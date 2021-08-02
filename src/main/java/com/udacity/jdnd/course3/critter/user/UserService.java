package com.udacity.jdnd.course3.critter.user;

import java.util.List;

public interface UserService {

    // Customer methods
    // saveCustomer
    Customer saveCustomer(Customer customer);

    // getAllCustomers
    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    //getOwnerByPet
    Customer getOwnerByPet(Long petId);

}
