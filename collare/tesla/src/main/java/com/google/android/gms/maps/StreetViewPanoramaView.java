package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private final zzb zzcd;

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzcd = new zzb(this, context, null);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class zza implements StreetViewLifecycleDelegate {
        private final ViewGroup parent;
        private final IStreetViewPanoramaViewDelegate zzce;
        private View zzcf;

        public zza(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.zzce = (IStreetViewPanoramaViewDelegate) Preconditions.checkNotNull(iStreetViewPanoramaViewDelegate);
            this.parent = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzce.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
                this.zzcf = (View) ObjectWrapper.unwrap(this.zzce.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzcf);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStart() {
            try {
                this.zzce.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onResume() {
            try {
                this.zzce.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onPause() {
            try {
                this.zzce.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onStop() {
            try {
                this.zzce.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onDestroy() {
            try {
                this.zzce.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onLowMemory() {
            try {
                this.zzce.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzce.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        @Override // com.google.android.gms.maps.internal.StreetViewLifecycleDelegate
        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzce.getStreetViewPanoramaAsync(new zzaj(this, onStreetViewPanoramaReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzcd = new zzb(this, context, null);
    }

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<zza> {
        private OnDelegateCreatedListener<zza> zzbd;
        private final ViewGroup zzbj;
        private final Context zzbk;
        private final List<OnStreetViewPanoramaReadyCallback> zzbw = new ArrayList();
        private final StreetViewPanoramaOptions zzcg;

        @VisibleForTesting
        zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.zzbj = viewGroup;
            this.zzbk = context;
            this.zzcg = streetViewPanoramaOptions;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
        public final void createDelegate(OnDelegateCreatedListener<zza> onDelegateCreatedListener) {
            this.zzbd = onDelegateCreatedListener;
            if (this.zzbd != null && getDelegate() == null) {
                try {
                    MapsInitializer.initialize(this.zzbk);
                    this.zzbd.onDelegateCreated(new zza(this.zzbj, zzbz.zza(this.zzbk).zza(ObjectWrapper.wrap(this.zzbk), this.zzcg)));
                    for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.zzbw) {
                        ((zza) getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
                    }
                    this.zzbw.clear();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException unused) {
                }
            }
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (getDelegate() != null) {
                ((zza) getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            } else {
                this.zzbw.add(onStreetViewPanoramaReadyCallback);
            }
        }
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzcd = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.zzcd = new zzb(this, context, streetViewPanoramaOptions);
    }

    public final void onCreate(Bundle bundle) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            this.zzcd.onCreate(bundle);
            if (this.zzcd.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onStart() {
        this.zzcd.onStart();
    }

    public void onResume() {
        this.zzcd.onResume();
    }

    public final void onPause() {
        this.zzcd.onPause();
    }

    public void onStop() {
        this.zzcd.onStop();
    }

    public void onDestroy() {
        this.zzcd.onDestroy();
    }

    public final void onLowMemory() {
        this.zzcd.onLowMemory();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzcd.onSaveInstanceState(bundle);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzcd.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }
}
