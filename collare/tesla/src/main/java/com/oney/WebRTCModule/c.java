package com.oney.WebRTCModule;

import android.os.Build;
import android.util.Log;
import org.webrtc.EglBase;

/* compiled from: EglUtils */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static EglBase f4707a;

    public static synchronized EglBase a() {
        EglBase eglBase;
        RuntimeException e;
        synchronized (c.class) {
            if (f4707a == null) {
                int[] iArr = EglBase.CONFIG_PLAIN;
                EglBase eglBase2 = null;
                try {
                    e = null;
                    eglBase2 = Build.VERSION.SDK_INT >= 18 ? EglBase.CC.createEgl14(iArr) : null;
                } catch (RuntimeException e2) {
                    e = e2;
                }
                if (eglBase2 == null) {
                    try {
                        eglBase2 = EglBase.CC.createEgl10(iArr);
                    } catch (RuntimeException e3) {
                        e = e3;
                    }
                }
                if (e != null) {
                    Log.e(c.class.getName(), "Failed to create EglBase", e);
                } else {
                    f4707a = eglBase2;
                }
            }
            eglBase = f4707a;
        }
        return eglBase;
    }

    public static EglBase.Context b() {
        EglBase a2 = a();
        if (a2 == null) {
            return null;
        }
        return a2.getEglBaseContext();
    }
}
