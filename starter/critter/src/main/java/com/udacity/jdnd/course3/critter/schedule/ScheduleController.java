package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.entites.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.saveSchedule(convertToEntity(scheduleDTO));
        return convertToDto(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertToEntity(scheduleService.findAllSchedule());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.findScheduleByPet(petId);
        return convertToEntity(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.findScheduleByEmployeeId(employeeId);
        return convertToEntity(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.findScheduleByCustomerId(customerId);
        return convertToEntity(schedules);
    }

    private Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }


    private List<ScheduleDTO> convertToEntity(List<Schedule> scheduleList) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule: scheduleList){
            scheduleDTOS.add(convertToDto(schedule));
        }
        return scheduleDTOS;
    }

    private ScheduleDTO convertToDto(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO, "petIds", "employeeIds");
        List<Long> petIds = new ArrayList<>();
        for(Pet pet: schedule.getPetIds()) petIds.add(pet.getId());

        List<Long> employeeIds = new ArrayList<>();
        for(Employee employee: schedule.getEmployeeIds()) employeeIds.add(employee.getId());

        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);

        return scheduleDTO;
    }
}
