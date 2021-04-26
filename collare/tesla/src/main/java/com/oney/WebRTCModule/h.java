package com.oney.WebRTCModule;

import android.util.Log;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.VideoCapturer;

/* compiled from: VideoCaptureController */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4726a = "h";

    /* renamed from: b  reason: collision with root package name */
    private int f4727b = 1280;

    /* renamed from: c  reason: collision with root package name */
    private int f4728c;

    /* renamed from: d  reason: collision with root package name */
    private int f4729d;
    private final a e;
    private VideoCapturer f;

    public h(CameraEnumerator cameraEnumerator, ReadableMap readableMap) {
        int i = 1280;
        int i2 = 720;
        this.f4728c = 720;
        int i3 = 30;
        this.f4729d = 30;
        this.e = new a();
        ReadableMap map = (!readableMap.hasKey("mandatory") || readableMap.getType("mandatory") != ReadableType.Map) ? null : readableMap.getMap("mandatory");
        this.f = a(cameraEnumerator, b(readableMap), a(readableMap));
        if (map != null) {
            this.f4727b = map.hasKey("minWidth") ? map.getInt("minWidth") : i;
            this.f4728c = map.hasKey("minHeight") ? map.getInt("minHeight") : i2;
            this.f4729d = map.hasKey("minFrameRate") ? map.getInt("minFrameRate") : i3;
        }
    }

    public void a() {
        VideoCapturer videoCapturer = this.f;
        if (videoCapturer != null) {
            videoCapturer.dispose();
            this.f = null;
        }
    }

    public VideoCapturer b() {
        return this.f;
    }

    public void c() {
        try {
            this.f.startCapture(this.f4727b, this.f4728c, this.f4729d);
        } catch (RuntimeException unused) {
        }
    }

    public boolean d() {
        try {
            this.f.stopCapture();
            return true;
        } catch (InterruptedException unused) {
            return false;
        }
    }

    public void e() {
        VideoCapturer videoCapturer = this.f;
        if (videoCapturer instanceof CameraVideoCapturer) {
            ((CameraVideoCapturer) videoCapturer).switchCamera(null);
        }
    }

    private VideoCapturer a(CameraEnumerator cameraEnumerator, String str, String str2) {
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            int length = deviceNames.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str3 = deviceNames[i];
                if (str3.equals(str)) {
                    CameraVideoCapturer createCapturer = cameraEnumerator.createCapturer(str3, this.e);
                    String str4 = "Create user-specified camera " + str3;
                    if (createCapturer != null) {
                        Log.d(f4726a, str4 + " succeeded");
                        return createCapturer;
                    }
                    Log.d(f4726a, str4 + " failed");
                    arrayList.add(str3);
                } else {
                    i++;
                }
            }
        }
        boolean z = str2 == null || !str2.equals("environment");
        for (String str5 : deviceNames) {
            if (!arrayList.contains(str5)) {
                try {
                    if (cameraEnumerator.isFrontFacing(str5) != z) {
                        continue;
                    } else {
                        CameraVideoCapturer createCapturer2 = cameraEnumerator.createCapturer(str5, this.e);
                        String str6 = "Create camera " + str5;
                        if (createCapturer2 != null) {
                            Log.d(f4726a, str6 + " succeeded");
                            return createCapturer2;
                        }
                        Log.d(f4726a, str6 + " failed");
                        arrayList.add(str5);
                    }
                } catch (Exception e2) {
                    Log.e(f4726a, "Failed to check the facing mode of camera " + str5, e2);
                    arrayList.add(str5);
                }
            }
        }
        for (String str7 : deviceNames) {
            if (!arrayList.contains(str7)) {
                CameraVideoCapturer createCapturer3 = cameraEnumerator.createCapturer(str7, this.e);
                String str8 = "Create fallback camera " + str7;
                if (createCapturer3 != null) {
                    Log.d(f4726a, str8 + " succeeded");
                    return createCapturer3;
                }
                Log.d(f4726a, str8 + " failed");
                arrayList.add(str7);
            }
        }
        Log.w(f4726a, "Unable to identify a suitable camera.");
        return null;
    }

    private String a(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        return f.a(readableMap, "facingMode");
    }

    private String b(ReadableMap readableMap) {
        if (readableMap == null || !readableMap.hasKey("optional") || readableMap.getType("optional") != ReadableType.Array) {
            return null;
        }
        ReadableArray array = readableMap.getArray("optional");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            if (array.getType(i) == ReadableType.Map) {
                ReadableMap map = array.getMap(i);
                if (map.hasKey("sourceId") && map.getType("sourceId") == ReadableType.String) {
                    return map.getString("sourceId");
                }
            }
        }
        return null;
    }
}
