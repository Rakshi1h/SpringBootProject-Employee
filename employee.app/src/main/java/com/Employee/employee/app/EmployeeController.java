package com.Employee.employee.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController//allows to handle all the api's put,get,delete (process)
public class EmployeeController {

	@Autowired//is used to connect b/w employeecontroller and employeerepository
	EmployeeRepository er;
	
	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		
		Employee employee1 =er.save(employee);
		return employee;
	}
	
	@GetMapping("/getEmployee")
	public List<Employee> getEmployee(){
		return er.findAll();
	}
	
	@PostMapping("/updateEmployee")
	public Employee updateEmployee(@RequestParam int id,@RequestBody Employee employee) {
		Optional<Employee> opt=er.findById(id);
		if(opt.isEmpty()) {
			return null;
		}else {
			return er.save(employee);
		}
	}
	@DeleteMapping("/deleteEmployee")
	public String DeleteEmployeeById(@RequestParam int id) {
		Optional<Employee> opt =er.findById(id);
		if(opt.isEmpty()) {
			return"No employee data deleted";
		}else {
			Employee employee=opt.get();
			er.delete(employee);
			return "Employee Deleted";
			}
		
	}
}
