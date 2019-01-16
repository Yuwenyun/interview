package com.owen.javaBasic;

import com.owen.common.Employee;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;

/**
 * 5 ways to create new object
 */
public class CreateObject {

    public static void main(String[] args)
    {
        try
        {
            // use "new"
            Employee newEmp = new Employee();

            // use reflection, call constructor with no args
            Employee refEmp = (Employee)Class.forName("com.owen.common.Employee").newInstance();

            // use Constructor.java, we can call constructor with args
            Constructor<Employee> cons = Employee.class.getConstructor(String.class, String.class);
            Employee conEmp = cons.newInstance("Owen", "Lee");

            // use Object.clone(), won't call any constructor
            // 1. the target type shall implements interface Cloneable
            // 2. the clone() in Object.java is protected, can't call conEmp.clone() directly
            //    unless override clone() in Employee.java with modifier "public"
            // 3. any reference in conEmp will be copied instead of creating new object as attribute
            //    for cloned object
            Employee cloEmp = (Employee)conEmp.clone();

            // use serialization, read object from ObjectInputStream.java
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialized.txt"));
            Employee serEmp = (Employee)ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}