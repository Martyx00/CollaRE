package android.support.v4.e;

import android.support.v4.util.k;
import android.util.Base64;
import java.util.List;

/* compiled from: FontRequest */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final String f404a;

    /* renamed from: b  reason: collision with root package name */
    private final String f405b;

    /* renamed from: c  reason: collision with root package name */
    private final String f406c;

    /* renamed from: d  reason: collision with root package name */
    private final List<List<byte[]>> f407d;
    private final int e = 0;
    private final String f = (this.f404a + "-" + this.f405b + "-" + this.f406c);

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        this.f404a = (String) k.a(str);
        this.f405b = (String) k.a(str2);
        this.f406c = (String) k.a(str3);
        this.f407d = (List) k.a(list);
    }

    public String a() {
        return this.f404a;
    }

    public String b() {
        return this.f405b;
    }

    public String c() {
        return this.f406c;
    }

    public List<List<byte[]>> d() {
        return this.f407d;
    }

    public int e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f404a + ", mProviderPackage: " + this.f405b + ", mQuery: " + this.f406c + ", mCertificates:");
        for (int i = 0; i < this.f407d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f407d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.e);
        return sb.toString();
    }
}
