package com.example.gpacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSemester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);

        Button add,back;
        final EditText sem;
        final DBHadler dbHadler;
        final Context context = this;

        dbHadler = new DBHadler(context);

        sem = findViewById(R.id.editSemName);
        add = findViewById(R.id.btnAddSemester_AddPage);
        back = findViewById(R.id.btnAddSemBack);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getsem = sem.getText().toString();

                GPA gpa = new GPA(getsem);
                dbHadler.addSemester(gpa);
                Toast t = Toast.makeText(getApplicationContext(),"Semestaer Adding Success",Toast.LENGTH_SHORT);
                t.show();
                startActivity(new Intent(getApplicationContext(),MainPage.class));


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MainPage.class));
            }
        });
    }
}
