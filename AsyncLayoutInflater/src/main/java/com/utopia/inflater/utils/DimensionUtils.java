package com.utopia.inflater.utils;

import android.content.Context;
import android.util.TypedValue;

import com.utopia.inflater.config.Unit;

/**
 * 分辨率单位换算相关帮助了类
 */
public class DimensionUtils {

    private DimensionUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static float dp2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics()) + 0.5f;
    }

    public static float sp2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics()) + 0.5f;
    }

    public static float pt2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, context.getResources().getDisplayMetrics()) + 0.5f;
    }

    public static float in2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN, value, context.getResources().getDisplayMetrics()) + 0.5f;
    }

    public static float mm2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, value, context.getResources().getDisplayMetrics()) + 0.5f;
    }

    public static float transToPx(Context context, float value , @Unit int unit){
        float result = 0 ;
        switch (unit) {
            case Unit.DP:
                result = dp2px(context, value);
                break;
            case Unit.PX:
                result = value;
                break;
            case Unit.IN:
                result = in2px(context, value);
                break;
            case Unit.MM:
                result = mm2px(context, value);
                break;
            case Unit.PT:
                result = pt2px(context, value);
                break;
            case Unit.SP:
                result = sp2px(context, value);
                break;
        }
        return result;
    }
}
