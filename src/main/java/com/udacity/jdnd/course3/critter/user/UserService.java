package com.udacity.jdnd.course3.critter.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface UserService {

    // Customer methods
    // saveCustomer
    Customer saveCustomer(Customer customer);

    // getAllCustomers
    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    //getOwnerByPet
    Customer getOwnerByPet(Long petId);

    // Employee methods
    Employee saveEmployee(Employee employee);
    Employee getEmployee(Long employeeId);
    void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId);
    List<Employee> findEmployeesForService(EmployeeRequest request);

}
