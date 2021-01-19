package com.example.gpacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHadler dbHadler;
        TextView dont;
        Button login,reg;
        final Context context;

        context = this;
        final EditText uname,pass;
        dbHadler = new DBHadler(context);

        reg = findViewById(R.id.btnReg);
        login = findViewById(R.id.btnLogin);

        uname = findViewById(R.id.editLogUname);
        pass = findViewById(R.id.editLogPass);
        dont = findViewById(R.id.txtdont);

        boolean check;

        check = dbHadler.checkregister();
        if (check == true){
            reg.setText("Change");
            dont.setText("Forgot Password");
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),ForgotPassword.class);
                    startActivity(i);
                }
            });


        }else{
            reg.setText("Register");
            dont.setText("Don't Have Account ?");

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),Register.class);
                    startActivity(i);
                }
            });
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getUname = uname.getText().toString();
                String getPass = pass.getText().toString();

                boolean count;

                count = dbHadler.login(getUname,getPass);

                if (count == true){
                    startActivity(new Intent(getApplicationContext(),MainPage.class));
                }else{
                    Toast t = Toast.makeText(context,"User Name or Password Wrong",Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });
    }
}
