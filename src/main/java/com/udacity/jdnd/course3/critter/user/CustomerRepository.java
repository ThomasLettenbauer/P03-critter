package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    //getOwnerByPet
    @Query("select c from Customer c where c.id in ( select p.customer.id from Pet p where p.id = ?1)")
    public Customer getOwnerByPet(Long petId);


}
