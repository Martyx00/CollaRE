package com.facebook.react.a;

import android.util.SparseArray;
import com.facebook.react.bridge.UiThreadUtil;

/* compiled from: AnimationRegistry */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final SparseArray<a> f2501a = new SparseArray<>();

    public void a(a aVar) {
        UiThreadUtil.assertOnUiThread();
        this.f2501a.put(aVar.c(), aVar);
    }

    public a a(int i) {
        UiThreadUtil.assertOnUiThread();
        return this.f2501a.get(i);
    }

    public a b(int i) {
        UiThreadUtil.assertOnUiThread();
        a aVar = this.f2501a.get(i);
        if (aVar != null) {
            this.f2501a.delete(i);
        }
        return aVar;
    }
}
