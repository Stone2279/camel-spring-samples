package de.stone.camel.routes;

import javax.xml.bind.JAXBContext;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

import de.stone.camel.entities.SomeData;
import de.stone.camel.processor.SampleDataProcessor;

@Component
public class XaSampleRoute extends RouteBuilder
{
	@Override
	public void configure() throws Exception 
	{
		
		// prepare JAXB for un-/marshalling 'someData' XML's
		JAXBContext jaxbContext = JAXBContext.newInstance(SomeData.class);
		DataFormat jaxb = new JaxbDataFormat(jaxbContext);
		
		from("jms:queue:{{receivemsg.queue.name}}?transacted=true")   
		.routeId("jms-receive-tx-route")
        .autoStartup(true)
        
        .unmarshal(jaxb)	// try to unmarshal the received JMS message
        .bean(SampleDataProcessor.class, "process")
        .to("sql:{{sql.insert.somedata}}")
        .choice()
    		.when(simple("${body.someString} == 'Fail'"))
    			.throwException(new IllegalArgumentException("Someone told me to fail"))
    	.end();
	}
}
