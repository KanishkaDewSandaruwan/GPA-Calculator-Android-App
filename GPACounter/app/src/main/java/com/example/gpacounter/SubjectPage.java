package com.example.gpacounter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubjectPage extends AppCompatActivity {
    Button addSub,backmain;
    List<GPA> gpaList;
    DBHadler dbHadler;
    Context context;
    ListView subjectList;
    SubArrayAdapter subArrayAdapter;
    double totaleGpaCredit;
    double totalGrade,totalGpa;
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_page);

        context = this;
        totaleGpaCredit = 0;
        totalGrade  = 0;
        totalGpa = 0;


        final Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        final int ids = id;

        showText = findViewById(R.id.txtShow);
        backmain = findViewById(R.id.btnbackMain);
        addSub = findViewById(R.id.btnAddSub_MainSubpage);
        subjectList = findViewById(R.id.listSubject);
        dbHadler = new DBHadler(context);

        gpaList = new ArrayList<>();
        gpaList = dbHadler.getAllSubject(id);


        subArrayAdapter = new SubArrayAdapter(context,R.layout.onesubject,gpaList);
        subjectList.setAdapter(subArrayAdapter);

        //get Count of Gpa Credit
        totaleGpaCredit = dbHadler.getAllCreditCount(id);

        //get Count of Gpa Credit
        totalGrade = dbHadler.getAllGradeCount(id);
        totalGpa = totalGrade / totaleGpaCredit;
        final String finalTotalGpa = String.format(Locale.CANADA, "%.2f", totalGpa);
        showText.setText("Semester Totle : "+finalTotalGpa);

//        dbHadler.updateSemesterGpa(finalTotalGpa,id,totaleGpaCredit,totalGrade); //update semester data

        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),AddSubject.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });




        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final GPA gpa = gpaList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle(gpa.getSubject());

                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHadler.deleteSubject(gpa.getSubId());
                        Intent intent3 = new Intent(context,SubjectPage.class);
                        intent3.putExtra("ID",ids);
                        startActivity(intent3);

                        Toast t = Toast.makeText(context,"Remove Success",Toast.LENGTH_SHORT);
                        t.show();
                    }
                });

                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent5 = new Intent(context,EditSubject.class);
                        intent5.putExtra("ID",gpa.getSubId());
                        intent5.putExtra("SEMID",ids);
                        startActivity(intent5);
                    }
                });
                builder.show();

            }
        });

        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHadler.updateSemesterGpa(finalTotalGpa,id,totaleGpaCredit,totalGrade);
                startActivity(new Intent(context,MainPage.class));
            }
        });

    }

}
