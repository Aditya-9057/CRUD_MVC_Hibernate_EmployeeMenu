package com.Adityabhosale.dao;

import com.Adityabhosale.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    int insertEmployee(Employee employee) throws DAOException;
    int deleteEmployee(int id) throws DAOException;
    int updateEmployee(Employee employee) throws DAOException;
    Employee getEmployeeById(int id) throws DAOException;
    List<Employee> getAllEmployees() throws DAOException;

}
