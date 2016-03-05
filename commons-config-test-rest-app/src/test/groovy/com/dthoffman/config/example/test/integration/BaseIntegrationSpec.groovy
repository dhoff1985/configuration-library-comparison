package com.dthoffman.config.example.test.integration

import groovyx.net.http.HTTPBuilder
import org.eclipse.jetty.annotations.AnnotationConfiguration
import org.eclipse.jetty.plus.webapp.EnvConfiguration
import org.eclipse.jetty.plus.webapp.PlusConfiguration
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.Configuration
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.webapp.WebInfConfiguration
import spock.lang.Specification

class BaseIntegrationSpec extends Specification {

    String getAppUri() {
        server.getURI()
    }

    static HTTPBuilder _appHttpBuilder
    HTTPBuilder getAppHttpBuilder() {
        if(!_appHttpBuilder) {
            _appHttpBuilder = new HTTPBuilder(appUri)
        }
        return _appHttpBuilder
    }

    static Server _server
    Server getServer() {
        if(!_server) {
            Server server = new Server(8080)
            WebAppContext webAppContext = new WebAppContext()
            webAppContext.setContextPath("/");
            webAppContext.setResourceBase(File.createTempDir().absolutePath);
            webAppContext.setConfigurations([new AnnotationConfiguration(), new WebInfConfiguration(), new EnvConfiguration(), new PlusConfiguration()] as Configuration[])
            webAppContext.setAttribute('org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern',".*/test-classes/.*,.*/classes/.*")
            server.setHandler(webAppContext)
            server.start()
            _server = server
        }
        return _server
    }

}
