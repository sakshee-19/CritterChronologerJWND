package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Repository
@Transactional
public class EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Employee persist(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public Employee merge(Employee employee) {
        return entityManager.merge(employee);
    }

    public void delete(Long id) {
        Employee emp = entityManager.find(Employee.class, id);
        entityManager.remove(emp);
    }

    private static final String UPDATE_AVAILABILITY = "update Employee e SET e.daysAvailable=:daysAvailable where e.id=:id";

    public void setUpdateAvailability(Set<DayOfWeek> daysAvailable, Long empId) {
        entityManager.createQuery(UPDATE_AVAILABILITY)
                .setParameter("daysAvailable", daysAvailable)
                .setParameter("id", empId)
                .executeUpdate();
    }

    public void findEmployeeForService(Set<EmployeeSkill> skillSet, LocalDate date) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    }
}
