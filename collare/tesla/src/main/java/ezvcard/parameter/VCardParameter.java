package ezvcard.parameter;

import ezvcard.VCardVersion;
import ezvcard.c;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class VCardParameter {
    protected final String u;

    public VCardParameter(String str) {
        this(str, false);
    }

    protected VCardParameter(String str, boolean z) {
        if (str != null && !z) {
            str = str.toLowerCase();
        }
        this.u = str;
    }

    public String c() {
        return this.u;
    }

    public VCardVersion[] d() {
        Field[] fields = getClass().getFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    if (field.get(null) == this) {
                        c cVar = (c) field.getAnnotation(c.class);
                        return cVar == null ? VCardVersion.values() : cVar.a();
                    }
                } catch (IllegalAccessException | IllegalArgumentException unused) {
                    continue;
                }
            }
        }
        return VCardVersion.values();
    }

    public boolean a(VCardVersion vCardVersion) {
        for (VCardVersion vCardVersion2 : d()) {
            if (vCardVersion2 == vCardVersion) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.u;
    }

    public int hashCode() {
        String str = this.u;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VCardParameter vCardParameter = (VCardParameter) obj;
        String str = this.u;
        if (str == null) {
            if (vCardParameter.u != null) {
                return false;
            }
        } else if (!str.equals(vCardParameter.u)) {
            return false;
        }
        return true;
    }
}
