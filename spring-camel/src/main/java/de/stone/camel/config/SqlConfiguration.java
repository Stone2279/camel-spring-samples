package de.stone.camel.config;

import javax.sql.DataSource;

import org.apache.camel.component.sql.SqlComponent;
import org.springframework.boot.jta.narayana.NarayanaDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlConfiguration 
{
	@Bean
	public SqlComponent sql(DataSource dataSource) 
	{
		SqlComponent rc = new SqlComponent();
		rc.setDataSource(dataSource);
		return rc;
	} 
}
