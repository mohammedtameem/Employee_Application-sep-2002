package com.airobosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airobosoft.entity.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer>{

}
