package com.facebook.yoga;

import com.facebook.j.a.a;

@a
public enum YogaDirection {
    INHERIT(0),
    LTR(1),
    RTL(2);
    

    /* renamed from: d  reason: collision with root package name */
    private final int f3666d;

    private YogaDirection(int i) {
        this.f3666d = i;
    }

    public int a() {
        return this.f3666d;
    }

    public static YogaDirection a(int i) {
        switch (i) {
            case 0:
                return INHERIT;
            case 1:
                return LTR;
            case 2:
                return RTL;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }
}
