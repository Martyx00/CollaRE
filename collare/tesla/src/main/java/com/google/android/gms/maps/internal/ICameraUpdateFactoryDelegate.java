package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface ICameraUpdateFactoryDelegate extends IInterface {
    IObjectWrapper newCameraPosition(CameraPosition cameraPosition);

    IObjectWrapper newLatLng(LatLng latLng);

    IObjectWrapper newLatLngBounds(LatLngBounds latLngBounds, int i);

    IObjectWrapper newLatLngBoundsWithSize(LatLngBounds latLngBounds, int i, int i2, int i3);

    IObjectWrapper newLatLngZoom(LatLng latLng, float f);

    IObjectWrapper scrollBy(float f, float f2);

    IObjectWrapper zoomBy(float f);

    IObjectWrapper zoomByWithFocus(float f, int i, int i2);

    IObjectWrapper zoomIn();

    IObjectWrapper zoomOut();

    IObjectWrapper zoomTo(float f);
}
