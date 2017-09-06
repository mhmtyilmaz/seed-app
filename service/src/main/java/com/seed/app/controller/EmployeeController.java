package com.seed.app.controller;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.operation.EmployeeOperation;
import com.seed.app.util.Constant;
import com.seed.app.util.EmployeeException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MEHMET on 3.9.2017.
 */
@CrossOrigin
@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeOperation employeeOperation;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = Constant.EMPLOYEE_ALL_OP)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constant.EMPLOYEE_ALL_SUCCESS, response = List.class),
            @ApiResponse(code = 400, message = Constant.EMPLOYEE_ALL_FAIL)
    })
    public
    @ResponseBody
    ResponseEntity<EmployeeDto> getAllEmployee() {
        return new ResponseEntity<>(employeeOperation.getAllEmployee(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = Constant.EMPLOYEE_ADD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constant.EMPLOYEE_ADD_SUCCESS, response = EmployeeDto.class),
            @ApiResponse(code = 400, message = Constant.EMPLOYEE_ADD_FAIL)
    })
    public
    @ResponseBody
    ResponseEntity<EmployeeDto> addNewEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeOperation.addEmployee(employee), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = Constant.EMPLOYEE_UPDATE_OP)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constant.EMPLOYEE_UPDATE_SUCCESS, response = EmployeeDto.class),
            @ApiResponse(code = 400, message = Constant.EMPLOYEE_UPDATE_FAIL)
    })
    public
    @ResponseBody
    ResponseEntity<EmployeeDto> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeOperation.updateEmployeeAllDetail(employee), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = Constant.EMPLOYEE_GET_OP)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constant.EMPLOYEE_GET_SUCCESS, response = Employee.class),
            @ApiResponse(code = 400, message = Constant.EMPLOYEE_GET_FAIL)
    })
    public
    @ResponseBody
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Integer id) throws EmployeeException {

        try {
            return new ResponseEntity<>(employeeOperation.getEmployee(id), HttpStatus.OK);
        } catch (EmployeeException e) {
            return new ResponseEntity<>(new EmployeeDto(e.getEMessage(),null),e.getHttpStatus());
        }
    }

}
