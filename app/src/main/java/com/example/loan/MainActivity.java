package com.example.loan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Float4;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RadioGroup rg;
    RadioButton rb;

    Button next,logout;
    SharedPreferences sp;
    FirebaseDatabase fbd=FirebaseDatabase.getInstance();
    FirebaseAuth mAuth;
    public List<String> l=new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp= (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        DatabaseReference ref=fbd.getReference(sp.getString("loginname","no name"));
        rg=findViewById(R.id.rg);
        mAuth = FirebaseAuth.getInstance();
        //sp=findViewById(R.id.sp);
        logout=findViewById(R.id.logout);
        next=findViewById(R.id.main_next);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                rb=group.findViewById(checkedId);
                String loan= (String) rb.getText();
                ref.child("Loan type").setValue(loan);
                Toast.makeText(getApplicationContext(),loan,Toast.LENGTH_LONG).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(MainActivity.this,upload.class);
               startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i=new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });

        l.add(0, "sanjeev");
        l.add(1,"ji");
        System.out.println(l);

        ArrayAdapter ad= new ArrayAdapter(this, android.R.layout.simple_spinner_item, l);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp.setAdapter(ad);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sp= (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        DatabaseReference ref=fbd.getReference(sp.getString("loginname","no name"));
        Toast.makeText(getApplicationContext(), "hi"+String.valueOf(l.get(position)),Toast.LENGTH_SHORT).show();
        ref.child("bank").setValue(l.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "hil",Toast.LENGTH_SHORT).show();
    }
}