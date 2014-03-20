package com.it.epolice.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

public class IDEEmbeddedServer {

    private final static String ACCESS_LOG_FILE = "environment.access.log";
    private final static String PORT = "environment.port";


    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.parseInt("8080"));

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        ProtectionDomain protectionDomain = IDEEmbeddedServer.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        String binFolder = location.toExternalForm();
        String ctxFolder = binFolder.substring(0, binFolder.lastIndexOf("/")) + "/src/main/webapp";
		webAppContext.setResourceBase("d:/it/Code/it-feeder/src/main/webapp");
//		webAppContext.setDescriptor(ctxFolder + "/WEB-INF/web.xml");

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