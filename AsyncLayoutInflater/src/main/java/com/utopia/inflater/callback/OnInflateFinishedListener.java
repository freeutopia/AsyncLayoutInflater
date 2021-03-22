package com.utopia.inflater.callback;

import android.view.View;

import androidx.annotation.NonNull;

public interface OnInflateFinishedListener {
    void onFinished(@NonNull View view);
}
