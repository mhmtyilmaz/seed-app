package com.seed.app.repository;

import com.seed.app.model.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MEHMET on 3.9.2017.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {


}
