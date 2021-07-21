package com.javainuse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "surveys")
@XmlAccessorType(XmlAccessType.FIELD)
public class Surveys {
    public List<Survey> survey;
}
