package com.reactnativecommunity.netinfo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.facebook.react.bridge.ReactApplicationContext;

@TargetApi(24)
/* compiled from: NetworkCallbackConnectivityReceiver */
class d extends b {

    /* renamed from: a  reason: collision with root package name */
    private final a f4754a = new a();

    /* renamed from: b  reason: collision with root package name */
    private Network f4755b = null;

    /* renamed from: c  reason: collision with root package name */
    private NetworkCapabilities f4756c = null;

    public d(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: package-private */
    @Override // com.reactnativecommunity.netinfo.b
    @SuppressLint({"MissingPermission"})
    public void a() {
        try {
            d().registerDefaultNetworkCallback(this.f4754a);
            if (d().getActiveNetwork() == null) {
                f();
            }
        } catch (SecurityException unused) {
            c();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.reactnativecommunity.netinfo.b
    public void b() {
        try {
            d().unregisterNetworkCallback(this.f4754a);
        } catch (SecurityException unused) {
            c();
        } catch (IllegalArgumentException unused2) {
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint({"MissingPermission"})
    private void f() {
        String str = "other";
        NetworkCapabilities networkCapabilities = this.f4756c;
        String str2 = null;
        if (networkCapabilities == null) {
            str = "none";
        } else if (networkCapabilities.hasTransport(2)) {
            str = "bluetooth";
        } else if (this.f4756c.hasTransport(0)) {
            str = "cellular";
            if (this.f4755b != null) {
                str2 = a(d().getNetworkInfo(this.f4755b));
            }
        } else if (this.f4756c.hasTransport(3)) {
            str = "ethernet";
        } else if (this.f4756c.hasTransport(1)) {
            str = "wifi";
        } else if (this.f4756c.hasTransport(4)) {
            str = "vpn";
        }
        a(str, str2);
    }

    /* compiled from: NetworkCallbackConnectivityReceiver */
    private class a extends ConnectivityManager.NetworkCallback {
        private a() {
        }

        public void onAvailable(Network network) {
            d.this.f4755b = network;
            d dVar = d.this;
            dVar.f4756c = dVar.d().getNetworkCapabilities(network);
            d.this.f();
        }

        public void onLosing(Network network, int i) {
            d.this.f4755b = network;
            d dVar = d.this;
            dVar.f4756c = dVar.d().getNetworkCapabilities(network);
            d.this.f();
        }

        public void onLost(Network network) {
            d.this.f4755b = null;
            d.this.f4756c = null;
            d.this.f();
        }

        public void onUnavailable() {
            d.this.f4755b = null;
            d.this.f4756c = null;
            d.this.f();
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            d.this.f4755b = network;
            d.this.f4756c = networkCapabilities;
            d.this.f();
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            d.this.f4755b = network;
            d dVar = d.this;
            dVar.f4756c = dVar.d().getNetworkCapabilities(network);
            d.this.f();
        }
    }
}
