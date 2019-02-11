package com.owen.techs.spring.mvc;

/**
 * Servlet initialization:
 * 1. container start up, trigger ContextLoaderListener.java
 * 2. ContainerLoaderListener create instance of XmlWebApplicationContext(webApplicationContext) and put it to ServletContext
 * 3. create servlet instances(say DispatcherServlet), several instances are allowed
 * 4. each servlet has their own applicationContext which will be put in ServletContext too
 *
 * note:
 * 1. ContextLoaderListener -> ServletContextListener -> EventListener
 * 2. webApplicationContext is shared for all the servlet, mainly shared beans like connection to db etc.
 *    applicationContext is private.
 *
 * DispatcherServlet process:
 * 1. request to server and mapped to specific servlet, say DispatcherServlet(DS)
 * 2. DS get corresponding Handler(Controller) and interceptors from HandlerMapping to wrap as HandlerExecutionChain(HEC)
 * 3. DS call HandlerAdaptor to get specific controllers and get result of controller(ModelAndView)
 * 4. HandlerAdaptor return ModelAndView to DS
 * 5. DS call ViewResolver to process the ModelAndView to View
 */
public class Main {
}
