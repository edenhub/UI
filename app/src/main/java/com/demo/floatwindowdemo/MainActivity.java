package com.demo.floatwindowdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.adam.pri.ui.R;

/**
 * Created by lab on 2015/4/25.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startFloatWindow = (Button) findViewById(R.id.start_float_window);
        startFloatWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
                startService(intent);
                finish();
            }
        });

        Button startBigWindow = (Button)findViewById(R.id.start_big_window);
        startBigWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWindowManager.createBigWindow(getApplicationContext());
            }
        });
    }
}
