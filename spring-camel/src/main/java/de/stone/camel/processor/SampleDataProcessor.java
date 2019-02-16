package de.stone.camel.processor;

import org.apache.camel.Exchange;

import de.stone.camel.entities.SomeData;

public class SampleDataProcessor
{
	public void process(Exchange exchange)
	{
		SomeData body = exchange.getIn().getBody(SomeData.class);
		exchange.getIn().setHeader("someString", body.getSomeString());
		exchange.getIn().setHeader("someInt", body.getSomeInt());
		exchange.getIn().setHeader("someDouble", body.getSomeDouble());
	}
}
