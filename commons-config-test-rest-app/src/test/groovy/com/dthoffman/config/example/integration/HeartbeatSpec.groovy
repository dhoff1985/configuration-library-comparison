package com.dthoffman.config.example.integration

import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.Method

/**
 * Created by dhoffman on 2/24/16.
 */
class HeartbeatSpec extends BaseIntegrationSpec {

    def "gets heartbeat"() {
        when:
        StringReader response = appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "heartbeat"
        }

        then:
        response.text == "heartbeat"
    }
}
