package com.zwng.ktgk;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zwng.ktgk.adapter.DepartmentAdapter;
import com.zwng.ktgk.model.DepartmentModel;

import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dReferenceDepartment;

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dReferenceDepartment = firebaseDatabase.getReference("departments");
    }

    public void loadDepartment(List<DepartmentModel> departmentList, DepartmentAdapter departmentAdapter) {
        dReferenceDepartment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                departmentList.clear();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    DepartmentModel department = keyNode.getValue(DepartmentModel.class);
                    departmentList.add(department);
                }
                departmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addDepartment(DepartmentModel department, View view) {
        String departmentId = department.getId();
        if (!departmentId.isEmpty()) {
            dReferenceDepartment.child(departmentId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Snackbar.make(view, "Department id already exist", Snackbar.LENGTH_SHORT).show();
                    }
                    else {
                        dReferenceDepartment.child(departmentId).setValue(department).addOnCompleteListener(task -> {
                           if (task.isSuccessful()) {
                                Snackbar.make(view, "Added successfully", Snackbar.LENGTH_SHORT).show();
                           }
                           else {
                               Snackbar.make(view, "Failed to add", Snackbar.LENGTH_SHORT).show();
                           }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {
            Snackbar.make(view, "Id field is empty", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void deleteDepartment(String id, View view) {
        dReferenceDepartment.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    dReferenceDepartment.child(id).removeValue().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Snackbar.make(view, "Deleted successfully", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            Snackbar.make(view, "Failed to delete", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Snackbar.make(view, "Department with ID: " + id + " does not exist", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
