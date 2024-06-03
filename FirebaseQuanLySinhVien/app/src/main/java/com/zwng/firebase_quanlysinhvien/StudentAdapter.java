package com.zwng.firebase_quanlysinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<StudentModel> {
    private Context context;
    private List<StudentModel> studentList;

    public StudentAdapter(Context context, List<StudentModel> studentList) {
        super(context, R.layout.list_item_student, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false);
        }

        StudentModel student = studentList.get(position);

        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvClassroom = convertView.findViewById(R.id.tvClassroom);

        tvId.setText(String.valueOf(student.getId()));
        tvName.setText(student.getName());
        tvDate.setText(student.getDate());
        tvClassroom.setText(student.getClassroom());

        return convertView;
    }
}