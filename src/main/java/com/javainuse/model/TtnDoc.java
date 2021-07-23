package com.javainuse.model;

import java.util.LinkedList;
import java.util.List;

public class TtnDoc {
    private String num;
    private String date;
    private int type;
    public List<Invoice> invoices;
    public TtnDoc (String num, String date, int type) {
        this.num = num;
        this.date = date;
        this.type = type;
        this.invoices = new LinkedList<>();
    }
    public String getNum() { return this.num; }
    public void setNum(String num) { this.num = num; }
    public String getDate() { return this.date; }
    public void setDate(String date) { this.date = date; }
    public int getType() { return this.type; }
    public void setType(int type) { this.type = type; }
}
