package com.utopia.inflater;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utopia.inflater.callback.OnInflateFinishedListener;

import androidx.annotation.LayoutRes;

/**
 * 用来在子线程< -- >到主线程之间的布局传递实体
 */
final class InflateRequest {
    Handler handler;//线程调度（布局加载完成，需要回调到主线程）
    LayoutInflater inflater;//真正的布局加载器(实现具体的xml到view解析工作)
    ViewGroup parent;//需要将layout加载到的父布局
    @LayoutRes int resid;//具体layout
    View view;//resid从xml转换成的最终java布局
    OnInflateFinishedListener callback;//布局加载完成回调
}
