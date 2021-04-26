package android.arch.lifecycle;

import android.arch.lifecycle.c;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: OnLifecycleEvent */
public @interface l {
    c.a a();
}
