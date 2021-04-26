package org.spongycastle.util;

import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Properties {
    public static boolean isOverrideSet(final String str) {
        try {
            return "true".equals(AccessController.doPrivileged(new PrivilegedAction() {
                /* class org.spongycastle.util.Properties.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Object run() {
                    String property = System.getProperty(str);
                    if (property == null) {
                        return null;
                    }
                    return Strings.toLowerCase(property);
                }
            }));
        } catch (AccessControlException unused) {
            return false;
        }
    }
}
