package com.facebook.react.devsupport;

import com.facebook.j.a.a;

@a
public class JSException extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private final String f2676a;

    @a
    public JSException(String str, String str2, Throwable th) {
        super(str, th);
        this.f2676a = str2;
    }
}
