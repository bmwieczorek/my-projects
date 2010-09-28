package com.bawi.myhibernate.dao;

import java.util.List;

import com.bawi.myhibernate.domain.Employee;

public interface EmployeeDao {

    List<Employee> getEmployees();
}
