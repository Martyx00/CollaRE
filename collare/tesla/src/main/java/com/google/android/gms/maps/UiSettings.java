package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
    private final IUiSettingsDelegate zzcj;

    UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        this.zzcj = iUiSettingsDelegate;
    }

    public final void setZoomControlsEnabled(boolean z) {
        try {
            this.zzcj.setZoomControlsEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setCompassEnabled(boolean z) {
        try {
            this.zzcj.setCompassEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationButtonEnabled(boolean z) {
        try {
            this.zzcj.setMyLocationButtonEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setIndoorLevelPickerEnabled(boolean z) {
        try {
            this.zzcj.setIndoorLevelPickerEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setScrollGesturesEnabled(boolean z) {
        try {
            this.zzcj.setScrollGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setZoomGesturesEnabled(boolean z) {
        try {
            this.zzcj.setZoomGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setTiltGesturesEnabled(boolean z) {
        try {
            this.zzcj.setTiltGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setRotateGesturesEnabled(boolean z) {
        try {
            this.zzcj.setRotateGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        try {
            this.zzcj.setScrollGesturesEnabledDuringRotateOrZoom(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setAllGesturesEnabled(boolean z) {
        try {
            this.zzcj.setAllGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMapToolbarEnabled(boolean z) {
        try {
            this.zzcj.setMapToolbarEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isZoomControlsEnabled() {
        try {
            return this.zzcj.isZoomControlsEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isCompassEnabled() {
        try {
            return this.zzcj.isCompassEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isMyLocationButtonEnabled() {
        try {
            return this.zzcj.isMyLocationButtonEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isIndoorLevelPickerEnabled() {
        try {
            return this.zzcj.isIndoorLevelPickerEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isScrollGesturesEnabled() {
        try {
            return this.zzcj.isScrollGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        try {
            return this.zzcj.isScrollGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isZoomGesturesEnabled() {
        try {
            return this.zzcj.isZoomGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isTiltGesturesEnabled() {
        try {
            return this.zzcj.isTiltGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isRotateGesturesEnabled() {
        try {
            return this.zzcj.isRotateGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isMapToolbarEnabled() {
        try {
            return this.zzcj.isMapToolbarEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
