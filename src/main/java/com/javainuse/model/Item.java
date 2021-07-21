package com.javainuse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    public double SKUinnercode;
    public String SKUcode;
    public String SKUdistrcode;
    public String qsttemplaterowcode;
    public String topiccode;
    public ItemValues itemvalues;
}
