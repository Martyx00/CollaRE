package com.facebook.react.bridge;

import android.content.Context;

public class ReactApplicationContext extends ReactContext {
    public ReactApplicationContext(Context context) {
        super(context.getApplicationContext());
    }
}
