package com.reactnativecommunity.netinfo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.c.a;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.measurement.AppMeasurement;
import org.spongycastle.i18n.ErrorBundle;

/* access modifiers changed from: package-private */
/* compiled from: ConnectivityReceiver */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private final ConnectivityManager f4750a;

    /* renamed from: b  reason: collision with root package name */
    private final ReactApplicationContext f4751b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4752c = false;

    /* renamed from: d  reason: collision with root package name */
    private String f4753d = "unknown";
    private String e = null;

    /* access modifiers changed from: package-private */
    public abstract void a();

    /* access modifiers changed from: package-private */
    public abstract void b();

    b(ReactApplicationContext reactApplicationContext) {
        this.f4751b = reactApplicationContext;
        this.f4750a = (ConnectivityManager) reactApplicationContext.getSystemService("connectivity");
    }

    public void a(Promise promise) {
        if (this.f4752c) {
            promise.reject("E_MISSING_PERMISSION", "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />");
        } else {
            promise.resolve(g());
        }
    }

    public ReactApplicationContext e() {
        return this.f4751b;
    }

    public ConnectivityManager d() {
        return this.f4750a;
    }

    public void c() {
        this.f4752c = true;
    }

    /* access modifiers changed from: package-private */
    public String a(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return null;
        }
        switch (networkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2g";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
                return "3g";
            case 13:
            case 15:
                return "4g";
            default:
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        boolean z = true;
        boolean z2 = (str == null && this.f4753d != null) || (str != null && !str.equalsIgnoreCase(this.f4753d));
        if ((str2 != null || this.e == null) && (str2 == null || str2.equalsIgnoreCase(this.e))) {
            z = false;
        }
        if (z2 || z) {
            this.f4753d = str;
            this.e = str2;
            f();
        }
    }

    private void f() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) e().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("netInfo.networkStatusDidChange", g());
    }

    private WritableMap g() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(AppMeasurement.Param.TYPE, this.f4753d);
        boolean z = !this.f4753d.equals("none") && !this.f4753d.equals("unknown");
        writableNativeMap.putBoolean("isConnected", z);
        WritableNativeMap writableNativeMap2 = null;
        if (z) {
            writableNativeMap2 = new WritableNativeMap();
            writableNativeMap2.putBoolean("isConnectionExpensive", a.a(d()));
            if (this.f4753d.equals("cellular")) {
                writableNativeMap2.putString("cellularGeneration", this.e);
            }
        }
        writableNativeMap.putMap(ErrorBundle.DETAIL_ENTRY, writableNativeMap2);
        return writableNativeMap;
    }
}
