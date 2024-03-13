package com.prashant.lovecalculator.model;

public class LoveModel {
    private String fname;
    private String sname;
    private String percentage;
    private String result;

    public LoveModel(String fname, String sname, String percentage, String result) {
        this.fname = fname;
        this.sname = sname;
        this.percentage = percentage;
        this.result = result;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getResult() {
        return result;
    }
}
