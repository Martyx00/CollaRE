package com.google.android.gms.maps.model;

public final class Gap extends PatternItem {
    public final float length;

    public Gap(float f) {
        super(2, Float.valueOf(Math.max(f, (float) BitmapDescriptorFactory.HUE_RED)));
        this.length = Math.max(f, (float) BitmapDescriptorFactory.HUE_RED);
    }

    @Override // com.google.android.gms.maps.model.PatternItem
    public final String toString() {
        float f = this.length;
        StringBuilder sb = new StringBuilder(29);
        sb.append("[Gap: length=");
        sb.append(f);
        sb.append("]");
        return sb.toString();
    }
}
