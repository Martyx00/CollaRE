package com.facebook.react.uimanager.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: ReactPropGroup */
public @interface b {
    String[] a();

    String b() default "__default_type__";

    float c() default 0.0f;

    double d() default 0.0d;

    int e() default 0;
}
