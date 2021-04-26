package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.h;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zaaw extends GoogleApiClient implements zabt {
    private final Context mContext;
    private final Looper zabj;
    private final int zacb;
    private final GoogleApiAvailability zacd;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private boolean zach;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    @VisibleForTesting
    final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafc = new LinkedList();
    private final GmsClientEventManager zags;
    private zabs zagt = null;
    private volatile boolean zagu;
    private long zagv;
    private long zagw;
    private final zabb zagx;
    @VisibleForTesting
    private zabq zagy;
    final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    Set<Scope> zaha;
    private final ListenerHolders zahb;
    private final ArrayList<zaq> zahc;
    private Integer zahd;
    Set<zacm> zahe;
    final zacp zahf;
    private final GmsClientEventManager.GmsClientEventState zahg;

    public zaaw(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.AnyClientKey<?>, Api.Client> map2, int i, int i2, ArrayList<zaq> arrayList, boolean z) {
        Map<Api<?>, Boolean> map3;
        this.zagv = ClientLibraryUtils.isPackageSide() ? 10000 : 120000;
        this.zagw = 5000;
        this.zaha = new HashSet();
        this.zahb = new ListenerHolders();
        this.zahd = null;
        this.zahe = null;
        this.zahg = new zaax(this);
        this.mContext = context;
        this.zaeo = lock;
        this.zach = false;
        this.zags = new GmsClientEventManager(looper, this.zahg);
        this.zabj = looper;
        this.zagx = new zabb(this, looper);
        this.zacd = googleApiAvailability;
        this.zacb = i;
        if (this.zacb >= 0) {
            this.zahd = Integer.valueOf(i2);
            map3 = map;
        } else {
            map3 = map;
        }
        this.zaew = map3;
        this.zagz = map2;
        this.zahc = arrayList;
        this.zahf = new zacp(this.zagz);
        for (GoogleApiClient.ConnectionCallbacks connectionCallbacks : list) {
            this.zags.registerConnectionCallbacks(connectionCallbacks);
        }
        for (GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener : list2) {
            this.zags.registerConnectionFailedListener(onConnectionFailedListener);
        }
        this.zaet = clientSettings;
        this.zace = abstractClientBuilder;
    }

    private static String zaf(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        Preconditions.checkArgument(t.getClientKey() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zagz.containsKey(t.getClientKey());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        Preconditions.checkArgument(containsKey, sb.toString());
        this.zaeo.lock();
        try {
            if (this.zagt == null) {
                this.zafc.add(t);
                return t;
            }
            T t2 = (T) this.zagt.enqueue(t);
            this.zaeo.unlock();
            return t2;
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        Preconditions.checkArgument(t.getClientKey() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zagz.containsKey(t.getClientKey());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        Preconditions.checkArgument(containsKey, sb.toString());
        this.zaeo.lock();
        try {
            if (this.zagt == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            } else if (this.zagu) {
                this.zafc.add(t);
                while (!this.zafc.isEmpty()) {
                    BaseImplementation.ApiMethodImpl<?, ?> remove = this.zafc.remove();
                    this.zahf.zab(remove);
                    remove.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                }
                return t;
            } else {
                T t2 = (T) this.zagt.execute(t);
                this.zaeo.unlock();
                return t2;
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <L> ListenerHolder<L> registerListener(L l) {
        this.zaeo.lock();
        try {
            return this.zahb.zaa(l, this.zabj, "NO_TYPE");
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <C extends Api.Client> C getClient(Api.AnyClientKey<C> anyClientKey) {
        C c2 = (C) this.zagz.get(anyClientKey);
        Preconditions.checkNotNull(c2, "Appropriate Api was not requested.");
        return c2;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasApi(Api<?> api) {
        return this.zagz.containsKey(api.getClientKey());
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasConnectedApi(Api<?> api) {
        Api.Client client;
        if (isConnected() && (client = this.zagz.get(api.getClientKey())) != null && client.isConnected()) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult getConnectionResult(Api<?> api) {
        this.zaeo.lock();
        try {
            if (!isConnected()) {
                if (!this.zagu) {
                    throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
                }
            }
            if (this.zagz.containsKey(api.getClientKey())) {
                ConnectionResult connectionResult = this.zagt.getConnectionResult(api);
                if (connectionResult != null) {
                    this.zaeo.unlock();
                    return connectionResult;
                } else if (this.zagu) {
                    return ConnectionResult.RESULT_SUCCESS;
                } else {
                    Log.w("GoogleApiClientImpl", zaay());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                    ConnectionResult connectionResult2 = new ConnectionResult(8, null);
                    this.zaeo.unlock();
                    return connectionResult2;
                }
            } else {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        this.zaeo.lock();
        try {
            boolean z = false;
            if (this.zacb >= 0) {
                if (this.zahd != null) {
                    z = true;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zahd.intValue());
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect(int i) {
        this.zaeo.lock();
        boolean z = true;
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i);
            Preconditions.checkArgument(z, sb.toString());
            zae(i);
            zaau();
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect() {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaeo.lock();
        try {
            if (this.zacb >= 0) {
                if (this.zahd == null) {
                    z = false;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zae(this.zahd.intValue());
            this.zags.enableCallbacks();
            return this.zagt.blockingConnect();
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zaeo.lock();
        try {
            if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zae(this.zahd.intValue());
            this.zags.enableCallbacks();
            return this.zagt.blockingConnect(j, timeUnit);
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zahf.release();
            if (this.zagt != null) {
                this.zagt.disconnect();
            }
            this.zahb.release();
            for (BaseImplementation.ApiMethodImpl<?, ?> apiMethodImpl : this.zafc) {
                apiMethodImpl.zaa((zacs) null);
                apiMethodImpl.cancel();
            }
            this.zafc.clear();
            if (this.zagt != null) {
                zaaw();
                this.zags.disableCallbacks();
                this.zaeo.unlock();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void reconnect() {
        disconnect();
        connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Preconditions.checkState(this.zahd.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult statusPendingResult = new StatusPendingResult(this);
        if (this.zagz.containsKey(Common.CLIENT_KEY)) {
            zaa(this, statusPendingResult, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new GoogleApiClient.Builder(this.mContext).addApi(Common.API).addConnectionCallbacks(new zaay(this, atomicReference, statusPendingResult)).addOnConnectionFailedListener(new zaaz(this, statusPendingResult)).setHandler(this.zagx).build();
            atomicReference.set(build);
            build.connect();
        }
        return statusPendingResult;
    }

    /* access modifiers changed from: private */
    public final void zaa(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z) {
        Common.zapi.zaa(googleApiClient).setResultCallback(new zaba(this, statusPendingResult, z, googleApiClient));
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void stopAutoManage(h hVar) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) hVar);
        if (this.zacb >= 0) {
            zaj.zaa(lifecycleActivity).zaa(this.zacb);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnected() {
        zabs zabs = this.zagt;
        return zabs != null && zabs.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnecting() {
        zabs zabs = this.zagt;
        return zabs != null && zabs.isConnecting();
    }

    private final void zae(int i) {
        Integer num = this.zahd;
        if (num == null) {
            this.zahd = Integer.valueOf(i);
        } else if (num.intValue() != i) {
            String zaf = zaf(i);
            String zaf2 = zaf(this.zahd.intValue());
            StringBuilder sb = new StringBuilder(String.valueOf(zaf).length() + 51 + String.valueOf(zaf2).length());
            sb.append("Cannot use sign-in mode: ");
            sb.append(zaf);
            sb.append(". Mode was already set to ");
            sb.append(zaf2);
            throw new IllegalStateException(sb.toString());
        }
        if (this.zagt == null) {
            boolean z = false;
            boolean z2 = false;
            for (Api.Client client : this.zagz.values()) {
                if (client.requiresSignIn()) {
                    z = true;
                }
                if (client.providesSignIn()) {
                    z2 = true;
                }
            }
            switch (this.zahd.intValue()) {
                case 1:
                    if (!z) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (z2) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (z) {
                        if (this.zach) {
                            this.zagt = new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, true);
                            return;
                        } else {
                            this.zagt = zas.zaa(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc);
                            return;
                        }
                    }
                    break;
            }
            if (!this.zach || z2) {
                this.zagt = new zabe(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this);
            } else {
                this.zagt = new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, false);
            }
        }
    }

    private final void zaau() {
        this.zags.enableCallbacks();
        this.zagt.connect();
    }

    /* access modifiers changed from: private */
    public final void resume() {
        this.zaeo.lock();
        try {
            if (this.zagu) {
                zaau();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    /* access modifiers changed from: private */
    public final void zaav() {
        this.zaeo.lock();
        try {
            if (zaaw()) {
                zaau();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zaaw() {
        if (!this.zagu) {
            return false;
        }
        this.zagu = false;
        this.zagx.removeMessages(2);
        this.zagx.removeMessages(1);
        zabq zabq = this.zagy;
        if (zabq != null) {
            zabq.unregister();
            this.zagy = null;
        }
        return true;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.registerConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zags.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.unregisterConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zags.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(Bundle bundle) {
        while (!this.zafc.isEmpty()) {
            execute(this.zafc.remove());
        }
        this.zags.onConnectionSuccess(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zac(ConnectionResult connectionResult) {
        if (!this.zacd.isPlayServicesPossiblyUpdating(this.mContext, connectionResult.getErrorCode())) {
            zaaw();
        }
        if (!this.zagu) {
            this.zags.onConnectionFailure(connectionResult);
            this.zags.disableCallbacks();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(int i, boolean z) {
        if (i == 1 && !z && !this.zagu) {
            this.zagu = true;
            if (this.zagy == null && !ClientLibraryUtils.isPackageSide()) {
                this.zagy = this.zacd.zaa(this.mContext.getApplicationContext(), new zabc(this));
            }
            zabb zabb = this.zagx;
            zabb.sendMessageDelayed(zabb.obtainMessage(1), this.zagv);
            zabb zabb2 = this.zagx;
            zabb2.sendMessageDelayed(zabb2.obtainMessage(2), this.zagw);
        }
        this.zahf.zabx();
        this.zags.onUnintentionalDisconnection(i);
        this.zags.disableCallbacks();
        if (i == 2) {
            zaau();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.zabj;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        zabs zabs = this.zagt;
        return zabs != null && zabs.maybeSignIn(signInConnectionListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void maybeSignOut() {
        zabs zabs = this.zagt;
        if (zabs != null) {
            zabs.maybeSignOut();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zaa(zacm zacm) {
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                this.zahe = new HashSet();
            }
            this.zahe.add(zacm);
        } finally {
            this.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zab(zacm zacm) {
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zahe.remove(zacm)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zaax()) {
                this.zagt.zaw();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final boolean zaax() {
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                this.zaeo.unlock();
                return false;
            }
            boolean z = !this.zahe.isEmpty();
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            this.zaeo.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final String zaay() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.mContext);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.zagu);
        printWriter.append(" mWorkQueue.size()=").print(this.zafc.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zahf.zakz.size());
        zabs zabs = this.zagt;
        if (zabs != null) {
            zabs.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public static int zaa(Iterable<Api.Client> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (Api.Client client : iterable) {
            if (client.requiresSignIn()) {
                z2 = true;
            }
            if (client.providesSignIn()) {
                z3 = true;
            }
        }
        if (!z2) {
            return 3;
        }
        if (!z3 || !z) {
            return 1;
        }
        return 2;
    }
}
