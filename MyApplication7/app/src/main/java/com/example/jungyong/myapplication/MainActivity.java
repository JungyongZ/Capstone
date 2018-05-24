package com.example.jungyong.myapplication;

import android.app.FragmentManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity

        implements OnMapReadyCallback {
    ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    ListView listView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getFragmentManager();

        MapFragment mapFragment = (MapFragment)fragmentManager

                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        list.add(new ListViewItem("삼겹살집","맛있어요!", R.drawable.samgyup));
        list.add(new ListViewItem("김치찌개집","더 맛있어요!",R.drawable.kimchi));
        listView = (ListView) findViewById(R.id.ListView01);
        CustomAdapter adp = new CustomAdapter(getApplicationContext(), R.layout.list_view, list);
        listView.setAdapter(adp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent2 = new Intent(getApplicationContext(), MenuActivity.class);
              intent2.putExtra("name",list.get(position).title );
              startActivity(intent2);

            }
        });
    }


    @Override

    public void onMapReady(final GoogleMap map) {


        LatLng SEOUL = new LatLng(37.56, 126.97);


        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(SEOUL);

        markerOptions.title("서울");

        markerOptions.snippet("한국의 수도");

       // map.addMarker(markerOptions);


        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));

        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }


}