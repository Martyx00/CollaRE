package com.facebook.react.modules.network;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.net.SocketTimeoutException;

/* compiled from: ResponseUtil */
public class o {
    public static void a(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, long j, long j2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushInt((int) j);
        createArray.pushInt((int) j2);
        rCTDeviceEventEmitter.emit("didSendNetworkData", createArray);
    }

    public static void a(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, String str, long j, long j2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushString(str);
        createArray.pushInt((int) j);
        createArray.pushInt((int) j2);
        rCTDeviceEventEmitter.emit("didReceiveNetworkIncrementalData", createArray);
    }

    public static void b(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, long j, long j2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushInt((int) j);
        createArray.pushInt((int) j2);
        rCTDeviceEventEmitter.emit("didReceiveNetworkDataProgress", createArray);
    }

    public static void a(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, String str) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushString(str);
        rCTDeviceEventEmitter.emit("didReceiveNetworkData", createArray);
    }

    public static void a(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, WritableMap writableMap) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushMap(writableMap);
        rCTDeviceEventEmitter.emit("didReceiveNetworkData", createArray);
    }

    public static void a(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, String str, Throwable th) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushString(str);
        if (th != null && th.getClass() == SocketTimeoutException.class) {
            createArray.pushBoolean(true);
        }
        rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
    }

    public static void a(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushNull();
        rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
    }

    public static void a(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, int i2, WritableMap writableMap, String str) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        createArray.pushInt(i2);
        createArray.pushMap(writableMap);
        createArray.pushString(str);
        rCTDeviceEventEmitter.emit("didReceiveNetworkResponse", createArray);
    }
}
