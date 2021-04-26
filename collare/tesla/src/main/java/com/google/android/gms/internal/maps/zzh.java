package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

public interface zzh extends IInterface {
    LatLng getCenter();

    int getFillColor();

    String getId();

    double getRadius();

    int getStrokeColor();

    List<PatternItem> getStrokePattern();

    float getStrokeWidth();

    float getZIndex();

    boolean isClickable();

    boolean isVisible();

    void remove();

    void setCenter(LatLng latLng);

    void setClickable(boolean z);

    void setFillColor(int i);

    void setRadius(double d2);

    void setStrokeColor(int i);

    void setStrokePattern(List<PatternItem> list);

    void setStrokeWidth(float f);

    void setVisible(boolean z);

    void setZIndex(float f);

    boolean zzb(zzh zzh);

    void zze(IObjectWrapper iObjectWrapper);

    int zzj();

    IObjectWrapper zzk();
}
