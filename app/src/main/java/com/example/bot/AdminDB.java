package com.example.bot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class AdminDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "BOT_DB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "AdminDB";
    private static final String ID_COL = "id";
    private static final String Pass_COL = "password";

    public AdminDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY, "
                    + Pass_COL + " TEXT)";
            db.execSQL(query);



        } catch(Exception e) {}
    }


    public void addNewUser(int id, String password) {

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(ID_COL, id);
            values.put(Pass_COL, password);

            db.insert(TABLE_NAME, null, values);
            db.close();
        } catch (Exception e){}


    }
    public ArrayList<DatabaseUsers> readData() {
        ArrayList<DatabaseUsers> data = new ArrayList<>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query,null);

            if (cursor.moveToFirst()) {
                do {

                    data.add(new DatabaseUsers(
                            cursor.getInt(0),
                            cursor.getString(1)));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        } catch (Exception e) { }
        return data;

    }

    public boolean checkPassword(int id,String password){
        String pass = "";
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT "+Pass_COL+ " FROM " + TABLE_NAME +" WHERE " + ID_COL + " = " + id + ";";
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            pass = cursor.getString(0);
            cursor.close();
            db.close();
            return pass.equals(password);
        } catch (Exception e) { }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
