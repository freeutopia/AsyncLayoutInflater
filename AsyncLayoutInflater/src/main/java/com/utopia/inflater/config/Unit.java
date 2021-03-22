package com.utopia.inflater.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * 适配像素单位
 */
@IntDef({Unit.PX, Unit.DP, Unit.SP, Unit.PT, Unit.IN, Unit.MM})
@Retention(RetentionPolicy.SOURCE)
public @interface Unit {
    int PX = 0;
    int DP = 1;
    int SP = 2;
    int PT = 3;
    int IN = 4;
    int MM = 5;
}
