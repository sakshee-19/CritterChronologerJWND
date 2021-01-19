package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Schedule;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    private static Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public Schedule saveSchedule(Schedule schedule){
        logger.info("**START** SaveSchedule {} ",schedule);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllSchedule() {
        logger.info("**START** findAllSchedule");
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleByPet(Long petId) {
        logger.info("**START** findScheduleByPet petId={}",petId);
        return scheduleRepository.findByPetIdsId(petId);
    }

    public List<Schedule> findScheduleByEmployeeId(long employeeId) {
        logger.info("**START** findScheduleByEmployeeId employeeId={}",employeeId);
        return scheduleRepository.findByEmployeeIdsId(employeeId);
    }

    public List<Schedule> findScheduleByCustomerId(long customerId) {
        logger.info("**START** findScheduleByCustomerId customerId={}",customerId);
        return scheduleRepository.findScheduleForCustomer(customerId);
    }
}
