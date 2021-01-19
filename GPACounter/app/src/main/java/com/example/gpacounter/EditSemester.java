package com.example.gpacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSemester extends AppCompatActivity {
    Button save,back;
    EditText semname;
    DBHadler dbHadler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_semester);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);


        context = this;
        dbHadler = new DBHadler(context);

        semname = findViewById(R.id.editEditSubject);
        back = findViewById(R.id.btnBackEditSem);


        save = findViewById(R.id.btnEditSubjectSave);

        GPA gpa = dbHadler.getSemesterName(id);
        semname.setText(gpa.getSemester());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEditName = semname.getText().toString();
                GPA gpa = new GPA(id,getEditName);

                dbHadler.editSemester(gpa);
                startActivity(new Intent(context,MainPage.class));
                Toast t = Toast.makeText(context,"Edit Save Success",Toast.LENGTH_SHORT);
                t.show();

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
