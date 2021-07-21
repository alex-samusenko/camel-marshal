package com.javainuse.processor;

import com.javainuse.model.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Date;

public class MyProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Documents documents = exchange.getIn().getBody(Documents.class);
		String ttnNum;
		Date ttnDate;
		for (Survey survey : documents.surveys.survey) {
			int docQty = 0;
			for (Item item : survey.body.item) {
				if (item.SKUcode == "ttnnum") {
					ttnNum = item.itemvalues.itemvalue.answerstr;
				}
				else if (item.SKUcode == "ttndate") {
					ttnDate = item.itemvalues.itemvalue.answerdate;
				}
				else if (item.topiccode == "returninvoicenumber") {
					docQty++;
				}
			}
		}
	}

}
