package org.spongycastle.util.test;

public interface TestResult {
    Throwable getException();

    boolean isSuccessful();

    String toString();
}
