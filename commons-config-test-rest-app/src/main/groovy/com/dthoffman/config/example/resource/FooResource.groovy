package com.dthoffman.config.example.resource

import com.dthoffman.config.example.config.Config
import org.springframework.stereotype.Component

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Response

/**
 * Created by dhoffman on 3/5/16.
 */
@Component
@Path("/foo")
class FooResource {

    @Config("foo")
    String foo

    @GET
    Response foo() {
        Response.ok(foo).build()
    }
}
