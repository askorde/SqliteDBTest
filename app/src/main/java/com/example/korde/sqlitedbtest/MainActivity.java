package com.example.korde.sqlitedbtest;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.app.ActionBar.*;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    DBHelper myDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

//        ActionBar mActionBar = getSupportActionBar();
//        if(mActionBar!=null) {
//            mActionBar.setTitle(R.string.app_name);
//            mActionBar.setLogo(R.mipmap.ic_launcher);
//            mActionBar.setDisplayUseLogoEnabled(true);
//            mActionBar.setDisplayShowHomeEnabled(true);
//            mActionBar.show();
//        }
        lv = (ListView)findViewById(R.id.listView);


        myDbHelper = new DBHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
        final String[] values = new String[] { "Administration", "Civil", "Computer", "E&AS", "E&TC","I.T.", "Mechanical", "Library"};
        final List<Staff> staffList ;//= myDbHelper.getAllStaff();
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,staffList.get(position).getExt(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, StaffListActivity.class);
                i.putExtra("dept", values[position]);
                startActivity(i);
            }
        });
    }
}
