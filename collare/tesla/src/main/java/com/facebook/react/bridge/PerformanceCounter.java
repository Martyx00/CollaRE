package com.facebook.react.bridge;

import java.util.Map;

public interface PerformanceCounter {
    Map<String, Long> getPerformanceCounters();

    void profileNextBatch();
}
