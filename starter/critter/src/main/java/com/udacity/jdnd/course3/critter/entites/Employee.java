package com.udacity.jdnd.course3.critter.entites;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

//@NamedQuery(
//        name = "Employee.updateSchedule",
//        query = ""
//)
@Entity
public class Employee extends User{

    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "employee_skills",
            joinColumns = @JoinColumn(name = "employee_id",referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_skills")
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @JoinTable(name = "employee_days_availability",
            joinColumns = @JoinColumn(name = "employee_id",referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "days")
    private Set<DayOfWeek> daysAvailable;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "skills=" + skills +
                ", daysAvailable=" + daysAvailable +
                '}';
    }

    public Employee() {
    }
    public Employee(long id) {
        super(id);
    }
}
