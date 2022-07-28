package com.example.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAOdata {
    private DatabaseReference databaseReference;

    /**
     * This class connects to the firebase database
     */
    public DAOdata(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Data.class.getSimpleName());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    long count = ds.child("Data").getChildrenCount();
                    Log.d("TAG", "Count: " + count);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    /**
     *
     * @param data b
     * add data into the database
     */
    public void add(Data data){

        databaseReference.push().setValue(data);
    }
}
