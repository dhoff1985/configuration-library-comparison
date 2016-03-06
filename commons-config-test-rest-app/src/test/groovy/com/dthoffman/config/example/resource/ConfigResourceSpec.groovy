package com.dthoffman.config.example.resource

import com.dthoffman.config.example.resource.ConfigResource
import com.dthoffman.config.example.config.MemoryConfiguration
import org.apache.commons.configuration.CompositeConfiguration
import spock.lang.Specification

import javax.ws.rs.core.Response

class ConfigResourceSpec extends Specification {

    ConfigResource configResource = new ConfigResource(
            configuration: Mock(CompositeConfiguration),
            memoryConfiguration: Mock(MemoryConfiguration))

    def "sets config"() {
        when:
        Response response = configResource.setConfig("foo", "baz")

        then:
        response.entity == "bar"
        response.status == 200
        1 * configResource.configuration.getProperty("foo") >> "bar"
        1 * configResource.memoryConfiguration.setProperty("foo", "baz")
    }

    def "gets config"() {
        when:
        Response response = configResource.getConfig("foo")

        then:
        response.entity == "bar"
        response.status == 200
        1 * configResource.configuration.getProperty("foo") >> "bar"
    }

    def "gets config - returns 404"() {
        when:
        Response response = configResource.getConfig("foo")

        then:
        response.entity == null
        response.status == 404
        1 * configResource.configuration.getProperty("foo") >> null
    }

    def "resets config"() {
        when:
        Response response = configResource.resetConfig()

        then:
        response.status == 200
        1 * configResource.memoryConfiguration.clear()
    }
}
