package com.dthoffman.config.example.web

import org.springframework.stereotype.Component

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Response

/**
 * Created by dhoffman on 2/24/16.
 */
@Component
@Path("/heartbeat")
class HeartBeatResource {

    @GET
    Response heartBeat() {
        Response.ok("heartbeat").build()
    }

}
