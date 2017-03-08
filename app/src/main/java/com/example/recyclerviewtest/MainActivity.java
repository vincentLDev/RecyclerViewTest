package com.example.recyclerviewtest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String TAG = "MainActivity";

    private List<Fruit> fruitList = new ArrayList<>();
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response_text);

        button.setOnClickListener(this);

        initFruits();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view); // 获取RecyclerView实例
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); // 创建LinearLayoutManager对象，线性布局
        recyclerView.setLayoutManager(layoutManager);  // 指定RecyclerView的布局方式，将LinearLayoutManager对象设置到RecyclerView中去

        FruitAdapter adapter = new FruitAdapter(fruitList); // 创建FruitAdapter实例，并将水果实例传入到FruitAdapter（）构造函数汇中
        recyclerView.setAdapter(adapter); // 完成适配器设置，调用RecyclerView的setAdapter（）方法

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);  // SwipeRefreshLayout，下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithOkHttp();

        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.kuaidi100.com/query?type=zhongtong&postid=427712222807")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);

//                    Log.d(TAG, "showResponse: " + responseData );

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void showResponse(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(responseData);
                Log.d(TAG, "showResponse.run(): ");

                //UI操作，将结果显示在界面上


            }
        });

    }


    private void initFruits() {  // Items 初始化
        for (int i = 0; i < 3; i++) {
            Fruit apple = new Fruit
                    ("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit
                    ("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit
                    ("orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit
                    ("watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit
                    ("pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit
                    ("grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit strawberry = new Fruit
                    ("strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit
                    ("cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit
                    ("mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }


}


















