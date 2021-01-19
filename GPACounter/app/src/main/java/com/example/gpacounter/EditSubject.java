package com.example.gpacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSubject extends AppCompatActivity {

    Button save,backedit;
    EditText subname,grade,credit;
    DBHadler dbHadler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);

        final Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        final int semid = intent.getIntExtra("SEMID",0);

        subname = findViewById(R.id.editEditSubject);
        grade = findViewById(R.id.editEditGrade);
        credit = findViewById(R.id.editEditCredit);

        context = this;
        dbHadler = new DBHadler(context);
        final GPA gpa = dbHadler.getSubjectName(id);


        subname.setText(gpa.getSubject());
        grade.setText(gpa.getGrade());
        credit.setText(String.valueOf(gpa.getCredit()));


        save = findViewById(R.id.btnEditSubjectSave);
        backedit = findViewById(R.id.btnbackEdit);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getSubject = subname.getText().toString();
                String getGrade = grade.getText().toString();
                String getCredit = credit.getText().toString();

                if (getGrade.equals("A+")||getGrade.equals("A")||getGrade.equals("A-")||getGrade.equals("B+")||getGrade.equals("B")||getGrade.equals("B-")||getGrade.equals("C+")||getGrade.equals("C")||getGrade.equals("C-")||getGrade.equals("D")||getGrade.equals("F")) {

                    GPA gpa1 = new GPA(semid, id, getSubject, getGrade, Integer.parseInt(getCredit));

                    dbHadler.updateSubject(gpa1);
                    Intent intent1 = new Intent(context, SubjectPage.class);
                    intent1.putExtra("ID", semid);
                    startActivity(intent1);
                    Toast toast = Toast.makeText(context, "Subject Edit Success", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(context,"Invalied Grade",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        backedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SubjectPage.class);
                intent.putExtra("ID",semid);
                startActivity(intent);
            }
        });

    }
}
