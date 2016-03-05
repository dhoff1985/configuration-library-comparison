package com.dthoffman.config.example.web.spring

import com.sun.jersey.spi.spring.container.servlet.SpringServlet
import org.springframework.stereotype.Component
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext

import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration

/**
 * Created by dhoffman on 2/24/16.
 */
@Component
class AppInitializer implements WebApplicationInitializer {

    @Override
    void onStartup(ServletContext servletContext) throws ServletException {
        SpringServlet springServlet = new SpringServlet()
        ServletRegistration registration = servletContext.addServlet('spring-servet', springServlet)
        registration.addMapping("/*")
        registration.setInitParameter('com.sun.jersey.config.property.packages', 'com.dthoffman.config.example.web')
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext))
    }
}
