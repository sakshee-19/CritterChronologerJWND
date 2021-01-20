package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.entites.Schedule;
import com.udacity.jdnd.course3.critter.exceptions.InvalidRequestException;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private static Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public Schedule saveSchedule(Schedule schedule) {
        logger.info("**START** SaveSchedule {} ", schedule);
        DayOfWeek day = findDayOfWeek(schedule.getDate());
        for (Employee emp : schedule.getEmployeeIds()) {
            if (!emp.getDaysAvailable().contains(day)) {
                logger.info("Can not Schedule the appointment for employee {}, not available on day {}", emp.getId(), day);
                throw new InvalidRequestException("Can not Schedule the appointment for employee " + emp.getId() + ", not available on day " + day);
            }
        }
        return scheduleRepository.save(schedule);
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
