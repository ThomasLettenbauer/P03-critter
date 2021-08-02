package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    @Query("select s from Schedule s join s.petIds p where p.id = ?1")
    public List<Schedule> getScheduleForPet(Long petId);

    @Query("select s from Schedule s join s.employeeIds e where e.id = ?1")
    public List<Schedule> getScheduleForEmployee(Long employeeId);

    @Query("select s from Schedule s join s.petIds p where p.id in (select p.id from Pet p where p.customer.id = ?1)")
    public List<Schedule> getScheduleForCustomer(Long customerId);
}
