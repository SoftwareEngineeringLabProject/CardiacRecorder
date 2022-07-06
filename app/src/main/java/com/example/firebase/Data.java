package com.example.firebase;

public class Data {
    private String ss;
    private String ds;
    private String hb;
    private String comment;

    public Data(){}

    public Data(String ss, String ds, String hb, String comment) {
        this.ss = ss;
        this.ds = ds;
        this.hb = hb;
        this.comment = comment;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getHb() {
        return hb;
    }

    public void setHb(String hb) {
        this.hb = hb;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

