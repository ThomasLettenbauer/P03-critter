package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class EmployeeRequest {

    @Id
    @GeneratedValue
    Long id;

    @ElementCollection(targetClass=EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="employeerequest_skill")
    @Column(name="skill")
    private Set<EmployeeSkill> skills;

    private LocalDate date;
}
