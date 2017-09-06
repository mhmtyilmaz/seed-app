package com.seed.app.operation;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.repository.EmployeeRepository;
import com.seed.app.util.EmployeeBuilder;
import com.seed.app.util.TestConstant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        List<Employee> employeeList = employeeOperation.getAllEmployee();
        assertTrue(employeeList.stream().findFirst().get().equals(employee));
    }

    @Test
    public void whenAddingNewEmployeeThenReturnDto() {
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        EmployeeDto employeeDto = employeeOperation.addEmployee(employee);
        assertTrue(employeeDto.getData().equals(employee));
    }

}
