package com.example.gpacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    Button back,change;
    EditText uname,newpass,repass;
    Context context;
    DBHadler dbHadler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        back = findViewById(R.id.btnForgetBack);
        change  = findViewById(R.id.btnForgeChange);

        uname = findViewById(R.id.editUsername);
        newpass = findViewById(R.id.editNewPassword);
        repass = findViewById(R.id.editRepassword);

        context = this;
        dbHadler = new DBHadler(context);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check;
                String getUname =  uname.getText().toString();
                String getNewPassword = newpass.getText().toString();
                String getRepass  = repass.getText().toString();

                check = dbHadler.checkUsername(getUname);

               if (uname.getText().toString().isEmpty()){
                   Toast t = Toast.makeText(context,"Please Enter Username",Toast.LENGTH_SHORT);
                   t.show();
               }else if (newpass.getText().toString().isEmpty()){
                   Toast t = Toast.makeText(context,"Please Enter New Password",Toast.LENGTH_SHORT);
                   t.show();
               }else if (repass.getText().toString().isEmpty()){
                   Toast t = Toast.makeText(context,"Please Enter Re Password",Toast.LENGTH_SHORT);
                   t.show();
               }else if (check ==true){
                    if (getNewPassword.equals(getRepass)){

                        dbHadler.updatePassword(getUname,getNewPassword);
                        startActivity(new Intent(context,MainActivity.class));
                        Toast t = Toast.makeText(context,"Password Change Success",Toast.LENGTH_SHORT);
                        t.show();
                    }else {
                        Toast t = Toast.makeText(context,"Password is not Match",Toast.LENGTH_SHORT);
                        t.show();
                    }
               }else{
                   Toast t = Toast.makeText(context,"User Name is Not Match",Toast.LENGTH_SHORT);
                   t.show();
               }


            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}
