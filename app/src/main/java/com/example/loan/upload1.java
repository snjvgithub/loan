package com.example.loan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class upload1 extends AppCompatActivity {
    EditText names,jobtypes,income,adds;
    Button save,com;
    FirebaseDatabase fbd=FirebaseDatabase.getInstance();

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload1);
        names=findViewById(R.id.name);
        sp= (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(upload1.this);
        DatabaseReference ref=fbd.getReference(sp.getString("loginname","no name"));
        jobtypes=findViewById(R.id.jobtype);
        income=findViewById(R.id.incomes);
        adds=findViewById(R.id.address);
        save=findViewById(R.id.save);
        com=findViewById(R.id.com);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=names.getText().toString();
                String j=jobtypes.getText().toString();
                String i=income.getText().toString();
                String a=adds.getText().toString();
                ref.child("name").setValue(n);
                ref.child("add").setValue(a);
                ref.child("income").setValue(i);
                ref.child("job type").setValue(j);
                Toast.makeText(getApplicationContext(),"uploaded",Toast.LENGTH_SHORT).show();
            }
        });

        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"completed",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(upload1.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}