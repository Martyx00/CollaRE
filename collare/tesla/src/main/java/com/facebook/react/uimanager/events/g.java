package com.facebook.react.uimanager.events;

import android.support.v4.util.Pools;
import android.view.MotionEvent;
import com.facebook.i.a.a;
import com.facebook.react.bridge.SoftAssertions;

/* compiled from: TouchEvent */
public class g extends c<g> {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.a<g> f3244a = new Pools.a<>(3);

    /* renamed from: b  reason: collision with root package name */
    private MotionEvent f3245b;

    /* renamed from: c  reason: collision with root package name */
    private i f3246c;

    /* renamed from: d  reason: collision with root package name */
    private short f3247d;
    private float e;
    private float f;

    public static g a(int i, i iVar, MotionEvent motionEvent, long j, float f2, float f3, h hVar) {
        g a2 = f3244a.a();
        if (a2 == null) {
            a2 = new g();
        }
        a2.b(i, iVar, motionEvent, j, f2, f3, hVar);
        return a2;
    }

    private g() {
    }

    private void b(int i, i iVar, MotionEvent motionEvent, long j, float f2, float f3, h hVar) {
        super.a(i);
        short s = 0;
        SoftAssertions.assertCondition(j != Long.MIN_VALUE, "Gesture start time must be initialized");
        int action = motionEvent.getAction() & 255;
        switch (action) {
            case 0:
                hVar.a(j);
                break;
            case 1:
                hVar.d(j);
                break;
            case 2:
                s = hVar.c(j);
                break;
            case 3:
                hVar.d(j);
                break;
            case 4:
            default:
                throw new RuntimeException("Unhandled MotionEvent action: " + action);
            case 5:
            case 6:
                hVar.b(j);
                break;
        }
        this.f3246c = iVar;
        this.f3245b = MotionEvent.obtain(motionEvent);
        this.f3247d = s;
        this.e = f2;
        this.f = f3;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void c() {
        ((MotionEvent) a.a(this.f3245b)).recycle();
        this.f3245b = null;
        f3244a.a(this);
    }

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return i.a((i) a.a(this.f3246c));
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        switch ((i) a.a(this.f3246c)) {
            case START:
            case END:
            case CANCEL:
                return false;
            case MOVE:
                return true;
            default:
                throw new RuntimeException("Unknown touch event type: " + this.f3246c);
        }
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return this.f3247d;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        j.a(rCTEventEmitter, (i) a.a(this.f3246c), d(), this);
    }

    public MotionEvent j() {
        a.a(this.f3245b);
        return this.f3245b;
    }

    public float k() {
        return this.e;
    }

    public float l() {
        return this.f;
    }
}
