package com.example.gpacounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class SubArrayAdapter  extends ArrayAdapter<GPA> {

    List<GPA> subList;
    Context context;
    int resource;


    public SubArrayAdapter(@NonNull Context context, int resource, List<GPA> subList) {
        super(context, resource, subList);

        this.context = context;
        this.resource = resource;
        this.subList = subList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView subName = row.findViewById(R.id.txtSubName);
        TextView grade = row.findViewById(R.id.txtGrade);
        TextView creadit = row.findViewById(R.id.txtCredit);


        GPA gpa = subList.get(position);

        subName.setText(gpa.getSubject());
        grade.setText(gpa.getGrade());
        creadit.setText(String.valueOf(gpa.getCredit()));


        return row;
    }
}

