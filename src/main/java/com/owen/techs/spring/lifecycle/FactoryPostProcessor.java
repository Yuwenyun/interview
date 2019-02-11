package com.owen.techs.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor is able to obtain all the BeanDefinition and do any
 * correction to the BeanDefinition
 *
 * BeanFactoryPostProcessor is called after the instantiation of container
 */
public class FactoryPostProcessor implements BeanFactoryPostProcessor
{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        System.out.println("Calling FactoryPostProcessor...");
        // get all the BeanDefinition
        String[] names = configurableListableBeanFactory.getBeanDefinitionNames();
        for(String name : names){
            if(name.equals("entity")){
                BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
                MutablePropertyValues m = beanDefinition.getPropertyValues();
                if(m.contains("name")){
                    m.add("name", "Owen");
                    System.out.println("Bean " + name + "'s firstName changed to Owen");
                }
            }
        }
    }
}
