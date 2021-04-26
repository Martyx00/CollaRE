package com.facebook.yoga;

import com.facebook.j.a.a;

@a
public enum YogaJustify {
    FLEX_START(0),
    CENTER(1),
    FLEX_END(2),
    SPACE_BETWEEN(3),
    SPACE_AROUND(4),
    SPACE_EVENLY(5);
    
    private final int g;

    private YogaJustify(int i) {
        this.g = i;
    }

    public int a() {
        return this.g;
    }
}
