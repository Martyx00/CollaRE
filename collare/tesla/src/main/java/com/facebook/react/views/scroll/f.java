package com.facebook.react.views.scroll;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.o;
import java.util.Map;

/* compiled from: ReactScrollViewCommandHelper */
public class f {

    /* compiled from: ReactScrollViewCommandHelper */
    public interface a<T> {
        void flashScrollIndicators(T t);

        void scrollTo(T t, b bVar);

        void scrollToEnd(T t, c cVar);
    }

    /* compiled from: ReactScrollViewCommandHelper */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f3393a;

        /* renamed from: b  reason: collision with root package name */
        public final int f3394b;

        /* renamed from: c  reason: collision with root package name */
        public final boolean f3395c;

        b(int i, int i2, boolean z) {
            this.f3393a = i;
            this.f3394b = i2;
            this.f3395c = z;
        }
    }

    /* compiled from: ReactScrollViewCommandHelper */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f3396a;

        c(boolean z) {
            this.f3396a = z;
        }
    }

    public static Map<String, Integer> a() {
        return e.a("scrollTo", 1, "scrollToEnd", 2, "flashScrollIndicators", 3);
    }

    public static <T> void a(a<T> aVar, T t, int i, ReadableArray readableArray) {
        com.facebook.i.a.a.a(aVar);
        com.facebook.i.a.a.a(t);
        com.facebook.i.a.a.a(readableArray);
        switch (i) {
            case 1:
                aVar.scrollTo(t, new b(Math.round(o.a(readableArray.getDouble(0))), Math.round(o.a(readableArray.getDouble(1))), readableArray.getBoolean(2)));
                return;
            case 2:
                aVar.scrollToEnd(t, new c(readableArray.getBoolean(0)));
                return;
            case 3:
                aVar.flashScrollIndicators(t);
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), aVar.getClass().getSimpleName()));
        }
    }
}
