package com.example.jungyong.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity {
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BtnOnclickListener onclickListener = new BtnOnclickListener();

        ImageButton bt1 = (ImageButton) findViewById(R.id.b1);
        bt1.setOnClickListener(onclickListener);
        ImageButton bt2 = (ImageButton) findViewById(R.id.b2);
        bt2.setOnClickListener(onclickListener);
        ImageButton bt3 = (ImageButton) findViewById(R.id.b3);
        bt3.setOnClickListener(onclickListener);
        ImageButton bt4 = (ImageButton) findViewById(R.id.b4);
        bt4.setOnClickListener(onclickListener);
        ImageButton bt5 = (ImageButton) findViewById(R.id.b5);
        bt5.setOnClickListener(onclickListener);
        ImageButton bt6 = (ImageButton) findViewById(R.id.b6);
        bt6.setOnClickListener(onclickListener);
        int permissionCheck = ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(StartActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            Log.v("ok","ok2");
        }
    }
    class BtnOnclickListener implements ImageButton.OnClickListener {
        @Override
        public void onClick(View v) {
            // ImageButton btn = (ImageButton) findViewById(R.id.b1);
            switch (v.getId()) {
                case R.id.b1:
                    intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MainActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                    break;
                case R.id.b2:
                    intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MainActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                    break;
                case R.id.b3:
                    intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MainActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                    break;
                case R.id.b4:
                    intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MainActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                    break;
                case R.id.b5:
                    intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MainActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                    break;
                case R.id.b6:
                    intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MainActivity.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                    break;

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults[0] == 0) {
                Toast.makeText(this, "위치승인", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "거절됨", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
