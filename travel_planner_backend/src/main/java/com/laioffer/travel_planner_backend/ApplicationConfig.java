package com.laioffer.travel_planner_backend;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class ApplicationConfig {
		
		@Bean(name = "sessionFactory")
		public LocalSessionFactoryBean sessionFactory() {
				LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
				sessionFactory.setDataSource(dataSource());
				sessionFactory.setPackagesToScan("onlineShop.entity");
				sessionFactory.setHibernateProperties(hibernateProperties());
				return sessionFactory;
		}
		
		@Bean(name = "dataSource")
		public DataSource dataSource() {
				Properties prop = new Properties();
				String propFileName = "config.properties";
				String username = prop.getProperty("user");
				String password = prop.getProperty("password");
				String url = prop.getProperty("url");
				
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl(url);
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
