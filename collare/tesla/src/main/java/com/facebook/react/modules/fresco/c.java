package com.facebook.react.modules.fresco;

import android.util.Pair;
import com.facebook.imagepipeline.k.a;
import com.facebook.systrace.a;
import io.a.a.a.a.d.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SystraceRequestListener */
public class c extends a {

    /* renamed from: a  reason: collision with root package name */
    int f2882a = 0;

    /* renamed from: b  reason: collision with root package name */
    Map<String, Pair<Integer, String>> f2883b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    Map<String, Pair<Integer, String>> f2884c = new HashMap();

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.n.an
    public boolean b(String str) {
        return false;
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.n.an
    public void a(String str, String str2) {
        if (com.facebook.systrace.a.a(0)) {
            Pair<Integer, String> create = Pair.create(Integer.valueOf(this.f2882a), "FRESCO_PRODUCER_" + str2.replace(':', '_'));
            com.facebook.systrace.a.a(0, (String) create.second, this.f2882a);
            this.f2883b.put(str, create);
            this.f2882a = this.f2882a + 1;
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.n.an
    public void a(String str, String str2, Map<String, String> map) {
        if (com.facebook.systrace.a.a(0) && this.f2883b.containsKey(str)) {
            Pair<Integer, String> pair = this.f2883b.get(str);
            com.facebook.systrace.a.b(0, (String) pair.second, ((Integer) pair.first).intValue());
            this.f2883b.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.n.an
    public void a(String str, String str2, Throwable th, Map<String, String> map) {
        if (com.facebook.systrace.a.a(0) && this.f2883b.containsKey(str)) {
            Pair<Integer, String> pair = this.f2883b.get(str);
            com.facebook.systrace.a.b(0, (String) pair.second, ((Integer) pair.first).intValue());
            this.f2883b.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.n.an
    public void b(String str, String str2, Map<String, String> map) {
        if (com.facebook.systrace.a.a(0) && this.f2883b.containsKey(str)) {
            Pair<Integer, String> pair = this.f2883b.get(str);
            com.facebook.systrace.a.b(0, (String) pair.second, ((Integer) pair.first).intValue());
            this.f2883b.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.n.an
    public void a(String str, String str2, String str3) {
        if (com.facebook.systrace.a.a(0)) {
            com.facebook.systrace.a.a(0, "FRESCO_PRODUCER_EVENT_" + str.replace(':', '_') + b.ROLL_OVER_FILE_NAME_SEPARATOR + str2.replace(':', '_') + b.ROLL_OVER_FILE_NAME_SEPARATOR + str3.replace(':', '_'), a.EnumC0063a.THREAD);
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, Object obj, String str, boolean z) {
        if (com.facebook.systrace.a.a(0)) {
            Pair<Integer, String> create = Pair.create(Integer.valueOf(this.f2882a), "FRESCO_REQUEST_" + bVar.b().toString().replace(':', '_'));
            com.facebook.systrace.a.a(0, (String) create.second, this.f2882a);
            this.f2884c.put(str, create);
            this.f2882a = this.f2882a + 1;
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, String str, boolean z) {
        if (com.facebook.systrace.a.a(0) && this.f2884c.containsKey(str)) {
            Pair<Integer, String> pair = this.f2884c.get(str);
            com.facebook.systrace.a.b(0, (String) pair.second, ((Integer) pair.first).intValue());
            this.f2884c.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, String str, Throwable th, boolean z) {
        if (com.facebook.systrace.a.a(0) && this.f2884c.containsKey(str)) {
            Pair<Integer, String> pair = this.f2884c.get(str);
            com.facebook.systrace.a.b(0, (String) pair.second, ((Integer) pair.first).intValue());
            this.f2884c.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a_(String str) {
        if (com.facebook.systrace.a.a(0) && this.f2884c.containsKey(str)) {
            Pair<Integer, String> pair = this.f2884c.get(str);
            com.facebook.systrace.a.b(0, (String) pair.second, ((Integer) pair.first).intValue());
            this.f2884c.remove(str);
        }
    }
}
