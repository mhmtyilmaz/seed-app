package com.seed.app.controller;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.operation.EmployeeOperation;
import com.seed.app.util.EmployeeBuilder;
import com.seed.app.util.TestConstant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

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
    }

    @Test
    public void whenGettingAllEmployeeThenReturnList() {
        Mockito.when(employeeOperation.getAllEmployee()).thenReturn(Arrays.asList(employee));
        ResponseEntity<List> resultEmployeeList = employeeController.getAllEmployee();
        assertTrue(resultEmployeeList.getBody().stream().findFirst().get().equals(employee));
    }

    @Test
    public void whenAddingNewEmployeeThenReturnHttpStatus() {
        Mockito.when(employeeOperation.addEmployee(employee)).thenReturn(Mockito.any(EmployeeDto.class));
        ResponseEntity<EmployeeDto> responseEntity = employeeController.addNewEmployee(employee);
        assertTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()));
    }

}
