package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.maps.model.CameraPosition;

public interface zzl extends IInterface {
    void onCameraChange(CameraPosition cameraPosition);
}
