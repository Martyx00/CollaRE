package com.airbnb.android.react.maps;

import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.maps.GoogleMapOptions;

public class AirMapLiteManager extends AirMapManager {
    private static final String REACT_CLASS = "AIRMapLite";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager, com.airbnb.android.react.maps.AirMapManager
    public String getName() {
        return REACT_CLASS;
    }

    public AirMapLiteManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.googleMapOptions = new GoogleMapOptions().liteMode(true);
    }
}
