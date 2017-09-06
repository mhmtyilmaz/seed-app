package com.seed.app.operation;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.repository.EmployeeRepository;
import com.seed.app.util.Constant;
import com.seed.app.util.EmployeeBuilder;
import com.seed.app.util.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by MEHMET on 3.9.2017.
 */
@Component
public class EmployeeOperation {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDto getAllEmployee() {
        return new EmployeeDto(Constant.EMPLOYEE_ALL_SUCCESS, employeeRepository.findAll());
    }

    public EmployeeDto addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new EmployeeDto(Constant.EMPLOYEE_ADD_SUCCESS, employee);
    }

    public EmployeeDto updateEmployeeAllDetail(Employee employee) {
        employeeRepository.save(employee);
        return new EmployeeDto(Constant.EMPLOYEE_UPDATE_SUCCESS, employee);
    }

    public EmployeeDto getEmployee(Integer id) throws EmployeeException {
        Employee employee = employeeRepository.findOne(id);
        if (employee == null) {
            throw new EmployeeException(HttpStatus.INTERNAL_SERVER_ERROR, Constant.EMPLOYEE_GET_FAIL);
        }

        return new EmployeeDto(Constant.EMPLOYEE_GET_SUCCESS, employee);
    }

}
