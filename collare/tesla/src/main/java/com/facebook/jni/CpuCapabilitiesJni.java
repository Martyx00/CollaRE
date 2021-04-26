package com.facebook.jni;

import com.facebook.j.a.a;
import com.facebook.soloader.SoLoader;

@a
public class CpuCapabilitiesJni {
    @a
    public static native boolean nativeDeviceSupportsNeon();

    @a
    public static native boolean nativeDeviceSupportsVFPFP16();

    @a
    public static native boolean nativeDeviceSupportsX86();

    static {
        SoLoader.a("fb");
    }
}
