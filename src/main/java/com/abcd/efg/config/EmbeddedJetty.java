package com.abcd.efg.config;

import java.io.IOException;

import org.eclipse.jetty.rewrite.handler.RewriteHandler;
import org.eclipse.jetty.rewrite.handler.RewriteRegexRule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.eclipse.jetty.server.Handler;


public class EmbeddedJetty {
	private static final Logger logger = LoggerFactory.getLogger(EmbeddedJetty.class);
    private static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/abcapp";
    private static final String CONFIG_LOCATION = "com.abcd.efg.config";
    private static final String MAPPING_URL = "/*";
    private static final String DEFAULT_PROFILE = "prod";

    public static void main(String[] args) throws Exception {
        new EmbeddedJetty().startJetty(getPortFromArgs(args));
        
    }

    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }
        logger.debug("No server port configured, falling back to {}", DEFAULT_PORT);
        return DEFAULT_PORT;
    }

    private void startJetty(int port) throws Exception {
        logger.debug("Starting server at port {}", port);
        Server server = new Server(port);
        HandlerList handlers = new HandlerList();
        
      
        RewriteHandler rewrite = new RewriteHandler();
        rewrite.setRewriteRequestURI(true);
        rewrite.setRewritePathInfo(false);        
        rewrite.setOriginalPathAttribute("requestedPath");
        
       
        RewriteRegexRule regexrule = new RewriteRegexRule();
        regexrule.setRegex("/abcapp/(.)([^/]*)");
        regexrule.setReplacement("/abcapp/");
        regexrule.setTerminating(true);
        rewrite.addRule(regexrule);
        
        ServletContextHandler webapp = getServletContextHandler(getContext());     
        handlers.setHandlers(new Handler [] {rewrite, webapp});
        server.setHandler(handlers);
        server.start();
        logger.info("Server started at port {}", port);
        server.join();
    }

    private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase("src/main/webapp/");
        contextHandler.setWelcomeFiles(new String[]{ "/abcapp/static-resources/index.html" });
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        
       // context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }



}
