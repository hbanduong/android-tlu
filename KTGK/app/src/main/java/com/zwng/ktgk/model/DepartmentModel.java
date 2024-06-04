package com.zwng.ktgk.model;

import java.util.ArrayList;

public class DepartmentModel {
    private String id, name, email, website, address, phoneNumber, idParent;

    public DepartmentModel() {
    }

    public DepartmentModel(String id, String name, String email, String website, String address, String phoneNumber, String idParent) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.website = website;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idParent = idParent;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }
}
