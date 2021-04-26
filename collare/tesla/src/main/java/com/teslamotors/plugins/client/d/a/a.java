package com.teslamotors.plugins.client.d.a;

import java.util.Timer;
import java.util.TimerTask;

/* compiled from: ExponentialBackoffFunction */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private b f5520a;

    /* renamed from: b  reason: collision with root package name */
    private int f5521b = -1;

    /* renamed from: c  reason: collision with root package name */
    private int f5522c;

    /* renamed from: d  reason: collision with root package name */
    private int f5523d;
    private boolean e;
    private boolean f;
    private boolean g;
    private c h;
    private b i;
    private AbstractC0150a j;

    /* renamed from: com.teslamotors.plugins.client.d.a.a$a  reason: collision with other inner class name */
    /* compiled from: ExponentialBackoffFunction */
    public interface AbstractC0150a {
        void a(a aVar, String str, Object obj);
    }

    /* compiled from: ExponentialBackoffFunction */
    public interface b {
        boolean a(a aVar, String str, Object obj);
    }

    /* compiled from: ExponentialBackoffFunction */
    public interface c {
        void a(d dVar);
    }

    /* compiled from: ExponentialBackoffFunction */
    public interface d {
        void a(String str, Object obj);
    }

    public void a() {
        if (this.h == null) {
            throw new IllegalStateException("Cannot execute backoff without a run function");
        } else if (!this.e) {
            this.g = false;
            this.f = false;
            if (this.f5520a == null) {
                this.f5520a = new b();
            }
            if (this.f5521b < 0) {
                this.f5521b = -1;
            }
            this.f5522c = 0;
            this.f5523d = 0;
            this.e = true;
            b();
        }
    }

    public void a(b bVar) {
        if (!this.e) {
            this.f5520a = bVar;
            return;
        }
        throw new IllegalStateException("Cannot change strategy while backoff is running");
    }

    public void a(int i2) {
        if (!this.e) {
            this.f5521b = i2;
            return;
        }
        throw new IllegalStateException("Cannot change allowable retries while backoff is running");
    }

    public void a(c cVar) {
        if (!this.e) {
            this.h = cVar;
            return;
        }
        throw new IllegalStateException("Cannot change run function while backoff is running");
    }

    public void a(b bVar) {
        if (!this.e) {
            this.i = bVar;
            return;
        }
        throw new IllegalStateException("Cannot change retryIf function while backoff is running");
    }

    public void a(AbstractC0150a aVar) {
        if (!this.e) {
            this.j = aVar;
            return;
        }
        throw new IllegalStateException("Cannot change completion handler function while backoff is running");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        this.f5522c++;
        new Timer().schedule(new TimerTask() {
            /* class com.teslamotors.plugins.client.d.a.a.AnonymousClass1 */

            public void run() {
                a.this.h.a(new d() {
                    /* class com.teslamotors.plugins.client.d.a.a.AnonymousClass1.AnonymousClass1 */

                    @Override // com.teslamotors.plugins.client.d.a.a.d
                    public void a(String str, Object obj) {
                        if (!a.this.g) {
                            if ((a.this.f5521b == -1 || a.this.f5522c < a.this.f5521b + 1) && ((a.this.i == null || a.this.i.a(a.this, str, obj)) && !(a.this.i == null && str == null))) {
                                if (a.this.f5523d == 0) {
                                    a.this.f5523d = a.this.f5520a.a();
                                } else {
                                    int i = a.this.f5523d * 2;
                                    a aVar = a.this;
                                    if (i > a.this.f5520a.b()) {
                                        i = a.this.f5520a.b();
                                    }
                                    aVar.f5523d = i;
                                }
                                a.this.b();
                                return;
                            }
                            a.this.e = false;
                            a.this.f = true;
                            a.this.j.a(a.this, str, obj);
                        }
                    }
                });
            }
        }, (long) this.f5523d);
    }
}
