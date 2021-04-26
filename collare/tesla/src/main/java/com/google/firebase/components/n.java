package com.google.firebase.components;

import java.util.HashSet;
import java.util.Set;

/* access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class n {

    /* renamed from: a  reason: collision with root package name */
    private final a<?> f3826a;

    /* renamed from: b  reason: collision with root package name */
    private final Set<n> f3827b = new HashSet();

    /* renamed from: c  reason: collision with root package name */
    private final Set<n> f3828c = new HashSet();

    n(a<?> aVar) {
        this.f3826a = aVar;
    }

    /* access modifiers changed from: package-private */
    public final void a(n nVar) {
        this.f3827b.add(nVar);
    }

    /* access modifiers changed from: package-private */
    public final void b(n nVar) {
        this.f3828c.add(nVar);
    }

    /* access modifiers changed from: package-private */
    public final Set<n> a() {
        return this.f3827b;
    }

    /* access modifiers changed from: package-private */
    public final void c(n nVar) {
        this.f3828c.remove(nVar);
    }

    /* access modifiers changed from: package-private */
    public final a<?> b() {
        return this.f3826a;
    }

    /* access modifiers changed from: package-private */
    public final boolean c() {
        return this.f3828c.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public final boolean d() {
        return this.f3827b.isEmpty();
    }
}
