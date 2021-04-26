package com.facebook.react;

import android.view.KeyEvent;
import android.view.View;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.e;
import java.util.Map;

/* compiled from: ReactAndroidHWInputDeviceHelper */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Integer, String> f2749a = e.c().a(23, "select").a(66, "select").a(62, "select").a(85, "playPause").a(89, "rewind").a(90, "fastForward").a(19, "up").a(22, "right").a(20, "down").a(21, "left").a();

    /* renamed from: b  reason: collision with root package name */
    private int f2750b = -1;

    /* renamed from: c  reason: collision with root package name */
    private final r f2751c;

    i(r rVar) {
        this.f2751c = rVar;
    }

    public void a(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 1 && f2749a.containsKey(Integer.valueOf(keyCode))) {
            a(f2749a.get(Integer.valueOf(keyCode)), this.f2750b);
        }
    }

    public void a(View view) {
        if (this.f2750b != view.getId()) {
            int i = this.f2750b;
            if (i != -1) {
                a("blur", i);
            }
            this.f2750b = view.getId();
            a("focus", view.getId());
        }
    }

    public void a() {
        int i = this.f2750b;
        if (i != -1) {
            a("blur", i);
        }
        this.f2750b = -1;
    }

    private void a(String str, int i) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("eventType", str);
        if (i != -1) {
            writableNativeMap.putInt("tag", i);
        }
        this.f2751c.a("onHWKeyEvent", writableNativeMap);
    }
}
