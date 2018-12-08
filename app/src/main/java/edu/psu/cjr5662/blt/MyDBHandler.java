package edu.psu.cjr5662.blt;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lab1.db";
    public static final String TABLE_LAB = "Lab";
    public static final String COLUMN_PK = "PK";
    public static final String COLUMN_LABNUM = "LabNumber";
    public static final String COLUMN_COMPNUM = "ComputersAvailable";
    public static final String COLUMN_SOFTWARE = "SoftwareAvailable";
/**
    public static final String TABLE_AVAILABILITY = "Availability";
    public static final String COLUMN_M = "Monday";
    public static final String COLUMN_T = "Tuesday";
    public static final String COLUMN_W = "Wednesday";
    public static final String COLUMN_TH = "Thursday";
    public static final String COLUMN_F = "Friday";
**/
    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LAB_TABLE = "CREATE TABLE " +
                TABLE_LAB + "(" + COLUMN_PK + " INTEGER PRIMARY KEY," + COLUMN_LABNUM + " INTEGER," + COLUMN_COMPNUM
                + " INTEGER, " + COLUMN_SOFTWARE + " TEXT " + ")";
        db.execSQL(CREATE_LAB_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }
    public String loadHandler(String software){
        String result = "";
        //String query = "Select*FROM " + TABLE_LAB + " WHERE " + COLUMN_LABNUM + " = " +  labNum + " AND " + COLUMN_SOFTWARE + " LIKE "  + "'%" + software + "%'";
        String query = "Select*FROM " + TABLE_LAB + " WHERE " + COLUMN_SOFTWARE + " LIKE "  + "'%" + software + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int res1 = cursor.getInt(1);
            int res2 = cursor.getInt(2);
            result += "  Lab: " + String.valueOf(res1) + "     Computers Available: " + String.valueOf(res2) +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public Lab findHandler(int labNumber) {
        String query = "Select*FROM " + TABLE_LAB + "WHERE" + COLUMN_LABNUM + " = " +  labNumber;
                //+ "'" + "AND" + COLUMN_SOFTWARE + "LIKE"  + "%'" + software + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        Lab l = new Lab();
        if(c.moveToFirst()){
            c.moveToFirst();
            l.setLabNumber(Integer.parseInt(c.getString(0)));
            //l.setComputers(Integer.parseInt(c.getString(1)));
            //l.setAvailableSoftware(software);
            c.close();
        }else
            l = null;

        db.close();
        return l;
    }

    public void addHandler(Lab lab) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PK, lab.getPK());
        values.put(COLUMN_LABNUM, lab.getLabNumber());
        values.put(COLUMN_COMPNUM, lab.getComputers());
        values.put(COLUMN_SOFTWARE, lab.getAvailabileSoftware());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_LAB, null, values);
        db.close();
    }
}
