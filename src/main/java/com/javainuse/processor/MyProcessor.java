package com.javainuse.processor;

import com.javainuse.model.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;

import java.util.Date;

public class MyProcessor implements Processor {

	public void process(Exchange exchange) {
		Documents documents = exchange.getIn().getBody(Documents.class);

		/*DefaultMessage message = new DefaultMessage(exchange);
		message.setHeaders(exchange.getIn().getHeaders());
		message.setBody(TtnDoc.class);

		exchange.setMessage(message);*/
	}

}
