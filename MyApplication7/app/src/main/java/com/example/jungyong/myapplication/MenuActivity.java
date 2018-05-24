package com.example.jungyong.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    ListView listView;
    ArrayList<ListViewItem> list2 = new ArrayList<ListViewItem>();
    ListView listView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent3 = getIntent();
        String name = intent3.getExtras().getString("name");
        textView = (TextView) findViewById(R.id.resname);
        textView.setText(name);
        list.add(new ListViewItem("삼겹살","1인분 8900원", R.drawable.samgyup));
        list.add(new ListViewItem("목살","1인분 7900원",R.drawable.mok));
        list.add(new ListViewItem("콜라/사이다","1000원", R.drawable.cola));
        listView = (ListView) findViewById(R.id.ListView02);
        CustomAdapter adp = new CustomAdapter(getApplicationContext(), R.layout.list_view, list);
        listView.setAdapter(adp);
        list2.add(new ListViewItem("콜라/사이다","1000원", R.drawable.cola));
        list2.add(new ListViewItem("소주","4000원",R.drawable.soju));
        listView = (ListView) findViewById(R.id.ListView03);
        CustomAdapter adp2 = new CustomAdapter(getApplicationContext(), R.layout.list_view, list2);
        listView.setAdapter(adp2);
    }
}
