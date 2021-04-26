package io.a.a.a.a.c.a;

/* compiled from: ExponentialBackoff */
public class c implements a {

    /* renamed from: a  reason: collision with root package name */
    private final long f5954a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5955b;

    public c(long j, int i) {
        this.f5954a = j;
        this.f5955b = i;
    }

    @Override // io.a.a.a.a.c.a.a
    public long getDelayMillis(int i) {
        return (long) (((double) this.f5954a) * Math.pow((double) this.f5955b, (double) i));
    }
}
