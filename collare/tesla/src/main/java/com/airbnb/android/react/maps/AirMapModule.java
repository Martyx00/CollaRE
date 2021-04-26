package com.airbnb.android.react.maps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ai;
import com.facebook.react.uimanager.k;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AirMapModule extends ReactContextBaseJavaModule {
    private static final String SNAPSHOT_FORMAT_JPG = "jpg";
    private static final String SNAPSHOT_FORMAT_PNG = "png";
    private static final String SNAPSHOT_RESULT_BASE64 = "base64";
    private static final String SNAPSHOT_RESULT_FILE = "file";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AirMapModule";
    }

    public AirMapModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("legalNotice", "This license information is displayed in Settings > Google > Open Source on any device running Google Play services.");
        return hashMap;
    }

    public Activity getActivity() {
        return getCurrentActivity();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @ReactMethod
    public void takeSnapshot(final int i, ReadableMap readableMap, final Promise promise) {
        final Bitmap.CompressFormat compressFormat;
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final String string = readableMap.hasKey("format") ? readableMap.getString("format") : SNAPSHOT_FORMAT_PNG;
        if (string.equals(SNAPSHOT_FORMAT_PNG)) {
            compressFormat = Bitmap.CompressFormat.PNG;
        } else {
            compressFormat = string.equals(SNAPSHOT_FORMAT_JPG) ? Bitmap.CompressFormat.JPEG : null;
        }
        final double d2 = readableMap.hasKey("quality") ? readableMap.getDouble("quality") : 1.0d;
        DisplayMetrics displayMetrics = reactApplicationContext.getResources().getDisplayMetrics();
        int i2 = 0;
        final Integer valueOf = Integer.valueOf(readableMap.hasKey("width") ? (int) (((double) displayMetrics.density) * readableMap.getDouble("width")) : 0);
        if (readableMap.hasKey("height")) {
            i2 = (int) (((double) displayMetrics.density) * readableMap.getDouble("height"));
        }
        final Integer valueOf2 = Integer.valueOf(i2);
        final String string2 = readableMap.hasKey(BiometricAuthenticationModule.RESULT) ? readableMap.getString(BiometricAuthenticationModule.RESULT) : SNAPSHOT_RESULT_FILE;
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new ai() {
            /* class com.airbnb.android.react.maps.AirMapModule.AnonymousClass1 */

            @Override // com.facebook.react.uimanager.ai
            public void a(k kVar) {
                j jVar = (j) kVar.a(i);
                if (jVar == null) {
                    promise.reject("AirMapView not found");
                } else if (jVar.f1585a == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    jVar.f1585a.snapshot(new GoogleMap.SnapshotReadyCallback() {
                        /* class com.airbnb.android.react.maps.AirMapModule.AnonymousClass1.AnonymousClass1 */

                        @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
                        public void onSnapshotReady(Bitmap bitmap) {
                            if (bitmap == null) {
                                promise.reject("Failed to generate bitmap, snapshot = null");
                                return;
                            }
                            if (!(valueOf.intValue() == 0 || valueOf2.intValue() == 0 || (valueOf.intValue() == bitmap.getWidth() && valueOf2.intValue() == bitmap.getHeight()))) {
                                bitmap = Bitmap.createScaledBitmap(bitmap, valueOf.intValue(), valueOf2.intValue(), true);
                            }
                            if (string2.equals(AirMapModule.SNAPSHOT_RESULT_FILE)) {
                                try {
                                    File createTempFile = File.createTempFile("AirMapSnapshot", "." + string, reactApplicationContext.getCacheDir());
                                    FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                                    bitmap.compress(compressFormat, (int) (d2 * 100.0d), fileOutputStream);
                                    AirMapModule.closeQuietly(fileOutputStream);
                                    promise.resolve(Uri.fromFile(createTempFile).toString());
                                } catch (Exception e) {
                                    promise.reject(e);
                                }
                            } else if (string2.equals(AirMapModule.SNAPSHOT_RESULT_BASE64)) {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                bitmap.compress(compressFormat, (int) (d2 * 100.0d), byteArrayOutputStream);
                                AirMapModule.closeQuietly(byteArrayOutputStream);
                                promise.resolve(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
                            }
                        }
                    });
                }
            }
        });
    }

    @ReactMethod
    public void getCamera(final int i, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new ai() {
            /* class com.airbnb.android.react.maps.AirMapModule.AnonymousClass2 */

            @Override // com.facebook.react.uimanager.ai
            public void a(k kVar) {
                j jVar = (j) kVar.a(i);
                if (jVar == null) {
                    promise.reject("AirMapView not found");
                } else if (jVar.f1585a == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    CameraPosition cameraPosition = jVar.f1585a.getCameraPosition();
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("latitude", cameraPosition.target.latitude);
                    writableNativeMap.putDouble("longitude", cameraPosition.target.longitude);
                    WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                    writableNativeMap2.putMap("center", writableNativeMap);
                    writableNativeMap2.putDouble("heading", (double) cameraPosition.bearing);
                    writableNativeMap2.putDouble("zoom", (double) cameraPosition.zoom);
                    writableNativeMap2.putDouble("pitch", (double) cameraPosition.tilt);
                    promise.resolve(writableNativeMap2);
                }
            }
        });
    }

    @ReactMethod
    public void pointForCoordinate(final int i, ReadableMap readableMap, final Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final double d2 = (double) reactApplicationContext.getResources().getDisplayMetrics().density;
        double d3 = 0.0d;
        double d4 = readableMap.hasKey("latitude") ? readableMap.getDouble("latitude") : 0.0d;
        if (readableMap.hasKey("longitude")) {
            d3 = readableMap.getDouble("longitude");
        }
        final LatLng latLng = new LatLng(d4, d3);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new ai() {
            /* class com.airbnb.android.react.maps.AirMapModule.AnonymousClass3 */

            @Override // com.facebook.react.uimanager.ai
            public void a(k kVar) {
                j jVar = (j) kVar.a(i);
                if (jVar == null) {
                    promise.reject("AirMapView not found");
                } else if (jVar.f1585a == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    Point screenLocation = jVar.f1585a.getProjection().toScreenLocation(latLng);
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("x", ((double) screenLocation.x) / d2);
                    writableNativeMap.putDouble("y", ((double) screenLocation.y) / d2);
                    promise.resolve(writableNativeMap);
                }
            }
        });
    }

    @ReactMethod
    public void coordinateForPoint(final int i, ReadableMap readableMap, final Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        double d2 = (double) reactApplicationContext.getResources().getDisplayMetrics().density;
        int i2 = 0;
        int i3 = readableMap.hasKey("x") ? (int) (readableMap.getDouble("x") * d2) : 0;
        if (readableMap.hasKey("y")) {
            i2 = (int) (readableMap.getDouble("y") * d2);
        }
        final Point point = new Point(i3, i2);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new ai() {
            /* class com.airbnb.android.react.maps.AirMapModule.AnonymousClass4 */

            @Override // com.facebook.react.uimanager.ai
            public void a(k kVar) {
                j jVar = (j) kVar.a(i);
                if (jVar == null) {
                    promise.reject("AirMapView not found");
                } else if (jVar.f1585a == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    LatLng fromScreenLocation = jVar.f1585a.getProjection().fromScreenLocation(point);
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("latitude", fromScreenLocation.latitude);
                    writableNativeMap.putDouble("longitude", fromScreenLocation.longitude);
                    promise.resolve(writableNativeMap);
                }
            }
        });
    }

    @ReactMethod
    public void getMapBoundaries(final int i, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new ai() {
            /* class com.airbnb.android.react.maps.AirMapModule.AnonymousClass5 */

            @Override // com.facebook.react.uimanager.ai
            public void a(k kVar) {
                j jVar = (j) kVar.a(i);
                if (jVar == null) {
                    promise.reject("AirMapView not found");
                } else if (jVar.f1585a == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    double[][] mapBoundaries = jVar.getMapBoundaries();
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                    WritableNativeMap writableNativeMap3 = new WritableNativeMap();
                    writableNativeMap2.putDouble("longitude", mapBoundaries[0][0]);
                    writableNativeMap2.putDouble("latitude", mapBoundaries[0][1]);
                    writableNativeMap3.putDouble("longitude", mapBoundaries[1][0]);
                    writableNativeMap3.putDouble("latitude", mapBoundaries[1][1]);
                    writableNativeMap.putMap("northEast", writableNativeMap2);
                    writableNativeMap.putMap("southWest", writableNativeMap3);
                    promise.resolve(writableNativeMap);
                }
            }
        });
    }
}
