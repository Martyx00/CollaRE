package com.facebook.yoga;

import com.facebook.j.a.a;

@a
public enum YogaFlexDirection {
    COLUMN(0),
    COLUMN_REVERSE(1),
    ROW(2),
    ROW_REVERSE(3);
    
    private final int e;

    private YogaFlexDirection(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }
}
