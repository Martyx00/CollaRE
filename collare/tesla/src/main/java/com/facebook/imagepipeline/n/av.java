package com.facebook.imagepipeline.n;

import android.util.Pair;
import com.facebook.common.d.i;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* compiled from: ThrottlingProducer */
public class av<T> implements ak<T> {

    /* renamed from: a  reason: collision with root package name */
    private final ak<T> f2335a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2336b;

    /* renamed from: c  reason: collision with root package name */
    private int f2337c = 0;

    /* renamed from: d  reason: collision with root package name */
    private final ConcurrentLinkedQueue<Pair<k<T>, al>> f2338d = new ConcurrentLinkedQueue<>();
    private final Executor e;

    static /* synthetic */ int b(av avVar) {
        int i = avVar.f2337c;
        avVar.f2337c = i - 1;
        return i;
    }

    public av(int i, Executor executor, ak<T> akVar) {
        this.f2336b = i;
        this.e = (Executor) i.a(executor);
        this.f2335a = (ak) i.a(akVar);
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<T> kVar, al alVar) {
        boolean z;
        alVar.c().a(alVar.b(), "ThrottlingProducer");
        synchronized (this) {
            z = true;
            if (this.f2337c >= this.f2336b) {
                this.f2338d.add(Pair.create(kVar, alVar));
            } else {
                this.f2337c++;
                z = false;
            }
        }
        if (!z) {
            b(kVar, alVar);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(k<T> kVar, al alVar) {
        alVar.c().a(alVar.b(), "ThrottlingProducer", (Map<String, String>) null);
        this.f2335a.a(new a(kVar), alVar);
    }

    /* access modifiers changed from: private */
    /* compiled from: ThrottlingProducer */
    public class a extends n<T, T> {
        private a(k<T> kVar) {
            super(kVar);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.b
        public void a(T t, int i) {
            d().b(t, i);
            if (a(i)) {
                c();
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a(Throwable th) {
            d().b(th);
            c();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a() {
            d().b();
            c();
        }

        private void c() {
            final Pair pair;
            synchronized (av.this) {
                pair = (Pair) av.this.f2338d.poll();
                if (pair == null) {
                    av.b(av.this);
                }
            }
            if (pair != null) {
                av.this.e.execute(new Runnable() {
                    /* class com.facebook.imagepipeline.n.av.a.AnonymousClass1 */

                    public void run() {
                        av.this.b((k) pair.first, (al) pair.second);
                    }
                });
            }
        }
    }
}
