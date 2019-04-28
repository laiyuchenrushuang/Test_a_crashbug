package com.example.laiji.test_a_crash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String TAG = "lylog";
    public static MyListView mListview;
    Button bt_crash;
    Button bt_add;
    Button bt_new;
    MyAdapter myAdapter;
  public static  List<Integer> list_data = new ArrayList();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences s = getSharedPreferences("result_data",0);
        if(null != s.getString("data","0")) {
            Gson gson = new Gson();
            String json = s.getString("data","0");
            Log.d("lylog",json);
            Type type = new TypeToken<List<Integer>>() {
            }.getType();
            Log.d("lylog",gson.fromJson(json, type).toString());
            list_data = gson.fromJson(json, type);
            count = list_data.size();
        }
        initView();
        initEvent();
    }

    private void insertData(int i) {
        list_data.add(111111 * i);

    }

    private void initEvent() {
        bt_crash.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_new.setOnClickListener(this);
    }

    private void initView() {
        mListview = findViewById(R.id.list);
        bt_new = findViewById(R.id.bt_new);
        myAdapter = new MyAdapter(this, list_data);
        mListview.setAdapter(myAdapter);
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
                bt_crash.setOnClickListener(this);
                break;
            case R.id.bt_new:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
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
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
