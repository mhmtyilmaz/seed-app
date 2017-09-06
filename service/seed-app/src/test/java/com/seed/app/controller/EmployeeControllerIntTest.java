package com.seed.app.controller;

import com.google.gson.Gson;
import com.seed.app.model.Employee;
import com.seed.app.util.EmployeeBuilder;
import com.seed.app.util.TestConstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by MEHMET on 3.9.2017.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(secure = false)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)
public class EmployeeControllerIntTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private MockMvc mockMvc;
    private Employee employee;
    private Gson gson;


    @Before
    public void setup() {
        gson = new Gson();
        employee = EmployeeBuilder.employeeBuilder()
                .withName(TestConstant.NAME)
                .withBirthDate(TestConstant.BIRTH_DATE)
                .withEducation(TestConstant.EDUCATION)
                .withDeptName(TestConstant.DEPT_NAME)
                .withTitle(TestConstant.TITLE)
                .buildEmployee();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldGetAllEmployee() {
        try {
            mockMvc.perform(get(TestConstant.API_PATH)).andExpect(status().isOk())
                    .andExpect(jsonPath("$name",is(TestConstant.NAME)))
                    .andExpect(jsonPath("$birthDate",is(TestConstant.BIRTH_DATE)))
                    .andExpect(jsonPath("$education",is(TestConstant.EDUCATION)))
                    .andExpect(jsonPath("$deptName",is(TestConstant.DEPT_NAME)))
                    .andExpect(jsonPath("$title",is(TestConstant.TITLE)));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Test
    public void  shouldAddNewEmployee() {
        try {
            mockMvc.perform(put(TestConstant.API_PATH)
                    .contentType(getMediaTypeJSON())
                    .content(gson.toJson(employee)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private MediaType getMediaTypeJSON() {
        return new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    }

}
