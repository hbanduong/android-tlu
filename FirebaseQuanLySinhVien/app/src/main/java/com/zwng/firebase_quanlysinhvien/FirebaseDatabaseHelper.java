package com.zwng.firebase_quanlysinhvien;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dReferenceStudent;

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dReferenceStudent = firebaseDatabase.getReference("students");
    }

    public void loadStudent(List<StudentModel> studentList, StudentAdapter studentAdapter) {
        dReferenceStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    StudentModel student = keyNode.getValue(StudentModel.class);
                    studentList.add(student);
                }
                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addStudent(StudentModel student, View view) {
        String studentId = student.getId();
        if (!studentId.isEmpty()) {
            dReferenceStudent.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Snackbar.make(view, "Student id already exists", Snackbar.LENGTH_SHORT).show();
                    }
                    else {
                        dReferenceStudent.child(studentId).setValue(student).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Snackbar.make(view, "Student added successfully", Snackbar.LENGTH_SHORT).show();
                            } else {
                                Snackbar.make(view, "Failed to add student", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void updateStudent(String studentId, String studentName, String studentDate, String studentClassroom, View view) {
        dReferenceStudent.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    dReferenceStudent.child(studentId).updateChildren(new HashMap<String, Object>() {{
                        put("id", studentId);
                        put("name", studentName);
                        put("date", studentDate);
                        put("classroom", studentClassroom);
                    }}).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Snackbar.make(view, "Student updated successfully", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(view, "Failed to update student", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Snackbar.make(view, "Student with ID: " + studentId + " does not exist", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteStudent(String studentId, View view) {
        dReferenceStudent.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    dReferenceStudent.child(studentId).removeValue().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Snackbar.make(view, "Student deleted successfully", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            Snackbar.make(view, "Failed to delete student", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Snackbar.make(view, "Student with ID: " + studentId + " does not exist", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
