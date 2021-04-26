package com.facebook.react.modules.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.util.SparseArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.a.a;
import com.facebook.react.modules.core.c;
import com.facebook.react.modules.core.d;
import java.util.ArrayList;

@a(a = PermissionsModule.NAME)
public class PermissionsModule extends ReactContextBaseJavaModule implements d {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    public static final String NAME = "PermissionsAndroid";
    private final String DENIED = "denied";
    private final String GRANTED = "granted";
    private final String NEVER_ASK_AGAIN = "never_ask_again";
    private final SparseArray<Callback> mCallbacks = new SparseArray<>();
    private int mRequestCode = 0;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public PermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void checkPermission(String str, Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        boolean z = true;
        if (Build.VERSION.SDK_INT < 23) {
            if (baseContext.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
                z = false;
            }
            promise.resolve(Boolean.valueOf(z));
            return;
        }
        if (baseContext.checkSelfPermission(str) != 0) {
            z = false;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @ReactMethod
    public void shouldShowRequestPermissionRationale(String str, Promise promise) {
        if (Build.VERSION.SDK_INT < 23) {
            promise.resolve(false);
            return;
        }
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @ReactMethod
    public void requestPermission(final String str, final Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        if (Build.VERSION.SDK_INT < 23) {
            promise.resolve(baseContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0 ? "granted" : "denied");
        } else if (baseContext.checkSelfPermission(str) == 0) {
            promise.resolve("granted");
        } else {
            try {
                c permissionAwareActivity = getPermissionAwareActivity();
                this.mCallbacks.put(this.mRequestCode, new Callback() {
                    /* class com.facebook.react.modules.permissions.PermissionsModule.AnonymousClass1 */

                    @Override // com.facebook.react.bridge.Callback
                    public void invoke(Object... objArr) {
                        int[] iArr = (int[]) objArr[0];
                        if (iArr.length > 0 && iArr[0] == 0) {
                            promise.resolve("granted");
                        } else if (((c) objArr[1]).shouldShowRequestPermissionRationale(str)) {
                            promise.resolve("denied");
                        } else {
                            promise.resolve("never_ask_again");
                        }
                    }
                });
                permissionAwareActivity.a(new String[]{str}, this.mRequestCode, this);
                this.mRequestCode++;
            } catch (IllegalStateException e) {
                promise.reject(ERROR_INVALID_ACTIVITY, e);
            }
        }
    }

    @ReactMethod
    public void requestMultiplePermissions(ReadableArray readableArray, final Promise promise) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList arrayList = new ArrayList();
        Context baseContext = getReactApplicationContext().getBaseContext();
        int i = 0;
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            String string = readableArray.getString(i2);
            if (Build.VERSION.SDK_INT < 23) {
                writableNativeMap.putString(string, baseContext.checkPermission(string, Process.myPid(), Process.myUid()) == 0 ? "granted" : "denied");
                i++;
            } else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, "granted");
                i++;
            } else {
                arrayList.add(string);
            }
        }
        if (readableArray.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            c permissionAwareActivity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback() {
                /* class com.facebook.react.modules.permissions.PermissionsModule.AnonymousClass2 */

                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    c cVar = (c) objArr[1];
                    for (int i = 0; i < arrayList.size(); i++) {
                        String str = (String) arrayList.get(i);
                        if (iArr.length > 0 && iArr[i] == 0) {
                            writableNativeMap.putString(str, "granted");
                        } else if (cVar.shouldShowRequestPermissionRationale(str)) {
                            writableNativeMap.putString(str, "denied");
                        } else {
                            writableNativeMap.putString(str, "never_ask_again");
                        }
                    }
                    promise.resolve(writableNativeMap);
                }
            });
            permissionAwareActivity.a((String[]) arrayList.toArray(new String[0]), this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.react.modules.core.d
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mCallbacks.get(i).invoke(iArr, getPermissionAwareActivity());
        this.mCallbacks.remove(i);
        if (this.mCallbacks.size() == 0) {
            return true;
        }
        return false;
    }

    private c getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (currentActivity instanceof c) {
            return (c) currentActivity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
    }
}
