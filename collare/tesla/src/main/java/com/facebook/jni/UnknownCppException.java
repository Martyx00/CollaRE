package com.facebook.jni;

import com.facebook.j.a.a;

@a
public class UnknownCppException extends CppException {
    @a
    public UnknownCppException() {
        super("Unknown");
    }

    @a
    public UnknownCppException(String str) {
        super(str);
    }
}
