package edu.psu.cjr5662.blt;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "labDB.db";
    public static final String TABLE_NAME = "Lab";
    public static final String COLUMN_ID = "LabID";
    public static final String COLUMN_NAME = "LabName";
    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //this is how we create our Database, TABLE_NAME & such should be changed to meet our needs
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }
    public String loadHandler() {
        String res0 = "";
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int resNum = cursor.getInt(0);
            String res1 = cursor.getString(1);
            res0 += String.valueOf(resNum) + " " + res1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return res0;
    }
    public boolean updateHandler(int ID, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }

    public void addHandler(Lab lab) {
        ContentValues vals = new ContentValues();
        //vals.put(COLUMN_ID, lab.getID());
        //vals.put(COLUMN_NAME, lab.getLabName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, vals);
        db.close();
    }
    public Lab findHandler(String labName) {
        String query = "Select*FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + labName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        Lab l = new Lab();
        if(c.moveToFirst()){
            c.moveToFirst();
            //l.set...
            c.close();
        }else
            l = null;

        db.close();
        return l;
    }
}
