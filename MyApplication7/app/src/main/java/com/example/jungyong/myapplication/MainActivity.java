package com.example.jungyong.myapplication;

import android.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity

        implements OnMapReadyCallback {
    ArrayList<ListViewItem2> list = new ArrayList<ListViewItem2>();
    ListView listView;
    Geocoder geocoder;
    private String myJSON=null;
    GoogleMap mMap;
    private static final String TAG_RESULTS = "1Page";
    private static final String TAG_ID = "M_Name";
    private static final String TAG_NAME = "M_Location";
    private static final String TAG_ADD = "M_pic_url";

    private JSONArray peoples = null;

    private ArrayList<HashMap<String, String>> personList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       geocoder = new Geocoder(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        FragmentManager fragmentManager = getFragmentManager();

        MapFragment mapFragment = (MapFragment)fragmentManager

                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        /*  MakeList()함수를 통해 지도에 마커생성, 리스트 만들기를하기 */
        listView = (ListView) findViewById(R.id.ListView01);
        personList=new ArrayList<HashMap<String, String>>();
        getData("http://116.32.57.232/PHP_connection.php");


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
    public void onMapReady(GoogleMap map) {
mMap = map;
    }


protected void showList() {
   Bitmap bm = null;

    try {
        geocoder = new Geocoder(this);
        JSONObject jsonObj = new JSONObject(myJSON);
        //Log.v("알림","2페이지 결과2"+jsonObj);
        peoples = jsonObj.getJSONArray(TAG_RESULTS);        //Tag_Result의 값에 따라 희망 배열 순번의 값을 불러옴을 확인
      //  Log.v("알림","2페이지 결과3"+peoples);
LatLng latLng;
        for (int i = 0; i < peoples.length(); i++) {

            JSONObject c = peoples.getJSONObject(i);
            String id = c.getString(TAG_ID);
            String name = c.getString(TAG_NAME);
            String address = c.getString(TAG_ADD);
       //     Log.v("id name address:",id+name+address);
          //  Log.v("알림k", name);
         //   Log.v("알림","2페이지 진행상황1");
            try {
                URL url = new URL(address);
                URLConnection conn = url.openConnection();
                conn.connect();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
               bm = BitmapFactory.decodeStream(bis);
                Log.v("asaaa", String.valueOf(bis));
                bis.close();


               // imgView.setImageBitmap(bm);
            } catch (Exception e) {
            }


            list.add(new ListViewItem2(id,name, bm));
     //  Log.v("Lat", String.valueOf(geocoder.getFromLocationName(name,10).get(0).getLatitude()));

double a1=geocoder.getFromLocationName(name,10).get(0).getLatitude();
double a2= geocoder.getFromLocationName(name,10).get(0).getLongitude();


            MarkerOptions markerOptions = new MarkerOptions();
           latLng=new LatLng(a1,a2);
            markerOptions
                    .position(latLng)
                    .title(id);
      mMap.addMarker(markerOptions);
            if(i==0) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

            }

        }
        CustomAdapter2 adp = new CustomAdapter2(getApplicationContext(), R.layout.list_view, list);
        listView.setAdapter(adp);
               Log.v("알림","2페이지 진행상황4");



    } catch (JSONException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result2) {
                myJSON = result2;
                Log.v("알림","2페이지 결과1"+myJSON.toString());
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }



}