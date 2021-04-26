package com.facebook.react.modules.debug;

import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.d;

/* compiled from: DidJSUpdateUiDuringFrameDetector */
public class a implements NotThreadSafeBridgeIdleDebugListener, com.facebook.react.uimanager.b.a {

    /* renamed from: a  reason: collision with root package name */
    private final d f2852a = d.a(20);

    /* renamed from: b  reason: collision with root package name */
    private final d f2853b = d.a(20);

    /* renamed from: c  reason: collision with root package name */
    private final d f2854c = d.a(20);

    /* renamed from: d  reason: collision with root package name */
    private final d f2855d = d.a(20);
    private volatile boolean e = true;

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeIdle() {
        this.f2852a.a(System.nanoTime());
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeBusy() {
        this.f2853b.a(System.nanoTime());
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onBridgeDestroyed() {
    }

    @Override // com.facebook.react.uimanager.b.a
    public synchronized void a() {
        this.f2854c.a(System.nanoTime());
    }

    @Override // com.facebook.react.uimanager.b.a
    public synchronized void b() {
        this.f2855d.a(System.nanoTime());
    }

    public synchronized boolean a(long j, long j2) {
        boolean z;
        boolean a2 = a(this.f2855d, j, j2);
        boolean b2 = b(j, j2);
        z = true;
        if (!a2) {
            if (!b2 || a(this.f2854c, j, j2)) {
                z = false;
            }
        }
        a(this.f2852a, j2);
        a(this.f2853b, j2);
        a(this.f2854c, j2);
        a(this.f2855d, j2);
        this.e = b2;
        return z;
    }

    private static boolean a(d dVar, long j, long j2) {
        for (int i = 0; i < dVar.a(); i++) {
            long b2 = dVar.b(i);
            if (b2 >= j && b2 < j2) {
                return true;
            }
        }
        return false;
    }

    private static long b(d dVar, long j, long j2) {
        long j3 = -1;
        for (int i = 0; i < dVar.a(); i++) {
            long b2 = dVar.b(i);
            if (b2 >= j && b2 < j2) {
                j3 = b2;
            } else if (b2 >= j2) {
                break;
            }
        }
        return j3;
    }

    private boolean b(long j, long j2) {
        long b2 = b(this.f2852a, j, j2);
        long b3 = b(this.f2853b, j, j2);
        if (b2 == -1 && b3 == -1) {
            return this.e;
        }
        return b2 > b3;
    }

    private static void a(d dVar, long j) {
        int a2 = dVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a2; i2++) {
            if (dVar.b(i2) < j) {
                i++;
            }
        }
        if (i > 0) {
            for (int i3 = 0; i3 < a2 - i; i3++) {
                dVar.a(i3, dVar.b(i3 + i));
            }
            dVar.c(i);
        }
    }
}
