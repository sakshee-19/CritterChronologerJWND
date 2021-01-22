package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.entites.Schedule;
import com.udacity.jdnd.course3.critter.exceptions.InvalidRequestException;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.*;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetServices petServices;

    private static Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public Schedule saveSchedule(ScheduleDTO scheduleDTO) {
        logger.info("**START** SaveSchedule {} ", scheduleDTO);
        DayOfWeek day = findDayOfWeek(scheduleDTO.getDate());
        Schedule schedule = convertToEntity(scheduleDTO);
        System.out.println(schedule);
        Set<EmployeeSkill> employeeSkillSet = new HashSet<>();
        System.out.println(schedule.getEmployeeIds());
        for (Employee emp : schedule.getEmployeeIds()) {
            employeeSkillSet.addAll(emp.getSkills());
            if (!emp.getDaysAvailable().contains(day)) {
                logger.info("Can not Schedule the appointment for employee {}, not available on day {}", emp.getId(), day);
                throw new InvalidRequestException("Can not Schedule the appointment for employee " + emp.getId() + ", not available on day " + day);
            }
        }
//        if(!employeeSkillSet.containsAll(schedule.getActivities()))
//        {
//            logger.info("Employees does not have the skill set , given in activities");
//            throw new InvalidRequestException("Employees does not have the skill set , given in activities. Please choose proper activities");
//        }
        Schedule scheduleSaved = scheduleRepository.save(schedule);
        return scheduleSaved;
    }

//    private Boolean validateActivities(List<Employee>)

    private Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        System.out.println(scheduleDTO.getActivities());
        BeanUtils.copyProperties(scheduleDTO, schedule, "employeeIds", "petIds");
        System.out.println(schedule.getActivities());
        List<Employee> employeeList = new ArrayList<>();
        for (long id : scheduleDTO.getEmployeeIds()) {
            employeeList.add(employeeRepository.findById(id));
        }
        List<Pet> petList = new ArrayList<>();
        for (long petId : scheduleDTO.getPetIds()) {
            Pet pet = petServices.findPetById(petId);
            petList.add(pet);
        }
        schedule.setPetIds(petList);
        schedule.setEmployeeIds(employeeList);
        return schedule;
    }


    private DayOfWeek findDayOfWeek(LocalDate date) {
        logger.info("Date={} day={}", date, date.getDayOfWeek());
        return date.getDayOfWeek();
    }

    public List<Schedule> findAllSchedule() {
        logger.info("**START** findAllSchedule");
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleByPet(Long petId) {
        logger.info("**START** findScheduleByPet petId={}", petId);
        return scheduleRepository.findByPetIdsId(petId);
    }

    public List<Schedule> findScheduleByEmployeeId(long employeeId) {
        logger.info("**START** findScheduleByEmployeeId employeeId={}", employeeId);
        return scheduleRepository.findByEmployeeIdsId(employeeId);
    }

    public List<Schedule> findScheduleByCustomerId(long customerId) {
        logger.info("**START** findScheduleByCustomerId customerId={}", customerId);
        return scheduleRepository.findScheduleForCustomer(customerId);
    }
}
