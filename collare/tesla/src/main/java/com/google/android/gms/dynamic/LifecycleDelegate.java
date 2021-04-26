package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface LifecycleDelegate {
    @KeepForSdk
    void onCreate(Bundle bundle);

    @KeepForSdk
    View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    @KeepForSdk
    void onDestroy();

    @KeepForSdk
    void onDestroyView();

    @KeepForSdk
    void onInflate(Activity activity, Bundle bundle, Bundle bundle2);

    @KeepForSdk
    void onLowMemory();

    @KeepForSdk
    void onPause();

    @KeepForSdk
    void onResume();

    @KeepForSdk
    void onSaveInstanceState(Bundle bundle);

    @KeepForSdk
    void onStart();

    @KeepForSdk
    void onStop();
}
