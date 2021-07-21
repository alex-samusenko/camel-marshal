package com.javainuse.processor;

import com.javainuse.model.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Documents documents = exchange.getIn().getBody(Documents.class);
	}

}
