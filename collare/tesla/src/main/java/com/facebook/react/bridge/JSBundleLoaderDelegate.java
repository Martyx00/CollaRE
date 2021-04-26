package com.facebook.react.bridge;

import android.content.res.AssetManager;

public interface JSBundleLoaderDelegate {
    void loadScriptFromAssets(AssetManager assetManager, String str, boolean z);

    void loadScriptFromDeltaBundle(String str, NativeDeltaClient nativeDeltaClient, boolean z);

    void loadScriptFromFile(String str, String str2, boolean z);

    void setSourceURLs(String str, String str2);
}
