package com.example.korde.sqlitedbtest;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by korde on 22/7/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.korde.sqlitedbtest/databases/";
    private static String DB_NAME = "test.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        myContext = context;
    }

    public DBHelper(Context context){
        super(context, DB_NAME,null, 1);
        myContext = context;
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

        myInput = myContext.getAssets().open("ver.txt");
        myOutput = new FileOutputStream(DB_PATH+"ver.txt");
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //database does't exist yet.
        }
        if(checkDB != null){
            checkDB.close();

            boolean f;
            try {
                InputStream myInput = myContext.getAssets().open("ver.txt");
                BufferedReader i = new BufferedReader(new InputStreamReader(myContext.getAssets().open("ver.txt")));
                String ver = i.readLine();

                BufferedReader i2 = new BufferedReader(new InputStreamReader(new FileInputStream(DB_PATH+"ver.txt")));
                String ver2 = i2.readLine();

                if(Integer.parseInt(ver)>Integer.parseInt(ver2))
                    return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return checkDB != null ? true : false;
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();

        if(dbExist){
        }else{
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public List<Staff> getAllStaff(){
        List<Staff> staffList = new ArrayList<>();
        String selectQuery = "SELECT * FROM staff" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        return staffList;
    }

    public List<Staff> getDeptStaff(String d){
        List<Staff> staffList = new ArrayList<>();
        String selectQuery = "SELECT * FROM staff WHERE dept LIKE '"+d+"%'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        return staffList;
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
