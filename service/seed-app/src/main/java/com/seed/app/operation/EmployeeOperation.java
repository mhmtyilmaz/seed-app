package com.seed.app.operation;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.repository.EmployeeRepository;
import com.seed.app.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by MEHMET on 3.9.2017.
 */
@Component
public class EmployeeOperation {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return (List) employeeRepository.findAll();
    }

    public EmployeeDto addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new EmployeeDto(Constant.EMPLOYEE_ADD_SUCCESS, employee);
    }
}
