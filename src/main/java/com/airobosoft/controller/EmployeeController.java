package com.airobosoft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airobosoft.dao.EmployeeDao;
import com.airobosoft.entity.Employee;

@RestController
@RequestMapping("/v1/api")
public class EmployeeController 
{

	@Autowired
	private EmployeeDao dao;
	
	@PostMapping("/addEmp")
	public Employee addEmployee(@RequestBody Employee emp)
	{
		return dao.save(emp);
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployees()
	{
		return (List<Employee>) dao.findAll();
	}
	
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id)
	{
		return dao.findById(id).orElse(null);
		
	}
	
	@GetMapping("/employeeCount")
	public long getEmployeesCount()
	{
		return dao.count();
		
	}
	@DeleteMapping("/deleteEmployee/{address}")
	public void deleteById(@PathVariable String address)
	{
		List<Employee> list = (List<Employee>) dao.findAll();
		Employee employee = null;
		for(int i =0;i<list.size();i++)
		{
			if(list.get(i).getAddress().equals(address))
			{
				employee = list.get(i);
				dao.deleteById(employee.getId());
				
			}
		}
		
	}
	
	@GetMapping("/employeeByName/{name}")
	public Employee getEmployeeByName(@PathVariable String name)
	{
		List<Employee> list = (List<Employee>) dao.findAll();
		Employee employee = null;
		for(int i =0;i<list.size();i++)
		{
			if(list.get(i).getName().equals(name))
			{
				employee = list.get(i);
				break;
			}
		}
		return employee;
		
	}
	@GetMapping("/employeeByAddress/{address}")
	public List<Employee> getEmployeeByAddress(@PathVariable String address)
	{
		List<Employee> list = (List<Employee>) dao.findAll();
		List<Employee> listOFBangalore = new ArrayList<>();
		for(int i =0;i<list.size();i++)
		{
			if(!list.get(i).getAddress().equals(address))
			{
				listOFBangalore.add(list.get(i));
			}
		}
		return listOFBangalore;
		
	}
	
	@PutMapping("/updateEmployee")
	public Employee getEmployeeByAddress(@RequestBody Employee emp)
	{
		List<Employee> list = (List<Employee>) dao.findAll();
		Employee employee = null;
		for(int i =0;i<list.size();i++)
		{
			if(emp.getId() == list.get(i).getId())
			{
				employee = list.get(i);
			}
		}
		
		
		employee.setAddress(emp.getAddress());
		employee.setAge(emp.getAge());
		employee.setName(emp.getName());
		dao.save(employee);
		return employee;
	}
}








