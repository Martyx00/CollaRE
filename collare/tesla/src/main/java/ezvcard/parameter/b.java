package ezvcard.parameter;

import ezvcard.parameter.MediaTypeParameter;
import ezvcard.util.a;
import java.lang.reflect.Constructor;

/* compiled from: MediaTypeCaseClasses */
public class b<T extends MediaTypeParameter> extends a<T, String[]> {
    public b(Class<T> cls) {
        super(cls);
    }

    /* access modifiers changed from: protected */
    public T a(String[] strArr) {
        try {
            Constructor declaredConstructor = this.f5829a.getDeclaredConstructor(String.class, String.class, String.class);
            declaredConstructor.setAccessible(true);
            return (T) ((MediaTypeParameter) declaredConstructor.newInstance(strArr[0], strArr[1], strArr[2]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(T t, String[] strArr) {
        String[] strArr2 = {t.c(), t.a(), t.b()};
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (!(str == null || str.equalsIgnoreCase(strArr2[i]))) {
                return false;
            }
        }
        return true;
    }
}
