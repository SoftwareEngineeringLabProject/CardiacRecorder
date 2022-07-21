package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        EditText ss=findViewById(R.id.ss);
        EditText ds=findViewById(R.id.ds);
        EditText hb=findViewById(R.id.hb);
        EditText comment=findViewById(R.id.comment);
        EditText date=findViewById(R.id.date);
        EditText time=findViewById(R.id.time);
        Button btn=findViewById(R.id.submit);
        DAOdata dao=new DAOdata();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tss=ss.getText().toString();
                String tds=ds.getText().toString();
                String thb=hb.getText().toString();
                if(tss.matches("[0-9]+") && tds.matches("[0-9]+") && thb.matches("[0-9]+")) {
                    Data data = new Data(ss.getText().toString(), ds.getText().toString(), hb.getText().toString(), comment.getText().toString(), date.getText().toString()
                            , time.getText().toString());
                    dao.add(data);
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Values", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}