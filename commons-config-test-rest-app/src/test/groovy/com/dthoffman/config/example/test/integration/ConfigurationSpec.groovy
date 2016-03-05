package com.dthoffman.config.example.test.integration

import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.Method

/**
 * Created by dhoffman on 3/5/16.
 */
class ConfigurationSpec extends BaseIntegrationSpec {

    def "sets/gets config"() {
        when:
        String responseBody = appHttpBuilder.request(Method.PUT, ContentType.TEXT) {
            uri.path = "/config/foo"
            body = "baz"
            response.success  = { HttpResponseDecorator resp, InputStreamReader reader ->
                reader.text
            }
        }

        then:
        responseBody == "bar"

        when:
        responseBody = appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "/config/foo"
            response.success  = { HttpResponseDecorator resp, InputStreamReader reader ->
                reader.text
            }
        }

        then:
        responseBody == "baz"

        cleanup:
        appHttpBuilder.request(Method.DELETE, ContentType.TEXT) {
            uri.path = "/config"
        }
    }

    def "gets config"() {
        when:
        String responseBody = appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "/config/foo"
            response.success  = { HttpResponseDecorator resp, InputStreamReader reader ->
                reader.text
            }
        }

        then:
        responseBody == "bar"
    }

    def "gets missing config"() {
        when:
        appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "/config/missing"
        }

        then:
        HttpResponseException e = thrown(HttpResponseException)
        e.response.status == 404
    }
}
