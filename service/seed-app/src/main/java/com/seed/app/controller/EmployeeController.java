package com.seed.app.controller;

import com.seed.app.operation.EmployeeOperation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ApiOperation(value = "Get All Employee Details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got all Employee Detail.", response = List.class),
            @ApiResponse(code = 400, message = "Could not get Employee.")
    })
    public
    @ResponseBody
    ResponseEntity<List> getAllStocks() {
        return new ResponseEntity<>(employeeOperation.getAllEmployee(), HttpStatus.OK);
    }
}
