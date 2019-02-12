package com.owen.techs.spring.tx;

import com.owen.db.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeDaoImpl implements EmployeeDao
{
    private JdbcTemplate template;

    @Override
    public void insert(Employee employee) {
        String sql = "insert into employees(emp_no, birth_date, hire_date, first_name, last_name) " +
                "values(?,?,?,?,?)";
        this.template.update(sql, 8888, employee.getBirthDate(),
                employee.getHireDate(), employee.getFirstName(), employee.getLastName());
    }

    @Override
    public void update(long empNo, Employee employee) {
        throw new IllegalArgumentException("Testing tx failure");
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public JdbcTemplate getTemplate() {
        return template;
    }
}
