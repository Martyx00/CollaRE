package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;

@a
public class CxxModuleWrapperBase implements NativeModule {
    @a
    private HybridData mHybridData;

    @Override // com.facebook.react.bridge.NativeModule
    public boolean canOverrideExistingModule() {
        return false;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public native String getName();

    @Override // com.facebook.react.bridge.NativeModule
    public void initialize() {
    }

    static {
        ReactBridge.staticInit();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mHybridData.a();
    }

    protected CxxModuleWrapperBase(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    /* access modifiers changed from: protected */
    public void resetModule(HybridData hybridData) {
        HybridData hybridData2 = this.mHybridData;
        if (hybridData != hybridData2) {
            hybridData2.a();
            this.mHybridData = hybridData;
        }
    }
}
