package com.example.laiji.test_a_crash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    public String TAG = "lylog";
    public static MyListView mListview;
    Button bt_crash;
    Button bt_add;
    Button bt_clear;
    MyAdapter myAdapter;
    SharedPreferences s;
    public static List<Integer> list_data = new ArrayList();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPhoneInfo();
        s = getSharedPreferences("result_data", MODE_PRIVATE);
        if (null != s && !s.getString("data", "").isEmpty()) {
            String json = s.getString("data", "");
            list_data = HttpResultUtil.getlist(json);
            count = list_data.size();
        }else{
            list_data.add(000);
            count ++;
        }
        initView();
        initEvent();
    }

    private void getPhoneInfo() {
        Log.d(TAG,"Build.MANUFACTURER ="+Build.MANUFACTURER
        +"  Build.PRODUCT="+ Build.PRODUCT
                +" Build.BRAND ="+Build.BRAND
                +" Build.MODEL ="+Build.MODEL
        );
    }

    private void insertData(int i) {
        list_data.add(111111 * i);

    }

    private void initEvent() {
        bt_crash.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_clear.setOnClickListener(this);
    }

    private void initView() {
        mListview = findViewById(R.id.list);
        bt_clear = findViewById(R.id.bt_clear);
        Log.d("lylog","list_data ="+list_data);
        if (list_data != null) {
            myAdapter = new MyAdapter(this, list_data);
            mListview.setAdapter(myAdapter);
        }
        bt_add = findViewById(R.id.bt_adddata);
        bt_crash = findViewById(R.id.bt_crash);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_adddata:
                insertData(count);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.notifyDataSetChanged();
                    }
                });
                count++;
                break;
            case R.id.bt_crash:
                bt_crash = null;
                bt_crash.setText("----oo----");
                // throw new NullPointerException();  //神奇的测试
                android.os.Process.killProcess(Process.myPid());
                System.exit(0);
                break;
            case R.id.bt_clear:
                SharedPreferences share = getSharedPreferences("result_data",MODE_PRIVATE);
                SharedPreferences.Editor e = share.edit();
                String clearString = share.getString("data","");
                showToast(list_data.toString());
                e.clear();
                e.commit();
                count = 0;
                list_data.clear();
                myAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void showToast(String clearString) {
        Toast.makeText(this,clearString,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("lylog", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
