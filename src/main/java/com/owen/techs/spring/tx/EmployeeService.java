package com.owen.techs.spring.tx;

import com.owen.db.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public class EmployeeService {

    private EmployeeDao dao;

    /**
     * declare the method to be a transaction(including insert and update)
     */
    @Transactional
    public void test(){
        Employee employee = new Employee();
        employee.setBirthDate(LocalDate.of(2012, 1, 1));
        employee.setHireDate(LocalDate.of(2016, 1,1));
        employee.setFirstName("Owen");
        employee.setLastName("Lee");
        this.dao.insert(employee);

        this.dao.update(8888L, employee);
    }

    public EmployeeDao getDao() {
        return dao;
    }

    public void setDao(EmployeeDao dao) {
        this.dao = dao;
    }
}
