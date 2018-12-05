package com.owen.javaBasic;

import com.owen.ModelFactory;
import com.owen.common.Employee;

import java.io.*;
import java.util.List;

/**
 * FYI: http://www.hollischuang.com/archives/1140#What%20Serializable%20Did
 *
 * Serialization: a way to persist or transfer(on Internet) an object as a byte array
 * 1. serialization won't care about static field
 * 2. class of the object has to implements java.io.Serializable
 * 3. any sub-class of the class which implements Serializable is able to serialize
 * 4. to serialize a parent object, the parent class has to implement Serializable
 *
 * 5. serialVersionID of serialized object has to be the same as the serialVersionID of the class
 *    if we want to deserialize an object
 * 6. use keyword transient to prevent a field from serialization
 *
 * 7. object array in ArrayList is transient while serialize ArrayList works well because ArrayList
 *    defines writeObject(ObjectOutputStream)/readObject(ObjectInputStream) by itself to
 *    exclude null objects in object array.
 *
 * 8. writeObject(ObjectOutputStream)/readObject(ObjectInputStream) defined in object class will be
 *    involved by ObjectOutputStream.writeObject()/ObjectInputStream.readObject() via reflection
 *
 * 9. parent class has to provide default constructor because sub-class object need parent object to initialize
 */
public class Serialization
{
    private static final String fileName = "serialization.txt";

    public static void main(String[] args)
    {
        serializeObjectDemo(ModelFactory.getEmploye());
        deserializeObjectDemo();

        serializeObjectDemo(ModelFactory.getEmployees());
        deserializeObjectsDemo();
    }

    private static void serializeObjectDemo(Employee emp){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            System.out.println("Serialize object: " + emp.toString());
            oos.writeObject(emp);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void deserializeObjectDemo(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            Employee emp = (Employee)ois.readObject();
            System.out.println("Deserialize object: " + emp.toString());
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static void serializeObjectDemo(List<Employee> emps){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            System.out.println("Serialize object: ");
            emps.forEach(System.out::println);
            oos.writeObject(emps);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void deserializeObjectsDemo(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            List<Employee> emps = (List<Employee>)ois.readObject();
            emps.forEach(System.out::println);
            System.out.println("Deserialize object: ");
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
