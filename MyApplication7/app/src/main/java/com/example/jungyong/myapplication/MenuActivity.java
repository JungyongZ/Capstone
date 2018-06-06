package com.example.jungyong.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MenuActivity extends AppCompatActivity {
    TextView textView;
   int count =1;
    private RecyclerView mVerticalView;
    private VerticalAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int MAX_ITEM_COUNT = 5;
    ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    ListView listView;

    private String myJSON;

    private static final String TAG_RESULTS = "Market1";
    private static final String TAG_RESULTS2 = "pic1";
    private static final String TAG_ID = "LM_Name";
    private static final String TAG_NAME = "LM_Charge";
    private static final String TAG_ADD = "MP_pic_url";

    private JSONArray peoples = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mVerticalView = (RecyclerView) findViewById(R.id.vertical_list);

        Intent intent3 = getIntent();
        String name = intent3.getExtras().getString("name");
        textView = (TextView) findViewById(R.id.resname);
        textView.setText(name);


        getData("http://116.32.57.232/PHP_connection.php"); //타깃 도메인


    }


    public void ClickMe(View view){
        Intent intent2 = new Intent(getApplicationContext(), Cart.class);

        startActivity(intent2);

    }

    ///////////////////////////
    protected void showList() {
        try {


            JSONObject jsonObj = new JSONObject(myJSON);
            Log.v("haha","1페이지 결과2"+jsonObj);

            peoples = jsonObj.getJSONArray(TAG_RESULTS);        //Tag_Result의 값에 따라 희망 배열 순번의 값을 불러옴을 확인
            Log.v("haha","1페이지 결과3"+peoples);

Log.v("c확인3", String.valueOf(peoples.length()));
            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                Log.v("c확인", String.valueOf(c));
                String id = c.getString(TAG_ID);
                Log.v("c확인2",id);
                String name = c.getString(TAG_NAME);
Log.v("id,name",id + name);

                list.add(new ListViewItem(id,name));
            }
            listView = (ListView) findViewById(R.id.ListView02);
           CustomAdapter adp = new CustomAdapter(getApplicationContext(), R.layout.list_view2, list);
            listView.setAdapter(adp);


               final SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
               final SharedPreferences.Editor editor = pref.edit();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent2 = new Intent(getApplicationContext(), Cart.class);

                    if(count==1){
                        editor.putInt("count", 1);
                        count++;
                        editor.putString("menu_name1", list.get(position).title);
                        editor.putInt("menu_price1", Integer.parseInt(list.get(position).content));
                        editor.commit();
                        startActivity(intent2);
                    }else if(count==2){
                        editor.putInt("count", 2);
                        count++;
                        editor.putString("menu_name2", list.get(position).title);
                        editor.putInt("menu_price2", Integer.parseInt(list.get(position).content));
                        editor.commit();
                        startActivity(intent2);
                    }else if(count==3){
                        editor.putInt("count", 3);
                        count++;
                        editor.putString("menu_name3", list.get(position).title);
                        editor.putInt("menu_price3", Integer.parseInt(list.get(position).content));
                        editor.commit();
                        startActivity(intent2);
                    }else if(count==4){
                        editor.putInt("count", 4);
                        count++;
                        editor.putString("menu_name4", list.get(position).title);
                        editor.putInt("menu_price4", Integer.parseInt(list.get(position).content));
                        editor.commit();
                        startActivity(intent2);
                    }

                    if(count==5){
                        count=1;
                    }


                }
            });

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
    protected void showList2() {
        ArrayList<VerticalData> data = new ArrayList<>();
        Bitmap bm = null;
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS2);
            for (int i = 0; i < peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                String add = c.getString(TAG_ADD);

                try {
                    URL url = new URL(add);
                    URLConnection conn = url.openConnection();
                    conn.connect();
                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                    bm = BitmapFactory.decodeStream(bis);
                    Log.v("asaaa", String.valueOf(bis));
                    bis.close();
                    data.add(new VerticalData(bm));
                } catch (Exception e) {
                }
            }
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 기본값이 VERTICAL

            // setLayoutManager
            mVerticalView.setLayoutManager(mLayoutManager);

            // init Adapter
            mAdapter = new VerticalAdapter();

            // set Data
            mAdapter.setData(data);

            // set Adapter
            mVerticalView.setAdapter(mAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    public void getData(final String url) {
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
                    Log.d("jsonreturn", sb.toString().trim());
                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }




            }
            @Override
            protected void onPostExecute(String result1) {
                myJSON = result1;
                Log.v("알림메뉴","1페이지 풀셋"+myJSON.toString());
                showList();
                showList2();
            }

        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
