package com.laioffer.travel_planner_backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
// @EnableWebMvc
public class ApplicationConfig {
		
		@Bean(name = "sessionFactory")
		public LocalSessionFactoryBean sessionFactory() throws IOException {
				LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
				sessionFactory.setDataSource(dataSource());
				sessionFactory.setPackagesToScan("com.laioffer.travel_planner_backend.entity");
				sessionFactory.setHibernateProperties(hibernateProperties());
				return sessionFactory;
		}
		
		@Bean(name = "dataSource")
		public DataSource dataSource() throws IOException {
				Properties prop = new Properties();
				String propFileName = "config.properties";
				
				InputStream inputStream = ApplicationConfig.class.getClassLoader().getResourceAsStream(propFileName);
				prop.load(inputStream);
				String username = prop.getProperty("user");
				String password = prop.getProperty("password");
				String rds_ip = prop.getProperty("rds_ip");
				String urlTemplate = "jdbc:mysql://%s:3306/travelplanner2?createDatabaseIfNotExist=true&serverTimezone=UTC";

				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl(String.format(urlTemplate, rds_ip));
				dataSource.setUsername(username);
				dataSource.setPassword(password);
				
				return dataSource;
		}
		
		
		private final Properties hibernateProperties() {
				Properties hibernateProperties = new Properties();
				hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
				hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
				hibernateProperties.setProperty("hibernate.show_sql", "true");
				return hibernateProperties;
		}
}
