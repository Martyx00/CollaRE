package com.facebook.f.g;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* compiled from: GestureDetector */
public class a {

    /* renamed from: a  reason: collision with root package name */
    AbstractC0046a f1965a;

    /* renamed from: b  reason: collision with root package name */
    final float f1966b;

    /* renamed from: c  reason: collision with root package name */
    boolean f1967c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1968d;
    long e;
    float f;
    float g;

    /* renamed from: com.facebook.f.g.a$a  reason: collision with other inner class name */
    /* compiled from: GestureDetector */
    public interface AbstractC0046a {
        boolean p();
    }

    public a(Context context) {
        this.f1966b = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        a();
    }

    public static a a(Context context) {
        return new a(context);
    }

    public void a() {
        this.f1965a = null;
        b();
    }

    public void b() {
        this.f1967c = false;
        this.f1968d = false;
    }

    public void a(AbstractC0046a aVar) {
        this.f1965a = aVar;
    }

    public boolean c() {
        return this.f1967c;
    }

    public boolean a(MotionEvent motionEvent) {
        AbstractC0046a aVar;
        switch (motionEvent.getAction()) {
            case 0:
                this.f1967c = true;
                this.f1968d = true;
                this.e = motionEvent.getEventTime();
                this.f = motionEvent.getX();
                this.g = motionEvent.getY();
                break;
            case 1:
                this.f1967c = false;
                if (Math.abs(motionEvent.getX() - this.f) > this.f1966b || Math.abs(motionEvent.getY() - this.g) > this.f1966b) {
                    this.f1968d = false;
                }
                if (this.f1968d && motionEvent.getEventTime() - this.e <= ((long) ViewConfiguration.getLongPressTimeout()) && (aVar = this.f1965a) != null) {
                    aVar.p();
                }
                this.f1968d = false;
                break;
            case 2:
                if (Math.abs(motionEvent.getX() - this.f) > this.f1966b || Math.abs(motionEvent.getY() - this.g) > this.f1966b) {
                    this.f1968d = false;
                    break;
                }
            case 3:
                this.f1967c = false;
                this.f1968d = false;
                break;
        }
        return true;
    }
}
