package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;

@a
public class CxxModuleWrapper extends CxxModuleWrapperBase {
    private static native CxxModuleWrapper makeDsoNative(String str, String str2);

    protected CxxModuleWrapper(HybridData hybridData) {
        super(hybridData);
    }

    public static CxxModuleWrapper makeDso(String str, String str2) {
        SoLoader.a(str);
        return makeDsoNative(SoLoader.b(str).getAbsolutePath(), str2);
    }
}
