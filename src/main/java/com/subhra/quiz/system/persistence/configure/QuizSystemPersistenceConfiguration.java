package com.subhra.quiz.system.persistence.configure;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableJpaRepositories(basePackages = "com.subhra.quiz.system.persistence.repository", entityManagerFactoryRef="entityManagerFactoryBean")
public class QuizSystemPersistenceConfiguration {
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		//Properties properties = new Properties();
	      //properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		hibernateJpaVendorAdapter.setShowSql(true);
		//localContainerEntityManagerFactoryBean.setJpaProperties(properties);
		
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.subhra.quiz.system.persistence.model");
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(driver);
		datasource.setUsername(username);
		datasource.setUrl(url);
		datasource.setPassword(password);
		return datasource;
	}
	
	@Bean("transactionManager")
	public PlatformTransactionManager platformTransactionManager() {
		JpaTransactionManager platformTransactionManager = new JpaTransactionManager();
		platformTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return platformTransactionManager;
	}
}
