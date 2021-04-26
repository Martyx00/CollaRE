package com.facebook.yoga;

import com.facebook.j.a.a;

@a
public enum YogaAlign {
    AUTO(0),
    FLEX_START(1),
    CENTER(2),
    FLEX_END(3),
    STRETCH(4),
    BASELINE(5),
    SPACE_BETWEEN(6),
    SPACE_AROUND(7);
    
    private final int i;

    private YogaAlign(int i2) {
        this.i = i2;
    }

    public int a() {
        return this.i;
    }
}
