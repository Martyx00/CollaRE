package io.realm;

import io.realm.internal.n;
import io.realm.internal.p;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: DynamicRealmObject */
public class d extends v implements n {

    /* renamed from: a  reason: collision with root package name */
    private final n<d> f6168a = new n<>(this);

    @Override // io.realm.internal.n
    public void b_() {
    }

    d(a aVar, p pVar) {
        this.f6168a.a(aVar);
        this.f6168a.a(pVar);
        this.f6168a.e();
    }

    public String[] a() {
        this.f6168a.a().d();
        String[] strArr = new String[((int) this.f6168a.b().a())];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = this.f6168a.b().e((long) i);
        }
        return strArr;
    }

    public int hashCode() {
        this.f6168a.a().d();
        String e = this.f6168a.a().e();
        String h = this.f6168a.b().b().h();
        long c2 = this.f6168a.b().c();
        int i = 0;
        int hashCode = (527 + (e != null ? e.hashCode() : 0)) * 31;
        if (h != null) {
            i = h.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) ((c2 >>> 32) ^ c2));
    }

    public boolean equals(Object obj) {
        this.f6168a.a().d();
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        String e = this.f6168a.a().e();
        String e2 = dVar.f6168a.a().e();
        if (e == null ? e2 != null : !e.equals(e2)) {
            return false;
        }
        String h = this.f6168a.b().b().h();
        String h2 = dVar.f6168a.b().b().h();
        if (h == null ? h2 != null : !h.equals(h2)) {
            return false;
        }
        if (this.f6168a.b().c() == dVar.f6168a.b().c()) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        this.f6168a.a().d();
        if (!this.f6168a.b().d()) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder(this.f6168a.b().b().i() + " = dynamic[");
        String[] a2 = a();
        for (String str2 : a2) {
            long a3 = this.f6168a.b().a(str2);
            RealmFieldType f = this.f6168a.b().f(a3);
            sb.append("{");
            sb.append(str2);
            sb.append(":");
            switch (f) {
                case BOOLEAN:
                    sb.append(this.f6168a.b().b(a3) ? "null" : Boolean.valueOf(this.f6168a.b().h(a3)));
                    break;
                case INTEGER:
                    sb.append(this.f6168a.b().b(a3) ? "null" : Long.valueOf(this.f6168a.b().g(a3)));
                    break;
                case FLOAT:
                    sb.append(this.f6168a.b().b(a3) ? "null" : Float.valueOf(this.f6168a.b().i(a3)));
                    break;
                case DOUBLE:
                    sb.append(this.f6168a.b().b(a3) ? "null" : Double.valueOf(this.f6168a.b().j(a3)));
                    break;
                case STRING:
                    sb.append(this.f6168a.b().l(a3));
                    break;
                case BINARY:
                    sb.append(Arrays.toString(this.f6168a.b().m(a3)));
                    break;
                case DATE:
                    sb.append(this.f6168a.b().b(a3) ? "null" : this.f6168a.b().k(a3));
                    break;
                case OBJECT:
                    if (this.f6168a.b().a(a3)) {
                        str = "null";
                    } else {
                        str = this.f6168a.b().b().c(a3).i();
                    }
                    sb.append(str);
                    break;
                case LIST:
                    sb.append(String.format(Locale.US, "RealmList<%s>[%s]", this.f6168a.b().b().c(a3).i(), Long.valueOf(this.f6168a.b().d(a3).c())));
                    break;
                case LINKING_OBJECTS:
                default:
                    sb.append("?");
                    break;
                case INTEGER_LIST:
                    sb.append(String.format(Locale.US, "RealmList<Long>[%s]", Long.valueOf(this.f6168a.b().a(a3, f).c())));
                    break;
                case BOOLEAN_LIST:
                    sb.append(String.format(Locale.US, "RealmList<Boolean>[%s]", Long.valueOf(this.f6168a.b().a(a3, f).c())));
                    break;
                case STRING_LIST:
                    sb.append(String.format(Locale.US, "RealmList<String>[%s]", Long.valueOf(this.f6168a.b().a(a3, f).c())));
                    break;
                case BINARY_LIST:
                    sb.append(String.format(Locale.US, "RealmList<byte[]>[%s]", Long.valueOf(this.f6168a.b().a(a3, f).c())));
                    break;
                case DATE_LIST:
                    sb.append(String.format(Locale.US, "RealmList<Date>[%s]", Long.valueOf(this.f6168a.b().a(a3, f).c())));
                    break;
                case FLOAT_LIST:
                    sb.append(String.format(Locale.US, "RealmList<Float>[%s]", Long.valueOf(this.f6168a.b().a(a3, f).c())));
                    break;
                case DOUBLE_LIST:
                    sb.append(String.format(Locale.US, "RealmList<Double>[%s]", Long.valueOf(this.f6168a.b().a(a3, f).c())));
                    break;
            }
            sb.append("},");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }

    @Override // io.realm.internal.n
    public n c_() {
        return this.f6168a;
    }
}
