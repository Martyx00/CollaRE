package com.google.android.gms.maps.internal;

import android.os.IInterface;

public interface IUiSettingsDelegate extends IInterface {
    boolean isCompassEnabled();

    boolean isIndoorLevelPickerEnabled();

    boolean isMapToolbarEnabled();

    boolean isMyLocationButtonEnabled();

    boolean isRotateGesturesEnabled();

    boolean isScrollGesturesEnabled();

    boolean isScrollGesturesEnabledDuringRotateOrZoom();

    boolean isTiltGesturesEnabled();

    boolean isZoomControlsEnabled();

    boolean isZoomGesturesEnabled();

    void setAllGesturesEnabled(boolean z);

    void setCompassEnabled(boolean z);

    void setIndoorLevelPickerEnabled(boolean z);

    void setMapToolbarEnabled(boolean z);

    void setMyLocationButtonEnabled(boolean z);

    void setRotateGesturesEnabled(boolean z);

    void setScrollGesturesEnabled(boolean z);

    void setScrollGesturesEnabledDuringRotateOrZoom(boolean z);

    void setTiltGesturesEnabled(boolean z);

    void setZoomControlsEnabled(boolean z);

    void setZoomGesturesEnabled(boolean z);
}
