package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

public interface zzz extends IInterface {
    int getColor();

    Cap getEndCap();

    String getId();

    int getJointType();

    List<PatternItem> getPattern();

    List<LatLng> getPoints();

    Cap getStartCap();

    float getWidth();

    float getZIndex();

    boolean isClickable();

    boolean isGeodesic();

    boolean isVisible();

    void remove();

    void setClickable(boolean z);

    void setColor(int i);

    void setEndCap(Cap cap);

    void setGeodesic(boolean z);

    void setJointType(int i);

    void setPattern(List<PatternItem> list);

    void setPoints(List<LatLng> list);

    void setStartCap(Cap cap);

    void setVisible(boolean z);

    void setWidth(float f);

    void setZIndex(float f);

    boolean zzb(zzz zzz);

    void zze(IObjectWrapper iObjectWrapper);

    int zzj();

    IObjectWrapper zzk();
}
