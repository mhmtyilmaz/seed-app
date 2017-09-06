package com.seed.app.controller;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.operation.EmployeeOperation;
import com.seed.app.util.EmployeeBuilder;
import com.seed.app.util.EmployeeException;
import com.seed.app.util.TestConstant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.Assert.assertTrue;

/**
 * Created by MEHMET on 3.9.2017.
 */
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeOperation employeeOperation;

    private Employee employee;
    private EmployeeDto employeeDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        employee = EmployeeBuilder.employeeBuilder()
                .withName(TestConstant.NAME)
                .withBirthDate(TestConstant.BIRTH_DATE)
                .withEducation(TestConstant.EDUCATION)
                .withDeptName(TestConstant.DEPT_NAME)
                .withTitle(TestConstant.TITLE)
                .buildEmployee();
        employeeDto = new EmployeeDto(TestConstant.NAME,employee);
    }

    @Test
    public void whenGettingAllEmployeeThenReturnResponseEntity() {
        Mockito.when(employeeOperation.getAllEmployee()).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> resultEmployeeDto = employeeController.getAllEmployee();
        assertTrue(resultEmployeeDto.getBody().getData().equals(employee));
    }

    @Test
    public void givenEmployeeWhenAddingNewEmployeeThenReturnResponseEntity() {
        Mockito.when(employeeOperation.addEmployee(employee)).thenReturn(Mockito.any(EmployeeDto.class));
        ResponseEntity<EmployeeDto> responseEntity = employeeController.addNewEmployee(employee);
        assertTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()));
    }

    @Test
    public void givenEmployeeWhenUpdateEmployeeThenReturnResponseEntity() {
        Mockito.when(employeeOperation.updateEmployeeAllDetail(employee)).thenReturn(Mockito.any(EmployeeDto.class));
        ResponseEntity<EmployeeDto> responseEntity = employeeController.updateEmployee(employee);
        assertTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()));
    }

    @Test
    public void givenEmployeeIdWhenGetEmployeeThenReturnResponseEntity() throws EmployeeException{
        Mockito.when(employeeOperation.getEmployee(Mockito.anyInt())).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> responseEntity = employeeController.getEmployee(employee.getId());
        assertTrue(responseEntity.getBody().getData().equals(employee));
    }

}
