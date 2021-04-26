package com.facebook.react.module.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: ReactModule */
public @interface a {
    String a();

    boolean b() default false;

    boolean c() default false;

    boolean d() default true;

    boolean e() default false;
}
