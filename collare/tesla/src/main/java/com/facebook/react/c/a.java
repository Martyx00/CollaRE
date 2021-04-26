package com.facebook.react.c;

import android.os.Handler;
import android.util.SparseArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: HeadlessJsTaskContext */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final WeakHashMap<ReactContext, a> f2593a = new WeakHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final WeakReference<ReactContext> f2594b;

    /* renamed from: c  reason: collision with root package name */
    private final Set<b> f2595c = new CopyOnWriteArraySet();

    /* renamed from: d  reason: collision with root package name */
    private final AtomicInteger f2596d = new AtomicInteger(0);
    private final Handler e = new Handler();
    private final Set<Integer> f = new CopyOnWriteArraySet();
    private final SparseArray<Runnable> g = new SparseArray<>();

    public static a a(ReactContext reactContext) {
        a aVar = f2593a.get(reactContext);
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(reactContext);
        f2593a.put(reactContext, aVar2);
        return aVar2;
    }

    private a(ReactContext reactContext) {
        this.f2594b = new WeakReference<>(reactContext);
    }

    public void a(b bVar) {
        this.f2595c.add(bVar);
    }

    public void b(b bVar) {
        this.f2595c.remove(bVar);
    }

    public boolean a() {
        return this.f.size() > 0;
    }

    public synchronized void a(final int i) {
        boolean remove = this.f.remove(Integer.valueOf(i));
        com.facebook.i.a.a.a(remove, "Tried to finish non-existent task with id " + i + ".");
        Runnable runnable = this.g.get(i);
        if (runnable != null) {
            this.e.removeCallbacks(runnable);
            this.g.remove(i);
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.c.a.AnonymousClass1 */

            public void run() {
                for (b bVar : a.this.f2595c) {
                    bVar.onHeadlessJsTaskFinish(i);
                }
            }
        });
    }

    public synchronized boolean b(int i) {
        return this.f.contains(Integer.valueOf(i));
    }
}
