package c;

/* access modifiers changed from: package-private */
/* compiled from: SegmentPool */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    static p f1368a;

    /* renamed from: b  reason: collision with root package name */
    static long f1369b;

    private q() {
    }

    static p a() {
        synchronized (q.class) {
            if (f1368a == null) {
                return new p();
            }
            p pVar = f1368a;
            f1368a = pVar.f;
            pVar.f = null;
            f1369b -= 8192;
            return pVar;
        }
    }

    static void a(p pVar) {
        if (pVar.f != null || pVar.g != null) {
            throw new IllegalArgumentException();
        } else if (!pVar.f1367d) {
            synchronized (q.class) {
                if (f1369b + 8192 <= 65536) {
                    f1369b += 8192;
                    pVar.f = f1368a;
                    pVar.f1366c = 0;
                    pVar.f1365b = 0;
                    f1368a = pVar;
                }
            }
        }
    }
}
