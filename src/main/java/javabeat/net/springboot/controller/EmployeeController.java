package javabeat.net.springboot.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.Employee;
import javabeat.net.springboot.service.EmployeeService;
import javabeat.net.springboot.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {    
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
  
    @Inject
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public List<Employee> listEmployees() {
        List<Employee> employees = employeeService.getList();
        return employees;
    }
    @RequestMapping(value="/add",method = RequestMethod.GET)
    public void addEmployee(@RequestParam(value = "employeeName", required = false) String employeeName,
    		@RequestParam(value = "employeeId", required = false) String employeeId,
    		@RequestParam(value = "employeeCity", required = false) String employeeCity){
    	//System.out.println("*********** employee name=" + employeeName);
    	LOGGER.info("*********** employee name=" + employeeName);
    	Employee employee = new Employee(employeeId,employeeName,employeeCity);
    	employeeService.save(employee);
    }
}
