package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;
import java.io.File;

/* compiled from: ApplicationSoSource */
public class b extends k {

    /* renamed from: a  reason: collision with root package name */
    private Context f3614a;

    /* renamed from: b  reason: collision with root package name */
    private int f3615b;

    /* renamed from: c  reason: collision with root package name */
    private c f3616c;

    public b(Context context, int i) {
        this.f3614a = context.getApplicationContext();
        if (this.f3614a == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
            this.f3614a = context;
        }
        this.f3615b = i;
        this.f3616c = new c(new File(this.f3614a.getApplicationInfo().nativeLibraryDir), i);
    }

    public boolean a() {
        try {
            File file = this.f3616c.f3617a;
            Context createPackageContext = this.f3614a.createPackageContext(this.f3614a.getPackageName(), 0);
            File file2 = new File(createPackageContext.getApplicationInfo().nativeLibraryDir);
            if (file.equals(file2)) {
                return false;
            }
            Log.d("SoLoader", "Native library directory updated from " + file + " to " + file2);
            this.f3615b = this.f3615b | 1;
            this.f3616c = new c(file2, this.f3615b);
            this.f3616c.a(this.f3615b);
            this.f3614a = createPackageContext;
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.facebook.soloader.k
    public int a(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return this.f3616c.a(str, i, threadPolicy);
    }

    @Override // com.facebook.soloader.k
    public File a(String str) {
        return this.f3616c.a(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.k
    public void a(int i) {
        this.f3616c.a(i);
    }

    @Override // com.facebook.soloader.k
    public String toString() {
        return this.f3616c.toString();
    }
}
