package com.owen.javaBasic;

import com.owen.common.Employee;
import com.owen.common.Manager;
import com.owen.common.Person;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 1. static proxy, needs an interface, proxy class refers a target class
 * 2. dynamic proxy
 *   a. JDK proxy: provide sub-class of java.lang.reflect.InvocationHandler,
 *                    use java.lang.reflect.Proxy to create the proxy object
 *   b. cglib proxy: generate sub-class for target class as proxy
 */
public class Proxy {

    public static void main(String[] args)
    {
        Proxy proxy = new Proxy();
        Manager manager = new Manager();

        // use static proxy, the proxy class is prepared for compile
        ManagerProxy staticProxy = proxy.new ManagerProxy(manager);
        staticProxy.setDescription("Static proxy");
        System.out.println(staticProxy.getDescription());

        // use jdk dynamic proxy, no need to prepare proxy class
        // target class Manager.java shall have DIRECT parent interface Person.java
        // instead of inherit from it's parent class Employee.java
        Person dynamicProxy = (Person)java.lang.reflect.Proxy.newProxyInstance(
                Manager.class.getClassLoader(),
                manager.getClass().getInterfaces(),
                proxy.new DescriptionHandler<Manager>(manager)
        );
        System.out.println(dynamicProxy.talk());

        // use cglib proxy, by default all the methods will be intercepted with
        // ManagerInterceptor.java
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Manager.class);
        enhancer.setCallback(proxy.new ManagerTalkInterceptor());
        Manager cglibProxy = (Manager)enhancer.create();
        System.out.println(cglibProxy.getDescription());

        // use cglib proxy, only proxy for specific methods
        enhancer.setCallbacks(new MethodInterceptor[]{
                proxy.new ManagerInterceptor(),
                proxy.new ManagerTalkInterceptor(),
                proxy.new ManagerGetDescInterceptor()
        });
        enhancer.setCallbackFilter(proxy.new ManagerMethodFilter());
        cglibProxy = (Manager)enhancer.create();
        System.out.println(cglibProxy.talk());
        System.out.println(cglibProxy.getDescription());
    }

    // static proxy, proxy class holds the target class object
    public class ManagerProxy extends Employee
    {
        // target class object
        private Manager manager;

        public ManagerProxy(Manager manager){
            this.manager = manager;
        }

        // proxy method
        @Override
        public String getDescription() {
            // do other things
            System.out.println("Do other things...");

            // call the target method
            return manager.getDescription();
        }

        @Override
        public void setDescription(String description) {
            this.manager.setDescription(description);
        }
    }

    // dynamic proxy, implement InvocationHandler to provide invoke() method
    public class DescriptionHandler<T> implements InvocationHandler
    {
        //target class object
        private T t;
        public DescriptionHandler(T t){
            this.t = t;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
            // do other things
            System.out.println("Do other things...");

            // call the target method
            return method.invoke(t, args);
        }
    }

    // cglib proxy, implement MethodInterceptor
    public class ManagerTalkInterceptor implements MethodInterceptor
    {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
        {
            // do other things
            System.out.println("Do other things before talk...");

            // call the target method
            return methodProxy.invokeSuper(o, objects);
        }
    }

    // cglib proxy, implement MethodInterceptor
    public class ManagerGetDescInterceptor implements MethodInterceptor
    {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
        {
            // do other things
            System.out.println("Do other things before getDesc...");

            // call the target method
            return methodProxy.invokeSuper(o, objects);
        }
    }

    // cglib proxy, implement MethodInterceptor
    public class ManagerInterceptor implements MethodInterceptor
    {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
        {
            // do other things
            System.out.println("Do other things...");

            // call the target method
            return methodProxy.invokeSuper(o, objects);
        }
    }

    // cglib proxy, define the filter of MethodInterceptor
    public class ManagerMethodFilter implements CallbackFilter
    {
        @Override
        public int accept(Method method) {
            String methodName = method.getName();
            int result = 0;
            switch (methodName){
                case "talk":
                    result = 1;
                    break;
                case "getDescription":
                    result = 2;
                    break;
                default:
                    break;
            }
            return result;
        }
    }
}
