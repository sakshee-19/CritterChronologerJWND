package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.NamedQuery;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    findAll
    List<Schedule> findByPetIdsId(long petIds);

    List<Schedule> findByEmployeeIdsId(long employeeId);

//    List<Schedule> findScheduleForCustomer(long customerId);
}
