package com.itacademy.spring;

import com.itacademy.service.configuration.ServiceConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextLoader {

    private static final ApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfiguration.class);

    public static Object getBean(String s){
        return context.getBean(s);
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

}
