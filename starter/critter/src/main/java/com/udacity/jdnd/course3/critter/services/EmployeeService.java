package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.exceptions.NotFoundException;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        logger.info("**START** saveEmployee ={}", employee);
        Employee savedEmployee = employeeRepository.persist(employee);
        return savedEmployee;
    }

    public Employee findEmployeeById(Long id) {
        logger.info("**START findEmployeeById id={}", id);
        return employeeRepository.findById(id);
    }

    public void removeEmployee(Long id) {
        logger.info("**START** delete employee id={}", id);
        employeeRepository.delete(id);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        logger.info("**START** updateEmployee id={}", id);
        Employee existingEmp = findEmployeeById(id);
        employee.setId(id);
        if (existingEmp != null)
            return employeeRepository.merge(employee);
        logger.info("**END** employee id={} does not exist", id);
        throw new NotFoundException("employee with id "+id+" Not found");
    }

    public void setAvailability(Set<DayOfWeek> avail, Long empId) {
        logger.info("**START** setAvailability avail={}, empId={}", avail, empId);
        Employee emp = findEmployeeById(empId);
        if (emp != null) {
            emp.setDaysAvailable(avail);
            employeeRepository.merge(emp);
        } else {
            logger.info("**END** emp with id={} does not exists", empId);
            throw new NotFoundException("Employee with id " + empId + " Not found");
        }
//        employeeRepository.setUpdateAvailability(avail, empId);
    }

    public List<Employee> findEmployeeForService(EmployeeRequestDTO employeeDTO) {
        logger.info("**START** findEmployeeForService");
        List<Employee> employeeList = employeeRepository.findEmployeeForService(employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek());
        List<Employee> filter = new ArrayList<>();
        for(Employee employee : employeeList){
            System.out.println(employee.getSkills());
            if(employee.getSkills().containsAll(employeeDTO.getSkills())){
                filter.add(employee);
            }
        }
        System.out.println(filter);
        logger.info("**END** findEmployeeForService");
        return filter;
    }
}
