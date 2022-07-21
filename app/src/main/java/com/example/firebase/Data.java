package com.example.firebase;

public class Data {
    private String ss;
    private String ds;
    private String hb;
    private String comment;
    private String date;
    private String time;

    public Data(){}

    public Data(String ss, String ds, String hb, String comment,String date,String time) {
        this.ss = ss;
        this.ds = ds;
        this.hb = hb;
        this.comment = comment;
        this.date=date;
        this.time=time;
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
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

//    @Override
    public int compareTo(Data o) {
        return this.ss.compareTo(o.ss);
    }
}

