package io.realm.internal.sync;

/* compiled from: SubscriptionAction */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f6321a = new a(null, 0, false);

    /* renamed from: b  reason: collision with root package name */
    public static final a f6322b = new a("", Long.MAX_VALUE, false);

    /* renamed from: c  reason: collision with root package name */
    private final String f6323c;

    /* renamed from: d  reason: collision with root package name */
    private final long f6324d;
    private final boolean e;

    public a(String str, long j, boolean z) {
        this.f6323c = str;
        this.f6324d = j;
        this.e = z;
    }

    public boolean a() {
        return this.f6323c != null;
    }

    public String b() {
        return this.f6323c;
    }

    public long c() {
        return this.f6324d;
    }

    public boolean d() {
        return this.e;
    }
}
