package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws ParseException {
        Schedule schedule = convertScheduleDTOToScheduleEntity(scheduleDTO);
        Schedule newSchedule = scheduleService.save(schedule);
        return convertScheduleToScheduleDTO(newSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream()
                .map(p -> convertScheduleToScheduleDTO(p))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable Long petId) {
        return scheduleService.getScheduleForPet(petId).stream()
            .map(p -> convertScheduleToScheduleDTO(p))
            .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId).stream()
                .map(p -> convertScheduleToScheduleDTO(p))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable Long customerId) {
        return scheduleService.getScheduleForCustomer(customerId).stream()
                .map(p -> convertScheduleToScheduleDTO(p))
                .collect(Collectors.toList());
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return scheduleDTO;
    }

    private Schedule convertScheduleDTOToScheduleEntity(ScheduleDTO scheduleDTO) throws ParseException {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
        return schedule;
    }

}
