package com.rakib.employeeapi.service;

import com.rakib.employeeapi.exception.UserNotFoundException;
import com.rakib.employeeapi.model.Employee;
import com.rakib.employeeapi.repositorys.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, Employee employee){
        Employee getEmployeeById = employeeRepository.findById(id).get();
        getEmployeeById.setName(employee.getName());
        getEmployeeById.setEmail(employee.getEmail());
        getEmployeeById.setPhone(employee.getPhone());
        getEmployeeById.setJobTitle(employee.getJobTitle());
        getEmployeeById.setImageUrl(employee.getImageUrl());
        return employeeRepository.save(getEmployeeById);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id "+ id + "Not found"));
    }


    public  void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }

}
