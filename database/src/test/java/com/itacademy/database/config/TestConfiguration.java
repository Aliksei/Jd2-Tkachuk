package com.itacademy.database.config;

import com.itacademy.database.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfiguration.class)
public class TestConfiguration {
}
