package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule s) {
        return scheduleRepository.save(s);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleRepository.findAll().forEach(scheduleList::add);
        return scheduleList;
    }

    @Override
    public List<Schedule> getScheduleForPet(Long petId) {
        return scheduleRepository.getScheduleForPet(petId);
    }

    @Override
    public List<Schedule> getScheduleForEmployee(Long employeeId) {

        return scheduleRepository.getScheduleForEmployee(employeeId);
    }

    @Override
    public List<Schedule> getScheduleForCustomer(Long customerId) {
        return scheduleRepository.getScheduleForCustomer(customerId);
    }
}
