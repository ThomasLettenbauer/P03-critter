package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

public interface ScheduleService {

    public List<Schedule> getAllSchedules();

    public List<Schedule> getScheduleForPet(Long petId);

    public List<Schedule> getScheduleForEmployee(Long employeeId);

    public List<Schedule> getScheduleForCustomer(Long customerId);

    public Schedule save(Schedule s);
}
