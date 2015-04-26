package com.demo.floatwindowdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import example.adam.pri.ui.R;

public class FloatWindowBigView extends LinearLayout {
  
    /** 
     * 记录大悬浮窗的宽度 
     */  
    public static int viewWidth;  
  
    /** 
     * 记录大悬浮窗的高度 
     */  
    public static int viewHeight;


  
    public FloatWindowBigView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_window_big_detail,this);
        View view = findViewById(R.id.big_window_layout_detail);
//        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
//        View view = findViewById(R.id.big_window_layout);
        viewWidth = view.getLayoutParams().width;  
        viewHeight = view.getLayoutParams().height;  
//        Button close = (Button) findViewById(R.id.close);


            ListView detailList = (ListView) findViewById(R.id.listviewe_v);
        System.err.println("out==========");
        List<String> datas = new ArrayList<>();
        for(int i=0;i<10;i++)
            datas.add("data_"+i);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,datas);

//        detailList.setAdapter(dataAdapter);
        Button back = (Button) findViewById(R.id.back);  
//        close.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 点击关闭悬浮窗的时候，移除所有悬浮窗，并停止Service
//                MyWindowManager.removeBigWindow(context);
//                MyWindowManager.removeSmallWindow(context);
//                Intent intent = new Intent(getContext(), FloatWindowService.class);
//                context.stopService(intent);
//            }
//        });
//        back.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 点击返回的时候，移除大悬浮窗，创建小悬浮窗
//                MyWindowManager.removeBigWindow(context);
//                MyWindowManager.createSmallWindow(context);
//            }
//        });
    }  
} 