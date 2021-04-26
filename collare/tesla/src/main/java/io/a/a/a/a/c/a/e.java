package io.a.a.a.a.c.a;

/* compiled from: RetryState */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final int f5963a;

    /* renamed from: b  reason: collision with root package name */
    private final a f5964b;

    /* renamed from: c  reason: collision with root package name */
    private final d f5965c;

    public e(a aVar, d dVar) {
        this(0, aVar, dVar);
    }

    public e(int i, a aVar, d dVar) {
        this.f5963a = i;
        this.f5964b = aVar;
        this.f5965c = dVar;
    }

    public long a() {
        return this.f5964b.getDelayMillis(this.f5963a);
    }

    public e b() {
        return new e(this.f5963a + 1, this.f5964b, this.f5965c);
    }

    public e c() {
        return new e(this.f5964b, this.f5965c);
    }
}
