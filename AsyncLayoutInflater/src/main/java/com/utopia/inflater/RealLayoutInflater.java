package com.utopia.inflater;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utopia.inflater.utils.ResizeView;

import org.xmlpull.v1.XmlPullParser;

import androidx.annotation.Nullable;

/**
 * 真正的布局加载类
 */
class RealLayoutInflater extends LayoutInflater {
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app."
    };

    RealLayoutInflater(Context context) {
        super(context);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new RealLayoutInflater(newContext);
    }

    @Override
    public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
        View rootView = super.inflate(parser , root, attachToRoot);
        ResizeView.autoView(rootView);
        return rootView;
    }

    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        for (String prefix : sClassPrefixList) {
            try {
                View view = createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return super.onCreateView(name, attrs);
    }
}
