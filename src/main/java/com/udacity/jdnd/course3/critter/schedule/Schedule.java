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
    private List<Long> employeeIds;

    @ElementCollection
    private List<Long> petIds;

    private LocalDate date;

    @ElementCollection(targetClass=EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="schedule_activity")
    @Column(name="skill")
    private Set<EmployeeSkill> activities;

}
