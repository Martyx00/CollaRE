package com.facebook.react.uimanager;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: ReactStylesDiffMap */
public class y {

    /* renamed from: a  reason: collision with root package name */
    final ReadableMap f3295a;

    public y(ReadableMap readableMap) {
        this.f3295a = readableMap;
    }

    public boolean a(String str) {
        return this.f3295a.hasKey(str);
    }

    public boolean b(String str) {
        return this.f3295a.isNull(str);
    }

    public boolean a(String str, boolean z) {
        return this.f3295a.isNull(str) ? z : this.f3295a.getBoolean(str);
    }

    public double a(String str, double d2) {
        return this.f3295a.isNull(str) ? d2 : this.f3295a.getDouble(str);
    }

    public float a(String str, float f) {
        return this.f3295a.isNull(str) ? f : (float) this.f3295a.getDouble(str);
    }

    public int a(String str, int i) {
        return this.f3295a.isNull(str) ? i : this.f3295a.getInt(str);
    }

    public String c(String str) {
        return this.f3295a.getString(str);
    }

    public ReadableArray d(String str) {
        return this.f3295a.getArray(str);
    }

    public ReadableMap e(String str) {
        return this.f3295a.getMap(str);
    }

    public Dynamic f(String str) {
        return this.f3295a.getDynamic(str);
    }

    public String toString() {
        return "{ " + getClass().getSimpleName() + ": " + this.f3295a.toString() + " }";
    }
}
