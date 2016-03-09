package com.abcd.efg.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * A class that extends AbstractAnnotationConfig ... is used automatically to configure DispatcherServlet
 * Because Servlet 3.0 looks for classes implementing the interface ServletContainerInitializer
 *
 */
public class AbcAppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	//Defines beans for configuring app context created by ContextLoaderListener
	//like data tier config, the backend
	//
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	//Defines beans for configuring the dispatcher servlet application context
	//Dispatcher servlet is expected to load beans like controllers and view resolvers
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
}