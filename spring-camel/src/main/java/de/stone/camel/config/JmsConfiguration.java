package de.stone.camel.config;

import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JmsConfiguration 
{
	
	@Bean
	public JmsComponent jms(ConnectionFactory xaJmsConnectionFactory, PlatformTransactionManager jtaTansactionManager)
	{
		JmsComponent jms = JmsComponent.jmsComponentTransacted(xaJmsConnectionFactory, jtaTansactionManager);
		
		return jms;
	}
	
	@Bean
	
	public JmsComponent jmsNoXa(ConnectionFactory nonXaJmsConnectionFactory)
	{
		JmsComponent jms = JmsComponent.jmsComponentTransacted(nonXaJmsConnectionFactory);
		
		return jms;
	}
	
	@Bean(name = "PROPAGATION_REQUIRED")
	public SpringTransactionPolicy propogationRequired(PlatformTransactionManager jtaTransactionManager)
	{
		SpringTransactionPolicy propagationRequired = new SpringTransactionPolicy();
		propagationRequired.setTransactionManager(jtaTransactionManager);
		propagationRequired.setPropagationBehaviorName("PROPAGATION_REQUIRED");
		return propagationRequired;
	}
}
