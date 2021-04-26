package com.facebook.react.uimanager;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.facebook.react.common.g;

/* compiled from: ShadowNodeRegistry */
public class ad {

    /* renamed from: a  reason: collision with root package name */
    private final SparseArray<w> f3080a = new SparseArray<>();

    /* renamed from: b  reason: collision with root package name */
    private final SparseBooleanArray f3081b = new SparseBooleanArray();

    /* renamed from: c  reason: collision with root package name */
    private final g f3082c = new g();

    public void a(w wVar) {
        this.f3082c.a();
        int reactTag = wVar.getReactTag();
        this.f3080a.put(reactTag, wVar);
        this.f3081b.put(reactTag, true);
    }

    public void a(int i) {
        this.f3082c.a();
        if (i != -1) {
            if (this.f3081b.get(i)) {
                this.f3080a.remove(i);
                this.f3081b.delete(i);
                return;
            }
            throw new f("View with tag " + i + " is not registered as a root view");
        }
    }

    public void b(w wVar) {
        this.f3082c.a();
        this.f3080a.put(wVar.getReactTag(), wVar);
    }

    public void b(int i) {
        this.f3082c.a();
        if (!this.f3081b.get(i)) {
            this.f3080a.remove(i);
            return;
        }
        throw new f("Trying to remove root node " + i + " without using removeRootNode!");
    }

    public w c(int i) {
        this.f3082c.a();
        return this.f3080a.get(i);
    }

    public boolean d(int i) {
        this.f3082c.a();
        return this.f3081b.get(i);
    }

    public int a() {
        this.f3082c.a();
        return this.f3081b.size();
    }

    public int e(int i) {
        this.f3082c.a();
        return this.f3081b.keyAt(i);
    }
}
