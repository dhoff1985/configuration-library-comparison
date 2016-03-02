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
        HttpResponseDecorator response = appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "heartbeat"
            response.success  = { HttpResponseDecorator resp, InputStreamReader reader ->
                assert reader.text == "heartbeat"
                resp
            }
        }

        then:
        response.status == 200
    }
}
