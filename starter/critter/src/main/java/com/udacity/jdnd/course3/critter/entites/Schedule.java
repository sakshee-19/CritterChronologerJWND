package com.udacity.jdnd.course3.critter.entites;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NamedQuery(
        name = "Schedule.findScheduleForCustomer",
        query = "Select s From Schedule s JOIN Pet p on (s.id=p.appointment) where p.owner.id=:customerId"
)
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employeeIds;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> petIds;

    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Employee> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Pet> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Pet> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", employeeIds=" + employeeIds +
                ", petIds=" + petIds +
                ", date=" + date +
                '}';
    }
}
