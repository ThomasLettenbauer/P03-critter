package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }


}
