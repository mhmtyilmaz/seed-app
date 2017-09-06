package com.seed.app.operation;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.repository.EmployeeRepository;
import com.seed.app.util.Constant;
import com.seed.app.util.EmployeeBuilder;
import com.seed.app.util.EmployeeException;
import com.seed.app.util.TestConstant;
import junit.extensions.TestSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by MEHMET on 4.9.2017.
 */
public class EmployeeOperationTest {

    @InjectMocks
    private EmployeeOperation employeeOperation;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        employee = EmployeeBuilder.employeeBuilder()
                .withName(TestConstant.NAME)
                .withBirthDate(TestConstant.BIRTH_DATE)
                .withEducation(TestConstant.EDUCATION)
                .withDeptName(TestConstant.DEPT_NAME)
                .withTitle(TestConstant.TITLE).buildEmployee();

    }

    @Test
    public void whenGettingAllEmployeeThenReturnList() {
        Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
        EmployeeDto returnEmployeeDto = employeeOperation.getAllEmployee();
        assertTrue(returnEmployeeDto.getData().equals(Arrays.asList(employee)));
    }

    @Test
    public void givenEmployeeWhenAddingNewEmployeeThenReturnDto() {
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        EmployeeDto employeeDto = employeeOperation.addEmployee(employee);
        assertTrue(employeeDto.getData().equals(employee));
    }

    @Test
    public void givenEmployeeObjectWhenUpdateEmployeeThenReturnDto() {
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        EmployeeDto employeeDto = employeeOperation.updateEmployeeAllDetail(employee);
        assertTrue(employeeDto.getData().equals(employee));
    }

    @Test
    public void givenEmployeeIdWhenGettingEmployeeThenReturnDto() throws EmployeeException{
        Mockito.when(employeeRepository.findOne(Mockito.anyInt())).thenReturn(employee);
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        EmployeeDto employeeDto = employeeOperation.getEmployee(TestConstant.ID);
        assertTrue(employeeDto.getData().equals(employee));
    }


    @Test(expected = EmployeeException.class)
    public void givenEmployeeIdWhenGettingEmployeeThenThrowException() throws EmployeeException{
        Mockito.when(employeeRepository.findOne(Mockito.anyInt())).thenReturn(null);
        employeeOperation.getEmployee(TestConstant.ID);
    }
}
