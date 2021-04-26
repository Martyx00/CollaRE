package com.facebook.react.modules.vibration;

import android.annotation.SuppressLint;
import android.os.Vibrator;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.a.a;

@SuppressLint({"MissingPermission"})
@a(a = VibrationModule.NAME)
public class VibrationModule extends ReactContextBaseJavaModule {
    public static final String NAME = "Vibration";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public VibrationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void vibrate(int i) {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.vibrate((long) i);
        }
    }

    @ReactMethod
    public void vibrateByPattern(ReadableArray readableArray, int i) {
        long[] jArr = new long[readableArray.size()];
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            jArr[i2] = (long) readableArray.getInt(i2);
        }
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.vibrate(jArr, i);
        }
    }

    @ReactMethod
    public void cancel() {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
}
