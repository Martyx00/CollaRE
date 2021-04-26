package com.facebook.react.bridge;

import com.facebook.j.a.a;

@a
interface ReactCallback {
    @a
    void decrementPendingJSCalls();

    @a
    void incrementPendingJSCalls();

    @a
    void onBatchComplete();
}
