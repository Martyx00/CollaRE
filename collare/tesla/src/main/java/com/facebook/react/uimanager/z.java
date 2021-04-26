package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConfig;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ReactYogaConfigProvider */
public class z {

    /* renamed from: a  reason: collision with root package name */
    private static YogaConfig f3296a;

    public static YogaConfig a() {
        if (f3296a == null) {
            f3296a = new YogaConfig();
            f3296a.a(BitmapDescriptorFactory.HUE_RED);
            f3296a.a(true);
        }
        return f3296a;
    }
}
