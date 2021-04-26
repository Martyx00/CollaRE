package android.support.v4.app;

import android.arch.lifecycle.e;
import android.arch.lifecycle.j;
import android.arch.lifecycle.k;
import android.arch.lifecycle.n;
import android.arch.lifecycle.o;
import android.arch.lifecycle.p;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.v;
import android.support.v4.content.e;
import android.support.v4.util.d;
import android.support.v4.util.m;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* access modifiers changed from: package-private */
public class LoaderManagerImpl extends v {

    /* renamed from: a  reason: collision with root package name */
    static boolean f137a = false;

    /* renamed from: b  reason: collision with root package name */
    private final e f138b;

    /* renamed from: c  reason: collision with root package name */
    private final LoaderViewModel f139c;

    public static class a<D> extends j<D> implements e.c<D> {

        /* renamed from: a  reason: collision with root package name */
        private final int f143a;

        /* renamed from: b  reason: collision with root package name */
        private final Bundle f144b;

        /* renamed from: c  reason: collision with root package name */
        private final android.support.v4.content.e<D> f145c;

        /* renamed from: d  reason: collision with root package name */
        private android.arch.lifecycle.e f146d;
        private b<D> e;
        private android.support.v4.content.e<D> f;

        /* access modifiers changed from: package-private */
        public android.support.v4.content.e<D> f() {
            return this.f145c;
        }

        /* access modifiers changed from: protected */
        @Override // android.arch.lifecycle.LiveData
        public void b() {
            if (LoaderManagerImpl.f137a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.f145c.q();
        }

        /* access modifiers changed from: protected */
        @Override // android.arch.lifecycle.LiveData
        public void c() {
            if (LoaderManagerImpl.f137a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.f145c.t();
        }

        /* access modifiers changed from: package-private */
        public void g() {
            android.arch.lifecycle.e eVar = this.f146d;
            b<D> bVar = this.e;
            if (eVar != null && bVar != null) {
                super.a((k) bVar);
                a(eVar, bVar);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: android.arch.lifecycle.k<? super D> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.arch.lifecycle.LiveData
        public void a(k<? super D> kVar) {
            super.a((k) kVar);
            this.f146d = null;
            this.e = null;
        }

        /* access modifiers changed from: package-private */
        public android.support.v4.content.e<D> a(boolean z) {
            if (LoaderManagerImpl.f137a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.f145c.r();
            this.f145c.u();
            b<D> bVar = this.e;
            if (bVar != null) {
                a((k) bVar);
                if (z) {
                    bVar.b();
                }
            }
            this.f145c.a(this);
            if ((bVar == null || bVar.a()) && !z) {
                return this.f145c;
            }
            this.f145c.w();
            return this.f;
        }

        @Override // android.support.v4.content.e.c
        public void a(android.support.v4.content.e<D> eVar, D d2) {
            if (LoaderManagerImpl.f137a) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                b((Object) d2);
                return;
            }
            if (LoaderManagerImpl.f137a) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            a((Object) d2);
        }

        @Override // android.arch.lifecycle.j, android.arch.lifecycle.LiveData
        public void b(D d2) {
            super.b((Object) d2);
            android.support.v4.content.e<D> eVar = this.f;
            if (eVar != null) {
                eVar.w();
                this.f = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.f143a);
            sb.append(" : ");
            d.a(this.f145c, sb);
            sb.append("}}");
            return sb.toString();
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f143a);
            printWriter.print(" mArgs=");
            printWriter.println(this.f144b);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f145c);
            android.support.v4.content.e<D> eVar = this.f145c;
            eVar.a(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.e != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.e);
                b<D> bVar = this.e;
                bVar.a(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(f().c((D) a()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(d());
        }
    }

    /* access modifiers changed from: package-private */
    public static class b<D> implements k<D> {

        /* renamed from: a  reason: collision with root package name */
        private final android.support.v4.content.e<D> f147a;

        /* renamed from: b  reason: collision with root package name */
        private final v.a<D> f148b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f149c;

        @Override // android.arch.lifecycle.k
        public void a(D d2) {
            if (LoaderManagerImpl.f137a) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.f147a + ": " + this.f147a.c(d2));
            }
            this.f148b.a(this.f147a, d2);
            this.f149c = true;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.f149c;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.f149c) {
                if (LoaderManagerImpl.f137a) {
                    Log.v("LoaderManager", "  Resetting: " + this.f147a);
                }
                this.f148b.a(this.f147a);
            }
        }

        public String toString() {
            return this.f148b.toString();
        }

        public void a(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.f149c);
        }
    }

    static class LoaderViewModel extends n {

        /* renamed from: a  reason: collision with root package name */
        private static final o.a f140a = new o.a() {
            /* class android.support.v4.app.LoaderManagerImpl.LoaderViewModel.AnonymousClass1 */

            @Override // android.arch.lifecycle.o.a
            public <T extends n> T a(Class<T> cls) {
                return new LoaderViewModel();
            }
        };

        /* renamed from: b  reason: collision with root package name */
        private m<a> f141b = new m<>();

        /* renamed from: c  reason: collision with root package name */
        private boolean f142c = false;

        LoaderViewModel() {
        }

        static LoaderViewModel a(p pVar) {
            return (LoaderViewModel) new o(pVar, f140a).a(LoaderViewModel.class);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            int b2 = this.f141b.b();
            for (int i = 0; i < b2; i++) {
                this.f141b.e(i).g();
            }
        }

        /* access modifiers changed from: protected */
        @Override // android.arch.lifecycle.n
        public void a() {
            super.a();
            int b2 = this.f141b.b();
            for (int i = 0; i < b2; i++) {
                this.f141b.e(i).a(true);
            }
            this.f141b.c();
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f141b.b() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f141b.b(); i++) {
                    a e = this.f141b.e(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f141b.d(i));
                    printWriter.print(": ");
                    printWriter.println(e.toString());
                    e.a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
    }

    LoaderManagerImpl(android.arch.lifecycle.e eVar, p pVar) {
        this.f138b = eVar;
        this.f139c = LoaderViewModel.a(pVar);
    }

    @Override // android.support.v4.app.v
    public void a() {
        this.f139c.b();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        d.a(this.f138b, sb);
        sb.append("}}");
        return sb.toString();
    }

    @Override // android.support.v4.app.v
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f139c.a(str, fileDescriptor, printWriter, strArr);
    }
}
