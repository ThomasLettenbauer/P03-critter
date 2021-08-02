package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleService;
import com.udacity.jdnd.course3.critter.schedule.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ScheduleServiceImpl scheduleService;

    // Customer methods
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().forEach(customerList::add);
        return customerList;
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer getOwnerByPet(Long petId) {
        return customerRepository.getOwnerByPet(petId);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            employee.setDaysAvailable(daysAvailable);
            employeeRepository.save(employee);
        }
    }

    @Override
    public List<Employee> findEmployeesForService(EmployeeRequest request) {
        LocalDate requestDate = request.getDate();
        Set<EmployeeSkill> employeeSkills = request.getSkills();

        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);

        List<Employee> employeeWithSkillsList = new ArrayList<>();

        // get all employees with necessary skills
        for (Employee employee : employeeList) {
            if (employee.getSkills().containsAll(employeeSkills))
                employeeWithSkillsList.add(employee);
        }

        // remove booked employees
        List<Schedule> scheduleList = new ArrayList<>();
        for (Employee employee : employeeWithSkillsList) {
            scheduleList = scheduleService.getScheduleForEmployee(employee.getId());
            if (scheduleList.contains(requestDate))
                employeeWithSkillsList.remove(employee);
        }

        // return available employees
        return employeeWithSkillsList;
    }
}
