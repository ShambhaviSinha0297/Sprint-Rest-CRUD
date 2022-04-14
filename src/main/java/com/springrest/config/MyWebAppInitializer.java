package com.springrest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new  Class[] {HibernateConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return  null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"} ;
	}

}
