package android.support.v4.g;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* compiled from: GestureDetectorCompat */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final a f481a;

    /* compiled from: GestureDetectorCompat */
    interface a {
        boolean a(MotionEvent motionEvent);
    }

    /* compiled from: GestureDetectorCompat */
    static class b implements a {
        private static final int j = ViewConfiguration.getLongPressTimeout();
        private static final int k = ViewConfiguration.getTapTimeout();
        private static final int l = ViewConfiguration.getDoubleTapTimeout();

        /* renamed from: a  reason: collision with root package name */
        final GestureDetector.OnGestureListener f482a;

        /* renamed from: b  reason: collision with root package name */
        GestureDetector.OnDoubleTapListener f483b;

        /* renamed from: c  reason: collision with root package name */
        boolean f484c;

        /* renamed from: d  reason: collision with root package name */
        boolean f485d;
        MotionEvent e;
        private int f;
        private int g;
        private int h;
        private int i;
        private final Handler m;
        private boolean n;
        private boolean o;
        private boolean p;
        private MotionEvent q;
        private boolean r;
        private float s;
        private float t;
        private float u;
        private float v;
        private boolean w;
        private VelocityTracker x;

        /* compiled from: GestureDetectorCompat */
        private class a extends Handler {
            a() {
            }

            a(Handler handler) {
                super(handler.getLooper());
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        b.this.f482a.onShowPress(b.this.e);
                        return;
                    case 2:
                        b.this.a();
                        return;
                    case 3:
                        if (b.this.f483b == null) {
                            return;
                        }
                        if (!b.this.f484c) {
                            b.this.f483b.onSingleTapConfirmed(b.this.e);
                            return;
                        } else {
                            b.this.f485d = true;
                            return;
                        }
                    default:
                        throw new RuntimeException("Unknown message " + message);
                }
            }
        }

        b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.m = new a(handler);
            } else {
                this.m = new a();
            }
            this.f482a = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                a((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            a(context);
        }

        private void a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            } else if (this.f482a != null) {
                this.w = true;
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
                int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
                this.h = viewConfiguration.getScaledMinimumFlingVelocity();
                this.i = viewConfiguration.getScaledMaximumFlingVelocity();
                this.f = scaledTouchSlop * scaledTouchSlop;
                this.g = scaledDoubleTapSlop * scaledDoubleTapSlop;
            } else {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
        }

        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.f483b = onDoubleTapListener;
        }

        /* JADX WARNING: Removed duplicated region for block: B:93:0x01ff  */
        /* JADX WARNING: Removed duplicated region for block: B:96:0x0216  */
        @Override // android.support.v4.g.d.a
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(android.view.MotionEvent r12) {
            /*
            // Method dump skipped, instructions count: 600
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.g.d.b.a(android.view.MotionEvent):boolean");
        }

        private void b() {
            this.m.removeMessages(1);
            this.m.removeMessages(2);
            this.m.removeMessages(3);
            this.x.recycle();
            this.x = null;
            this.r = false;
            this.f484c = false;
            this.o = false;
            this.p = false;
            this.f485d = false;
            if (this.n) {
                this.n = false;
            }
        }

        private void c() {
            this.m.removeMessages(1);
            this.m.removeMessages(2);
            this.m.removeMessages(3);
            this.r = false;
            this.o = false;
            this.p = false;
            this.f485d = false;
            if (this.n) {
                this.n = false;
            }
        }

        private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (!this.p || motionEvent3.getEventTime() - motionEvent2.getEventTime() > ((long) l)) {
                return false;
            }
            int x2 = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
            int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
            if ((x2 * x2) + (y * y) < this.g) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.m.removeMessages(3);
            this.f485d = false;
            this.n = true;
            this.f482a.onLongPress(this.e);
        }
    }

    /* compiled from: GestureDetectorCompat */
    static class c implements a {

        /* renamed from: a  reason: collision with root package name */
        private final GestureDetector f487a;

        c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.f487a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // android.support.v4.g.d.a
        public boolean a(MotionEvent motionEvent) {
            return this.f487a.onTouchEvent(motionEvent);
        }
    }

    public d(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public d(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        if (Build.VERSION.SDK_INT > 17) {
            this.f481a = new c(context, onGestureListener, handler);
        } else {
            this.f481a = new b(context, onGestureListener, handler);
        }
    }

    public boolean a(MotionEvent motionEvent) {
        return this.f481a.a(motionEvent);
    }
}
