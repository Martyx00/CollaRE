package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.a.c;
import com.google.firebase.a.d;
import com.google.firebase.components.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class m extends i {

    /* renamed from: a  reason: collision with root package name */
    private final List<a<?>> f3823a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<Class<?>, q<?>> f3824b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final o f3825c;

    @Override // com.google.firebase.components.b, com.google.firebase.components.i
    public final /* bridge */ /* synthetic */ Object a(Class cls) {
        return super.a(cls);
    }

    public m(Executor executor, Iterable<d> iterable, a<?>... aVarArr) {
        this.f3825c = new o(executor);
        ArrayList arrayList = new ArrayList();
        arrayList.add(a.a(this.f3825c, o.class, d.class, c.class));
        for (d dVar : iterable) {
            arrayList.addAll(dVar.getComponents());
        }
        Collections.addAll(arrayList, aVarArr);
        this.f3823a = Collections.unmodifiableList(a.AnonymousClass1.a(arrayList));
        for (a<?> aVar : this.f3823a) {
            a(aVar);
        }
        a();
    }

    @Override // com.google.firebase.components.b
    public final <T> com.google.firebase.b.a<T> b(Class<T> cls) {
        Preconditions.checkNotNull(cls, "Null interface requested.");
        return this.f3824b.get(cls);
    }

    public final void a(boolean z) {
        for (a<?> aVar : this.f3823a) {
            if (aVar.e() || (aVar.f() && z)) {
                a(aVar.a().iterator().next());
            }
        }
        this.f3825c.a();
    }

    private <T> void a(a<T> aVar) {
        q<?> qVar = new q<>(aVar.c(), new s(aVar, this));
        for (Class<? super T> cls : aVar.a()) {
            this.f3824b.put(cls, qVar);
        }
    }

    private void a() {
        for (a<?> aVar : this.f3823a) {
            Iterator<e> it = aVar.b().iterator();
            while (true) {
                if (it.hasNext()) {
                    e next = it.next();
                    if (next.b() && !this.f3824b.containsKey(next.a())) {
                        throw new h(String.format("Unsatisfied dependency for component %s: %s", aVar, next.a()));
                    }
                }
            }
        }
    }
}
