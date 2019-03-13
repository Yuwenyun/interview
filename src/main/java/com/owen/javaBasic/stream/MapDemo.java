package com.owen.javaBasic.stream;

import com.owen.ModelFactory;
import com.owen.common.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MapDemo {

    public static void main(String[] args)
    {
        List<Employee> employees = ModelFactory.getEmployees();
        Map<Boolean, Integer> groupGender = new HashMap<>();
        sumAgeAndGroupByGender(employees, groupGender);
        System.out.println(groupGender.toString());

        Map<String, String> demoMap = ModelFactory.getStringKeyedMap();
        computDemoString(demoMap);
        System.out.println(demoMap.toString());
    }

    private static void sumAgeAndGroupByGender(List<Employee> employees, Map<Boolean, Integer> resultMap){
        if(employees == null || employees.size() == 0 || resultMap == null){
            return;
        }

        for(Employee employee : employees){
            // merge args:
            // 1. key to group
            // 2. value to use
            // 3. the way to use the value
            resultMap.merge(employee.isMale(), employee.getAge(), (ageA, ageB) -> ageA + ageB);
        }
    }

    private static void computDemoString(Map<String, String> demoMap)
    {
        String targetKey = ModelFactory.MAP_KEY_3;
        String msg = "_msg";
        BiFunction<String, String, String> func = (key, value) -> value == null ? msg : value.concat(msg);

        // if the target key exists, apply the BiFunction to it
        demoMap.compute(targetKey, func);

        // if not exist, add target key to it
        targetKey = "owen";
        demoMap.compute(targetKey, func);
    }
}
