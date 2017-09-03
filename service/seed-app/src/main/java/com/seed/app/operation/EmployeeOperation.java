package com.seed.app.operation;

import com.seed.app.model.Employee;
import com.seed.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
