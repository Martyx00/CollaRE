package com.facebook.react.uimanager;

import com.facebook.react.common.a;
import com.facebook.yoga.YogaNode;

/* compiled from: YogaNodePool */
public class aw {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f3181a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static a<YogaNode> f3182b;

    public static a<YogaNode> a() {
        a<YogaNode> aVar;
        a<YogaNode> aVar2 = f3182b;
        if (aVar2 != null) {
            return aVar2;
        }
        synchronized (f3181a) {
            if (f3182b == null) {
                f3182b = new a<>(1024);
            }
            aVar = f3182b;
        }
        return aVar;
    }
}
