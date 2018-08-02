package com.base.comfig;
import java.io.IOException;

import javax.sql.DataSource;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration{

	@Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            DataSource dataSource,
            PlatformTransactionManager transactionManager,
            SpringAsyncExecutor springAsyncExecutor) throws IOException {
		SpringProcessEngineConfiguration baseSpringProcessEngineConfiguration = baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
		baseSpringProcessEngineConfiguration.setCreateDiagramOnDeploy(false);
		baseSpringProcessEngineConfiguration.setDeploymentResources(null);
		baseSpringProcessEngineConfiguration.setActivityFontName("宋体");
		baseSpringProcessEngineConfiguration.setLabelFontName("宋体");
		baseSpringProcessEngineConfiguration.setXmlEncoding("UTF-8");
		baseSpringProcessEngineConfiguration.setAsyncExecutorActivate(false);
		baseSpringProcessEngineConfiguration.setCreateDiagramOnDeploy(false);
		//baseSpringProcessEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
		return baseSpringProcessEngineConfiguration;
    }
	
}
