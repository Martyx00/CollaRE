package com.facebook.react.modules.core;

import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.i;
import com.facebook.react.modules.core.a;
import com.facebook.react.modules.core.e;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@com.facebook.react.module.a.a(a = Timing.NAME)
public final class Timing extends ReactContextBaseJavaModule implements LifecycleEventListener, com.facebook.react.c.b {
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    public static final String NAME = "Timing";
    private final AtomicBoolean isPaused = new AtomicBoolean(true);
    private final AtomicBoolean isRunningTasks = new AtomicBoolean(false);
    private a mCurrentIdleCallbackRunnable;
    private final com.facebook.react.devsupport.a.b mDevSupportManager;
    private boolean mFrameCallbackPosted = false;
    private boolean mFrameIdleCallbackPosted = false;
    private final Object mIdleCallbackGuard = new Object();
    private final b mIdleFrameCallback = new b();
    private final e mReactChoreographer;
    private boolean mSendIdleEvents = false;
    private final d mTimerFrameCallback = new d();
    private final Object mTimerGuard = new Object();
    private final SparseArray<c> mTimerIdsToTimers;
    private final PriorityQueue<c> mTimers;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    /* access modifiers changed from: private */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private final int f2820a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f2821b;

        /* renamed from: c  reason: collision with root package name */
        private final int f2822c;

        /* renamed from: d  reason: collision with root package name */
        private long f2823d;

        private c(int i, long j, int i2, boolean z) {
            this.f2820a = i;
            this.f2823d = j;
            this.f2822c = i2;
            this.f2821b = z;
        }
    }

    /* access modifiers changed from: private */
    public class d extends a.AbstractC0055a {

        /* renamed from: b  reason: collision with root package name */
        private WritableArray f2825b;

        private d() {
            this.f2825b = null;
        }

        @Override // com.facebook.react.modules.core.a.AbstractC0055a
        public void b(long j) {
            if (!Timing.this.isPaused.get() || Timing.this.isRunningTasks.get()) {
                long j2 = j / 1000000;
                synchronized (Timing.this.mTimerGuard) {
                    while (!Timing.this.mTimers.isEmpty() && ((c) Timing.this.mTimers.peek()).f2823d < j2) {
                        c cVar = (c) Timing.this.mTimers.poll();
                        if (this.f2825b == null) {
                            this.f2825b = Arguments.createArray();
                        }
                        this.f2825b.pushInt(cVar.f2820a);
                        if (cVar.f2821b) {
                            cVar.f2823d = ((long) cVar.f2822c) + j2;
                            Timing.this.mTimers.add(cVar);
                        } else {
                            Timing.this.mTimerIdsToTimers.remove(cVar.f2820a);
                        }
                    }
                }
                if (this.f2825b != null) {
                    ((JSTimers) Timing.this.getReactApplicationContext().getJSModule(JSTimers.class)).callTimers(this.f2825b);
                    this.f2825b = null;
                }
                Timing.this.mReactChoreographer.a(e.a.TIMERS_EVENTS, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public class b extends a.AbstractC0055a {
        private b() {
        }

        @Override // com.facebook.react.modules.core.a.AbstractC0055a
        public void b(long j) {
            if (!Timing.this.isPaused.get() || Timing.this.isRunningTasks.get()) {
                if (Timing.this.mCurrentIdleCallbackRunnable != null) {
                    Timing.this.mCurrentIdleCallbackRunnable.a();
                }
                Timing timing = Timing.this;
                timing.mCurrentIdleCallbackRunnable = new a(j);
                Timing.this.getReactApplicationContext().runOnJSQueueThread(Timing.this.mCurrentIdleCallbackRunnable);
                Timing.this.mReactChoreographer.a(e.a.IDLE_EVENT, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public class a implements Runnable {

        /* renamed from: b  reason: collision with root package name */
        private volatile boolean f2817b = false;

        /* renamed from: c  reason: collision with root package name */
        private final long f2818c;

        public a(long j) {
            this.f2818c = j;
        }

        public void run() {
            boolean z;
            if (!this.f2817b) {
                long c2 = i.c() - (this.f2818c / 1000000);
                long a2 = i.a() - c2;
                if (Timing.FRAME_DURATION_MS - ((float) c2) >= Timing.IDLE_CALLBACK_FRAME_DEADLINE_MS) {
                    synchronized (Timing.this.mIdleCallbackGuard) {
                        z = Timing.this.mSendIdleEvents;
                    }
                    if (z) {
                        ((JSTimers) Timing.this.getReactApplicationContext().getJSModule(JSTimers.class)).callIdleCallbacks((double) a2);
                    }
                    Timing.this.mCurrentIdleCallbackRunnable = null;
                }
            }
        }

        public void a() {
            this.f2817b = true;
        }
    }

    public Timing(ReactApplicationContext reactApplicationContext, com.facebook.react.devsupport.a.b bVar) {
        super(reactApplicationContext);
        this.mDevSupportManager = bVar;
        this.mTimers = new PriorityQueue<>(11, new Comparator<c>() {
            /* class com.facebook.react.modules.core.Timing.AnonymousClass1 */

            /* renamed from: a */
            public int compare(c cVar, c cVar2) {
                int i = ((cVar.f2823d - cVar2.f2823d) > 0 ? 1 : ((cVar.f2823d - cVar2.f2823d) == 0 ? 0 : -1));
                if (i == 0) {
                    return 0;
                }
                return i < 0 ? -1 : 1;
            }
        });
        this.mTimerIdsToTimers = new SparseArray<>();
        this.mReactChoreographer = e.b();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        com.facebook.react.c.a.a(getReactApplicationContext()).a(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.isPaused.set(true);
        clearFrameCallback();
        maybeIdleCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        clearFrameCallback();
        maybeIdleCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.isPaused.set(false);
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    public void onHeadlessJsTaskStart(int i) {
        if (!this.isRunningTasks.getAndSet(true)) {
            setChoreographerCallback();
            maybeSetChoreographerIdleCallback();
        }
    }

    @Override // com.facebook.react.c.b
    public void onHeadlessJsTaskFinish(int i) {
        if (!com.facebook.react.c.a.a(getReactApplicationContext()).a()) {
            this.isRunningTasks.set(false);
            clearFrameCallback();
            maybeIdleCallback();
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        clearFrameCallback();
        clearChoreographerIdleCallback();
        com.facebook.react.c.a.a(getReactApplicationContext()).b(this);
    }

    private void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEvents) {
                setChoreographerIdleCallback();
            }
        }
    }

    private void maybeIdleCallback() {
        if (this.isPaused.get() && !this.isRunningTasks.get()) {
            clearFrameCallback();
        }
    }

    private void setChoreographerCallback() {
        if (!this.mFrameCallbackPosted) {
            this.mReactChoreographer.a(e.a.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = true;
        }
    }

    private void clearFrameCallback() {
        com.facebook.react.c.a a2 = com.facebook.react.c.a.a(getReactApplicationContext());
        if (this.mFrameCallbackPosted && this.isPaused.get() && !a2.a()) {
            this.mReactChoreographer.b(e.a.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChoreographerIdleCallback() {
        if (!this.mFrameIdleCallbackPosted) {
            this.mReactChoreographer.a(e.a.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = true;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            this.mReactChoreographer.b(e.a.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = false;
        }
    }

    @ReactMethod
    public void createTimer(int i, int i2, double d2, boolean z) {
        long a2 = i.a();
        long j = (long) d2;
        if (this.mDevSupportManager.d() && Math.abs(j - a2) > 60000) {
            ((JSTimers) getReactApplicationContext().getJSModule(JSTimers.class)).emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        long max = Math.max(0L, (j - a2) + ((long) i2));
        if (i2 != 0 || z) {
            c cVar = new c(i, (i.b() / 1000000) + max, i2, z);
            synchronized (this.mTimerGuard) {
                this.mTimers.add(cVar);
                this.mTimerIdsToTimers.put(i, cVar);
            }
            return;
        }
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        ((JSTimers) getReactApplicationContext().getJSModule(JSTimers.class)).callTimers(createArray);
    }

    @ReactMethod
    public void deleteTimer(int i) {
        synchronized (this.mTimerGuard) {
            c cVar = this.mTimerIdsToTimers.get(i);
            if (cVar != null) {
                this.mTimerIdsToTimers.remove(i);
                this.mTimers.remove(cVar);
            }
        }
    }

    @ReactMethod
    public void setSendIdleEvents(final boolean z) {
        synchronized (this.mIdleCallbackGuard) {
            this.mSendIdleEvents = z;
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.modules.core.Timing.AnonymousClass2 */

            public void run() {
                synchronized (Timing.this.mIdleCallbackGuard) {
                    if (z) {
                        Timing.this.setChoreographerIdleCallback();
                    } else {
                        Timing.this.clearChoreographerIdleCallback();
                    }
                }
            }
        });
    }
}
