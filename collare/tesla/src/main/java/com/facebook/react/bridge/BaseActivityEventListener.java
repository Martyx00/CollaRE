package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Intent;

public class BaseActivityEventListener implements ActivityEventListener {
    @Deprecated
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }
}
