package com.utopia.inflater.config;


import androidx.annotation.IntDef;

/**
 * 屏幕方向
 */
@IntDef(value = {
        Orientation.ORIENTATION_UNDEFINED,
        Orientation.ORIENTATION_PORTRAIT,
        Orientation.ORIENTATION_LANDSCAPE
})
public @interface Orientation {
    int ORIENTATION_UNDEFINED = 0;
    int ORIENTATION_PORTRAIT = 1;
    int ORIENTATION_LANDSCAPE = 2;
}
