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
import java.util.ArrayList;
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

        CustomerDTO customerDTO = convertCustomerToCustomerDTO(userService.getOwnerByPet(petId));

        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws ParseException {
        Employee employee = convertEmployeeDTOToEmployeeEntity(employeeDTO);
        Employee newEmployee = userService.saveEmployee(employee);
        return convertEmployeeToEmployeeDTO(newEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToEmployeeDTO(userService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.setAvailability(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) throws ParseException {
        EmployeeRequest request = convertRequestDTOToRequestEntity(employeeDTO);
        List<EmployeeDTO> employeeDTOList =
        userService.findEmployeesForService(request).stream()
            .map(e -> convertEmployeeToEmployeeDTO(e))
            .collect(Collectors.toList());
        return employeeDTOList;
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        List<Long> petIdList = petService.getPetsByOwner(customer.getId()).stream()
                .map(p -> p.getId())
                .collect(Collectors.toList());
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setPetIds(petIdList);
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
