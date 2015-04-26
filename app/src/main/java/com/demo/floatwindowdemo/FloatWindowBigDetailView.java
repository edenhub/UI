package com.demo.floatwindowdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.adam.pri.ui.R;

/**
 * Created by lab on 2015/4/25.
 */
public class FloatWindowBigDetailView extends LinearLayout {
    public static int viewHeight;

    public static int viewWidth;

    private ListView detailList;

    public FloatWindowBigDetailView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_window_big_detail,this);
        View view = findViewById(R.id.big_window_layout_detail);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;

        Toast.makeText(context,"Big detail",Toast.LENGTH_SHORT).show();

//        detailList = (ListView) findViewById(R.id.window_big_detail);
//        System.err.println("out==========");
//        List<String> datas = new ArrayList<>();
//        for(int i=0;i<10;i++)
//            datas.add("data_"+i);
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
//                android.R.layout.simple_list_item_1,datas);
//
//        detailList.setAdapter(dataAdapter);
    }


}
