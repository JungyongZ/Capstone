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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.Toast;



public class Cart extends AppCompatActivity {
    int count=0;
    TextView textView5;
    int tot_price=0;
    String app_url;
    SharedPreferences pref;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference Menu_Reference = firebaseDatabase.getReference("Menu");
    private DatabaseReference Value_Reference = firebaseDatabase.getReference("Value");;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Restaurant");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        textView5 = (TextView)findViewById(R.id.resname_sum);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        count = pref.getInt("count",0);

        Menu_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final LinearLayout container = (LinearLayout)findViewById(R.id.dynamicArea);
                    if(count==1) {
                        final TextView topTV1 = new TextView(Cart.this);
                        topTV1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV1.setText("" + snapshot.child("title").getValue());
                        topTV1.setTextColor(Color.BLACK);
                        topTV1.setTextSize(30);
                        topTV1.setPadding(20, 10, 10, 10);
                        container.addView(topTV1);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV1);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;
                                        editor.putString("menu_name1", ""+snapshot.child("title").getValue());
                                        editor.commit();
                            }
                        });
                    }

                    else if(count==2) {
                        final TextView topTV2 = new TextView(Cart.this);
                        topTV2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV2.setText("" + snapshot.child("title").getValue());
                        topTV2.setTextColor(Color.BLACK);
                        topTV2.setTextSize(30);
                        topTV2.setPadding(20, 10, 10, 10);
                        container.addView(topTV2);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV2);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==3) {
                        final TextView topTV3 = new TextView(Cart.this);
                        topTV3.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV3.setText("" + snapshot.child("title").getValue());
                        topTV3.setTextColor(Color.BLACK);
                        topTV3.setTextSize(30);
                        topTV3.setPadding(20, 10, 10, 10);
                        container.addView(topTV3);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV3);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==4) {
                        final TextView topTV4 = new TextView(Cart.this);
                        topTV4.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV4.setText("" + snapshot.child("title").getValue());
                        topTV4.setTextColor(Color.BLACK);
                        topTV4.setTextSize(30);
                        topTV4.setPadding(20, 10, 10, 10);
                        container.addView(topTV4);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV4);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==5) {
                        final TextView topTV5 = new TextView(Cart.this);
                        topTV5.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV5.setText("" + snapshot.child("title").getValue());
                        topTV5.setTextColor(Color.BLACK);
                        topTV5.setTextSize(30);
                        topTV5.setPadding(20, 10, 10, 10);
                        container.addView(topTV5);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV5);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==6) {
                        final TextView topTV6 = new TextView(Cart.this);
                        topTV6.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV6.setText("" + snapshot.child("title").getValue());
                        topTV6.setTextColor(Color.BLACK);
                        topTV6.setTextSize(30);
                        topTV6.setPadding(20, 10, 10, 10);
                        container.addView(topTV6);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV6);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==7) {
                        final TextView topTV7 = new TextView(Cart.this);
                        topTV7.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV7.setText("" + snapshot.child("title").getValue());
                        topTV7.setTextColor(Color.BLACK);
                        topTV7.setTextSize(30);
                        topTV7.setPadding(20, 10, 10, 10);
                        container.addView(topTV7);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV7);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==8) {
                        final TextView topTV8 = new TextView(Cart.this);
                        topTV8.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV8.setText("" + snapshot.child("title").getValue());
                        topTV8.setTextColor(Color.BLACK);
                        topTV8.setTextSize(30);
                        topTV8.setPadding(20, 10, 10, 10);
                        container.addView(topTV8);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV8);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==9) {
                        final TextView topTV9 = new TextView(Cart.this);
                        topTV9.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV9.setText("" + snapshot.child("title").getValue());
                        topTV9.setTextColor(Color.BLACK);
                        topTV9.setTextSize(30);
                        topTV9.setPadding(20, 10, 10, 10);
                        container.addView(topTV9);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV9);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }
                    else if(count==10) {
                        final TextView topTV10 = new TextView(Cart.this);
                        topTV10.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        topTV10.setText("" + snapshot.child("title").getValue());
                        topTV10.setTextColor(Color.BLACK);
                        topTV10.setTextSize(30);
                        topTV10.setPadding(20, 10, 10, 10);
                        container.addView(topTV10);
                        tot_price += Integer.parseInt("" + snapshot.child("content").getValue());
                        textView5.setText(String.valueOf(tot_price));
                        editor.putInt("tot_price", tot_price);
                        editor.commit();
                        topTV10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast = Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                tot_price= tot_price - Integer.parseInt(""+snapshot.child("content").getValue());
                                container.removeView(topTV10);
                                textView5.setText(String.valueOf(tot_price));
                                Menu_Reference.child(snapshot.getKey()).removeValue();
                                int set = pref.getInt("set",0);
                                if(set==1){
                                    editor.putInt("set2",2);
                                    editor.commit();
                                }
                                count = 0;

                            }
                        });

                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





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
String m_name=pref.getString("menu_name1","");
            Log.d("Testing1", pref.getString("menu_name1","")+","+pref.getInt("tot_price", 0));
            writer.write(
                    "cid=TC0ONETIME&" +
                            "partner_order_id=partner_order_id&" +
                            "partner_user_id=partner_user_id&" +
                            "item_name=" +
                             m_name+
                            "&" +
                            "quantity=1&" +
                            "total_amount=" +
                            String.valueOf(tot_price)+
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
        try {
            app_url= getURL(KaKaoGo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(app_url));
        startActivity(intent);



        Menu_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(count==1) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==2) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==3) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==4) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==5) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==6) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==7) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==8) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==9) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                    else if(count==10) {
                        databaseReference.push().setValue("" + snapshot.getValue());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        databaseReference.child("total_money").push().setValue(tot_price);
        SharedPreferences.Editor editor = pref.edit();
        Menu_Reference.removeValue();
        Value_Reference.removeValue();
        editor.putInt("reset", 1);
        editor.commit();
    }
}