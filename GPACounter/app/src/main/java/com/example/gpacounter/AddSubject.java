package com.example.gpacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSubject extends AppCompatActivity {
    Button addSub,back;
    EditText subname,grade,credit;

    DBHadler dbHadler;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);


        addSub = findViewById(R.id.btnAddSub_MainSubpage);
        back = findViewById(R.id.btnAddSubBack);

        subname = findViewById(R.id.editAddSubName);
        grade = findViewById(R.id.editAddGrade);
        credit = findViewById(R.id.editAddCredit);


        final Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);


        context = this;
        dbHadler = new DBHadler(context);



        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getSubname = subname.getText().toString();
                String getGrade = grade.getText().toString();
                String getCredit = credit.getText().toString();


                if (getGrade.equals("A+")||getGrade.equals("A")||getGrade.equals("A-")||getGrade.equals("B+")||getGrade.equals("B")||getGrade.equals("B-")||getGrade.equals("C+")||getGrade.equals("C")||getGrade.equals("C-")||getGrade.equals("D")||getGrade.equals("F")){
                    GPA gpa = new GPA(id,getSubname,getGrade,Integer.parseInt(getCredit));

                    dbHadler.addSubject(gpa);
                    Intent intent2 = new Intent(context,SubjectPage.class);
                    intent2.putExtra("ID",id);
                    startActivity(intent2);
                    Toast t = Toast.makeText(context,"Subject Add Success",Toast.LENGTH_SHORT);
                    t.show();
                }else{
                    Toast toast = Toast.makeText(context,"Invalied Grade",Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context,SubjectPage.class);
                intent1.putExtra("ID",id);
                startActivity(intent1);
            }
        });

    }
}
