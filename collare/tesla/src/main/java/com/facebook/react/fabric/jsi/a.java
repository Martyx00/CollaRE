package com.facebook.react.fabric.jsi;

import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.soloader.SoLoader;

/* compiled from: FabricSoLoader */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f2698a = false;

    public static void a() {
        if (!f2698a) {
            f2698a = true;
            com.facebook.systrace.a.a(0, "FabricSoLoader.staticInit::load:fabricjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_START);
            SoLoader.a("fabricjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_END);
            com.facebook.systrace.a.b(0);
        }
    }
}
