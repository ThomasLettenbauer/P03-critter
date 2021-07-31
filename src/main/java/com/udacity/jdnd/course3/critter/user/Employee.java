package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ElementCollection(targetClass=EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="employee_skill")
    @Column(name="skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass=DayOfWeek.class)
    @CollectionTable(name="employee_daysavailable")
    @Column(name="day")
    private Set<DayOfWeek> daysAvailable;
}
