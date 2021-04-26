package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.views.view.f;
import com.google.android.gms.maps.GoogleMap;

/* compiled from: AirMapFeature */
public abstract class c extends f {
    public abstract void b(GoogleMap googleMap);

    public abstract Object getFeature();

    public c(Context context) {
        super(context);
    }
}
