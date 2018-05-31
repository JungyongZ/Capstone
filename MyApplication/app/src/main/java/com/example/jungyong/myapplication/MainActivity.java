package com.example.jungyong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        init();
    }

    private void init(){

        mRealm = Realm.getInstance(this);

        RealmResults<UserVO> userList = getUserList();
        Log.i(TAG,">>>>>   userList.size :  " + userList.size()); // :0



    }


    /**
     * 유저 정보 데이터 리스트 반환
     */
    private RealmResults<UserVO> getUserList(){
        return mRealm.where(UserVO.class).findAll();
    }


}
