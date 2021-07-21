package com.javainuse.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.javainuse.model.*;
import com.javainuse.processor.MyProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// XML Data Format
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext ctx = JAXBContext.newInstance(Documents.class);
		//new Class[]{Documents.class, Surveys.class, Survey.class, Body.class, Item.class, ItemValues.class, ItemValue.class});
		xmlDataFormat.setContext(ctx);

		// JSON Data Format
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Documents.class);

		from("file:C:/inputFolder?charset=utf-8")
			.doTry()
				.unmarshal(xmlDataFormat)
				.process(new MyProcessor())
				//.log(">>> ${body}")
				.marshal(jsonDataFormat)
				.to("jms:queue:chicago")
			.doCatch(Exception.class)
				.process(exchange -> {
					Exception cause = exchange.getProperty(
						Exchange.EXCEPTION_CAUGHT, Exception.class
					);
					System.out.println(cause);
				})
			.end();
		from("jms:queue:chicago")
			.to("file:C:/temp/camel?filename=${date:now:yyyyMMdd}.json");
	}
}
