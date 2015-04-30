package com.demo.floatwindowdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import example.adam.pri.ui.R;

public class FloatWindowSmallView extends LinearLayout {
    private BaiduASRDigitalDialog mDialog = null;

    private DialogRecognitionListener mRecognitionListener;

    private int mCurrentTheme = Config.DIALOG_THEME;

    /**
     * 记录小悬浮窗的宽度 
     */  
    public static int viewWidth;  
  
    /** 
     * 记录小悬浮窗的高度 
     */  
    public static int viewHeight;  
  
    /** 
     * 记录系统状态栏的高度 
     */  
     private static int statusBarHeight;  
  
    /** 
     * 用于更新小悬浮窗的位置 
     */  
    private WindowManager windowManager;
  
    /** 
     * 小悬浮窗的参数 
     */  
    private WindowManager.LayoutParams mParams;  
  
    /** 
     * 记录当前手指位置在屏幕上的横坐标值 
     */  
    private float xInScreen;  
  
    /** 
     * 记录当前手指位置在屏幕上的纵坐标值 
     */  
    private float yInScreen;  
  
    /** 
     * 记录手指按下时在屏幕上的横坐标的值 
     */  
    private float xDownInScreen;  
  
    /** 
     * 记录手指按下时在屏幕上的纵坐标的值 
     */  
    private float yDownInScreen;  
  
    /** 
     * 记录手指按下时在小悬浮窗的View上的横坐标的值 
     */  
    private float xInView;  
  
    /** 
     * 记录手指按下时在小悬浮窗的View上的纵坐标的值 
     */  
    private float yInView;  
  
    public FloatWindowSmallView(final Context context) {
        super(context);  
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        LayoutInflater.from(context).inflate(R.layout.float_window_small, this);
        View view = findViewById(R.id.small_window_layout);
        viewWidth = view.getLayoutParams().width;  
        viewHeight = view.getLayoutParams().height;
//        Button btSay = (Button) findViewById(R.id.say);
//        btSay.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Say something",Toast.LENGTH_LONG).show();
//            }
//        });
//        TextView percentView = (TextView) findViewById(R.id.percent);
//        percentView.setText(MyWindowManager.getUsedPercentValue(context));
    }  
  
    @Override  
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {  
        case MotionEvent.ACTION_DOWN:  
            // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度  
            xInView = event.getX();  
            yInView = event.getY();  
            xDownInScreen = event.getRawX();  
            yDownInScreen = event.getRawY() - getStatusBarHeight();  
            xInScreen = event.getRawX();  
            yInScreen = event.getRawY() - getStatusBarHeight();  
            break;  
        case MotionEvent.ACTION_MOVE:  
            xInScreen = event.getRawX();  
            yInScreen = event.getRawY() - getStatusBarHeight();  
            // 手指移动的时候更新小悬浮窗的位置  
            updateViewPosition();  
            break;  
        case MotionEvent.ACTION_UP:  
            // 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。  
            if (xDownInScreen == xInScreen && yDownInScreen == yInScreen) {
//                openBigWindow();
                final Context context = FloatWindowSmallView.this.getContext();
                Toast.makeText(context, "Say something voices", Toast.LENGTH_SHORT).show();
                mCurrentTheme = Config.DIALOG_THEME;
                if (mDialog != null) {
                    mDialog.dismiss();
                }
                Bundle params = new Bundle();
                params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, Constants.API_KEY);
                params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, Constants.SECRET_KEY);
                params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, Config.DIALOG_THEME);
                mDialog = new BaiduASRDigitalDialog(context, params);
                mDialog.setDialogRecognitionListener(mRecognitionListener);
//                }
                mDialog.getParams().putInt(BaiduASRDigitalDialog.PARAM_PROP, Config.CURRENT_PROP);
                mDialog.getParams().putString(BaiduASRDigitalDialog.PARAM_LANGUAGE,
                        Config.getCurrentLanguage());
                Log.e("DEBUG", "Config.PLAY_START_SOUND = "+Config.PLAY_START_SOUND);
                mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_START_TONE_ENABLE, Config.PLAY_START_SOUND);
                mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_END_TONE_ENABLE, Config.PLAY_END_SOUND);
                mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_TIPS_TONE_ENABLE, Config.DIALOG_TIPS_SOUND);
                mDialog.show();
            }
            break;  
        default:  
            break;  
        }  
        return true;  
    }



    /**
     * 将小悬浮窗的参数传入，用于更新小悬浮窗的位置。 
     *  
     * @param params 
     *            小悬浮窗的参数 
     */  
    public void setParams(WindowManager.LayoutParams params) {  
        mParams = params;  
    }  
  
    /** 
     * 更新小悬浮窗在屏幕中的位置。 
     */  
    private void updateViewPosition() {  
        mParams.x = (int) (xInScreen - xInView);  
        mParams.y = (int) (yInScreen - yInView);  
        windowManager.updateViewLayout(this, mParams);  
    }  
  
    /** 
     * 打开大悬浮窗，同时关闭小悬浮窗。 
     */  
    private void openBigWindow() {  
        MyWindowManager.createBigWindow(getContext());  
        MyWindowManager.removeSmallWindow(getContext());  
    }  
  
    /** 
     * 用于获取状态栏的高度。 
     *  
     * @return 返回状态栏高度的像素值。 
     */  
    private int getStatusBarHeight() {  
        if (statusBarHeight == 0) {  
            try {  
                Class<?> c = Class.forName("com.android.internal.R$dimen");  
                Object o = c.newInstance();  
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);  
                statusBarHeight = getResources().getDimensionPixelSize(x);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return statusBarHeight;  
    }
}