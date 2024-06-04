package com.zwng.ktgk.model;

public class EmployeeModel {
    private String id, name, role, email, phoneNumber, departmentId;

    public EmployeeModel() {
    }

    public EmployeeModel(String id, String name, String role, String email, String phoneNumber, String departmentId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
