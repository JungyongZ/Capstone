package com.example.jungyong.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Cart extends AppCompatActivity {
int count=0;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    int tot_price=0;
    String app_url;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        textView = (TextView)findViewById(R.id.resname2);
        textView2 = (TextView)findViewById(R.id.resname3);
        textView3 = (TextView)findViewById(R.id.resname4);
        textView4 = (TextView)findViewById(R.id.resname5);
        textView5 = (TextView)findViewById(R.id.resname_sum);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            app_url= getURL(KaKaoGo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        count = pref.getInt("count",0);
        if(count==1){
            textView.setText(pref.getString("menu_name1",""));
            tot_price=pref.getInt("menu_price1",0);
            textView5.setText(String.valueOf(tot_price));
            editor.putInt("tot_price", tot_price);
            editor.commit();
        }else if(count==2){
            textView.setText(pref.getString("menu_name1",""));
            tot_price=pref.getInt("menu_price1",0);
            textView2.setText(pref.getString("menu_name2",""));
            tot_price+=pref.getInt("menu_price2",0);
            textView5.setText(String.valueOf(tot_price));
            editor.putInt("tot_price", tot_price);
            editor.commit();
        }else if(count==3){
            textView.setText(pref.getString("menu_name1",""));
            tot_price=pref.getInt("menu_price1",0);
            textView2.setText(pref.getString("menu_name2",""));
            tot_price+=pref.getInt("menu_price2",0);
            textView3.setText(pref.getString("menu_name3",""));
            tot_price+=pref.getInt("menu_price3",0);
            textView5.setText(String.valueOf(tot_price));
            editor.putInt("tot_price", tot_price);
            editor.commit();
        }else if(count==4){
            textView.setText(pref.getString("menu_name1",""));
            tot_price=pref.getInt("menu_price1",0);
            textView2.setText(pref.getString("menu_name2",""));
            tot_price+=pref.getInt("menu_price2",0);
            textView3.setText(pref.getString("menu_name3",""));
            tot_price+=pref.getInt("menu_price3",0);
            textView4.setText(pref.getString("menu_name4",""));
            tot_price+=pref.getInt("menu_price4",0);
            textView5.setText(String.valueOf(tot_price));
            editor.putInt("tot_price", tot_price);
            editor.commit();
        }

    }
    public String KaKaoGo() {
        pref = getSharedPreferences("pref", MODE_PRIVATE);
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

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //캐릭터셋 설정

            Log.d("Testing1", pref.getString("menu_name1","")+","+pref.getInt("tot_price", 0));
            writer.write(
                    "cid=TC0ONETIME&" +
                            "partner_order_id=partner_order_id&" +
                            "partner_user_id=partner_user_id&" +
                            "item_name=" +
                            pref.getString("menu_name1","") +
                            "&" +
                            "quantity=1&" +
                            "total_amount=" +
                            String.valueOf(pref.getInt("tot_price",0))+
                            "&" +
                            "vat_amount=0&" +
                            "tax_free_amount=0&" +
                            "approval_url=http://116.32.57.232/Pay_1.php&" +
                            "fail_url=http://116.32.57.232/Pay_2.php&" +
                            "cancel_url=http://116.32.57.232/Pay_3.php"
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
}
