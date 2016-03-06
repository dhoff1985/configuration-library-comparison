package com.dthoffman.config.example.resource

import spock.lang.Specification

import javax.ws.rs.core.Response

/**
 * Created by dhoffman on 3/5/16.
 */
class FooResourceSpec extends Specification {

    FooResource fooResource = new FooResource(foo: "bar")

    def "foo" () {
        when:
        Response response = fooResource.foo()

        then:
        response.status == 200
        response.entity == "bar"
    }
}
