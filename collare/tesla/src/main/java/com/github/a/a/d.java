package com.github.a.a;

/* compiled from: VObjectProperty */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private String f3763a;

    /* renamed from: b  reason: collision with root package name */
    private String f3764b;

    /* renamed from: c  reason: collision with root package name */
    private c f3765c;

    /* renamed from: d  reason: collision with root package name */
    private String f3766d;

    public d() {
        this(null, null);
    }

    public d(String str, String str2) {
        this(null, str, str2);
    }

    public d(String str, String str2, String str3) {
        this(str, str2, new c(), str3);
    }

    public d(String str, String str2, c cVar, String str3) {
        this.f3763a = str;
        this.f3764b = str2;
        this.f3765c = cVar;
        this.f3766d = str3;
    }

    public String a() {
        return this.f3763a;
    }

    public void a(String str) {
        this.f3763a = str;
    }

    public String b() {
        return this.f3764b;
    }

    public void b(String str) {
        this.f3764b = str;
    }

    public c c() {
        return this.f3765c;
    }

    public String d() {
        return this.f3766d;
    }

    public void c(String str) {
        this.f3766d = str;
    }

    public int hashCode() {
        String str = this.f3763a;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.f3764b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        c cVar = this.f3765c;
        int hashCode3 = (hashCode2 + (cVar == null ? 0 : cVar.hashCode())) * 31;
        String str3 = this.f3766d;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        String str = this.f3763a;
        if (str == null) {
            if (dVar.f3763a != null) {
                return false;
            }
        } else if (!str.equals(dVar.f3763a)) {
            return false;
        }
        String str2 = this.f3764b;
        if (str2 == null) {
            if (dVar.f3764b != null) {
                return false;
            }
        } else if (!str2.equals(dVar.f3764b)) {
            return false;
        }
        c cVar = this.f3765c;
        if (cVar == null) {
            if (dVar.f3765c != null) {
                return false;
            }
        } else if (!cVar.equals(dVar.f3765c)) {
            return false;
        }
        String str3 = this.f3766d;
        if (str3 == null) {
            if (dVar.f3766d != null) {
                return false;
            }
        } else if (!str3.equals(dVar.f3766d)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "VObjectProperty [group=" + this.f3763a + ", name=" + this.f3764b + ", parameters=" + this.f3765c + ", value=" + this.f3766d + "]";
    }
}
