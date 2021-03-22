package com.utopia.inflater.config;

import android.content.Context;
import com.utopia.inflater.utils.DimensionUtils;
import com.utopia.inflater.utils.ScreenUtils;


public class LayoutInflaterConfig {
    private static volatile LayoutInflaterConfig mInstance;
    private final @OrientationMode  int orientation;//屏幕方向，横屏or竖屏
    private final @Unit int unit;//像素单位
    private final float designWidth;//设计图宽度
    private final float designHeight;//设计图高度

    private float hRatio;//水平比例
    private float vRatio;//垂直比例

    public static LayoutInflaterConfig getInstance() {
        return mInstance;
    }

    private LayoutInflaterConfig(Builder builder) {
        this.designWidth = builder.designWidth;
        this.designHeight = builder.designHeight;
        this.orientation = builder.orientation;
        this.unit = builder.unit;

        calculateRatio(builder.context);//计算比例
    }


    public float getHRatio() {
        return hRatio;
    }

    public float getVRatio() {
        return vRatio;
    }


    private void calculateRatio(Context context) {
        switch (orientation) {
            case OrientationMode.VERTICAL:
                hRatio = vRatio = ScreenUtils.getScreenHeightPixels(context) / DimensionUtils.transToPx(context, designHeight, unit);
                break;
            case OrientationMode.HORIZONTAL:
                vRatio = hRatio = ScreenUtils.getScreenWidthPixels(context) / DimensionUtils.transToPx(context, designWidth, unit);
                break;

            case OrientationMode.BOTH:
                hRatio = ScreenUtils.getScreenWidthPixels(context) / DimensionUtils.transToPx(context, designWidth, unit);
                vRatio = ScreenUtils.getScreenHeightPixels(context) / DimensionUtils.transToPx(context, designHeight, unit);
                break;
        }
    }

    public static class Builder {
        private @OrientationMode
        int orientation;//适配方向
        private @Unit int unit;//像素单位
        private float designWidth;//设计图宽
        private float designHeight;//设计图高
        private Context context;//上下文

        /**
         * 设计图尺寸
         */
        public Builder designSize(float designWidth, float designHeight, @Unit int unit) {
            this.designWidth = designWidth;
            this.designHeight = designHeight;
            this.unit = unit;
            return this;
        }

        /**
         * 适配方向
         */
        public Builder orientation(@OrientationMode int orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder context(Context context){
            this.context = context;
            return this;
        }

        public void build() {
            if (context == null){
                throw new NullPointerException("context 是必传参数！");
            }
            LayoutInflaterConfig.mInstance = new LayoutInflaterConfig(this);
        }
    }

}

