package com.example.korde.sqlitedbtest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class IntroActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ActionBar mActionBar = getSupportActionBar();
        if(mActionBar!=null) {
            mActionBar.setTitle(R.string.app_name);
            mActionBar.setLogo(R.mipmap.ic_launcher);
            mActionBar.setDisplayUseLogoEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.show();
        }

        listView = (ListView)findViewById(R.id.listView0);

        final String[] values = new String[] {"Staff Locator","Notice Forum"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=null;
                switch(position){
                    case 0:
                        i = new Intent(IntroActivity.this, MainActivity.class);
                }
                if(i!=null)
                    startActivity(i);
            }
        });
    }
}
