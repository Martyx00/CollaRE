package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.g;
import android.support.v4.app.h;
import android.support.v4.util.a;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzc extends g implements LifecycleFragment {
    private static WeakHashMap<h, WeakReference<zzc>> zzbe = new WeakHashMap<>();
    private Map<String, LifecycleCallback> zzbf = new a();
    private int zzbg = 0;
    private Bundle zzbh;

    public static zzc zza(h hVar) {
        zzc zzc;
        WeakReference<zzc> weakReference = zzbe.get(hVar);
        if (weakReference != null && (zzc = weakReference.get()) != null) {
            return zzc;
        }
        try {
            zzc zzc2 = (zzc) hVar.d().a("SupportLifecycleFragmentImpl");
            if (zzc2 == null || zzc2.isRemoving()) {
                zzc2 = new zzc();
                hVar.d().a().a(zzc2, "SupportLifecycleFragmentImpl").d();
            }
            zzbe.put(hVar, new WeakReference<>(zzc2));
            return zzc2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return cls.cast(this.zzbf.get(str));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String str, LifecycleCallback lifecycleCallback) {
        if (!this.zzbf.containsKey(str)) {
            this.zzbf.put(str, lifecycleCallback);
            if (this.zzbg > 0) {
                new zze(Looper.getMainLooper()).post(new zzd(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 59);
        sb.append("LifecycleCallback with tag ");
        sb.append(str);
        sb.append(" already added to this fragment.");
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isCreated() {
        return this.zzbg > 0;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isStarted() {
        return this.zzbg >= 2;
    }

    @Override // android.support.v4.app.g
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbg = 1;
        this.zzbh = bundle;
        for (Map.Entry<String, LifecycleCallback> entry : this.zzbf.entrySet()) {
            entry.getValue().onCreate(bundle != null ? bundle.getBundle(entry.getKey()) : null);
        }
    }

    @Override // android.support.v4.app.g
    public final void onStart() {
        super.onStart();
        this.zzbg = 2;
        for (LifecycleCallback lifecycleCallback : this.zzbf.values()) {
            lifecycleCallback.onStart();
        }
    }

    @Override // android.support.v4.app.g
    public final void onResume() {
        super.onResume();
        this.zzbg = 3;
        for (LifecycleCallback lifecycleCallback : this.zzbf.values()) {
            lifecycleCallback.onResume();
        }
    }

    @Override // android.support.v4.app.g
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback lifecycleCallback : this.zzbf.values()) {
            lifecycleCallback.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.support.v4.app.g
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Map.Entry<String, LifecycleCallback> entry : this.zzbf.entrySet()) {
                Bundle bundle2 = new Bundle();
                entry.getValue().onSaveInstanceState(bundle2);
                bundle.putBundle(entry.getKey(), bundle2);
            }
        }
    }

    @Override // android.support.v4.app.g
    public final void onStop() {
        super.onStop();
        this.zzbg = 4;
        for (LifecycleCallback lifecycleCallback : this.zzbf.values()) {
            lifecycleCallback.onStop();
        }
    }

    @Override // android.support.v4.app.g
    public final void onDestroy() {
        super.onDestroy();
        this.zzbg = 5;
        for (LifecycleCallback lifecycleCallback : this.zzbf.values()) {
            lifecycleCallback.onDestroy();
        }
    }

    @Override // android.support.v4.app.g
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.zzbf.values()) {
            lifecycleCallback.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final /* synthetic */ Activity getLifecycleActivity() {
        return getActivity();
    }
}
