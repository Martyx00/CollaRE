package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public interface zzt extends IInterface {
    float getAlpha();

    String getId();

    LatLng getPosition();

    float getRotation();

    String getSnippet();

    String getTitle();

    float getZIndex();

    void hideInfoWindow();

    boolean isDraggable();

    boolean isFlat();

    boolean isInfoWindowShown();

    boolean isVisible();

    void remove();

    void setAlpha(float f);

    void setAnchor(float f, float f2);

    void setDraggable(boolean z);

    void setFlat(boolean z);

    void setInfoWindowAnchor(float f, float f2);

    void setPosition(LatLng latLng);

    void setRotation(float f);

    void setSnippet(String str);

    void setTitle(String str);

    void setVisible(boolean z);

    void setZIndex(float f);

    void showInfoWindow();

    void zze(IObjectWrapper iObjectWrapper);

    void zzg(IObjectWrapper iObjectWrapper);

    int zzj();

    boolean zzj(zzt zzt);

    IObjectWrapper zzk();
}
