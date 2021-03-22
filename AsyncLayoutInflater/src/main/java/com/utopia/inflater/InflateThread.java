package com.utopia.inflater;

import android.os.Message;

import java.util.concurrent.ArrayBlockingQueue;
import androidx.core.util.Pools;

class InflateThread extends Thread {
    private static final InflateThread sInstance;
    static {
        sInstance = new InflateThread();
        sInstance.start();
    }

    static InflateThread getInstance() {
        return sInstance;
    }

    private final ArrayBlockingQueue<InflateRequest> mQueue = new ArrayBlockingQueue<>(100);
    private final Pools.SynchronizedPool<InflateRequest> mRequestPool = new Pools.SynchronizedPool<>(100);


    @Override
    public void run() {
        while (true) {
            InflateRequest request;
            try {
                request = mQueue.take();
                request.view = request.inflater.inflate(request.resid, request.parent, false);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            Message.obtain(request.handler, 0, request).sendToTarget();
        }
    }

    InflateRequest obtainRequest() {
        InflateRequest obj = mRequestPool.acquire();
        if (obj == null) {
            obj = new InflateRequest();
        }
        return obj;
    }

    void releaseRequest(InflateRequest obj) {
        obj.callback = null;
        obj.inflater = null;
        obj.parent = null;
        obj.resid = 0;
        obj.view = null;
        mRequestPool.release(obj);
    }

    void enqueue(InflateRequest request) {
        mQueue.add(request);
    }
}
