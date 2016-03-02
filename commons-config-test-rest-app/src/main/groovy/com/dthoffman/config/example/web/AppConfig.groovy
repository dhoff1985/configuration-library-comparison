package com.dthoffman.config.example.web

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by dhoffman on 2/27/16.
 */
@Configuration
@ComponentScan(basePackages = ["com.dthoffman.config.example.web"])
class AppConfig {
}
