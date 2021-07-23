package com.javainuse.processor;

import com.javainuse.model.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.LinkedList;

public class ParseXml implements Processor {
    public void process(Exchange exchange) {
        TtnList ttnList = new TtnList();
        ttnList.ttnDocs = new LinkedList<>();
        for (Survey survey : exchange.getIn().getBody(Documents.class).surveys.survey) {
            String ttnNum = null;
            String ttnDate = null;
            TtnDoc ttnDoc = null;
            for (Item item : survey.body.item) {
                if (item.SKUcode.equals("ttnnum")) {
                    ttnNum = item.itemvalues.itemvalue.answerstr;
                } else if (item.SKUcode.equals("ttndate")) {
                    ttnDate = item.itemvalues.itemvalue.answerdate;
                } else if (item.topiccode.equals("returninvoicenumber")) {
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
    }
}