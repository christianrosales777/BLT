package edu.psu.cjr5662.blt;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lab5.db";
    public static final String TABLE_LAB = "Lab";
    public static final String COLUMN_PK = "PK";
    public static final String COLUMN_LABNUM = "LabNumber";
    public static final String COLUMN_COMPNUM = "ComputersAvailable";
    public static final String COLUMN_SOFTWARE = "SoftwareAvailable";
    public static final String COLUMN_DAY = "Day";
    public static final String COLUMN_TIMES = "Times";

    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LAB_TABLE = "CREATE TABLE " +
                TABLE_LAB + "(" + COLUMN_PK + " INTEGER PRIMARY KEY," + COLUMN_LABNUM + " INTEGER," + COLUMN_COMPNUM
                + " INTEGER, " + COLUMN_SOFTWARE + " TEXT, " + COLUMN_DAY + " TEXT, "+ COLUMN_TIMES + " TEXT " + ")";
        db.execSQL(CREATE_LAB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){}

    public String loadHandler(String time, String day, String software, int lab){
        String result = "";
        String query = "";
        String prev = "";

        if(time == null && day == null && software == null && lab == 0){ //0000
            query = "Select * FROM Lab";
        }
        else if(time == null && day == null && software == null){ //0001
            query = "Select * FROM Lab WHERE LabNumber = "+lab;
        }
        else if(time == null && day == null && lab == 0){ //0010
            query = "Select * FROM Lab WHERE SoftwareAvailable LIKE '%"+software+"%'";
        }
        else if(time == null && day == null){ //0011
            query = "Select * FROM Lab WHERE SoftwareAvailable LIKE '%"+software+"%' AND LabNumber = "+lab;
        }
        else if(time == null && software == null && lab == 0){ //0100
            query = "Select * FROM Lab WHERE Day LIKE '%"+day+"%' AND Times LIKE '%All%'";
        }
        else if(time == null && software == null){ //0101
            query = "Select * FROM Lab WHERE Day LIKE '%"+day+"%'AND Times LIKE '%All%' AND LabNumber = "+lab;
        }
        else if(time == null && lab == 0){ //0110
            query = "Select * FROM Lab WHERE Day LIKE '%"+day+"%'AND Times LIKE '%All%' AND SoftwareAvailable LIKE '%"+software+"%'";
        }
        else if(time == null){ //0111
            query = "Select * FROM Lab WHERE Day LIKE '%"+day+"%'AND Times LIKE '%All%' AND LabNumber = "+lab+" AND SoftwareAvailable LIKE '%"+software+"%'";
        }
        else if(day == null && software == null && lab == 0){ //1000
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%'";
        }
        else if(day == null && software == null){ //1001
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%' AND LabNumber = "+lab;
        }
        else if(day == null && lab == 0){ //1010
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%' AND SoftwareAvailable LIKE '%"+software+"%'";
        }
        else if(day == null){ //1011
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%' AND SoftwareAvailable LIKE '%"+software+"%' AND LabNumber = "+lab;
        }
        else if(software == null && lab == 0){ //1100
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%' AND Day LIKE '%"+day+"%'";
        }
        else if(software == null){ //1101
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%' AND LabNumber = "+lab+" AND Day LIKE '%"+day+"%'";
        }
        else if(lab == 0){ //1110
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%' AND Day LIKE '%"+day+"%' AND SoftwareAvailable LIKE '%"+software+"%'";
        }
        else if(time == null && day == null && software == null && lab == 0) {
            result = "No results found.";
            return result;
        }
        else{ //1111
            query = "Select * FROM Lab WHERE Times LIKE '%"+time+"%' AND Day LIKE '%"+day+"%' AND SoftwareAvailable LIKE '%"+software+"%' AND LabNumber = "+lab;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int res1 = cursor.getInt(1);
            int res2 = cursor.getInt(2);
            if(String.valueOf(res1).equals(prev) == false) {
                result += "  Lab: " + String.valueOf(res1) + "     Computers Available: " + String.valueOf(res2) +
                        System.getProperty("line.separator");
            }
            prev = String.valueOf(res1);
        }
        cursor.close();
        db.close();

        return result;
    }

    public void addHandler(Lab lab) {
        ContentValues labValues = new ContentValues();
        labValues.put(COLUMN_PK, lab.getPK());
        labValues.put(COLUMN_LABNUM, lab.getLabNumber());
        labValues.put(COLUMN_COMPNUM, lab.getComputers());
        labValues.put(COLUMN_SOFTWARE, lab.getAvailabileSoftware());
        labValues.put(COLUMN_DAY, lab.getDay());
        labValues.put(COLUMN_TIMES, lab.getTimes());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_LAB, null, labValues);
        db.close();
    }
}
