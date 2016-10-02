package com.example.korde.sqlitedbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StaffInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_info);

        Bundle extras = getIntent().getExtras();
        String ext = extras.getString("ext");
        String name = extras.getString("name");
        String place = extras.getString("place");

        TextView n,p,e,d;
        n = (TextView)findViewById(R.id.textView4);
        p = (TextView)findViewById(R.id.textView6);
        e = (TextView)findViewById(R.id.textView7);

        n.setText("  "+name);
        p.setText("  "+place);
        e.setText("  "+ext);
    }
}
