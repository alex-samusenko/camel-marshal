package com.javainuse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "itemvalue")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemValue {
    public String answerstr;
    public double answernumber;
    public Date answerdate;
    public String answercode;
}
