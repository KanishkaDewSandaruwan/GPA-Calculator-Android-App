package com.example.gpacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DBHadler dbHadler;

        Button exit, next;
        final EditText name,university,fac,uname,pass,re_pass;
        final Context context = this;
        dbHadler = new DBHadler(context);

        exit = findViewById(R.id.btnEx);
        next = findViewById(R.id.btnNext);
        name = findViewById(R.id.editName);
        university = findViewById(R.id.editUni);
        fac = findViewById(R.id.editFac);
        uname = findViewById(R.id.edituname);
        pass = findViewById(R.id.editLogUname);
        re_pass = findViewById(R.id.editRepass);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getText().toString();
                String getUniversity = university.getText().toString();
                String getFac = fac.getText().toString();
                String getuname = uname.getText().toString();
                String getpass = pass.getText().toString();
                String getrepass = re_pass.getText().toString();


                if (name.getText().toString().isEmpty()){
                    Toast t = Toast.makeText(context,"Please enter Your Name",Toast.LENGTH_SHORT);
                    t.show();
                }else if (university.getText().toString().isEmpty()){
                    Toast t = Toast.makeText(context,"Please enter Your University",Toast.LENGTH_SHORT);
                    t.show();
                }else if (fac.getText().toString().isEmpty()){
                    Toast t = Toast.makeText(context,"Please enter Your Faculty",Toast.LENGTH_SHORT);
                    t.show();
                }else if (uname.getText().toString().isEmpty()){
                    Toast t = Toast.makeText(context,"Please enter User Name",Toast.LENGTH_SHORT);
                    t.show();
                }else if (pass.getText().toString().isEmpty()){
                    Toast t = Toast.makeText(context,"Please enter Password",Toast.LENGTH_SHORT);
                    t.show();
                }else if (re_pass.getText().toString().isEmpty()){
                    Toast t = Toast.makeText(context,"Please enter Re-Password",Toast.LENGTH_SHORT);
                    t.show();
                }else if (getpass.equals(getrepass)){
                    Student student = new Student(getName,getUniversity,getFac,getuname,getpass);
                    dbHadler.addStudent(student);
                    Intent i = new Intent(context,MainActivity.class);
                    startActivity(i);
                    Toast t = Toast.makeText(context,"Your Registration Success",Toast.LENGTH_SHORT);
                    t.show();
                }else{
                    Toast t = Toast.makeText(context,"Passwords is not Match",Toast.LENGTH_SHORT);
                    t.show();
                }



            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }
}
