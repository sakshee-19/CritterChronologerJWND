package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
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
}
