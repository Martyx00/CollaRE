package com.facebook.react.uimanager.events;

import android.util.SparseIntArray;

/* compiled from: TouchEventCoalescingKeyHelper */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private final SparseIntArray f3249a = new SparseIntArray();

    public void a(long j) {
        this.f3249a.put((int) j, 0);
    }

    public void b(long j) {
        int i = (int) j;
        int i2 = this.f3249a.get(i, -1);
        if (i2 != -1) {
            this.f3249a.put(i, i2 + 1);
            return;
        }
        throw new RuntimeException("Tried to increment non-existent cookie");
    }

    public short c(long j) {
        int i = this.f3249a.get((int) j, -1);
        if (i != -1) {
            return (short) (65535 & i);
        }
        throw new RuntimeException("Tried to get non-existent cookie");
    }

    public void d(long j) {
        this.f3249a.delete((int) j);
    }

    public boolean e(long j) {
        return this.f3249a.get((int) j, -1) != -1;
    }
}
