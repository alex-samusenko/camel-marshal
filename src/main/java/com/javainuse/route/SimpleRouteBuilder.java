package com.javainuse.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.javainuse.model.*;

import java.util.LinkedList;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// XML Data Format
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext ctx = JAXBContext.newInstance(Documents.class);
		//new Class[]{Documents.class, Surveys.class, Survey.class, Body.class, Item.class, ItemValues.class, ItemValue.class});
		xmlDataFormat.setContext(ctx);

		// JSON Data Format
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat();

		from("file:C:/inputFolder?charset=utf-8")
			.doTry()
				.unmarshal(xmlDataFormat)
				//.process(new MyProcessor())
				//.log(">>> ${body}")
				.process(exchange -> {
					TtnList ttnList = new TtnList();
					ttnList.ttnDocs = new LinkedList<>();
					for (Survey survey : exchange.getIn().getBody(Documents.class).surveys.survey) {
						String ttnNum = null;
						String ttnDate = null;
						TtnDoc ttnDoc = null;
						for (Item item : survey.body.item) {
							if (item.SKUcode.equals("ttnnum")) {
								ttnNum = item.itemvalues.itemvalue.answerstr;
							}
							else if (item.SKUcode.equals("ttndate")) {
								ttnDate = item.itemvalues.itemvalue.answerdate;
							}
							else if (item.topiccode.equals("returninvoicenumber")) {
								if (ttnNum != null && ttnDate != null) {
									if (ttnDoc == null) {
										ttnDoc = new TtnDoc(ttnNum, ttnDate, 101);
										ttnList.ttnDocs.add(ttnDoc);
									}
									ttnDoc.invoices.add(new Invoice(item.SKUcode));
								}
							}
						}
					}
					exchange.getIn().setBody(ttnList);
				})
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
