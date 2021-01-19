package com.example.gpacounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHadler extends SQLiteOpenHelper {

    Context context;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss ");
    String currentDateandTime = sdf.format(new Date());

    private static final int VERSION = 2;
    private static final String DBNAME = "gpacalculator";

    //Student Table Data
    private static final String TABLE_NAME = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String UNIVERSITY = "university";
    private static final String FACULTY = "fac";
    private static final String DATE = "trn_date";
    private static final String USERNAME = "username";
    private static final String PASS = "pass";

    //Semester Table Data

    private static final String SEMESTER_TABLE = "semester";

    private static final String SEMESTER_ID = "sem_id";
    private static final String SEMESTER_NAME = "sem_name";
    private static final String SEMESTER_TOTAL_GPA = "total_gpa";
    private static final String SEMESTER_DATE = "trn_date";
    private static final String SEMESTER_AllCREDIT = "allcredit";
    private static final String SEMESTER_AllGrade = "allgrade";


    //Subject Table Data

    private static final String SUBJECT_TABLE = "sub";

    private static final String SUB_ID = "sub_id";
    private static final String SEM_ID = "semester_id";
    private static final String SUBJECT_NAME = "sub_name";
    private static final String GRADE = "grade";
    private static final String CREDIT = "credit";
    private static final String TRN_DATE = "tdate";




    public DBHadler(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Student_table_query = "CREATE TABLE "+TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME+" TEXT, "
                +UNIVERSITY+" TEXT, "
                +FACULTY+" TEXT, "
                +USERNAME+" TEXT, "
                +PASS+" TEXT, "
                +DATE+" DATETIME);";
        db.execSQL(Student_table_query);

        //Create Semester Table

        String Semester_table_query = "CREATE TABLE "+SEMESTER_TABLE+"("
                +SEMESTER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +SEMESTER_NAME+" TEXT, "
                +SEMESTER_TOTAL_GPA+" DOUBLE, "
                +SEMESTER_AllCREDIT+" DOUBLE, "
                +SEMESTER_AllGrade+" DOUBLE, "
                +SEMESTER_DATE+" DATETIME);";

        db.execSQL(Semester_table_query);

        //Create Subject Table

        String Subject_table_query = "CREATE TABLE "+SUBJECT_TABLE+"("
                +SUB_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +SUBJECT_NAME+" TEXT, "
                +GRADE+" TEXT, "
                +CREDIT+" INTEGER, "
                +TRN_DATE+" DATE, "
                +SEM_ID+" INTEGER);";
        db.execSQL(Subject_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        String DROP_TABLE_SEMESTER = "DROP TABLE IF EXISTS "+SEMESTER_TABLE;
        String DROP_TABLE_SUBJECT = "DROP TABLE IF EXISTS "+SUBJECT_TABLE;

        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_TABLE_SEMESTER);
        db.execSQL(DROP_TABLE_SUBJECT);
        onCreate(db);
    }

    public void addStudent(Student student){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put(NAME,student.getName());
        contentValues.put(UNIVERSITY,student.getUniversity());
        contentValues.put(FACULTY,student.getFac());
        contentValues.put(USERNAME,student.getUsername());
        contentValues.put(PASS,student.getPass());
        contentValues.put(DATE,currentDateandTime);

        db.insert(TABLE_NAME,null,contentValues);
    }

    public boolean login(String uname, String pass){

        boolean result;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE username = ? AND pass = ?",new String[]{uname,pass});

        int count = 0;
        count = cursor.getCount();

        if (count > 0){
            result = true;
        }else{
            result = false;
        }

        return result;
    }

    public boolean checkregister(){

        boolean result;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        int count = 0;
        count = cursor.getCount();

        if (count > 0){
            result = true;
        }else{
            result = false;
        }

        return result;
    }

    public boolean checkUsername(String uname){

        boolean result;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE username = ?",new String[]{uname});

        int count = 0;
        count = cursor.getCount();

        if (count > 0){
            result = true;
        }else{
            result = false;
        }

        return result;
    }

    public void updatePassword(String uname, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PASS,password);

        db.update(TABLE_NAME,contentValues,USERNAME+" = ? ",new String[]{uname});
    }




    public void addSemester(GPA gpa){

        double defaultvalue = 0.00;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SEMESTER_NAME,gpa.getSemester());
        contentValues.put(SEMESTER_TOTAL_GPA,defaultvalue);
        contentValues.put(SEMESTER_AllCREDIT,defaultvalue);
        contentValues.put(SEMESTER_AllGrade,defaultvalue);
        contentValues.put(SEMESTER_DATE,currentDateandTime);
        db.insert(SEMESTER_TABLE,null,contentValues);
        db.close();
    }

    public List<GPA> getSemester(){

        List<GPA> gpaList = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        String get_semester_query = "SELECT * FROM "+SEMESTER_TABLE;
        Cursor cursor = db.rawQuery(get_semester_query,null);

        if (cursor.moveToFirst()){
            do {
                GPA gpa = new GPA();

                gpa.setSem_id(cursor.getInt(0));
                gpa.setSemester(cursor.getString(1));
                gpa.setTotal_gpa(cursor.getDouble(2));

                gpaList.add(gpa);

            }while (cursor.moveToNext());
        }
        return gpaList;
    }


    public GPA getSemesterName(int position){

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(SEMESTER_TABLE,new String[]{SEMESTER_ID,SEMESTER_NAME,SEMESTER_TOTAL_GPA,SEMESTER_DATE},SEMESTER_ID + " = ?",new String[]{String.valueOf(position)},null,null,null);

        GPA gpa;
        if (cursor != null){
            cursor.moveToFirst();
            gpa = new GPA(
                    cursor.getInt(0),
                    cursor.getString(1)
            );

            return  gpa;
        }
        return null;
    }

    public void editSemester(GPA gpa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SEMESTER_NAME,gpa.getSemester());
        db.update(SEMESTER_TABLE,contentValues,SEMESTER_ID+" = ?",new String[]{String.valueOf(gpa.getSem_id())});
        db.close();

    }

    public void deleteSemester(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SEMESTER_TABLE,SEMESTER_ID+" = ? ",new String[]{String.valueOf(id)});
        db.delete(SUBJECT_TABLE,SEM_ID+" = ? ",new String[]{String.valueOf(id)});
        db.delete(SUBJECT_TABLE,SEM_ID+" = ? ",new String[]{String.valueOf(id)});
    }


    public void addSubject(GPA gpa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SUBJECT_NAME,gpa.getSubject());
        contentValues.put(GRADE,gpa.getGrade());
        contentValues.put(CREDIT,gpa.getCredit());
        contentValues.put(TRN_DATE,currentDateandTime);
        contentValues.put(SEM_ID,gpa.getSem_id());

        db.insert(SUBJECT_TABLE,null,contentValues);
    }

    public List<GPA> getAllSubject(int position){


        List<GPA> gpaList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
//        String get_subject_query = "SELECT * FROM "+SUBJECT_TABLE;
//        Cursor cursor = db.rawQuery(get_subject_query,null);

        Cursor cursor = db.query(SUBJECT_TABLE,new String[]{SUB_ID,SUBJECT_NAME,GRADE,CREDIT,SEM_ID},SEM_ID + " = ?",new String[]{String.valueOf(position)},null,null,null);


        if (cursor.moveToFirst()){
            do {
                GPA gpa = new GPA();
                gpa.setSubId(cursor.getInt(0));
                gpa.setSubject(cursor.getString(1));
                gpa.setGrade(cursor.getString(2));
                gpa.setCredit(cursor.getInt(3));
                gpa.setSem_id(cursor.getInt(4));


                gpaList.add(gpa);
            }while (cursor.moveToNext());
        }
        return gpaList;
    }


    public int getAllCreditCount(int position){
        int sum = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SUBJECT_TABLE,new String[]{CREDIT},SEM_ID + " = ?",new String[]{String.valueOf(position)},null,null,null);


        if (cursor.moveToFirst()){
                do {
                    sum = sum + cursor.getInt(0);
                }while (cursor.moveToNext());
        }
        return sum;
    }

    public double getAllGradeCount(int position){
        double sum = 0;
        String getgrade;
        double cred = 0;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SUBJECT_TABLE,new String[]{GRADE,CREDIT},SEM_ID + " = ?",new String[]{String.valueOf(position)},null,null,null);


        if (cursor.moveToFirst()){
            do {
                getgrade = cursor.getString(0);
                cred = cursor.getInt(1);

                switch (getgrade){
                        case  "A+":
                        sum = sum + 4.00 * cred;
                        break;

                    case  "A":
                        sum = sum + 4.00  * cred;
                        break;

                    case  "A-":
                        sum = sum + 3.67  * cred;
                        break;

                    case  "B+":
                        sum = sum + 3.33  * cred;
                        break;

                    case  "B":
                        sum = sum + 3.00  * cred;
                        break;

                    case  "B-":
                        sum = sum + 2.67  * cred;
                        break;

                    case  "C+":
                        sum = sum + 2.33  * cred;
                        break;

                    case  "C":
                        sum = sum + 2.00  * cred;
                        break;

                    case  "D":
                        sum = sum + 1.00 * cred;
                        break;

                    case  "F":
                        sum = sum + 0.00 * cred;
                        break;


                        //simple letter
                    case  "a+":
                        sum = sum + 4.00 * cred;
                        break;

                    case  "a":
                        sum = sum + 4.00 * cred;
                        break;

                    case  "a-":
                        sum = sum + 3.67 * cred;
                        break;

                    case  "b+":
                        sum = sum + 3.33 * cred;
                        break;

                    case  "b":
                        sum = sum + 3.00 * cred;
                        break;

                    case  "b-":
                        sum = sum + 2.67 * cred;
                        break;

                    case  "c+":
                        sum = sum + 2.33 * cred;
                        break;

                    case  "c":
                        sum = sum + 2.00 * cred;
                        break;

                    case  "d":
                        sum = sum + 1.00 * cred;
                        break;

                    case  "f":
                        sum = sum + 0.00 * cred;
                        break;

                }
            }while (cursor.moveToNext());
        }
        return sum;
    }


    public void updateSemesterGpa(String totalgpa,int id,double totalcredit, double totalGrade){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SEMESTER_TOTAL_GPA,totalgpa);
        contentValues.put(SEMESTER_AllCREDIT,totalcredit);
        contentValues.put(SEMESTER_AllGrade,totalGrade);
        db.update(SEMESTER_TABLE,contentValues,SEMESTER_ID+" = ? ",new String[]{String.valueOf(id)});
    }


    public double getFinalTotaleGPA(){
        double sum_of_credit = 0;
        double sum_of_grade = 0;
        double finalGPA = 0;

        SQLiteDatabase db = getReadableDatabase();
        String get_semester_query = "SELECT * FROM "+SEMESTER_TABLE;
        Cursor cursor = db.rawQuery(get_semester_query,null);

        if (cursor.moveToFirst()){
            do {
                sum_of_credit = sum_of_credit + cursor.getDouble(3);
                sum_of_grade = sum_of_grade + cursor.getDouble(4);


            }while (cursor.moveToNext());
            finalGPA = sum_of_grade/sum_of_credit;
        }else {
            finalGPA = 0.00;
        }


        return finalGPA;
    }

    public void deleteSubject(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SUBJECT_TABLE,SUB_ID+" = ? ",new String[]{String.valueOf(id)});
    }


    public GPA getSubjectName(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SUBJECT_TABLE,new String[]{SEM_ID,SUBJECT_NAME,GRADE,CREDIT},SUB_ID + " = ?",new String[]{String.valueOf(id)},null,null,null);

        GPA gpa;

        if (cursor != null){
            cursor.moveToFirst();
            gpa = new GPA(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            return  gpa;
        }
        return  null;
    }


    public void updateSubject(GPA gpa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT_NAME,gpa.getSubject());
        contentValues.put(GRADE,gpa.getGrade());
        contentValues.put(CREDIT,gpa.getCredit());

        db.update(SUBJECT_TABLE,contentValues,SUB_ID+" = ? ",new String[]{String.valueOf(gpa.getSubId())});
    }


//    public int getId(String name){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM "+SEMESTER_TABLE+" WHERE sem_name = ?",new String[]{name});
//        int id;
//
//        id = cursor.getInt(0);
//        return id;
//    }

}

