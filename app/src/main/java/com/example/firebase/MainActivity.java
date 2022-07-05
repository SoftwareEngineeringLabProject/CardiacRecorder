package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText ss=findViewById(R.id.ss);
        EditText ds=findViewById(R.id.ds);
        EditText hb=findViewById(R.id.hb);
        EditText comment=findViewById(R.id.comment);
        Button btn=findViewById(R.id.submit);
        DAOdata dao=new DAOdata();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data=new Data(ss.getText().toString(),ds.getText().toString(),hb.getText().toString(),comment.getText().toString());
                dao.add(data);
                Intent intent=new Intent(MainActivity.this,home.class );
                startActivity(intent);
            }
        });
    }
}