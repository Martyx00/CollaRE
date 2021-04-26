package com.teslamotors.TeslaApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.g;
import com.facebook.react.h;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.teslamotors.TeslaApp.b;
import com.teslamotors.plugins.client.f;
import com.teslamotors.plugins.crashlytics.b;
import com.teslamotors.plugins.notifications.c;
import org.spongycastle.crypto.tls.CipherSuite;

public class MainActivity extends g {
    private static final String k = "MainActivity";

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.g
    public String i() {
        return "TeslaApp";
    }

    private static class a extends h {

        /* renamed from: a  reason: collision with root package name */
        private final Activity f4830a;

        /* renamed from: b  reason: collision with root package name */
        private OrientationEventListener f4831b;

        public a(Activity activity, String str) {
            super(activity, str);
            this.f4830a = activity;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.react.h
        public void a(Bundle bundle) {
            super.a(bundle);
            this.f4831b = new OrientationEventListener(this.f4830a.getApplicationContext()) {
                /* class com.teslamotors.TeslaApp.MainActivity.a.AnonymousClass1 */

                public void onOrientationChanged(int i) {
                    a.this.a((a) i);
                }
            };
            a(this.f4830a.getResources().getConfiguration());
            b.a(this.f4830a);
            this.f4830a.getWindow().getDecorView().setSystemUiVisibility(1280);
            f.a(BuildConfig.GIT_HASH, this.f4830a);
            f a2 = f.a((Context) this.f4830a);
            a2.F();
            if (this.f4830a.getIntent() != null && "android.intent.action.MAIN".equalsIgnoreCase(this.f4830a.getIntent().getAction())) {
                a2.a(6, "standard");
            }
            new c().a(this.f4830a.getApplicationContext());
            String i = a2.i();
            String s = a2.s();
            if (s == null || (i != null && !i.equals(s))) {
                a2.u();
            }
            a2.c(i);
            if (Build.VERSION.SDK_INT >= 26) {
                com.teslamotors.plugins.notifications.b.a(this.f4830a.getApplicationContext()).a();
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.react.h
        public void e() {
            super.e();
            OrientationEventListener orientationEventListener = this.f4831b;
            if (orientationEventListener != null) {
                orientationEventListener.disable();
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.react.h
        public void f() {
            super.f();
            OrientationEventListener orientationEventListener = this.f4831b;
            if (orientationEventListener != null) {
                orientationEventListener.enable();
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.react.h
        public Bundle a() {
            f.a(BuildConfig.GIT_HASH, this.f4830a);
            f a2 = f.a((Context) this.f4830a);
            Bundle bundle = new Bundle();
            bundle.putString("mode", BuildConfig.MODE);
            bundle.putString("ownerAPIBaseURL", a2.d());
            bundle.putString("ownerAPIClientID", a2.e());
            bundle.putString("ownerAPIClientSecret", a2.f());
            bundle.putString("streamingServerBaseURL", a2.g());
            bundle.putString("singleSignOnClientID", a2.h());
            bundle.putString("remoteNotificationDeviceType", a2.q());
            bundle.putString("gitHash", BuildConfig.GIT_HASH);
            if (a2.c() != null) {
                bundle.putString("googleAnalyticsTrackerID", a2.c());
            }
            bundle.putLong("appStartTimestamp", System.currentTimeMillis());
            return bundle;
        }

        @Override // com.facebook.react.h
        public boolean a(Intent intent) {
            super.a(intent);
            return c.a().a(intent);
        }

        private void a(Configuration configuration) {
            if (configuration.fontScale != 1.0f) {
                configuration.fontScale = 1.0f;
                DisplayMetrics displayMetrics = this.f4830a.getResources().getDisplayMetrics();
                ((WindowManager) this.f4830a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                displayMetrics.scaledDensity = configuration.fontScale * displayMetrics.density;
                this.f4830a.getBaseContext().getResources().updateConfiguration(configuration, displayMetrics);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(int i) {
            String str;
            boolean z = true;
            int i2 = CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
            if (i == 90) {
                str = "landscape-secondary";
                i2 = 90;
            } else if (i == 180) {
                str = "portrait-secondary";
                z = false;
            } else if (i != 270) {
                str = "portrait-primary";
                z = false;
                i2 = 0;
            } else {
                str = "landscape-primary";
                i2 = -90;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString("name", str);
            createMap.putInt("rotationDegrees", i2);
            createMap.putBoolean("isLandscape", z);
            if (d().j() != null) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) d().j().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("namedOrientationDidChange", createMap);
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            b.a().a(b.a.ACTIVE);
        } else {
            b.a().a(b.a.INACTIVE);
        }
    }

    @Override // android.support.v4.app.h, com.facebook.react.g, android.support.v4.app.a.AbstractC0004a
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        f.a(getApplicationContext()).a(i, strArr, iArr);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.g
    public h j() {
        return new a(this, i());
    }
}
