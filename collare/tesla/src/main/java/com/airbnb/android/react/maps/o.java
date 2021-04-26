package com.airbnb.android.react.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: LatLngBoundsUtils */
public class o {
    public static boolean a(LatLngBounds latLngBounds, LatLngBounds latLngBounds2) {
        LatLng center = latLngBounds.getCenter();
        double d2 = center.latitude;
        double d3 = center.longitude;
        double d4 = latLngBounds.northeast.latitude - latLngBounds.southwest.latitude;
        double d5 = latLngBounds.northeast.longitude - latLngBounds.southwest.longitude;
        LatLng center2 = latLngBounds2.getCenter();
        double d6 = center2.latitude;
        double d7 = center2.longitude;
        double d8 = latLngBounds2.northeast.latitude - latLngBounds2.southwest.latitude;
        double d9 = latLngBounds2.northeast.longitude - latLngBounds2.southwest.longitude;
        double b2 = b(latLngBounds, latLngBounds2);
        double c2 = c(latLngBounds, latLngBounds2);
        return a(d2, d6, b2) || a(d3, d7, c2) || a(d4, d8, b2) || a(d5, d9, c2);
    }

    private static boolean a(double d2, double d3, double d4) {
        return Math.abs(d2 - d3) > d4;
    }

    private static double b(LatLngBounds latLngBounds, LatLngBounds latLngBounds2) {
        return Math.min(Math.abs(latLngBounds.northeast.latitude - latLngBounds.southwest.latitude), Math.abs(latLngBounds2.northeast.latitude - latLngBounds2.southwest.latitude)) / 2560.0d;
    }

    private static double c(LatLngBounds latLngBounds, LatLngBounds latLngBounds2) {
        return Math.min(Math.abs(latLngBounds.northeast.longitude - latLngBounds.southwest.longitude), Math.abs(latLngBounds2.northeast.longitude - latLngBounds2.southwest.longitude)) / 2560.0d;
    }
}
