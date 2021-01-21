package com.udacity.jdnd.course3.critter.entites;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQuery(
        name = "Schedule.findScheduleForCustomer",
//        query = "Select s From Schedule s where s.id in (Select spi.schedule_id  FROM schedule_pet_ids spi join Pet p on (spi.pet_ids_id=p.id) where p.owner.id=:customerId)"
        query = "Select s FROM Schedule s inner join s.petIds pet where pet.owner.id=:customerId"
)
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employeeIds;

    @ManyToMany(targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> petIds;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "appointment_activities",
            joinColumns = @JoinColumn(name = "schedule_id",referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "activities")
    private Set<EmployeeSkill> activities;

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

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public void addActivity(EmployeeSkill activity) {
        if(this.activities == null)
            this.activities = new HashSet<>();
        this.activities.add(activity);
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
