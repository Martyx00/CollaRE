package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import com.facebook.react.views.view.f;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ViewAttacherGroup */
public class s extends f {
    @Override // com.facebook.react.views.view.f
    public boolean hasOverlappingRendering() {
        return false;
    }

    public s(Context context) {
        super(context);
        setWillNotDraw(true);
        setVisibility(0);
        setAlpha(BitmapDescriptorFactory.HUE_RED);
        setRemoveClippedSubviews(false);
        if (Build.VERSION.SDK_INT >= 18) {
            setClipBounds(new Rect(0, 0, 0, 0));
        }
        setOverflow("hidden");
    }
}
