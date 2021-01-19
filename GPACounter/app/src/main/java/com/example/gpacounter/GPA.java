package com.example.gpacounter;

public class GPA {
    private int sem_id;
    private String semester;
    private String trn_date;
    private double total_gpa;

    private int subId;
    private String subject;
    private String grade;
    private int credit;

    public GPA() {
    }

    public GPA(String semester)
    {
        this.semester = semester;
    }

    public GPA(int sem_id, String semester) {
        this.sem_id = sem_id;
        this.semester = semester;
    }


    public GPA(int sem_id,String semester, double total_gpa) {
        this.sem_id = sem_id;
        this.semester = semester;
        this.total_gpa = total_gpa;
    }

    public GPA(int sem_id, String subject, String grade, int credit) {
        this.sem_id = sem_id;
        this.subject = subject;
        this.grade = grade;
        this.credit = credit;
    }

    public GPA(int sem_id, int subId, String subject, String grade, int credit) {
        this.sem_id = sem_id;
        this.subId = subId;
        this.subject = subject;
        this.grade = grade;
        this.credit = credit;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public double getTotal_gpa() {
        return total_gpa;
    }

    public void setTotal_gpa(double total_gpa) {
        this.total_gpa = total_gpa;
    }

    public int getSem_id() {
        return sem_id;
    }

    public void setSem_id(int sem_id) {
        this.sem_id = sem_id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTrn_date() {
        return trn_date;
    }

    public void setTrn_date(String trn_date) {
        this.trn_date = trn_date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}

