package com.idehub.GoogleAnalyticsBridge;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GoogleTagManagerBridge extends ReactContextBaseJavaModule {
    private final String E_CONTAINER_ALREADY_OPEN = "E_CONTAINER_ALREADY_OPEN";
    private final String E_CONTAINER_NOT_OPENED = "E_CONTAINER_NOT_OPENED";
    private final String E_ONGOING_OPEN_OPERATION = "E_ONGOING_OPEN_OPERATION";
    private final String E_OPEN_CONTAINER_FAILED = "E_OPEN_CONTAINER_FAILED";
    private final String E_PUSH_EVENT_FAILED = "E_PUSH_EVENT_FAILED";
    private ContainerHolder mContainerHolder;
    private DataLayer mDatalayer;
    private Boolean openOperationInProgress = false;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "GoogleTagManagerBridge";
    }

    public GoogleTagManagerBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void openContainerWithId(final String str, final Promise promise) {
        if (this.mContainerHolder != null) {
            promise.reject("E_CONTAINER_ALREADY_OPEN", new Throwable("The container is already open."));
        } else if (this.openOperationInProgress.booleanValue()) {
            promise.reject("E_ONGOING_OPEN_OPERATION", new Throwable("Container open-operation already in progress."));
        } else {
            TagManager instance = TagManager.getInstance(getReactApplicationContext());
            this.openOperationInProgress = true;
            instance.loadContainerPreferFresh(str, -1).setResultCallback(new ResultCallback<ContainerHolder>() {
                /* class com.idehub.GoogleAnalyticsBridge.GoogleTagManagerBridge.AnonymousClass1 */

                /* renamed from: a */
                public void onResult(ContainerHolder containerHolder) {
                    if (containerHolder == null || !containerHolder.getStatus().isSuccess()) {
                        promise.reject("E_OPEN_CONTAINER_FAILED", new Throwable(String.format("Failed to open container. Does container with id %s exist?", str)));
                    } else {
                        GoogleTagManagerBridge.this.mContainerHolder = containerHolder;
                        promise.resolve(true);
                    }
                    GoogleTagManagerBridge.this.openOperationInProgress = false;
                }
            }, 2000, TimeUnit.MILLISECONDS);
        }
    }

    @ReactMethod
    public void booleanForKey(String str, Promise promise) {
        ContainerHolder containerHolder = this.mContainerHolder;
        if (containerHolder == null || containerHolder.getContainer() == null) {
            promise.reject("E_CONTAINER_NOT_OPENED", new Throwable("The container has not been opened. You must call openContainerWithId(..)"));
        } else {
            promise.resolve(Boolean.valueOf(this.mContainerHolder.getContainer().getBoolean(str)));
        }
    }

    @ReactMethod
    public void stringForKey(String str, Promise promise) {
        ContainerHolder containerHolder = this.mContainerHolder;
        if (containerHolder == null || containerHolder.getContainer() == null) {
            promise.reject("E_CONTAINER_NOT_OPENED", new Throwable("The container has not been opened. You must call openContainerWithId(..)"));
        } else {
            promise.resolve(this.mContainerHolder.getContainer().getString(str));
        }
    }

    @ReactMethod
    public void doubleForKey(String str, Promise promise) {
        ContainerHolder containerHolder = this.mContainerHolder;
        if (containerHolder == null || containerHolder.getContainer() == null) {
            promise.reject("E_CONTAINER_NOT_OPENED", new Throwable("The container has not been opened. You must call openContainerWithId(..)"));
        } else {
            promise.resolve(Double.valueOf(this.mContainerHolder.getContainer().getDouble(str)));
        }
    }

    @ReactMethod
    public void pushDataLayerEvent(ReadableMap readableMap, Promise promise) {
        if (this.mContainerHolder != null && isValidMapToPushEvent(readableMap)) {
            getDataLayer().push(getMap(readableMap));
            promise.resolve(true);
        } else if (this.mContainerHolder == null) {
            promise.reject("E_CONTAINER_NOT_OPENED", new Throwable("The container has not been opened. You must call openContainerWithId(..)"));
        } else {
            promise.reject("E_PUSH_EVENT_FAILED", new Throwable("Validation error, data must have a key \"event\" with valid event name"));
        }
    }

    private boolean isValidMapToPushEvent(ReadableMap readableMap) {
        return (readableMap == null || readableMap.getString(DataLayer.EVENT_KEY) == null || readableMap.getString(DataLayer.EVENT_KEY).length() <= 0) ? false : true;
    }

    private Map<String, Object> getMap(ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            hashMap.put(nextKey, readableMap.getString(nextKey));
        }
        return hashMap;
    }

    private DataLayer getDataLayer() {
        if (this.mDatalayer == null) {
            this.mDatalayer = TagManager.getInstance(getReactApplicationContext()).getDataLayer();
        }
        return this.mDatalayer;
    }
}
