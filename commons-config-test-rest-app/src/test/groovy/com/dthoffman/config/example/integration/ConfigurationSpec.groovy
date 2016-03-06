package com.dthoffman.config.example.integration

import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseException
import groovyx.net.http.Method

/**
 * Created by dhoffman on 3/5/16.
 */
class ConfigurationSpec extends BaseIntegrationSpec {

    def "sets/gets config"() {
        when:
        StringReader responseBody = appHttpBuilder.request(Method.PUT, ContentType.TEXT) {
            uri.path = "/config/foo"
            body = "baz"
        }

        then:
        responseBody.text == "bar"

        when:
        responseBody = appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "/config/foo"
        }

        then:
        responseBody.text == "baz"

        cleanup:
        appHttpBuilder.request(Method.DELETE, ContentType.TEXT) {
            uri.path = "/config"
        }
    }

    def "gets config"() {
        when:
        StringReader responseBody = appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "/config/foo"
        }

        then:
        responseBody.text == "bar"
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
