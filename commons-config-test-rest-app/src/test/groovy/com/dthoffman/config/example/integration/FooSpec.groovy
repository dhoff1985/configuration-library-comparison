package com.dthoffman.config.example.integration

import groovyx.net.http.ContentType
import groovyx.net.http.Method

/**
 * Created by dhoffman on 3/5/16.
 */
class FooSpec extends BaseIntegrationSpec {

    def "foo service returns bar"() {
        when:
        StringReader responseBody = appHttpBuilder.request(Method.GET, ContentType.TEXT) {
            uri.path = "/foo"
        }

        then:
        responseBody.text == "bar"
    }

}
