package com.crud.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dao.EmployeeRepository;
import com.crud.model.Employee;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = {"*"})
public class EmployeeController {
	 private final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 
	 @GetMapping("/employee")
	 ResponseEntity<List<Employee>> getUsersList() {
        return ResponseEntity.ok().body(employeeRepository.findAll());
     }
	 
	 @GetMapping("/employee/{id}")
	 ResponseEntity<Optional<Employee>> getEmployee(@PathVariable Long id) {
		 return ResponseEntity.ok().body(employeeRepository.findById(id));
     }
	 
	 @PostMapping("/employee")
	 ResponseEntity<Employee> createUser(@Valid @RequestBody Employee employee) throws URISyntaxException {
	        log.info("Request to create employee: {}", employee);
	        Employee result = employeeRepository.save(employee);
	        return ResponseEntity.created(new URI("/api/employee/" + result.getId()))
	                .body(result);
	 }
	 
	 @PutMapping("/employee")
     ResponseEntity<Employee> updateemployee(@Valid @RequestBody Employee employee) {
        log.info("Request to update employee: {}", employee);
        Employee result = employeeRepository.save(employee);
        return ResponseEntity.ok().body(result);
     }
	

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Request to delete user: {}", id);
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

