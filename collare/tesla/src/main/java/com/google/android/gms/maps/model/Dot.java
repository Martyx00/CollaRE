package com.google.android.gms.maps.model;

public final class Dot extends PatternItem {
    public Dot() {
        super(1, null);
    }

    @Override // com.google.android.gms.maps.model.PatternItem
    public final String toString() {
        return "[Dot]";
    }
}
