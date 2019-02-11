package com.owen.techs.spring.lifecycle;

import com.owen.techs.spring.common.Entity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanFactory is a container to maintain beans, two stages for bean initialization.
 * 1. BeanFactory initialization(container initialization):
 *    read xml configuration to create BeanDefinition and register those objects to DefaultListableBeanFactory's
 *    ConcurrentMap. Spring provide BeanFactoryPostProcessor for extension. PropertyPlaceholderConfigurer is
 *    a sub-class for extension.
 *
 *   a. BeanFactoryPostProcessor.postProcessBeanFactory()
 *
 * 2. Bean initialization
 *   a. InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()
 *   b. Bean's constructor()
 *   c. InstantiationAwareBeanPostProcessor.postProcessPropertyValues()
 *   d. Bean's setter()
 *   e. BeanNameAware.setBeanName()
 *   f. BeanFactoryAware.setBeanFactory()
 *   g. ApplicationContextAware.setApplicationContext()
 *   h. BeanPostProcessor.postProcessBeforeInitialization()
 *   i. InitializingBean.afterPropertiesSet()
 *   j. init-method()
 *   k. BeanPostProcessor.postProcessAfterInitialization()
 */
public class Main {

    public static void main(String[] args){
//        Resource resource = new ClassPathResource("spring/lifecycle.xml");
//        BeanFactory beanFactory = new XmlBeanFactory(resource);
//        Employee emp = (Employee)beanFactory.getBean("employee");

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/lifecycle.xml");
        Entity emp = (Entity) context.getBean("entity");
    }
}
