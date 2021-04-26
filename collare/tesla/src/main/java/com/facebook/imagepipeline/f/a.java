package com.facebook.imagepipeline.f;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: DefaultExecutorSupplier */
public class a implements e {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f2098a = Executors.newFixedThreadPool(2, new k(10, "FrescoIoBoundExecutor", true));

    /* renamed from: b  reason: collision with root package name */
    private final Executor f2099b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f2100c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f2101d;

    public a(int i) {
        this.f2099b = Executors.newFixedThreadPool(i, new k(10, "FrescoDecodeExecutor", true));
        this.f2100c = Executors.newFixedThreadPool(i, new k(10, "FrescoBackgroundExecutor", true));
        this.f2101d = Executors.newFixedThreadPool(1, new k(10, "FrescoLightWeightBackgroundExecutor", true));
    }

    @Override // com.facebook.imagepipeline.f.e
    public Executor a() {
        return this.f2098a;
    }

    @Override // com.facebook.imagepipeline.f.e
    public Executor b() {
        return this.f2098a;
    }

    @Override // com.facebook.imagepipeline.f.e
    public Executor c() {
        return this.f2099b;
    }

    @Override // com.facebook.imagepipeline.f.e
    public Executor d() {
        return this.f2100c;
    }

    @Override // com.facebook.imagepipeline.f.e
    public Executor e() {
        return this.f2101d;
    }
}
