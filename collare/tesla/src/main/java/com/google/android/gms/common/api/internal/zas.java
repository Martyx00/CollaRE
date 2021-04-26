package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.a;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/* access modifiers changed from: package-private */
public final class zas implements zabs {
    private final Context mContext;
    private final Looper zabj;
    private final zaaw zaee;
    private final zabe zaef;
    private final zabe zaeg;
    private final Map<Api.AnyClientKey<?>, zabe> zaeh;
    private final Set<SignInConnectionListener> zaei = Collections.newSetFromMap(new WeakHashMap());
    private final Api.Client zaej;
    private Bundle zaek;
    private ConnectionResult zael = null;
    private ConnectionResult zaem = null;
    private boolean zaen = false;
    private final Lock zaeo;
    private int zaep = 0;

    public static zas zaa(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList) {
        a aVar = new a();
        a aVar2 = new a();
        Api.Client client = null;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api.Client value = entry.getValue();
            if (value.providesSignIn()) {
                client = value;
            }
            if (value.requiresSignIn()) {
                aVar.put(entry.getKey(), value);
            } else {
                aVar2.put(entry.getKey(), value);
            }
        }
        Preconditions.checkState(!aVar.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        a aVar3 = new a();
        a aVar4 = new a();
        for (Api<?> api : map2.keySet()) {
            Api.AnyClientKey<?> clientKey = api.getClientKey();
            if (aVar.containsKey(clientKey)) {
                aVar3.put(api, map2.get(api));
            } else if (aVar2.containsKey(clientKey)) {
                aVar4.put(api, map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<zaq> arrayList4 = arrayList;
        int size = arrayList4.size();
        int i = 0;
        while (i < size) {
            zaq zaq = arrayList4.get(i);
            i++;
            zaq zaq2 = zaq;
            if (aVar3.containsKey(zaq2.mApi)) {
                arrayList2.add(zaq2);
            } else if (aVar4.containsKey(zaq2.mApi)) {
                arrayList3.add(zaq2);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zas(context, zaaw, lock, looper, googleApiAvailabilityLight, aVar, aVar2, clientSettings, abstractClientBuilder, client, arrayList2, arrayList3, aVar3, aVar4);
    }

    private zas(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, Map<Api.AnyClientKey<?>, Api.Client> map2, ClientSettings clientSettings, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Api.Client client, ArrayList<zaq> arrayList, ArrayList<zaq> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zaee = zaaw;
        this.zaeo = lock;
        this.zabj = looper;
        this.zaej = client;
        this.zaef = new zabe(context, this.zaee, lock, looper, googleApiAvailabilityLight, map2, null, map4, null, arrayList2, new zau(this, null));
        this.zaeg = new zabe(context, this.zaee, lock, looper, googleApiAvailabilityLight, map, clientSettings, map3, abstractClientBuilder, arrayList, new zav(this, null));
        a aVar = new a();
        for (Api.AnyClientKey<?> anyClientKey : map2.keySet()) {
            aVar.put(anyClientKey, this.zaef);
        }
        for (Api.AnyClientKey<?> anyClientKey2 : map.keySet()) {
            aVar.put(anyClientKey2, this.zaeg);
        }
        this.zaeh = Collections.unmodifiableMap(aVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        if (!zaa(t)) {
            return (T) this.zaef.enqueue(t);
        }
        if (!zaz()) {
            return (T) this.zaeg.enqueue(t);
        }
        t.setFailedResult(new Status(4, null, zaaa()));
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        if (!zaa(t)) {
            return (T) this.zaef.execute(t);
        }
        if (!zaz()) {
            return (T) this.zaeg.execute(t);
        }
        t.setFailedResult(new Status(4, null, zaaa()));
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult getConnectionResult(Api<?> api) {
        if (!this.zaeh.get(api.getClientKey()).equals(this.zaeg)) {
            return this.zaef.getConnectionResult(api);
        }
        if (zaz()) {
            return new ConnectionResult(4, zaaa());
        }
        return this.zaeg.getConnectionResult(api);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void connect() {
        this.zaep = 2;
        this.zaen = false;
        this.zaem = null;
        this.zael = null;
        this.zaef.connect();
        this.zaeg.connect();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void disconnect() {
        this.zaem = null;
        this.zael = null;
        this.zaep = 0;
        this.zaef.disconnect();
        this.zaeg.disconnect();
        zay();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean isConnected() {
        this.zaeo.lock();
        try {
            boolean z = true;
            if (!this.zaef.isConnected() || (!this.zaeg.isConnected() && !zaz() && this.zaep != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean isConnecting() {
        this.zaeo.lock();
        try {
            return this.zaep == 2;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaeo.lock();
        try {
            if ((isConnecting() || isConnected()) && !this.zaeg.isConnected()) {
                this.zaei.add(signInConnectionListener);
                if (this.zaep == 0) {
                    this.zaep = 1;
                }
                this.zaem = null;
                this.zaeg.connect();
                return true;
            }
            this.zaeo.unlock();
            return false;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zaw() {
        this.zaef.zaw();
        this.zaeg.zaw();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void maybeSignOut() {
        this.zaeo.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zaeg.disconnect();
            this.zaem = new ConnectionResult(4);
            if (isConnecting) {
                new zap(this.zabj).post(new zat(this));
            } else {
                zay();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    /* access modifiers changed from: private */
    public final void zax() {
        if (zab(this.zael)) {
            if (zab(this.zaem) || zaz()) {
                switch (this.zaep) {
                    case 2:
                        this.zaee.zab(this.zaek);
                    case 1:
                        zay();
                        break;
                    default:
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        break;
                }
                this.zaep = 0;
                return;
            }
            ConnectionResult connectionResult = this.zaem;
            if (connectionResult == null) {
                return;
            }
            if (this.zaep == 1) {
                zay();
                return;
            }
            zaa(connectionResult);
            this.zaef.disconnect();
        } else if (this.zael == null || !zab(this.zaem)) {
            ConnectionResult connectionResult2 = this.zael;
            if (connectionResult2 != null && this.zaem != null) {
                if (this.zaeg.zahs < this.zaef.zahs) {
                    connectionResult2 = this.zaem;
                }
                zaa(connectionResult2);
            }
        } else {
            this.zaeg.disconnect();
            zaa(this.zael);
        }
    }

    private final void zaa(ConnectionResult connectionResult) {
        switch (this.zaep) {
            case 2:
                this.zaee.zac(connectionResult);
            case 1:
                zay();
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        this.zaep = 0;
    }

    private final void zay() {
        for (SignInConnectionListener signInConnectionListener : this.zaei) {
            signInConnectionListener.onComplete();
        }
        this.zaei.clear();
    }

    /* access modifiers changed from: private */
    public final void zaa(int i, boolean z) {
        this.zaee.zab(i, z);
        this.zaem = null;
        this.zael = null;
    }

    private final boolean zaz() {
        ConnectionResult connectionResult = this.zaem;
        return connectionResult != null && connectionResult.getErrorCode() == 4;
    }

    private final boolean zaa(BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient> apiMethodImpl) {
        Api.AnyClientKey<? extends Api.AnyClient> clientKey = apiMethodImpl.getClientKey();
        Preconditions.checkArgument(this.zaeh.containsKey(clientKey), "GoogleApiClient is not configured to use the API required for this call.");
        return this.zaeh.get(clientKey).equals(this.zaeg);
    }

    private final PendingIntent zaaa() {
        if (this.zaej == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zaee), this.zaej.getSignInIntent(), 134217728);
    }

    /* access modifiers changed from: private */
    public final void zaa(Bundle bundle) {
        Bundle bundle2 = this.zaek;
        if (bundle2 == null) {
            this.zaek = bundle;
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
    }

    private static boolean zab(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(":");
        this.zaeg.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(":");
        this.zaef.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }
}
