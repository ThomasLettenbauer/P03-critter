package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "schedule_employee")
    @Column(name = "employee_id")
    private List<Long> employeeIds;

    @ElementCollection
    @CollectionTable(name = "schedule_pet")
    @Column(name = "pet_id")
    private List<Long> petIds;

    private LocalDate date;

    @ElementCollection(targetClass=EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="schedule_activity")
    @Column(name="skill")
    private Set<EmployeeSkill> activities;

}
