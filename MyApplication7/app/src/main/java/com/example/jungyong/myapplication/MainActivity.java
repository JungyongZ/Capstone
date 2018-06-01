package com.example.jungyong.myapplication;

import android.app.FragmentManager;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity

        implements OnMapReadyCallback {
    ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    ListView listView;
    Geocoder geocoder;
    private String myJSON=null;

    private static final String TAG_RESULTS = "result1";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "address";

    private JSONArray peoples = null;

    private ArrayList<HashMap<String, String>> personList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


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
    public void MakeList() throws JSONException {



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
                Log.d("yesADDRESS",list.get(0).toString());

            }
        }

        /* 지도에 여러개의 마커를 생성하기 최대10(개수조정)개까지 */
        for(int i=0; i<10; i++){

        }


    }
///////////////////////
protected void showList() {
    try {

        JSONObject jsonObj = new JSONObject(myJSON);
        Log.v("알림","2페이지 결과2"+jsonObj);
        peoples = jsonObj.getJSONArray(TAG_RESULTS);        //Tag_Result의 값에 따라 희망 배열 순번의 값을 불러옴을 확인
        Log.v("알림","2페이지 결과3"+peoples);

        for (int i = 0; i < peoples.length(); i++) {
           Log.d("peoplelength", String.valueOf(peoples.length()));
            JSONObject c = peoples.getJSONObject(i);
            String id = c.getString(TAG_ID);
            String name = c.getString(TAG_NAME);
            String address = c.getString(TAG_ADD);
            Log.d("id name address:",id+name+address);
            Log.v("알림","2페이지 진행상황1");
            HashMap<String, String> persons = new HashMap<String, String>();

            persons.put(TAG_ID, id);
            persons.put(TAG_NAME, name);
            persons.put(TAG_ADD, address);
            Log.v("알림","2페이지 진행상황2");
            personList.add(persons);
           list.add(new ListViewItem(id,name, R.drawable.samgyup));
            Log.v("알림","2페이지 진행상황3");
        }
        CustomAdapter adp = new CustomAdapte    r(getApplicationContext(), R.layout.list_view, list);
        listView.setAdapter(adp);
               Log.v("알림","2페이지 진행상황4");
        //list.deferNotifyDataSetChanged();                     //리스트뷰의 낮은 이해도로 인해 실제로 두 페이지가 동시에 뜨도록 못하고 있는 상황..
        //list.setAdapter(adapter);                             //MainActivity.java 내용을 복사 붙여넣기 후, 그대로 사용하였고 이로인해 문제가 생긴것으로 확인

        Log.v("알림","2페이지 진행상황5");

    } catch (JSONException e) {
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