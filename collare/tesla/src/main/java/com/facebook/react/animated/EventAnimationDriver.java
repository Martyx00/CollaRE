package com.facebook.react.animated;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;

class EventAnimationDriver implements RCTEventEmitter {
    private List<String> mEventPath;
    s mValueNode;

    public EventAnimationDriver(List<String> list, s sVar) {
        this.mEventPath = list;
        this.mValueNode = sVar;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, String str, WritableMap writableMap) {
        if (writableMap != null) {
            int i2 = 0;
            WritableMap writableMap2 = writableMap;
            while (i2 < this.mEventPath.size() - 1) {
                i2++;
                writableMap2 = writableMap2.getMap(this.mEventPath.get(i2));
            }
            s sVar = this.mValueNode;
            List<String> list = this.mEventPath;
            sVar.e = writableMap2.getDouble(list.get(list.size() - 1));
            return;
        }
        throw new IllegalArgumentException("Native animated events must have event data.");
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
