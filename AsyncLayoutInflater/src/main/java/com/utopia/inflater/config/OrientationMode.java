package com.utopia.inflater.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * 布局适配方向
 */
@IntDef({OrientationMode.HORIZONTAL, OrientationMode.VERTICAL,OrientationMode.BOTH})
@Retention(RetentionPolicy.SOURCE)
public @interface OrientationMode {
    int HORIZONTAL = 0;
    int VERTICAL = 1;
    int BOTH = 2;
}
