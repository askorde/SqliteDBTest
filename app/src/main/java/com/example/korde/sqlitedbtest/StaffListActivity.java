package com.example.korde.sqlitedbtest;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaffListActivity extends AppCompatActivity {

    ListView lv;
    DBHelper myDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);


        lv = (ListView)findViewById(R.id.listView2);


        myDbHelper = new DBHelper(this);

        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,staffList.get(position).getExt(),Toast.LENGTH_SHORT).show();
            }
        });

        Bundle extras = getIntent().getExtras();
        String val = extras.getString("dept");

        List<Staff> staffList = null;

        if(val.equals("Administration")){
            staffList = myDbHelper.getDeptStaff("Administration");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));

        }
        else if(val.equals("Computer")){
            staffList = myDbHelper.getDeptStaff("Computer");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));
        }
        else if(val.equals("Civil")){
            staffList = myDbHelper.getDeptStaff("Civil");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));
        }
        else if(val.equals("E&AS")){
            staffList = myDbHelper.getDeptStaff("E&AS");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));
        }
        else if(val.equals("E&TC")){
            staffList = myDbHelper.getDeptStaff("E&TC");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));
        }
        else if(val.equals("I.T.")){
            staffList = myDbHelper.getDeptStaff("IT");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));
        }
        else if(val.equals("Mechanical")){
            staffList = myDbHelper.getDeptStaff("Mechanical");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));
        }else if(val.equals("Library")){
            staffList = myDbHelper.getDeptStaff("Library");
            lv.setAdapter(new ArrayAdapter<Staff>(this,android.R.layout.simple_list_item_1,staffList));
        }
        final List<Staff> finalStaffList = staffList;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(StaffListActivity.this, finalStaffList.get(position).getExt(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(StaffListActivity.this, StaffInfoActivity.class);
                i.putExtra("ext", finalStaffList.get(position).getExt());
                i.putExtra("name", finalStaffList.get(position).getName());
                i.putExtra("place", finalStaffList.get(position).getPlace());
                i.putExtra("place", finalStaffList.get(position).getPlace());
                startActivity(i);
            }
        });
    }
}
