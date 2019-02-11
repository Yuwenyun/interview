package com.owen.techs.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void add(){
        System.out.println("MyService.add()...");
    }

    public boolean delete(){
        System.out.println("MyService.delete()...");
        return true;
    }

    public void edit(){
        System.out.println("MyService.edit()...");
        int i = 5/0;
    }
}
