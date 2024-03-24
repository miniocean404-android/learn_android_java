package com.example.learnandroidjava.components;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

/**
 * 自定义 TextView 组件
 */
public class CustomMyTextView extends androidx.appcompat.widget.AppCompatTextView {

    public CustomMyTextView(Context context) {
        super(context);
    }

    public CustomMyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
