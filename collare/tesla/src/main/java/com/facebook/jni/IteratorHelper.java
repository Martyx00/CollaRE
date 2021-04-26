package com.facebook.jni;

import com.facebook.j.a.a;
import java.util.Iterator;

@a
public class IteratorHelper {

    /* renamed from: a  reason: collision with root package name */
    private final Iterator f2481a;
    @a
    private Object mElement;

    @a
    public IteratorHelper(Iterator it) {
        this.f2481a = it;
    }

    @a
    public IteratorHelper(Iterable iterable) {
        this.f2481a = iterable.iterator();
    }

    /* access modifiers changed from: package-private */
    @a
    public boolean hasNext() {
        if (this.f2481a.hasNext()) {
            this.mElement = this.f2481a.next();
            return true;
        }
        this.mElement = null;
        return false;
    }
}
