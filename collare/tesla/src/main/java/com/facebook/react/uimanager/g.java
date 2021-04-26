package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.common.e.a;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.uimanager.events.h;
import com.facebook.react.uimanager.events.i;

/* compiled from: JSTouchDispatcher */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private int f3256a = -1;

    /* renamed from: b  reason: collision with root package name */
    private final float[] f3257b = new float[2];

    /* renamed from: c  reason: collision with root package name */
    private boolean f3258c = false;

    /* renamed from: d  reason: collision with root package name */
    private long f3259d = Long.MIN_VALUE;
    private final ViewGroup e;
    private final h f = new h();

    public g(ViewGroup viewGroup) {
        this.e = viewGroup;
    }

    public void a(MotionEvent motionEvent, d dVar) {
        if (!this.f3258c) {
            c(motionEvent, dVar);
            this.f3258c = true;
            this.f3256a = -1;
        }
    }

    public void b(MotionEvent motionEvent, d dVar) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.f3256a != -1) {
                a.d("ReactNative", "Got DOWN touch before receiving UP or CANCEL from last gesture");
            }
            this.f3258c = false;
            this.f3259d = motionEvent.getEventTime();
            this.f3256a = a(motionEvent);
            int i = this.f3256a;
            i iVar = i.START;
            long j = this.f3259d;
            float[] fArr = this.f3257b;
            dVar.a(com.facebook.react.uimanager.events.g.a(i, iVar, motionEvent, j, fArr[0], fArr[1], this.f));
        } else if (!this.f3258c) {
            int i2 = this.f3256a;
            if (i2 == -1) {
                a.d("ReactNative", "Unexpected state: received touch event but didn't get starting ACTION_DOWN for this gesture before");
            } else if (action == 1) {
                a(motionEvent);
                int i3 = this.f3256a;
                i iVar2 = i.END;
                long j2 = this.f3259d;
                float[] fArr2 = this.f3257b;
                dVar.a(com.facebook.react.uimanager.events.g.a(i3, iVar2, motionEvent, j2, fArr2[0], fArr2[1], this.f));
                this.f3256a = -1;
                this.f3259d = Long.MIN_VALUE;
            } else if (action == 2) {
                a(motionEvent);
                int i4 = this.f3256a;
                i iVar3 = i.MOVE;
                long j3 = this.f3259d;
                float[] fArr3 = this.f3257b;
                dVar.a(com.facebook.react.uimanager.events.g.a(i4, iVar3, motionEvent, j3, fArr3[0], fArr3[1], this.f));
            } else if (action == 5) {
                i iVar4 = i.START;
                long j4 = this.f3259d;
                float[] fArr4 = this.f3257b;
                dVar.a(com.facebook.react.uimanager.events.g.a(i2, iVar4, motionEvent, j4, fArr4[0], fArr4[1], this.f));
            } else if (action == 6) {
                i iVar5 = i.END;
                long j5 = this.f3259d;
                float[] fArr5 = this.f3257b;
                dVar.a(com.facebook.react.uimanager.events.g.a(i2, iVar5, motionEvent, j5, fArr5[0], fArr5[1], this.f));
            } else if (action == 3) {
                if (this.f.e(motionEvent.getDownTime())) {
                    c(motionEvent, dVar);
                } else {
                    a.d("ReactNative", "Received an ACTION_CANCEL touch event for which we have no corresponding ACTION_DOWN");
                }
                this.f3256a = -1;
                this.f3259d = Long.MIN_VALUE;
            } else {
                a.c("ReactNative", "Warning : touch event was ignored. Action=" + action + " Target=" + this.f3256a);
            }
        }
    }

    private int a(MotionEvent motionEvent) {
        return ag.a(motionEvent.getX(), motionEvent.getY(), this.e, this.f3257b, (int[]) null);
    }

    private void c(MotionEvent motionEvent, d dVar) {
        if (this.f3256a == -1) {
            a.c("ReactNative", "Can't cancel already finished gesture. Is a child View trying to start a gesture from an UP/CANCEL event?");
            return;
        }
        com.facebook.i.a.a.a(!this.f3258c, "Expected to not have already sent a cancel for this gesture");
        int i = this.f3256a;
        i iVar = i.CANCEL;
        long j = this.f3259d;
        float[] fArr = this.f3257b;
        ((d) com.facebook.i.a.a.a(dVar)).a(com.facebook.react.uimanager.events.g.a(i, iVar, motionEvent, j, fArr[0], fArr[1], this.f));
    }
}
