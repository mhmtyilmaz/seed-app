package com.seed.app.controller;

import com.seed.app.dto.EmployeeDto;
import com.seed.app.model.Employee;
import com.seed.app.operation.EmployeeOperation;
import com.seed.app.util.Constant;
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
    ResponseEntity<List> getAllEmployee() {
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
}
