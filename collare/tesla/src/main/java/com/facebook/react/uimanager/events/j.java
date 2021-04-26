package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.o;
import com.google.android.gms.measurement.AppMeasurement;

/* compiled from: TouchesHelper */
public class j {
    private static WritableArray a(int i, g gVar) {
        WritableArray createArray = Arguments.createArray();
        MotionEvent j = gVar.j();
        float x = j.getX() - gVar.k();
        float y = j.getY() - gVar.l();
        for (int i2 = 0; i2 < j.getPointerCount(); i2++) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("pageX", (double) o.d(j.getX(i2)));
            createMap.putDouble("pageY", (double) o.d(j.getY(i2)));
            createMap.putDouble("locationX", (double) o.d(j.getX(i2) - x));
            createMap.putDouble("locationY", (double) o.d(j.getY(i2) - y));
            createMap.putInt("target", i);
            createMap.putDouble(AppMeasurement.Param.TIMESTAMP, (double) gVar.e());
            createMap.putDouble("identifier", (double) j.getPointerId(i2));
            createArray.pushMap(createMap);
        }
        return createArray;
    }

    public static void a(RCTEventEmitter rCTEventEmitter, i iVar, int i, g gVar) {
        WritableArray a2 = a(i, gVar);
        MotionEvent j = gVar.j();
        WritableArray createArray = Arguments.createArray();
        if (iVar == i.MOVE || iVar == i.CANCEL) {
            for (int i2 = 0; i2 < j.getPointerCount(); i2++) {
                createArray.pushInt(i2);
            }
        } else if (iVar == i.START || iVar == i.END) {
            createArray.pushInt(j.getActionIndex());
        } else {
            throw new RuntimeException("Unknown touch type: " + iVar);
        }
        rCTEventEmitter.receiveTouches(i.a(iVar), a2, createArray);
    }
}
