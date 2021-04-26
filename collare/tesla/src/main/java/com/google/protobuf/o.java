package com.google.protobuf;

import com.google.protobuf.k;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ExtensionRegistry */
public class o extends q {

    /* renamed from: a  reason: collision with root package name */
    static final o f4450a = new o(true);

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, b> f4451c;

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, b> f4452d;
    private final Map<a, b> e;
    private final Map<a, b> f;

    /* compiled from: ExtensionRegistry */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final k.f f4455a;

        /* renamed from: b  reason: collision with root package name */
        public final ac f4456b;
    }

    public static o a() {
        return f4450a;
    }

    public b a(k.a aVar, int i) {
        return this.e.get(new a(aVar, i));
    }

    private o() {
        this.f4451c = new HashMap();
        this.f4452d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
    }

    o(boolean z) {
        super(f4459b);
        this.f4451c = Collections.emptyMap();
        this.f4452d = Collections.emptyMap();
        this.e = Collections.emptyMap();
        this.f = Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    /* compiled from: ExtensionRegistry */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final k.a f4453a;

        /* renamed from: b  reason: collision with root package name */
        private final int f4454b;

        a(k.a aVar, int i) {
            this.f4453a = aVar;
            this.f4454b = i;
        }

        public int hashCode() {
            return (this.f4453a.hashCode() * 65535) + this.f4454b;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.f4453a == aVar.f4453a && this.f4454b == aVar.f4454b) {
                return true;
            }
            return false;
        }
    }
}
