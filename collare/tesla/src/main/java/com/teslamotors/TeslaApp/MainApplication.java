package com.teslamotors.TeslaApp;

import android.app.Application;
import cl.json.c;
import com.RNFetchBlob.e;
import com.airbnb.android.react.maps.p;
import com.facebook.react.j;
import com.facebook.react.n;
import com.facebook.react.o;
import com.horcrux.svg.SvgPackage;
import com.teslamotors.plugins.colorblending.b;
import com.teslamotors.plugins.devicestorage.a;
import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements c, j {

    /* renamed from: a  reason: collision with root package name */
    private final n f4833a = new n(this) {
        /* class com.teslamotors.TeslaApp.MainApplication.AnonymousClass1 */

        @Override // com.facebook.react.n
        public boolean k() {
            return false;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.react.n
        public List<o> l() {
            return Arrays.asList(new d(), new b(), new a(), new com.teslamotors.plugins.nativelocationutils.a(), new com.teslamotors.plugins.shakehandler.b(), new p(), new com.learnium.RNDeviceInfo.a(), new e(), new com.rnfs.e(), new cl.json.a(), new com.i18n.reactnativei18n.a(), new com.idehub.GoogleAnalyticsBridge.b(), new com.teslamotors.plugins.alert.b(), new com.teslamotors.plugins.viewsnapshotter.a(), new com.teslamotors.plugins.client.a(), new com.teslamotors.plugins.crashlytics.a(), new com.teslamotors.plugins.calendar.b(), new com.teslamotors.plugins.contacts.a(), new com.teslamotors.plugins.proximitysensornotifier.a(), com.teslamotors.plugins.notifications.c.a(), new SvgPackage(), new com.BV.LinearGradient.a(), new com.teslamotors.plugins.biometricauthentication.a(), new com.teslamotors.plugins.ble.e(), new com.zmxv.RNSound.a(), new com.reactnativecommunity.webview.a(), new com.github.reactnativecommunity.location.a(), new com.teslamotors.plugins.securewebview.b(), new com.reactnativecommunity.netinfo.c(), new com.oney.WebRTCModule.j(), new com.sha256lib.a());
        }
    };

    @Override // cl.json.c
    public String a() {
        return "com.teslamotors.tesla.provider";
    }

    @Override // com.facebook.react.j
    public n b() {
        return this.f4833a;
    }

    public void onCreate() {
        super.onCreate();
        io.realm.o.a(this);
    }
}
