package ezvcard;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: SupportedVersions */
public @interface c {
    VCardVersion[] a();
}
