package com.example.gpacounter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainPage extends AppCompatActivity {
    Button addNew;
    EditText sem;
    DBHadler dbHadler;
    SemArrayAdapter semArrayAdapter;
    Context context;
    List<GPA> gpaList;
    ListView semLists;
    double total;
    TextView viewTotal;

    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        context = this;

        im = findViewById(R.id.imageView5);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(context,"Kanishka Dew Sandaruwan / 0713664071 / kanishkadewsandaruwan@gmail.com ..... Copyright©2020.CodeStation(Pvt)Limited. All Rights Reserved.",Toast.LENGTH_LONG);
                t.show();
            }
        });



        addNew = findViewById(R.id.btnAddNew);
        semLists = findViewById(R.id.semester_list);
        dbHadler = new DBHadler(context);

        gpaList = new ArrayList<>();
        gpaList = dbHadler.getSemester();

        semArrayAdapter = new SemArrayAdapter(context,R.layout.one_semester,gpaList);
        semLists.setAdapter(semArrayAdapter);


        total = dbHadler.getFinalTotaleGPA();
        viewTotal = findViewById(R.id.txtTotal);
        viewTotal.setText("Total GPA : "+String.format(Locale.CANADA, "%.2f", total));

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddSemester.class));
            }
        });


        semLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final GPA gpa = gpaList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle(Html.fromHtml("<font color='#000000'>"+gpa.getSemester()+"</font>"));

                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(context, EditSemester.class);
                        intent.putExtra("ID", gpa.getSem_id());
                        startActivity(intent);

                    }
                }).setPositiveButton("Add Subjects", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,SubjectPage.class);
                        intent.putExtra("ID",gpa.getSem_id());
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHadler.deleteSemester(gpa.getSem_id());
                        startActivity(new Intent(context,MainPage.class));
                        Toast t = Toast.makeText(context,"Remove Success",Toast.LENGTH_SHORT);
                        t.show();
                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
