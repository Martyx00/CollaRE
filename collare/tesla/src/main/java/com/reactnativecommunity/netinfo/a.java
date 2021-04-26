package com.reactnativecommunity.netinfo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;

/* compiled from: BroadcastReceiverConnectivityReceiver */
public class a extends b {

    /* renamed from: a  reason: collision with root package name */
    private final C0084a f4747a = new C0084a();

    @Override // com.reactnativecommunity.netinfo.b
    public /* bridge */ /* synthetic */ void a(Promise promise) {
        super.a(promise);
    }

    @Override // com.reactnativecommunity.netinfo.b
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    @Override // com.reactnativecommunity.netinfo.b
    public /* bridge */ /* synthetic */ ConnectivityManager d() {
        return super.d();
    }

    @Override // com.reactnativecommunity.netinfo.b
    public /* bridge */ /* synthetic */ ReactApplicationContext e() {
        return super.e();
    }

    public a(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.reactnativecommunity.netinfo.b
    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        e().registerReceiver(this.f4747a, intentFilter);
        this.f4747a.a(true);
        f();
    }

    @Override // com.reactnativecommunity.netinfo.b
    public void b() {
        if (this.f4747a.a()) {
            e().unregisterReceiver(this.f4747a);
            this.f4747a.a(false);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint({"MissingPermission"})
    private void f() {
        String str = "other";
        String str2 = null;
        try {
            NetworkInfo activeNetworkInfo = d().getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    switch (activeNetworkInfo.getType()) {
                        case 0:
                        case 4:
                            str = "cellular";
                            str2 = a(activeNetworkInfo);
                            break;
                        case 1:
                            str = "wifi";
                            break;
                        case 6:
                            str = "wimax";
                            break;
                        case 7:
                            str = "bluetooth";
                            break;
                        case 9:
                            str = "ethernet";
                            break;
                        case 17:
                            str = "vpn";
                            break;
                    }
                    a(str, str2);
                }
            }
            str = "none";
        } catch (SecurityException unused) {
            c();
            str = "unknown";
        }
        a(str, str2);
    }

    /* renamed from: com.reactnativecommunity.netinfo.a$a  reason: collision with other inner class name */
    /* compiled from: BroadcastReceiverConnectivityReceiver */
    private class C0084a extends BroadcastReceiver {

        /* renamed from: b  reason: collision with root package name */
        private boolean f4749b;

        private C0084a() {
            this.f4749b = false;
        }

        public void a(boolean z) {
            this.f4749b = z;
        }

        public boolean a() {
            return this.f4749b;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                a.this.f();
            }
        }
    }
}
