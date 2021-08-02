package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetServiceImpl;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PetServiceImpl petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) throws ParseException {
        Customer customer = convertCustomerDTOToCustomerEntity(customerDTO);
        Customer newCustomer = userService.saveCustomer(customer);
        System.out.println("********");
        System.out.println(newCustomer);
        System.out.println("********");
        return convertCustomerToCustomerDTO(newCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return userService.getAllCustomers().stream()
                .map(c -> convertCustomerToCustomerDTO(c))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        Customer customer = userService.getOwnerByPet(petId);
        List<Long> petIdList = petService.getPetsByOwner(customer.getId()).stream()
                .map(p -> p.getId())
                .collect(Collectors.toList());

        CustomerDTO customerDTO = convertCustomerToCustomerDTO(userService.getOwnerByPet(petId));
        customerDTO.setPetIds(petIdList);

        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomerEntity(CustomerDTO customerDTO) throws ParseException {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return customer;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployeeEntity(EmployeeDTO employeeDTO) throws ParseException {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return employee;
    }

    private EmployeeRequestDTO convertRequestToRequestDTO(EmployeeRequest employeeRequest) {
        EmployeeRequestDTO employeeRequestDTO = modelMapper.map(employeeRequest, EmployeeRequestDTO.class);
        return employeeRequestDTO;
    }

    private EmployeeRequest convertRequestDTOToRequestEntity(EmployeeRequestDTO employeeRequestDTO) throws ParseException {
        EmployeeRequest employeeRequest = modelMapper.map(employeeRequestDTO, EmployeeRequest.class);
        return employeeRequest;
    }

}
