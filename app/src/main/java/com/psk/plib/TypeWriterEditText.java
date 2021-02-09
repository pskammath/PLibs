package com.psk.plib;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

public class TypeWriterEditText extends androidx.appcompat.widget.AppCompatTextView {

    private final Handler mHandler = new Handler();
    private CharSequence mText;
    private int mIndex;
    private long mDelay = 500; //Default 500ms delay
    private final Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public TypeWriterEditText(Context context) {
        super(context);
    }

    public TypeWriterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }
}