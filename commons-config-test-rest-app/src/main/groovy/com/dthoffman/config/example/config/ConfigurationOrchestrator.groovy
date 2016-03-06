package com.dthoffman.config.example.config

import org.apache.commons.configuration.CompositeConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

import java.lang.reflect.Field

/**
 * Created by dhoffman on 3/5/16.
 */
@Component
class ConfigurationOrchestrator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CompositeConfiguration configuration

    @Override
    void onApplicationEvent(ContextRefreshedEvent event) {
        event.applicationContext.getBeansOfType(Object, false, false).each { String beanName, Object bean ->
            bean.getClass().getDeclaredFields().each { Field field ->
                Config config = field.getDeclaredAnnotation(Config)
                if(config) {
                    String propertyName = config.value()
                    bean."${field.getName()}" = configuration.getProperty(propertyName)
                }
            }
        }
    }
}
