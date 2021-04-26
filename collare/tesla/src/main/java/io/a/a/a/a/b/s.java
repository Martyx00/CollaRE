package io.a.a.a.a.b;

import android.content.Context;
import io.a.a.a.a.a.b;
import io.a.a.a.a.a.d;
import io.a.a.a.c;

/* compiled from: InstallerPackageNameProvider */
public class s {

    /* renamed from: a  reason: collision with root package name */
    private final d<String> f5921a = new d<String>() {
        /* class io.a.a.a.a.b.s.AnonymousClass1 */

        /* renamed from: a */
        public String load(Context context) {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? "" : installerPackageName;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private final b<String> f5922b = new b<>();

    public String a(Context context) {
        try {
            String a2 = this.f5922b.a(context, this.f5921a);
            if ("".equals(a2)) {
                return null;
            }
            return a2;
        } catch (Exception e) {
            c.g().e("Fabric", "Failed to determine installer package name", e);
            return null;
        }
    }
}
