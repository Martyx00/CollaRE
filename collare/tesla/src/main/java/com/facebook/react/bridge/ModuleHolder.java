package com.facebook.react.bridge;

import com.facebook.d.b.c;
import com.facebook.j.a.a;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.systrace.b;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;

@a
public class ModuleHolder {
    private static final AtomicInteger sInstanceKeyCounter = new AtomicInteger(1);
    private boolean mInitializable;
    private final int mInstanceKey = sInstanceKeyCounter.getAndIncrement();
    private boolean mIsCreating;
    private boolean mIsInitializing;
    private NativeModule mModule;
    private final String mName;
    private Provider<? extends NativeModule> mProvider;
    private final ReactModuleInfo mReactModuleInfo;

    public ModuleHolder(ReactModuleInfo reactModuleInfo, Provider<? extends NativeModule> provider) {
        this.mName = reactModuleInfo.a();
        this.mProvider = provider;
        this.mReactModuleInfo = reactModuleInfo;
        if (reactModuleInfo.d()) {
            this.mModule = create();
        }
    }

    public ModuleHolder(NativeModule nativeModule) {
        this.mName = nativeModule.getName();
        this.mReactModuleInfo = new ReactModuleInfo(nativeModule.getName(), nativeModule.getClass().getSimpleName(), nativeModule.canOverrideExistingModule(), true, true, CxxModuleWrapper.class.isAssignableFrom(nativeModule.getClass()), com.facebook.react.f.a.a.a.class.isAssignableFrom(nativeModule.getClass()));
        this.mModule = nativeModule;
        c.a().a(com.facebook.d.c.a.e, "NativeModule init: %s", this.mName);
    }

    /* access modifiers changed from: package-private */
    public void markInitializable() {
        boolean z;
        NativeModule nativeModule;
        synchronized (this) {
            z = true;
            this.mInitializable = true;
            boolean z2 = false;
            if (this.mModule != null) {
                if (!this.mIsInitializing) {
                    z2 = true;
                }
                com.facebook.i.a.a.a(z2);
                nativeModule = this.mModule;
            } else {
                nativeModule = null;
                z = false;
            }
        }
        if (z) {
            doInitialize(nativeModule);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean hasInstance() {
        return this.mModule != null;
    }

    public synchronized void destroy() {
        if (this.mModule != null) {
            this.mModule.onCatalystInstanceDestroy();
        }
    }

    @a
    public String getName() {
        return this.mName;
    }

    public boolean getCanOverrideExistingModule() {
        return this.mReactModuleInfo.c();
    }

    public boolean getHasConstants() {
        return this.mReactModuleInfo.e();
    }

    public boolean isTurboModule() {
        return this.mReactModuleInfo.g();
    }

    public boolean isCxxModule() {
        return this.mReactModuleInfo.f();
    }

    public String getClassName() {
        return this.mReactModuleInfo.b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        if (r1 == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
        r0 = create();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.mIsCreating = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0025, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0028, code lost:
        if (r3.mModule == null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0032, code lost:
        r0 = (com.facebook.react.bridge.NativeModule) com.facebook.i.a.a.a(r3.mModule);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003a, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x003b, code lost:
        return r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0026 */
    @com.facebook.j.a.a
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.NativeModule getModule() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.facebook.react.bridge.NativeModule r0 = r3.mModule     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0009
            com.facebook.react.bridge.NativeModule r0 = r3.mModule     // Catch:{ all -> 0x003f }
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            return r0
        L_0x0009:
            boolean r0 = r3.mIsCreating     // Catch:{ all -> 0x003f }
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0012
            r3.mIsCreating = r1     // Catch:{ all -> 0x003f }
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x0025
            com.facebook.react.bridge.NativeModule r0 = r3.create()
            monitor-enter(r3)
            r3.mIsCreating = r2     // Catch:{ all -> 0x0022 }
            r3.notifyAll()     // Catch:{ all -> 0x0022 }
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            return r0
        L_0x0022:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            throw r0
        L_0x0025:
            monitor-enter(r3)
        L_0x0026:
            com.facebook.react.bridge.NativeModule r0 = r3.mModule     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x0032
            boolean r0 = r3.mIsCreating     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0032
            r3.wait()     // Catch:{ InterruptedException -> 0x0026 }
            goto L_0x0026
        L_0x0032:
            com.facebook.react.bridge.NativeModule r0 = r3.mModule
            java.lang.Object r0 = com.facebook.i.a.a.a(r0)
            com.facebook.react.bridge.NativeModule r0 = (com.facebook.react.bridge.NativeModule) r0
            monitor-exit(r3)
            return r0
        L_0x003c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x003f:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ModuleHolder.getModule():com.facebook.react.bridge.NativeModule");
    }

    private NativeModule create() {
        boolean z = true;
        SoftAssertions.assertCondition(this.mModule == null, "Creating an already created module.");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, this.mName, this.mInstanceKey);
        b.a(0, "ModuleHolder.createModule").a("name", this.mName).a();
        c.a().a(com.facebook.d.c.a.e, "NativeModule init: %s", this.mName);
        try {
            NativeModule nativeModule = (NativeModule) ((Provider) com.facebook.i.a.a.a(this.mProvider)).get();
            this.mProvider = null;
            synchronized (this) {
                this.mModule = nativeModule;
                if (!this.mInitializable || this.mIsInitializing) {
                    z = false;
                }
            }
            if (z) {
                doInitialize(nativeModule);
            }
            return nativeModule;
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END, this.mName, this.mInstanceKey);
            b.a(0).a();
        }
    }

    private void doInitialize(NativeModule nativeModule) {
        boolean z;
        b.a(0, "ModuleHolder.initialize").a("name", this.mName).a();
        ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_START, this.mName, this.mInstanceKey);
        try {
            synchronized (this) {
                z = true;
                if (!this.mInitializable || this.mIsInitializing) {
                    z = false;
                } else {
                    this.mIsInitializing = true;
                }
            }
            if (z) {
                nativeModule.initialize();
                synchronized (this) {
                    this.mIsInitializing = false;
                }
            }
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_END, this.mName, this.mInstanceKey);
            b.a(0).a();
        }
    }
}
