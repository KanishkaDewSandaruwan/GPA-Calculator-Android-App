package com.example.gpacounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SemArrayAdapter extends ArrayAdapter<GPA> {

    Context context;
    int resource;
    List<GPA> gpaList;


    public SemArrayAdapter(@NonNull Context context, int resource, List<GPA> gpaList) {
        super(context, resource, gpaList);

        this.context = context;
        this.resource = resource;
        this.gpaList = gpaList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);


        TextView name =row.findViewById(R.id.textSemesterView);
        TextView gpa = row.findViewById(R.id.textTotalGPA);

        GPA gpa1 = gpaList.get(position);

        name.setText(gpa1.getSemester());
        gpa.setText(String.valueOf(gpa1.getTotal_gpa()));

        return row;
    }
}

