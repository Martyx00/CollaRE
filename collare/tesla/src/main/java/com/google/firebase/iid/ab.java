package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

/* access modifiers changed from: package-private */
public final class ab implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final long f3861a;

    /* renamed from: b  reason: collision with root package name */
    private final PowerManager.WakeLock f3862b = ((PowerManager) a().getSystemService("power")).newWakeLock(1, "fiid-sync");

    /* renamed from: c  reason: collision with root package name */
    private final FirebaseInstanceId f3863c;

    /* renamed from: d  reason: collision with root package name */
    private final q f3864d;
    private final ae e;

    @VisibleForTesting
    ab(FirebaseInstanceId firebaseInstanceId, q qVar, ae aeVar, long j) {
        this.f3863c = firebaseInstanceId;
        this.f3864d = qVar;
        this.e = aeVar;
        this.f3861a = j;
        this.f3862b.setReferenceCounted(false);
    }

    public final void run() {
        this.f3862b.acquire();
        try {
            this.f3863c.a(true);
            if (!this.f3863c.j()) {
                this.f3863c.a(false);
            } else if (!b()) {
                new ac(this).a();
                this.f3862b.release();
            } else {
                if (!c() || !d() || !this.e.a(this.f3863c)) {
                    this.f3863c.a(this.f3861a);
                } else {
                    this.f3863c.a(false);
                }
                this.f3862b.release();
            }
        } finally {
            this.f3862b.release();
        }
    }

    @VisibleForTesting
    private final boolean c() {
        try {
            if (this.f3863c.k()) {
                return true;
            }
            this.f3863c.l();
            return true;
        } catch (IOException e2) {
            String valueOf = String.valueOf(e2.getMessage());
            Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Build channel failed: ".concat(valueOf) : new String("Build channel failed: "));
            return false;
        }
    }

    @VisibleForTesting
    private final boolean d() {
        aa f = this.f3863c.f();
        if (f != null && !f.b(this.f3864d.b())) {
            return true;
        }
        try {
            String g = this.f3863c.g();
            if (g == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (f == null || (f != null && !g.equals(f.f3858a))) {
                Context a2 = a();
                Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                intent.putExtra("token", g);
                y.b(a2, intent);
                y.a(a2, new Intent("com.google.firebase.iid.TOKEN_REFRESH"));
            }
            return true;
        } catch (IOException | SecurityException e2) {
            String valueOf = String.valueOf(e2.getMessage());
            Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Token retrieval failed: ".concat(valueOf) : new String("Token retrieval failed: "));
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final Context a() {
        return this.f3863c.b().a();
    }

    /* access modifiers changed from: package-private */
    public final boolean b() {
        ConnectivityManager connectivityManager = (ConnectivityManager) a().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
