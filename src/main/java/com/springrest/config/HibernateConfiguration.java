package com.springrest.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.springrest.model.Book;

 


@Configuration
@EnableTransactionManagement
@ComponentScan("com.springrest")
@EnableWebMvc
public class HibernateConfiguration {
	
	@Bean
	public  DataSource  getDataSource() {
		BasicDataSource dataSource =  new  BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root123");
		dataSource.setUrl("jdbc:mysql://localhost:3306/boschdb?useSSL=false");
		return  dataSource;
	
	}
	
	@Bean
	@Autowired
	public  LocalSessionFactoryBean   getSessionFactory(DataSource  dataSource) {
			LocalSessionFactoryBean   sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		Properties   properties = new  Properties();
		properties.put("hibernate.hbm2ddl.auto", "create");
		
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		// version hibernate 5.2.0.Final
		//prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		// version 5.4.0.Final
		// prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");

		sessionFactory.setHibernateProperties(properties);
		
		sessionFactory.setAnnotatedClasses(Book.class);
		return  sessionFactory;
	
	}
	
	@Bean
	@Autowired
	public  HibernateTransactionManager  getTransactionManager(SessionFactory  sessionFactory) {
		HibernateTransactionManager   transactionManager = new HibernateTransactionManager(sessionFactory);
		 return  transactionManager;
		
	}
	
	

}
