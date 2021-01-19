package com.example.gpacounter;

public class Student {
    private int id;
    private String name;
    private String university;
    private String fac;
    private String username;
    private String pass;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Student() {
    }

    public Student(int id, String name, String university, String fac) {
        this.id = id;
        this.name = name;
        this.university = university;
        this.fac = fac;
    }

    public Student(String name, String university, String fac, String username, String pass) {
        this.name = name;
        this.university = university;
        this.fac = fac;
        this.username = username;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFac() {
        return fac;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }
}

