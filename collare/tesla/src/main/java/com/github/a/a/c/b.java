package com.github.a.a.c;

import com.github.a.a.a;
import com.github.a.a.c.a;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.eac.EACTags;

/* compiled from: VObjectValidator */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<a, Map<Boolean, a>> f3759a = new EnumMap(a.class);

    /* renamed from: b  reason: collision with root package name */
    private static final Map<a, Map<Boolean, a>> f3760b = f3759a;

    /* renamed from: c  reason: collision with root package name */
    private static final Map<a, Map<Boolean, a>> f3761c = new EnumMap(a.class);

    /* renamed from: d  reason: collision with root package name */
    private static final Map<a, Map<Boolean, Map<Boolean, a>>> f3762d = new EnumMap(a.class);

    static {
        a aVar = a.OLD;
        HashMap hashMap = new HashMap();
        hashMap.put(false, new a.C0065a().a().b("\r\n:.;").d());
        hashMap.put(true, new a.C0065a().b().b("[]=:.,").b(';').d());
        f3759a.put(aVar, hashMap);
        com.github.a.a.a aVar2 = com.github.a.a.a.NEW;
        HashMap hashMap2 = new HashMap();
        hashMap2.put(false, f3759a.get(com.github.a.a.a.OLD).get(false));
        hashMap2.put(true, new a.C0065a().a(65, 90).a(97, EACTags.SECURITY_SUPPORT_TEMPLATE).a(48, 57).a('-').d());
        f3759a.put(aVar2, hashMap2);
        com.github.a.a.a aVar3 = com.github.a.a.a.OLD;
        HashMap hashMap3 = new HashMap();
        hashMap3.put(false, new a.C0065a().a().b("\r\n:;=").d());
        hashMap3.put(true, f3759a.get(aVar3).get(true));
        f3761c.put(aVar3, hashMap3);
        com.github.a.a.a aVar4 = com.github.a.a.a.NEW;
        HashMap hashMap4 = new HashMap();
        hashMap4.put(false, f3761c.get(com.github.a.a.a.OLD).get(false));
        hashMap4.put(true, f3759a.get(aVar4).get(true));
        f3761c.put(aVar4, hashMap4);
        com.github.a.a.a aVar5 = com.github.a.a.a.OLD;
        HashMap hashMap5 = new HashMap();
        HashMap hashMap6 = new HashMap();
        hashMap6.put(false, new a.C0065a().a().b("\r\n:").d());
        hashMap6.put(true, new a.C0065a(f3761c.get(aVar5).get(true)).a(';').d());
        hashMap5.put(false, hashMap6);
        hashMap5.put(true, hashMap5.get(false));
        f3762d.put(aVar5, hashMap5);
        com.github.a.a.a aVar6 = com.github.a.a.a.NEW;
        HashMap hashMap7 = new HashMap();
        HashMap hashMap8 = new HashMap();
        hashMap8.put(false, new a.C0065a().a().b("\r\n\"").d());
        hashMap8.put(true, new a.C0065a().b().c().a('\t').b('\"').d());
        hashMap7.put(false, hashMap8);
        HashMap hashMap9 = new HashMap();
        hashMap9.put(false, new a.C0065a().a().d());
        hashMap9.put(true, new a.C0065a().b().c().a("\r\n\t").d());
        hashMap7.put(true, hashMap9);
        f3762d.put(aVar6, hashMap7);
    }

    public static a a(com.github.a.a.a aVar, boolean z) {
        return f3760b.get(aVar).get(Boolean.valueOf(z));
    }

    public static boolean a(String str, com.github.a.a.a aVar, boolean z) {
        return b(aVar, z).a(str);
    }

    public static a b(com.github.a.a.a aVar, boolean z) {
        return f3761c.get(aVar).get(Boolean.valueOf(z));
    }

    public static boolean a(String str, com.github.a.a.a aVar, boolean z, boolean z2) {
        return a(aVar, z, z2).a(str);
    }

    public static a a(com.github.a.a.a aVar, boolean z, boolean z2) {
        return f3762d.get(aVar).get(Boolean.valueOf(z)).get(Boolean.valueOf(z2));
    }
}
