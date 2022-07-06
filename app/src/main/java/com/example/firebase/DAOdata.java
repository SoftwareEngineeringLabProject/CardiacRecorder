package com.example.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOdata {
    private DatabaseReference databaseReference;
    public DAOdata(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Data.class.getSimpleName());

    }
    public void add(Data data){

        databaseReference.push().setValue(data);
    }
}
