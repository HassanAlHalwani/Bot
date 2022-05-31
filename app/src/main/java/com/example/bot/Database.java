package com.example.bot;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Console;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper{

    private static final String DB_NAME = "BOT_DB";
    private static  final int  DB_VERSION = 1;
    private String TABLE_NAME = "QA";
    private static final String ID_COL = "id";
    private static final String QUESTION_COL = "question";
    private static final String CATEGORY_COL = "category";
    private static final String ANSWER_COL = "answer";

    public Database(Context context, String TABLE_NAME ){
        super(context, DB_NAME, null, DB_VERSION);
        this.TABLE_NAME = TABLE_NAME;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY, "
                    + QUESTION_COL + " TEXT,"
                    + ANSWER_COL + " TEXT,"
                    + CATEGORY_COL + " TEXT)";
            db.execSQL(query);

        } catch(Exception e) {

            System.out.print(e);}
    }
    public ArrayList<DatabaseTrace> readData() {
        ArrayList<DatabaseTrace> data = new ArrayList<>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query,null);

            if (cursor.moveToFirst()) {
                do {

                    data.add(new DatabaseTrace(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        } catch (Exception e) { }
            return data;

    }

    public ArrayList<DatabaseTrace> readData1() {
        ArrayList<DatabaseTrace> data = new ArrayList<>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query,null);

            if (cursor.moveToFirst()) {
                do {

                    data.add(new DatabaseTrace(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        } catch (Exception e) { }
        return data;

    }
    public  int getMaxID(){
        int id = 0;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT max(id) FROM "+ TABLE_NAME+" ;";
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            id = cursor.getInt(0);
            cursor.close();
            db.close();

        } catch (Exception e) { }
        return id;
    }

    public  boolean deleteRow(int id){
        boolean flag = false;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
            db.close();
            flag = true;

        } catch (Exception e) { }
        return flag;
    }
    public void addNewQuestion(int id, String question, String answer,String category) {

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(ID_COL, id);
            values.put(QUESTION_COL, question);
            values.put(ANSWER_COL, answer);
            values.put(CATEGORY_COL,category);

            db.insert(TABLE_NAME, null, values);
            db.close();
        } catch (Exception e){}
    }

    public ArrayList<DatabaseTrace> selectQuestions(String category){
        ArrayList<DatabaseTrace> data = new ArrayList<>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME +" WHERE " + CATEGORY_COL + " = '" + category + "';";
            Cursor cursor = db.rawQuery(query,null);

            if (cursor.moveToFirst()) {
                do {

                    data.add(new DatabaseTrace(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        } catch (Exception e) { }
        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
