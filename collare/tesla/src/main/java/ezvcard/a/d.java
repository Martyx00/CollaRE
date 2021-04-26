package ezvcard.a;

import ezvcard.b;

/* compiled from: ParseWarning */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final Integer f5735a;

    /* renamed from: b  reason: collision with root package name */
    private final Integer f5736b;

    /* renamed from: c  reason: collision with root package name */
    private final String f5737c;

    /* renamed from: d  reason: collision with root package name */
    private final String f5738d;

    private d(Integer num, String str, Integer num2, String str2) {
        this.f5736b = num;
        this.f5737c = str;
        this.f5735a = num2;
        this.f5738d = str2;
    }

    public String toString() {
        int i;
        String str = this.f5738d;
        if (this.f5735a != null) {
            str = "(" + this.f5735a + ") " + str;
        }
        if (this.f5736b == null && this.f5737c == null) {
            return str;
        }
        if (this.f5736b != null || this.f5737c == null) {
            i = (this.f5736b == null || this.f5737c != null) ? 36 : 37;
        } else {
            i = 35;
        }
        return b.INSTANCE.b(i, this.f5736b, this.f5737c, str);
    }

    /* compiled from: ParseWarning */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private Integer f5739a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f5740b;

        /* renamed from: c  reason: collision with root package name */
        private String f5741c;

        /* renamed from: d  reason: collision with root package name */
        private String f5742d;

        public a() {
        }

        public a(c cVar) {
            a(cVar.b());
            a(cVar.c());
        }

        public a a(String str) {
            this.f5741c = str;
            return this;
        }

        public a a(Integer num) {
            this.f5739a = num;
            return this;
        }

        public a a(int i, Object... objArr) {
            this.f5740b = Integer.valueOf(i);
            this.f5742d = b.INSTANCE.b(i, objArr);
            return this;
        }

        public a a(a aVar) {
            return a(aVar.a().intValue(), aVar.b());
        }

        public d a() {
            return new d(this.f5739a, this.f5741c, this.f5740b, this.f5742d);
        }
    }
}
