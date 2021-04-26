package com.facebook.react.bridge;

public abstract class GuardedRunnable implements Runnable {
    private final ReactContext mReactContext;

    public abstract void runGuarded();

    public GuardedRunnable(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    public final void run() {
        try {
            runGuarded();
        } catch (RuntimeException e) {
            this.mReactContext.handleException(e);
        }
    }
}
