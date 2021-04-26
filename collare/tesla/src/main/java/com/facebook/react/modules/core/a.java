package com.facebook.react.modules.core;

import android.view.Choreographer;
import com.facebook.react.bridge.UiThreadUtil;

/* compiled from: ChoreographerCompat */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f2826a;

    /* renamed from: b  reason: collision with root package name */
    private Choreographer f2827b = b();

    public static a a() {
        UiThreadUtil.assertOnUiThread();
        if (f2826a == null) {
            f2826a = new a();
        }
        return f2826a;
    }

    private a() {
    }

    public void a(AbstractC0055a aVar) {
        a(aVar.a());
    }

    public void b(AbstractC0055a aVar) {
        b(aVar.a());
    }

    private Choreographer b() {
        return Choreographer.getInstance();
    }

    private void a(Choreographer.FrameCallback frameCallback) {
        this.f2827b.postFrameCallback(frameCallback);
    }

    private void b(Choreographer.FrameCallback frameCallback) {
        this.f2827b.removeFrameCallback(frameCallback);
    }

    /* renamed from: com.facebook.react.modules.core.a$a  reason: collision with other inner class name */
    /* compiled from: ChoreographerCompat */
    public static abstract class AbstractC0055a {

        /* renamed from: a  reason: collision with root package name */
        private Choreographer.FrameCallback f2828a;

        public abstract void b(long j);

        /* access modifiers changed from: package-private */
        public Choreographer.FrameCallback a() {
            if (this.f2828a == null) {
                this.f2828a = new Choreographer.FrameCallback() {
                    /* class com.facebook.react.modules.core.a.AbstractC0055a.AnonymousClass1 */

                    public void doFrame(long j) {
                        AbstractC0055a.this.b(j);
                    }
                };
            }
            return this.f2828a;
        }
    }
}
