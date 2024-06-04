package com.zwng.ktgk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zwng.ktgk.R;
import com.zwng.ktgk.model.DepartmentModel;

import java.util.List;

public class DepartmentAdapter extends ArrayAdapter<DepartmentModel> {
    private Context context;
    private List<DepartmentModel> departmentList;

    public DepartmentAdapter(Context context, List<DepartmentModel> list) {
        super(context, 0, list);
        this.context = context;
        this.departmentList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DepartmentModel department = departmentList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(0, parent, false);
        }
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);
        TextView tvWebsite = convertView.findViewById(R.id.tvWebsite);
        TextView tvAddress = convertView.findViewById(R.id.tvAddress);
        TextView tvPhoneNumber = convertView.findViewById(R.id.tvPhoneNumber);
        TextView tvIdParent = convertView.findViewById(R.id.tvIdParent);

        tvId.setText(department.getId());
        tvName.setText(department.getName());
        tvEmail.setText(department.getEmail());
        tvWebsite.setText(department.getWebsite());
        tvAddress.setText(department.getAddress());
        tvPhoneNumber.setText(department.getPhoneNumber());
        tvIdParent.setText(department.getIdParent());

        return convertView;
    }
}
