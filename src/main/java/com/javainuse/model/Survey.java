package com.javainuse.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "survey")
@XmlAccessorType(XmlAccessType.FIELD)
public class Survey {
    public double innercode;
    public String outercode;
    public String outerparentcode;
    public int innerparentcode;
    public double innercodefirstversion;
    public Date date;
    public double docno;
    public double printdocnum;
    public String prndocnum;
    public String creatorcode;
    public boolean deleted;
    public int routecode;
    public String employeecode;
    public String qsttemplatecode;
    public String comment;
    public int pdadocnum;
    public Date crdate;
    public String buypointcode;
    public int pdaroutecode;
    public String businessstatuscode;
    public String distributoroutercode;
    public Body body;
}
