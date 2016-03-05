package com.dthoffman.config.example.web.spring

import com.dthoffman.config.example.web.MemoryConfiguration
import org.apache.commons.configuration.CompositeConfiguration
import org.apache.commons.configuration.PropertiesConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by dhoffman on 2/27/16.
 */
@Configuration
@ComponentScan(basePackages = ["com.dthoffman.config.example.web"])
class AppConfig {
    @Bean
    CompositeConfiguration configuration(MemoryConfiguration memoryConfiguration) {
        PropertiesConfiguration propertiesConfiguration =  new PropertiesConfiguration(Thread.currentThread().contextClassLoader.getResource("configuration.properties"))
        return new CompositeConfiguration([memoryConfiguration, propertiesConfiguration])
    }

    @Bean
    MemoryConfiguration memoryConfiguration() {
        return new MemoryConfiguration()
    }
}
