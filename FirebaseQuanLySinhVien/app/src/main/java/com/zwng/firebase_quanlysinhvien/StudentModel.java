package com.zwng.firebase_quanlysinhvien;

import java.util.Date;

public class StudentModel {
    private String id;
    private String name;
    private String date;
    private String classroom;

    public StudentModel() {}

    public StudentModel(String id, String name, String date, String classroom) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.classroom = classroom;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}