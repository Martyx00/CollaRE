package ezvcard;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* compiled from: Messages */
public enum b {
    INSTANCE;
    

    /* renamed from: b  reason: collision with root package name */
    private final ResourceBundle f5748b = ResourceBundle.getBundle("ezvcard/messages");

    private b() {
    }

    public String a(int i, Object... objArr) {
        return a("validate." + i, objArr);
    }

    public String b(int i, Object... objArr) {
        return a("parse." + i, objArr);
    }

    public String c(int i, Object... objArr) {
        String a2 = a("exception." + i, objArr);
        if (a2 == null) {
            return null;
        }
        return a("exception.0", Integer.valueOf(i), a2);
    }

    public IllegalArgumentException d(int i, Object... objArr) {
        String c2 = c(i, objArr);
        if (c2 == null) {
            return null;
        }
        return new IllegalArgumentException(c2);
    }

    public String a(String str, Object... objArr) {
        try {
            return MessageFormat.format(this.f5748b.getString(str), objArr);
        } catch (MissingResourceException unused) {
            return null;
        }
    }
}
