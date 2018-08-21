package com.example.administrator.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MyView extends View {
    public static final String TAG = "MyView";
    private Paint mPaint;
    private float x,y;
    private int radio;
    private float m;
    private int n;

    public MyView(Context context) {
        super(context);
        initPaint();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);//设置画笔模式
        mPaint.setAntiAlias(false);//抗锯齿
        mPaint.setStrokeWidth(radio/2);
        mPaint.setAlpha(n);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"点击了");
                radio=40;
                n=255;
                //获取点击的坐标位置
                x = event.getX();
                y = event.getY();
                flushPaint();
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e(TAG,"点击了移动");
                if (event.getX() - x >= 10 || event.getY() -y >= 10){
                    Log.e(TAG,"点击了移动");
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flushPaint();
        }
    };

    private void flushPaint() {
        radio=radio+10;
        n=n-20;
        if (n <= 20){
            n=0;
            initPaint();
            invalidate();
        }else {
            initPaint();
            invalidate();
            handler.sendEmptyMessageDelayed(0,50);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x,y,radio,mPaint);
    }

}
