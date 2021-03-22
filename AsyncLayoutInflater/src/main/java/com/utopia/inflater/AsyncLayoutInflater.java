package com.utopia.inflater;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.utopia.inflater.callback.OnInflateFinishedListener;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

/**
 * LayoutInflater Decorator，使用线程和Handler完成layout异步加载回调
 */
    public final class AsyncLayoutInflater {
    private final LayoutInflater mInflater;
    final Handler mHandler;
    private InflateThread mInflateThread = null;

    public static AsyncLayoutInflater from(@NonNull Context context) {
        return new AsyncLayoutInflater(context);
    }

    private AsyncLayoutInflater(@NonNull Context context) {
        mInflater = new RealLayoutInflater(context);
        Handler.Callback callback = msg -> {
            InflateRequest request = (InflateRequest) msg.obj;
            if (request.view == null) {
                request.view = mInflater.inflate(request.resid, request.parent, false);
            }
            request.callback.onFinished(request.view);
            mInflateThread.releaseRequest(request);
            return true;
        };
        mHandler = new Handler(Looper.getMainLooper(), callback);
        mInflateThread = InflateThread.getInstance();
    }

    @UiThread
    public void inflate(@LayoutRes int resid, ViewGroup parent, OnInflateFinishedListener callback) {
        InflateRequest request = mInflateThread.obtainRequest();
        request.inflater = mInflater;
        request.resid = resid;
        request.parent = parent;
        request.callback = callback;
        request.handler = mHandler;
        mInflateThread.enqueue(request);
    }

    @UiThread
    public void inflate(@LayoutRes int resid, OnInflateFinishedListener callback) {
        inflate(resid,null,callback);
    }
}