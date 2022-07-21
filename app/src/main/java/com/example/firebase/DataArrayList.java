package com.example.firebase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataArrayList {
    public List<Data> datalist=new ArrayList<>();

    public void addData(Data c){
        if(datalist.contains(c)){
            throw new IllegalArgumentException();
        }
        datalist.add(c);
    }

    public List<Data>getDatas(){
        List<Data>  newdatalist=datalist;
//        Collections.sort(newdatalist);
        return newdatalist;
    }

    public List<Data>getdatalist(int tmp){
        List<Data>  newdatalist=datalist;
        return newdatalist;
    }

    public void delete(Data c){
        List<Data> newdatalist= datalist;
        if(newdatalist.contains(c)){
            datalist.remove(c);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public int count(){
        return datalist.size();
    }

}
