package com.teslamotors.plugins.client.d;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import com.teslamotors.plugins.client.a.d;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: LocationServicesHelper */
public class c {

    /* renamed from: b  reason: collision with root package name */
    private static final String f5529b = "c";

    /* renamed from: c  reason: collision with root package name */
    private static final Pattern f5530c = Pattern.compile("(市|区|县|省|市|区|自治州|自治区|自治州|市|区|盟)");

    /* renamed from: d  reason: collision with root package name */
    private static c f5531d;

    /* renamed from: a  reason: collision with root package name */
    private Geocoder f5532a;
    private WeakReference<Context> e;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (f5531d == null) {
                f5531d = new c(context);
            }
            cVar = f5531d;
        }
        return cVar;
    }

    private c(Context context) {
        this.f5532a = new Geocoder(context);
        this.e = new WeakReference<>(context);
    }

    public void a(double d2, double d3, String str, d dVar) {
        List<Address> list;
        HashMap hashMap = new HashMap();
        if (!Geocoder.isPresent()) {
            dVar.a();
            return;
        }
        try {
            list = this.f5532a.getFromLocation(d2, d3, 1);
        } catch (IOException e2) {
            Log.e(f5529b, "Failed to get address from location", e2);
            list = null;
        }
        if (list == null) {
            dVar.a();
        } else if (list.size() == 0 || list.get(0).getMaxAddressLineIndex() < 0) {
            dVar.a(hashMap);
        } else {
            Address address = list.get(0);
            hashMap.put("street_prefix", address.getSubThoroughfare());
            hashMap.put("street_name", address.getThoroughfare());
            hashMap.put("city_name", address.getLocality());
            hashMap.put("region_name", address.getAdminArea());
            hashMap.put("country_name", address.getCountryName());
            hashMap.put("postal_code", address.getPostalCode());
            hashMap.put("country_code", address.getCountryCode());
            dVar.a(hashMap);
        }
    }
}
