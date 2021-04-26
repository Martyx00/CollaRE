package com.google.android.gms.maps.model;

public final class RoundCap extends Cap {
    public RoundCap() {
        super(2);
    }

    @Override // com.google.android.gms.maps.model.Cap
    public final String toString() {
        return "[RoundCap]";
    }
}
