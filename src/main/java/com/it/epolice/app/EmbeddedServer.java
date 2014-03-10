package com.it.epolice.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class EmbeddedServer {

    private final static String ACCESS_LOG_FILE = "environment.access.log";
    private final static String PORT = "environment.port";


    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.parseInt("8081"));

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        ProtectionDomain protectionDomain = EmbeddedServer.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        webAppContext.setWar(location.toExternalForm());

        server.setHandler(webAppContext);

//        NCSARequestLog log = new NCSARequestLog(readEnvironmentVariable(ACCESS_LOG_FILE));
//        RequestLogHandler requestLog = new RequestLogHandler();
//        requestLog.setRequestLog(log);
//        webAppContext.setHandler(requestLog);

        server.start();
        server.join();

    }

    private String readEnvironmentVariable(String env) {
        String propertyFileName = System.getProperty(env);
        if (propertyFileName == null) {
            throw new IllegalStateException("Must specify the property file via -D" + env);
        }
        return propertyFileName;
    }

}