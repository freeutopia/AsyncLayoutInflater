package com.utopia.inflater.utils;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.utopia.inflater.R;
import com.utopia.inflater.config.LayoutInflaterConfig;

/**
 * 用来递归适配布局
 */
public class ResizeView {
    /**
     * 拿到一个布局开始递归适配
     */
    public static void autoView(View rootView) {
        if (rootView instanceof ViewGroup){//如果是ViewGroup，需要递归适配子View
            ViewGroup viewGroup = (ViewGroup)rootView;
            for (int i = 0 ; i < viewGroup.getChildCount() ;i++){
                View childView = viewGroup.getChildAt(i);
                autoInnerView(childView);
                autoView(childView);
            }
        }else{
            autoInnerView(rootView);
        }
    }

    public static void autoInnerView(View view) {
        final LayoutInflaterConfig config = LayoutInflaterConfig.getInstance();

        //不具备适配条件
        if (config == null || view == null) {
            return;
        }

        int rotation = ScreenUtils.getOrientation(view.getContext());

        //已完成适配不需要重复适配
        Object alreadyAdapter = view.getTag(R.id.auto_async_layout_inflater);
        if (alreadyAdapter != null && Integer.parseInt(alreadyAdapter.toString()) == rotation) {
            return ;
        }

        autoViewParam(view, rotation, config.getHRatio(), config.getVRatio());
    }

    /**
     * 开始调整ViewParam
     */
    private static void autoViewParam(View view, int rotation, float hRatio, float vRatio) {
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, ((TextView) view).getTextSize() * Math.min(hRatio, vRatio));
        }

        view.setPadding((int) (view.getPaddingLeft() * hRatio)
                , (int) (view.getPaddingTop() * vRatio)
                , (int) (view.getPaddingRight() * hRatio)
                , (int) (view.getPaddingBottom() * vRatio));

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        autoLayout(layoutParams);
        view.setLayoutParams(layoutParams);

        view.setTag(R.id.auto_async_layout_inflater, rotation);
    }

    /**
     * 开始调整LayoutParams
     */
    public static void autoLayout(ViewGroup.LayoutParams lp) {
        final LayoutInflaterConfig layoutInflaterConfig = LayoutInflaterConfig.getInstance();
        if (layoutInflaterConfig == null || lp == null) {
            return;
        }

        final float hRatio = layoutInflaterConfig.getHRatio();
        final float vRatio = layoutInflaterConfig.getVRatio();

        if (lp.width != -1 && lp.width != -2) {
            lp.width = (int) (lp.width * hRatio);
        }
        if (lp.height != -1 && lp.height != -2) {
            lp.height = (int) (lp.height * vRatio);
        }
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            final ViewGroup.MarginLayoutParams mplp = (ViewGroup.MarginLayoutParams) lp;
            mplp.leftMargin = (int) (mplp.leftMargin * hRatio);
            mplp.rightMargin = (int) (mplp.rightMargin * hRatio);
            mplp.topMargin = (int) (mplp.topMargin * vRatio);
            mplp.bottomMargin = (int) (mplp.bottomMargin * vRatio );
        }
    }
}

