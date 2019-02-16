package de.stone.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JmsSampleRoute extends RouteBuilder
{

	@Override
	public void configure() throws Exception 
	{
		// Receive messages from JMS queue
		from("jmsNoXa:queue:{{receivemsg.queue.name}}")   
		.routeId("jms-receive-route")
        .autoStartup(false)
        
        .to("log:jms?showAll=true");
		
		
		
		// Send messages to JMS queue
		from("timer://send-message-timer?fixedRate=true&period=5000")   // run every 5 seconds
    	.routeId("send-message-route")
        .autoStartup(false)
        
        .setBody(constant("Hello from Camel"))
        .to("jmsNoXa:queue:{{receivemsg.queue.name}}?jmsMessageType=Text");
	}

}
