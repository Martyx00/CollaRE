package ezvcard.a;

import ezvcard.VCardVersion;
import ezvcard.a.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ParseContext */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private VCardVersion f5723a;

    /* renamed from: b  reason: collision with root package name */
    private List<d> f5724b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private Integer f5725c;

    /* renamed from: d  reason: collision with root package name */
    private String f5726d;

    public VCardVersion a() {
        return this.f5723a;
    }

    public void a(VCardVersion vCardVersion) {
        this.f5723a = vCardVersion;
    }

    public Integer b() {
        return this.f5725c;
    }

    public void a(Integer num) {
        this.f5725c = num;
    }

    public String c() {
        return this.f5726d;
    }

    public void a(String str) {
        this.f5726d = str;
    }

    public void a(int i, Object... objArr) {
        this.f5724b.add(new d.a(this).a(i, objArr).a());
    }

    public List<d> d() {
        return this.f5724b;
    }
}
