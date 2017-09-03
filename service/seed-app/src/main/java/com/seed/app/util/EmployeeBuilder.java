package com.seed.app.util;

import com.seed.app.model.Employee;

import java.util.Date;

/**
 * Created by MEHMET on 3.9.2017.
 */
public class EmployeeBuilder {

    private String name;
    private Date birthDate;
    private String education;
    private String deptName;
    private String title;

    public static EmployeeBuilder employeeBuilder(){
        return  new EmployeeBuilder();
    }

    public EmployeeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public EmployeeBuilder withEducation(String education) {
        this.education = education;
        return this;
    }

    public EmployeeBuilder withDeptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    public EmployeeBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public Employee buildEmployee(){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setBirthDate(birthDate);
        employee.setEducation(education);
        employee.setDeptName(deptName);
        employee.setTitle(title);
        return employee;
    }
}
