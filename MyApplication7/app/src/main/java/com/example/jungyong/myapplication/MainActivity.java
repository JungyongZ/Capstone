package com.example.jungyong.myapplication;

import android.app.FragmentManager;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity

        implements OnMapReadyCallback {
    ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    ListView listView;
    Geocoder geocoder;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getFragmentManager();

        MapFragment mapFragment = (MapFragment)fragmentManager

                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        /*  MakeList()함수를 통해 지도에 마커생성, 리스트 만들기를하기 */
        MakeList();


        //음식점 클릭이벤트
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

        map.addMarker(markerOptions);


        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));

        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }

    /*추후에 Intent값을 받아서 어떤음식종류인지 확인하고, 그 음식종류에 따라 리스트를 생성할수 있도록 하기
      1차적으로 데이터베이스로부터 값을 받으면 여러 음식점들을 표시하기                               */
    public void MakeList(){
        list.add(new ListViewItem("삼겹살집","맛있어요!", R.drawable.samgyup));
        list.add(new ListViewItem("김치찌개집","더 맛있어요!",R.drawable.kimchi));
        listView = (ListView) findViewById(R.id.ListView01);
        CustomAdapter adp = new CustomAdapter(getApplicationContext(), R.layout.list_view, list);
        listView.setAdapter(adp);


        geocoder = new Geocoder(this);
        List<Address> list = null;
        /*   http://bitsoul.tistory.com/135    --참조사이트,역지오코딩*/
        try{
            double d1 = Double.parseDouble("37.52487");
            double d2 = Double.parseDouble("126.92723");
            list = geocoder.getFromLocation(d1, d2, 10);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("geocoder","input&output err");
        }
        if(list!=null){
            if(list.size()==0){
                Log.d("noaddress","no_address");
            }else{          /*여기에서 마커, 리스트추가하면될듯*/
                Log.d("yesADDRESS",list.get(0).getLocality());

            }
        }

        /* 지도에 여러개의 마커를 생성하기 최대10(개수조정)개까지 */
        for(int i=0; i<10; i++){

        }


    }


}