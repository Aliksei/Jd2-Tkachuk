package com.itacademy.service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.itacademy.service")
@Import(DatabaseConfiguration.class)
public class ServiceConfiguration {

}
