package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class EmployeeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ElementCollection(targetClass=EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="employeerequest_skill")
    @Column(name="skill")
    private Set<EmployeeSkill> skills;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
