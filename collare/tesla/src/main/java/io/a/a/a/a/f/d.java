package io.a.a.a.a.f;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import io.a.a.a.i;

/* compiled from: PreferenceStoreImpl */
public class d implements c {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f6019a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6020b;

    /* renamed from: c  reason: collision with root package name */
    private final Context f6021c;

    public d(Context context, String str) {
        if (context != null) {
            this.f6021c = context;
            this.f6020b = str;
            this.f6019a = this.f6021c.getSharedPreferences(this.f6020b, 0);
            return;
        }
        throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }

    @Deprecated
    public d(i iVar) {
        this(iVar.getContext(), iVar.getClass().getName());
    }

    @Override // io.a.a.a.a.f.c
    public SharedPreferences a() {
        return this.f6019a;
    }

    @Override // io.a.a.a.a.f.c
    public SharedPreferences.Editor b() {
        return this.f6019a.edit();
    }

    @Override // io.a.a.a.a.f.c
    @TargetApi(9)
    public boolean a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
