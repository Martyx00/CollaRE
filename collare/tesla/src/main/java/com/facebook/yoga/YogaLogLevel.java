package com.facebook.yoga;

import com.facebook.j.a.a;

@a
public enum YogaLogLevel {
    ERROR(0),
    WARN(1),
    INFO(2),
    DEBUG(3),
    VERBOSE(4),
    FATAL(5);
    
    private final int g;

    private YogaLogLevel(int i) {
        this.g = i;
    }
}
