package com.facebook.react;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.bridge.MemoryPressureListener;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: MemoryPressureRouter */
public class d implements ComponentCallbacks2 {

    /* renamed from: a  reason: collision with root package name */
    private final Set<MemoryPressureListener> f2621a = Collections.synchronizedSet(new LinkedHashSet());

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    d(Context context) {
        context.getApplicationContext().registerComponentCallbacks(this);
    }

    public void a(MemoryPressureListener memoryPressureListener) {
        this.f2621a.add(memoryPressureListener);
    }

    public void b(MemoryPressureListener memoryPressureListener) {
        this.f2621a.remove(memoryPressureListener);
    }

    public void onTrimMemory(int i) {
        a(i);
    }

    private void a(int i) {
        Set<MemoryPressureListener> set = this.f2621a;
        for (MemoryPressureListener memoryPressureListener : (MemoryPressureListener[]) set.toArray(new MemoryPressureListener[set.size()])) {
            memoryPressureListener.handleMemoryPressure(i);
        }
    }
}
