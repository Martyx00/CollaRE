package com.facebook.imagepipeline.k;

import com.facebook.common.e.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: ForwardingRequestListener */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private final List<c> f2175a;

    public b(Set<c> set) {
        this.f2175a = new ArrayList(set.size());
        for (c cVar : set) {
            if (cVar != null) {
                this.f2175a.add(cVar);
            }
        }
    }

    public b(c... cVarArr) {
        this.f2175a = new ArrayList(cVarArr.length);
        for (c cVar : cVarArr) {
            if (cVar != null) {
                this.f2175a.add(cVar);
            }
        }
    }

    public void a(c cVar) {
        this.f2175a.add(cVar);
    }

    @Override // com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, Object obj, String str, boolean z) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(bVar, obj, str, z);
            } catch (Exception e) {
                a("InternalListener exception in onRequestStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.an
    public void a(String str, String str2) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(str, str2);
            } catch (Exception e) {
                a("InternalListener exception in onProducerStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.an
    public void a(String str, String str2, Map<String, String> map) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(str, str2, map);
            } catch (Exception e) {
                a("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.an
    public void a(String str, String str2, Throwable th, Map<String, String> map) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(str, str2, th, map);
            } catch (Exception e) {
                a("InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.an
    public void b(String str, String str2, Map<String, String> map) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).b(str, str2, map);
            } catch (Exception e) {
                a("InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.an
    public void a(String str, String str2, String str3) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(str, str2, str3);
            } catch (Exception e) {
                a("InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.an
    public void a(String str, String str2, boolean z) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(str, str2, z);
            } catch (Exception e) {
                a("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, String str, boolean z) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(bVar, str, z);
            } catch (Exception e) {
                a("InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, String str, Throwable th, boolean z) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a(bVar, str, th, z);
            } catch (Exception e) {
                a("InternalListener exception in onRequestFailure", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.k.c
    public void a_(String str) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            try {
                this.f2175a.get(i).a_(str);
            } catch (Exception e) {
                a("InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.an
    public boolean b(String str) {
        int size = this.f2175a.size();
        for (int i = 0; i < size; i++) {
            if (this.f2175a.get(i).b(str)) {
                return true;
            }
        }
        return false;
    }

    private void a(String str, Throwable th) {
        a.c("ForwardingRequestListener", str, th);
    }
}
