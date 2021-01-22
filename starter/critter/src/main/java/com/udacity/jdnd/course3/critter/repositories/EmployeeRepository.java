package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
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
//    private static final String UPDATE_AVAILABILITY_EMP = "update employee_days_availability eda SET eda.daysAvailable=:daysAvailable where e.id=:id";

    public void setUpdateAvailability(Set<DayOfWeek> daysAvailable, Long empId) {
        entityManager.createQuery(UPDATE_AVAILABILITY)
                .setParameter("daysAvailable", daysAvailable)
                .setParameter("id", empId)
                .executeUpdate();
    }

    private static final String UP = "Select * FROM employee e where e.skills in :skills AND :day in e.daysAvailable";

    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skillSet, DayOfWeek dayOfWeek) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> root = criteria.from(Employee.class);


//        criteria.where(root.get("daysAvailable").in(dayOfWeek));
//        criteria.where();
        criteria.distinct(true).where(criteriaBuilder.and(root
                .join("skills", JoinType.INNER)
                .in(skillSet), root.join("daysAvailable", JoinType.INNER).in(dayOfWeek)));

        return entityManager.createQuery(criteria).getResultList();
    }
}
