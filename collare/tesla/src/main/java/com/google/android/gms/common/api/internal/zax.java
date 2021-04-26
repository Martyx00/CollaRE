package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zax implements zabs {
    private final Looper zabj;
    private final GoogleApiManager zabm;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaev = new HashMap();
    private final Map<Api<?>, Boolean> zaew;
    private final zaaw zaex;
    private final GoogleApiAvailabilityLight zaey;
    private final Condition zaez;
    private final boolean zafa;
    private final boolean zafb;
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafc = new LinkedList();
    private boolean zafd;
    private Map<zai<?>, ConnectionResult> zafe;
    private Map<zai<?>, ConnectionResult> zaff;
    private zaaa zafg;
    private ConnectionResult zafh;

    public zax(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zaaw zaaw, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zaeo = lock;
        this.zabj = looper;
        this.zaez = lock.newCondition();
        this.zaey = googleApiAvailabilityLight;
        this.zaex = zaaw;
        this.zaew = map2;
        this.zaet = clientSettings;
        this.zafa = z;
        HashMap hashMap = new HashMap();
        for (Api<?> api : map2.keySet()) {
            hashMap.put(api.getClientKey(), api);
        }
        HashMap hashMap2 = new HashMap();
        ArrayList<zaq> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            zaq zaq = arrayList2.get(i);
            i++;
            zaq zaq2 = zaq;
            hashMap2.put(zaq2.mApi, zaq2);
        }
        boolean z5 = true;
        boolean z6 = false;
        boolean z7 = true;
        boolean z8 = false;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            Api.Client value = entry.getValue();
            if (!value.requiresGooglePlayServices()) {
                z2 = z6;
                z4 = z8;
                z3 = false;
            } else if (!this.zaew.get(api2).booleanValue()) {
                z3 = z7;
                z4 = true;
                z2 = true;
            } else {
                z3 = z7;
                z4 = z8;
                z2 = true;
            }
            zaw<?> zaw = new zaw<>(context, api2, looper, value, (zaq) hashMap2.get(api2), clientSettings, abstractClientBuilder);
            this.zaeu.put(entry.getKey(), zaw);
            if (value.requiresSignIn()) {
                this.zaev.put(entry.getKey(), zaw);
            }
            z8 = z4;
            z7 = z3;
            z6 = z2;
        }
        this.zafb = (!z6 || z7 || z8) ? false : z5;
        this.zabm = GoogleApiManager.zabc();
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zaw() {
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        if (this.zafa && zab(t)) {
            return t;
        }
        if (!isConnected()) {
            this.zafc.add(t);
            return t;
        }
        this.zaex.zahf.zab(t);
        return (T) this.zaeu.get(t.getClientKey()).doRead(t);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        Api.AnyClientKey<A> clientKey = t.getClientKey();
        if (this.zafa && zab(t)) {
            return t;
        }
        this.zaex.zahf.zab(t);
        return (T) this.zaeu.get(clientKey).doWrite(t);
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(T t) {
        Api.AnyClientKey<?> clientKey = t.getClientKey();
        ConnectionResult zaa = zaa(clientKey);
        if (zaa == null || zaa.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(4, null, this.zabm.zaa(this.zaeu.get(clientKey).zak(), System.identityHashCode(this.zaex))));
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void connect() {
        this.zaeo.lock();
        try {
            if (!this.zafd) {
                this.zafd = true;
                this.zafe = null;
                this.zaff = null;
                this.zafg = null;
                this.zafh = null;
                this.zabm.zao();
                this.zabm.zaa(this.zaeu.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), new zaz(this));
                this.zaeo.unlock();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaez.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafh;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            } else {
                nanos = this.zaez.awaitNanos(nanos);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafh;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zafd = false;
            this.zafe = null;
            this.zaff = null;
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            this.zafh = null;
            while (!this.zafc.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> remove = this.zafc.remove();
                remove.zaa((zacs) null);
                remove.cancel();
            }
            this.zaez.signalAll();
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult getConnectionResult(Api<?> api) {
        return zaa(api.getClientKey());
    }

    private final ConnectionResult zaa(Api.AnyClientKey<?> anyClientKey) {
        this.zaeo.lock();
        try {
            zaw<?> zaw = this.zaeu.get(anyClientKey);
            if (this.zafe != null && zaw != null) {
                return this.zafe.get(zaw.zak());
            }
            this.zaeo.unlock();
            return null;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean isConnected() {
        this.zaeo.lock();
        try {
            return this.zafe != null && this.zafh == null;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean isConnecting() {
        this.zaeo.lock();
        try {
            return this.zafe == null && this.zafd;
        } finally {
            this.zaeo.unlock();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f A[Catch:{ all -> 0x0044 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zaac() {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.lock()
            boolean r0 = r3.zafd     // Catch:{ all -> 0x0044 }
            r1 = 0
            if (r0 == 0) goto L_0x003e
            boolean r0 = r3.zafa     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x000f
            goto L_0x003e
        L_0x000f:
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.internal.zaw<?>> r0 = r3.zaev     // Catch:{ all -> 0x0044 }
            java.util.Set r0 = r0.keySet()     // Catch:{ all -> 0x0044 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0044 }
        L_0x0019:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0037
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.common.api.Api$AnyClientKey r2 = (com.google.android.gms.common.api.Api.AnyClientKey) r2     // Catch:{ all -> 0x0044 }
            com.google.android.gms.common.ConnectionResult r2 = r3.zaa(r2)     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0031
            boolean r2 = r2.isSuccess()     // Catch:{ all -> 0x0044 }
            if (r2 != 0) goto L_0x0019
        L_0x0031:
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.unlock()
            return r1
        L_0x0037:
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.unlock()
            r0 = 1
            return r0
        L_0x003e:
            java.util.concurrent.locks.Lock r0 = r3.zaeo
            r0.unlock()
            return r1
        L_0x0044:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r3.zaeo
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zax.zaac():boolean");
    }

    /* JADX INFO: finally extract failed */
    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaeo.lock();
        try {
            if (!this.zafd || zaac()) {
                this.zaeo.unlock();
                return false;
            }
            this.zabm.zao();
            this.zafg = new zaaa(this, signInConnectionListener);
            this.zabm.zaa(this.zaev.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), this.zafg);
            this.zaeo.unlock();
            return true;
        } catch (Throwable th) {
            this.zaeo.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void maybeSignOut() {
        this.zaeo.lock();
        try {
            this.zabm.maybeSignOut();
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            if (this.zaff == null) {
                this.zaff = new a(this.zaev.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zaw<?> zaw : this.zaev.values()) {
                this.zaff.put(zaw.zak(), connectionResult);
            }
            if (this.zafe != null) {
                this.zafe.putAll(this.zaff);
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    /* access modifiers changed from: private */
    public final void zaad() {
        ClientSettings clientSettings = this.zaet;
        if (clientSettings == null) {
            this.zaex.zaha = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(clientSettings.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zaet.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        this.zaex.zaha = hashSet;
    }

    /* access modifiers changed from: private */
    public final void zaae() {
        while (!this.zafc.isEmpty()) {
            execute(this.zafc.remove());
        }
        this.zaex.zab((Bundle) null);
    }

    /* access modifiers changed from: private */
    public final boolean zaa(zaw<?> zaw, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zaew.get(zaw.getApi()).booleanValue() && zaw.zaab().requiresGooglePlayServices() && this.zaey.isUserResolvableError(connectionResult.getErrorCode());
    }

    /* access modifiers changed from: private */
    public final ConnectionResult zaaf() {
        ConnectionResult connectionResult = null;
        ConnectionResult connectionResult2 = null;
        int i = 0;
        int i2 = 0;
        for (zaw<?> zaw : this.zaeu.values()) {
            Api<?> api = zaw.getApi();
            ConnectionResult connectionResult3 = this.zafe.get(zaw.zak());
            if (!connectionResult3.isSuccess() && (!this.zaew.get(api).booleanValue() || connectionResult3.hasResolution() || this.zaey.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() != 4 || !this.zafa) {
                    int priority = api.zah().getPriority();
                    if (connectionResult == null || i > priority) {
                        connectionResult = connectionResult3;
                        i = priority;
                    }
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult2 == null || i2 > priority2) {
                        connectionResult2 = connectionResult3;
                        i2 = priority2;
                    }
                }
            }
        }
        return (connectionResult == null || connectionResult2 == null || i <= i2) ? connectionResult : connectionResult2;
    }
}
