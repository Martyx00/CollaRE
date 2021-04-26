package com.facebook.yoga;

import com.facebook.j.a.a;

@a
public enum YogaMeasureMode {
    UNDEFINED(0),
    EXACTLY(1),
    AT_MOST(2);
    

    /* renamed from: d  reason: collision with root package name */
    private final int f3693d;

    private YogaMeasureMode(int i) {
        this.f3693d = i;
    }

    public static YogaMeasureMode a(int i) {
        switch (i) {
            case 0:
                return UNDEFINED;
            case 1:
                return EXACTLY;
            case 2:
                return AT_MOST;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }
}
