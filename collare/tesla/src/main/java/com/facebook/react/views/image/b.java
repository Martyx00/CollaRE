package com.facebook.react.views.image;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: ImageLoadEvent */
public class b extends c<b> {

    /* renamed from: a  reason: collision with root package name */
    private final int f3323a;

    /* renamed from: b  reason: collision with root package name */
    private final String f3324b;

    /* renamed from: c  reason: collision with root package name */
    private final int f3325c;

    /* renamed from: d  reason: collision with root package name */
    private final int f3326d;
    private final String e;

    public b(int i, int i2) {
        this(i, i2, null);
    }

    public b(int i, int i2, boolean z, String str) {
        this(i, i2, null, 0, 0, str);
    }

    public b(int i, int i2, String str) {
        this(i, i2, str, 0, 0, null);
    }

    public b(int i, int i2, String str, int i3, int i4) {
        this(i, i2, str, i3, i4, null);
    }

    public b(int i, int i2, String str, int i3, int i4, String str2) {
        super(i);
        this.f3323a = i2;
        this.f3324b = str;
        this.f3325c = i3;
        this.f3326d = i4;
        this.e = str2;
    }

    public static String b(int i) {
        switch (i) {
            case 1:
                return "topError";
            case 2:
                return "topLoad";
            case 3:
                return "topLoadEnd";
            case 4:
                return "topLoadStart";
            case 5:
                return "topProgress";
            default:
                throw new IllegalStateException("Invalid image event: " + Integer.toString(i));
        }
    }

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return b(this.f3323a);
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return (short) this.f3323a;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        WritableMap writableMap;
        int i;
        if (this.f3324b != null || (i = this.f3323a) == 2 || i == 1) {
            writableMap = Arguments.createMap();
            String str = this.f3324b;
            if (str != null) {
                writableMap.putString("uri", str);
            }
            int i2 = this.f3323a;
            if (i2 == 2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("width", (double) this.f3325c);
                createMap.putDouble("height", (double) this.f3326d);
                String str2 = this.f3324b;
                if (str2 != null) {
                    createMap.putString(ImagesContract.URL, str2);
                }
                writableMap.putMap(FirebaseAnalytics.b.SOURCE, createMap);
            } else if (i2 == 1) {
                writableMap.putString("error", this.e);
            }
        } else {
            writableMap = null;
        }
        rCTEventEmitter.receiveEvent(d(), a(), writableMap);
    }
}
