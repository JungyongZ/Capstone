package com.example.jungyong.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MenuActivity extends AppCompatActivity {
    TextView textView;
   int count =1;
    TextView textView2;
    String app_url;
    ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    ListView listView;
    ArrayList<ListViewItem> list2 = new ArrayList<ListViewItem>();
    ListView listView2;
    private String myJSON;

    private static final String TAG_RESULTS = "result2";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "address";

    private JSONArray peoples = null;

    private ArrayList<HashMap<String, String>> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    //    final SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
     //   final SharedPreferences.Editor editor = pref.edit();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            app_url= getURL(KaKaoGo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent3 = getIntent();
        String name = intent3.getExtras().getString("name");
        textView = (TextView) findViewById(R.id.resname);
        textView.setText(name);


        personList = new ArrayList<HashMap<String, String>>();
        getData("http://116.32.57.232/PHP_connection.php"); //타깃 도메인


/*
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

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent3 = new Intent(getApplicationContext(), Cart.class);
                if(count==1){
                    editor.putInt("count", 1);
                    count++;
                    editor.putString("menu_name1", list2.get(position).title);
                    editor.putInt("menu_price1", Integer.parseInt(list2.get(position).content));
                    editor.commit();
                    startActivity(intent3);
                }else if(count==2){
                    editor.putInt("count", 2);
                    count++;
                    editor.putString("menu_name2", list2.get(position).title);
                    editor.putInt("menu_price2", Integer.parseInt(list2.get(position).content));
                    editor.commit();
                    startActivity(intent3);
                }else if(count==3){
                    editor.putInt("count", 3);
                    count++;
                    editor.putString("menu_name3", list2.get(position).title);
                    editor.putInt("menu_price3", Integer.parseInt(list2.get(position).content));
                    editor.commit();
                    startActivity(intent3);
                }else if(count==4){
                    editor.putInt("count", 4);
                    count++;
                    editor.putString("menu_name4", list2.get(position).title);
                    editor.putInt("menu_price4", Integer.parseInt(list2.get(position).content));
                    editor.commit();
                    startActivity(intent3);
                }

                if(count==5){
                    count=1;
                }



                startActivity(intent3);

            }
        });

*/
    }
    public String KaKaoGo() {

        HttpsURLConnection conn = null;
        try {
            URL url = new URL("https://kapi.kakao.com/v1/payment/ready"); //요청 URL을 입력
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)

            Log.d("Testing", "a");
            conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
            conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)
            conn.setRequestProperty("Authorization", "KakaoAK 2d84bf6e2a0d3a1cbaf05155fe2f95c1");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setConnectTimeout(60); //타임아웃 시간 설정 (default : 무한대기)

            Log.d("Testing", "a");
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //캐릭터셋 설정

            Log.d("Testing", "a");
            writer.write(
                    "cid=TC0ONETIME&" +
                            "partner_order_id=partner_order_id&" +
                            "partner_user_id=partner_user_id&" +
                            "item_name=핫초코&" +
                            "quantity=1&" +
                            "total_amount=3000&" +
                            "vat_amount=200&" +
                            "tax_free_amount=0&" +
                            "approval_url=https://35.229.177.70:7000&" +
                            "fail_url=https://35.229.177.70:7000&" +
                            "cancel_url=https://35.229.177.70:7000"
            ); //요청 파라미터를 입력
            writer.flush();
            writer.close();
            os.close();

            conn.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
            Log.d("http", sb.toString());
            return sb.toString();
            // Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
            //    System.out.println("response:" + sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (conn != null) {
                conn.disconnect();

            }
        }

    }

    public String getURL(String URL_Json) throws JSONException {
        JSONObject jsonObject = new JSONObject(URL_Json);
        return jsonObject.getString("next_redirect_mobile_url");
    }

    public void ClickMe(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(app_url));
        startActivity(intent);
    }

    ///////////////////////////
    protected void showList() {
        try {

            /*
            JSONArray jsonarray = new JSONArray(myJSON);
            Log.v("알림","1페이지 결과2"+jsonarray.getJSONArray(1));
            int list_cnt = jsonarray.length();
            Log.v("알림","1페이지 결과3"+list_cnt);
*/

            JSONObject jsonObj = new JSONObject(myJSON);
            Log.v("알림","1페이지 결과2"+jsonObj);

            peoples = jsonObj.getJSONArray(TAG_RESULTS);        //Tag_Result의 값에 따라 희망 배열 순번의 값을 불러옴을 확인
            Log.v("알림","1페이지 결과3"+peoples);


            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String name = c.getString(TAG_NAME);
                String address = c.getString(TAG_ADD);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_ID, id);
                persons.put(TAG_NAME, name);
                persons.put(TAG_ADD, address);
                Log.d("person",id +", "+ name +", "+ address);
                personList.add(persons);
                list.add(new ListViewItem(id,name, R.drawable.samgyup));
            }
            listView = (ListView) findViewById(R.id.ListView02);
           CustomAdapter adp = new CustomAdapter(getApplicationContext(), R.layout.list_view, list);
            listView.setAdapter(adp);
            list2.add(new ListViewItem("콜라/사이다","1000", R.drawable.cola));
            list2.add(new ListViewItem("소주","4000",R.drawable.soju));
            listView2 = (ListView) findViewById(R.id.ListView03);
            CustomAdapter adp2 = new CustomAdapter(getApplicationContext(), R.layout.list_view, list2);
            listView2.setAdapter(adp2);

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

            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent3 = new Intent(getApplicationContext(), Cart.class);
                    if(count==1){
                        editor.putInt("count", 1);
                        count++;
                        editor.putString("menu_name1", list2.get(position).title);
                        editor.putInt("menu_price1", Integer.parseInt(list2.get(position).content));
                        editor.commit();
                        startActivity(intent3);
                    }else if(count==2){
                        editor.putInt("count", 2);
                        count++;
                        editor.putString("menu_name2", list2.get(position).title);
                        editor.putInt("menu_price2", Integer.parseInt(list2.get(position).content));
                        editor.commit();
                        startActivity(intent3);
                    }else if(count==3){
                        editor.putInt("count", 3);
                        count++;
                        editor.putString("menu_name3", list2.get(position).title);
                        editor.putInt("menu_price3", Integer.parseInt(list2.get(position).content));
                        editor.commit();
                        startActivity(intent3);
                    }else if(count==4){
                        editor.putInt("count", 4);
                        count++;
                        editor.putString("menu_name4", list2.get(position).title);
                        editor.putInt("menu_price4", Integer.parseInt(list2.get(position).content));
                        editor.commit();
                        startActivity(intent3);
                    }

                    if(count==5){
                        count=1;
                    }



                    startActivity(intent3);

                }
            });
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.list_item,
                    new String[]{TAG_ID, TAG_NAME, TAG_ADD},
                    new int[]{R.id.id, R.id.name, R.id.address}
            );

            list.setAdapter(adapter);
*/
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
                Log.v("알림","1페이지 풀셋"+myJSON.toString());
                showList();
            }

        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
