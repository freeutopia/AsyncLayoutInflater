package com.utopia.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.utopia.inflater.AsyncLayoutInflater;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AsyncLayoutInflater.from(this).inflate(R.layout.activity_main, this::setContentView);
    }

    private void loadSpeedTest() {
        new Thread(()->{
            CountDownLatch downLatch = new CountDownLatch(100);
            long startTime = System.currentTimeMillis();
            for (int i = 0 ; i < 100 ; i++) {
                AsyncLayoutInflater.from(this).inflate(R.layout.activity_main, (view) -> {
                    downLatch.countDown();
                });
            }

            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("test", "异步装载布局耗时：" + (System.currentTimeMillis() - startTime) + "ms");


            long startTime2 = System.currentTimeMillis();
            for (int i = 0 ; i < 100 ; i++){
                getLayoutInflater().inflate(R.layout.activity_main,null);
            }
            Log.e("test","同步装载布局耗时："+(System.currentTimeMillis()-startTime2)+"ms");
        }).start();
    }
}