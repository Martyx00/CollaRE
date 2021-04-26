package com.facebook.react.uimanager;

/* compiled from: ReactRootViewTagGenerator */
public class v {

    /* renamed from: a  reason: collision with root package name */
    private static int f3294a = 1;

    public static synchronized int a() {
        int i;
        synchronized (v.class) {
            i = f3294a;
            f3294a += 10;
        }
        return i;
    }
}
