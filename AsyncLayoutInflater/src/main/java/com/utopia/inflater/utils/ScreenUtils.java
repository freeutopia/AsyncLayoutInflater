package com.utopia.inflater.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.utopia.inflater.config.Orientation;


public class ScreenUtils {
    /**
     * 获取屏幕横屏、竖屏状态
     */
    public static @Orientation int getOrientation(Context context){
        return context.getResources().getConfiguration().orientation;
    }

    /**
     * 返回屏幕宽度像素
     * @param ctx 上下文
     * @return 屏幕宽度像素
     */
    public static int getScreenWidthPixels(Context ctx) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 返回屏幕高度像素
     * @param ctx 上下文
     * @return 屏幕高度像素
     */
    public static int getScreenHeightPixels(Context ctx) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
