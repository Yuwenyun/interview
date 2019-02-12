package com.owen.techs.spring.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * select * from employees where emp_no = 8888
 * delete from employees where emp_no = 8888
 *
 * Isolation Level:
 * read uncommitted: T1 read row R when T2 is changing R and still not committed
 * read committed: T1 read R and T2 changed R, then T1 read again, result differently
 * read repeatable: T1 read R, T2 not allowed to do any change till T1 release the shared lock
 * serializable.
 *
 * dirty read: read something uncommitted
 * not repeatable read: read two times but with different results
 * phantom read: read two times but with more/less records
 *
 * propagation_required: default in spring transaction, caller method in transaction, wrap the
 *   method in that transaction, otherwise start new transaction
 * propagation_required_new: caller method in transaction, suspend it and create new transaction
 * propagation_supported: caller method in transaction, wrap in that transaction, otherwise no tx
 * propagation_not_supported: if in transaction, suspend the transaction
 * propagation_mandatory: transaction required, otherwise throw an exception
 * propagation_never: no transaction allowed, otherwise throw an exception
 * propagation_nested: nest current method in the caller's transaction, support savepoint
 *
 * spring transaction elements:
 * TransactionDefinition: isolation_level, propagation, readonly, timeout, define an transaction
 * PlatformTransactionManagement: manage transaction based on target db
 *   a. integrate with mybatis, use Connection object of Mybatis to perform transaction
 *   b. integrate with Hibernate, use Session object of Hibernate to perform transaction
 * TransactionStatus
 *
 * spring transaction:
 *   1. use TransactionTemplate
 *   2. declare transaction with TransactionManager, implemented with aop around advance
 */
public class Main {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/tx.xml");
        EmployeeService service = (EmployeeService)context.getBean("employeeService");
        service.test();
    }
}
