package com.owen.techs.spring.tx;

import com.owen.db.Employee;

public interface EmployeeDao {

    void insert(Employee employee);
    void update(long empNo, Employee employee);
}
