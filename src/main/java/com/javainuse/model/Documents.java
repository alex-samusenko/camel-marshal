package com.javainuse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "documents")
@XmlAccessorType(XmlAccessType.FIELD)
public class Documents {
    public Surveys surveys;
}
