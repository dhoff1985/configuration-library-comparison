package com.dthoffman.config.example.resource

import com.dthoffman.config.example.config.MemoryConfiguration
import org.apache.commons.configuration.CompositeConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody

import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

/**
 * Created by dhoffman on 3/5/16.
 */
@Component
@Path("/config")
class ConfigResource {

    @Autowired
    CompositeConfiguration configuration

    @Autowired
    MemoryConfiguration memoryConfiguration

    @PUT
    @Path("/{property}")
    Response setConfig(@PathParam('property') String property, @RequestBody String value) {
        String old = configuration.getProperty(property)
        memoryConfiguration.setProperty(property, value)
        return Response.ok().entity(old).build()
    }

    @GET
    @Path("/{property}")
    Response getConfig(@PathParam('property') String property) {
        Object value = configuration.getProperty(property)
        if(value == null) {
            return Response.status(404).build()
        } else {
            return Response.ok().entity(value).build()
        }
    }

    @DELETE
    Response resetConfig() {
        memoryConfiguration.clear()
        return Response.ok().build()
    }
}
