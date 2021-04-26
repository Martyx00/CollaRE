package android.support.v7.view;

import android.support.v4.g.aa;
import android.support.v4.g.y;
import android.support.v4.g.z;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ViewPropertyAnimatorCompatSet */
public class h {

    /* renamed from: a  reason: collision with root package name */
    final ArrayList<y> f936a = new ArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    z f937b;

    /* renamed from: c  reason: collision with root package name */
    private long f938c = -1;

    /* renamed from: d  reason: collision with root package name */
    private Interpolator f939d;
    private boolean e;
    private final aa f = new aa() {
        /* class android.support.v7.view.h.AnonymousClass1 */

        /* renamed from: b  reason: collision with root package name */
        private boolean f941b = false;

        /* renamed from: c  reason: collision with root package name */
        private int f942c = 0;

        @Override // android.support.v4.g.aa, android.support.v4.g.z
        public void a(View view) {
            if (!this.f941b) {
                this.f941b = true;
                if (h.this.f937b != null) {
                    h.this.f937b.a(null);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.f942c = 0;
            this.f941b = false;
            h.this.b();
        }

        @Override // android.support.v4.g.aa, android.support.v4.g.z
        public void b(View view) {
            int i = this.f942c + 1;
            this.f942c = i;
            if (i == h.this.f936a.size()) {
                if (h.this.f937b != null) {
                    h.this.f937b.b(null);
                }
                a();
            }
        }
    };

    public h a(y yVar) {
        if (!this.e) {
            this.f936a.add(yVar);
        }
        return this;
    }

    public h a(y yVar, y yVar2) {
        this.f936a.add(yVar);
        yVar2.b(yVar.a());
        this.f936a.add(yVar2);
        return this;
    }

    public void a() {
        if (!this.e) {
            Iterator<y> it = this.f936a.iterator();
            while (it.hasNext()) {
                y next = it.next();
                long j = this.f938c;
                if (j >= 0) {
                    next.a(j);
                }
                Interpolator interpolator = this.f939d;
                if (interpolator != null) {
                    next.a(interpolator);
                }
                if (this.f937b != null) {
                    next.a(this.f);
                }
                next.c();
            }
            this.e = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.e = false;
    }

    public void c() {
        if (this.e) {
            Iterator<y> it = this.f936a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.e = false;
        }
    }

    public h a(long j) {
        if (!this.e) {
            this.f938c = j;
        }
        return this;
    }

    public h a(Interpolator interpolator) {
        if (!this.e) {
            this.f939d = interpolator;
        }
        return this;
    }

    public h a(z zVar) {
        if (!this.e) {
            this.f937b = zVar;
        }
        return this;
    }
}
