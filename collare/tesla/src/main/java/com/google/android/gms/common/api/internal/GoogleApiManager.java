package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.util.a;
import android.support.v4.util.b;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public class GoogleApiManager implements Handler.Callback {
    private static final Object lock = new Object();
    public static final Status zahx = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zahy = new Status(4, "The user must be signed in to make this API call.");
    private static GoogleApiManager zaic;
    private final Handler handler;
    private long zahz = 5000;
    private long zaia = 120000;
    private long zaib = 10000;
    private final Context zaid;
    private final GoogleApiAvailability zaie;
    private final GoogleApiAvailabilityCache zaif;
    private final AtomicInteger zaig = new AtomicInteger(1);
    private final AtomicInteger zaih = new AtomicInteger(0);
    private final Map<zai<?>, zaa<?>> zaii = new ConcurrentHashMap(5, 0.75f, 1);
    private zaae zaij = null;
    private final Set<zai<?>> zaik = new b();
    private final Set<zai<?>> zail = new b();

    public static GoogleApiManager zab(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zaic == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zaic = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zaic;
        }
        return googleApiManager;
    }

    /* access modifiers changed from: private */
    public static class zab {
        private final zai<?> zajb;
        private final Feature zajc;

        private zab(zai<?> zai, Feature feature) {
            this.zajb = zai;
            this.zajc = feature;
        }

        public final boolean equals(Object obj) {
            if (obj == null || !(obj instanceof zab)) {
                return false;
            }
            zab zab = (zab) obj;
            if (!Objects.equal(this.zajb, zab.zajb) || !Objects.equal(this.zajc, zab.zajc)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zajb, this.zajc);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", this.zajb).add("feature", this.zajc).toString();
        }

        /* synthetic */ zab(zai zai, Feature feature, zabi zabi) {
            this(zai, feature);
        }
    }

    /* access modifiers changed from: private */
    public class zac implements zach, BaseGmsClient.ConnectionProgressReportCallbacks {
        private final zai<?> zafq;
        private final Api.Client zaio;
        private IAccountAccessor zajd = null;
        private Set<Scope> zaje = null;
        private boolean zajf = false;

        public zac(Api.Client client, zai<?> zai) {
            this.zaio = client;
            this.zafq = zai;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post(new zabo(this, connectionResult));
        }

        @Override // com.google.android.gms.common.api.internal.zach
        public final void zag(ConnectionResult connectionResult) {
            ((zaa) GoogleApiManager.this.zaii.get(this.zafq)).zag(connectionResult);
        }

        @Override // com.google.android.gms.common.api.internal.zach
        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zag(new ConnectionResult(4));
                return;
            }
            this.zajd = iAccountAccessor;
            this.zaje = set;
            zabr();
        }

        /* access modifiers changed from: private */
        public final void zabr() {
            IAccountAccessor iAccountAccessor;
            if (this.zajf && (iAccountAccessor = this.zajd) != null) {
                this.zaio.getRemoteService(iAccountAccessor, this.zaje);
            }
        }
    }

    public static GoogleApiManager zabc() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zaic, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zaic;
        }
        return googleApiManager;
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (lock) {
            if (zaic != null) {
                GoogleApiManager googleApiManager = zaic;
                googleApiManager.zaih.incrementAndGet();
                googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
            }
        }
    }

    public class zaa<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zar {
        private final zai<O> zafq;
        private final Queue<zab> zain = new LinkedList();
        private final Api.Client zaio;
        private final Api.AnyClient zaip;
        private final zaab zaiq;
        private final Set<zak> zair = new HashSet();
        private final Map<ListenerHolder.ListenerKey<?>, zabw> zais = new HashMap();
        private final int zait;
        private final zace zaiu;
        private boolean zaiv;
        private final List<zab> zaiw = new ArrayList();
        private ConnectionResult zaix = null;

        public zaa(GoogleApi<O> googleApi) {
            this.zaio = googleApi.zaa(GoogleApiManager.this.handler.getLooper(), this);
            Api.Client client = this.zaio;
            if (client instanceof SimpleClientAdapter) {
                this.zaip = ((SimpleClientAdapter) client).getClient();
            } else {
                this.zaip = client;
            }
            this.zafq = googleApi.zak();
            this.zaiq = new zaab();
            this.zait = googleApi.getInstanceId();
            if (this.zaio.requiresSignIn()) {
                this.zaiu = googleApi.zaa(GoogleApiManager.this.zaid, GoogleApiManager.this.handler);
            } else {
                this.zaiu = null;
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnected(Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabg();
            } else {
                GoogleApiManager.this.handler.post(new zabj(this));
            }
        }

        /* access modifiers changed from: private */
        public final void zabg() {
            zabl();
            zai(ConnectionResult.RESULT_SUCCESS);
            zabn();
            Iterator<zabw> it = this.zais.values().iterator();
            while (it.hasNext()) {
                zabw next = it.next();
                if (zaa(next.zajx.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        next.zajx.registerListener(this.zaip, new TaskCompletionSource<>());
                    } catch (DeadObjectException unused) {
                        onConnectionSuspended(1);
                        this.zaio.disconnect();
                    } catch (RemoteException unused2) {
                        it.remove();
                    }
                }
            }
            zabi();
            zabo();
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabh();
            } else {
                GoogleApiManager.this.handler.post(new zabk(this));
            }
        }

        /* access modifiers changed from: private */
        public final void zabh() {
            zabl();
            this.zaiv = true;
            this.zaiq.zaai();
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafq), GoogleApiManager.this.zahz);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 11, this.zafq), GoogleApiManager.this.zaia);
            GoogleApiManager.this.zaif.flush();
        }

        public final void zag(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaio.disconnect();
            onConnectionFailed(connectionResult);
        }

        private final boolean zah(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                if (GoogleApiManager.this.zaij == null || !GoogleApiManager.this.zaik.contains(this.zafq)) {
                    return false;
                }
                GoogleApiManager.this.zaij.zab(connectionResult, this.zait);
                return true;
            }
        }

        @Override // com.google.android.gms.common.api.internal.zar
        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                GoogleApiManager.this.handler.post(new zabl(this, connectionResult));
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zace zace = this.zaiu;
            if (zace != null) {
                zace.zabs();
            }
            zabl();
            GoogleApiManager.this.zaif.flush();
            zai(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zac(GoogleApiManager.zahy);
            } else if (this.zain.isEmpty()) {
                this.zaix = connectionResult;
            } else if (!zah(connectionResult) && !GoogleApiManager.this.zac(connectionResult, this.zait)) {
                if (connectionResult.getErrorCode() == 18) {
                    this.zaiv = true;
                }
                if (this.zaiv) {
                    GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafq), GoogleApiManager.this.zahz);
                    return;
                }
                String zan = this.zafq.zan();
                StringBuilder sb = new StringBuilder(String.valueOf(zan).length() + 38);
                sb.append("API: ");
                sb.append(zan);
                sb.append(" is not available on this device.");
                zac(new Status(17, sb.toString()));
            }
        }

        private final void zabi() {
            ArrayList arrayList = new ArrayList(this.zain);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                zab zab = (zab) obj;
                if (!this.zaio.isConnected()) {
                    return;
                }
                if (zab(zab)) {
                    this.zain.remove(zab);
                }
            }
        }

        public final void zaa(zab zab) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zaio.isConnected()) {
                this.zain.add(zab);
                ConnectionResult connectionResult = this.zaix;
                if (connectionResult == null || !connectionResult.hasResolution()) {
                    connect();
                } else {
                    onConnectionFailed(this.zaix);
                }
            } else if (zab(zab)) {
                zabo();
            } else {
                this.zain.add(zab);
            }
        }

        public final void zabj() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zac(GoogleApiManager.zahx);
            this.zaiq.zaah();
            for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.zais.keySet().toArray(new ListenerHolder.ListenerKey[this.zais.size()])) {
                zaa(new zah(listenerKey, new TaskCompletionSource()));
            }
            zai(new ConnectionResult(4));
            if (this.zaio.isConnected()) {
                this.zaio.onUserSignOut(new zabm(this));
            }
        }

        public final Api.Client zaab() {
            return this.zaio;
        }

        public final Map<ListenerHolder.ListenerKey<?>, zabw> zabk() {
            return this.zais;
        }

        public final void zabl() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaix = null;
        }

        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            return this.zaix;
        }

        private final boolean zab(zab zab) {
            if (!(zab instanceof zac)) {
                zac(zab);
                return true;
            }
            zac zac = (zac) zab;
            Feature zaa = zaa(zac.zab((zaa<?>) this));
            if (zaa == null) {
                zac(zab);
                return true;
            } else if (zac.zac(this)) {
                zab zab2 = new zab(this.zafq, zaa, null);
                int indexOf = this.zaiw.indexOf(zab2);
                if (indexOf >= 0) {
                    zab zab3 = this.zaiw.get(indexOf);
                    GoogleApiManager.this.handler.removeMessages(15, zab3);
                    GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zab3), GoogleApiManager.this.zahz);
                    return false;
                }
                this.zaiw.add(zab2);
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zab2), GoogleApiManager.this.zahz);
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 16, zab2), GoogleApiManager.this.zaia);
                ConnectionResult connectionResult = new ConnectionResult(2, null);
                if (zah(connectionResult)) {
                    return false;
                }
                GoogleApiManager.this.zac(connectionResult, this.zait);
                return false;
            } else {
                zac.zaa(new UnsupportedApiCallException(zaa));
                return false;
            }
        }

        private final void zac(zab zab) {
            zab.zaa(this.zaiq, requiresSignIn());
            try {
                zab.zaa((zaa<?>) this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.zaio.disconnect();
            }
        }

        public final void zac(Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            for (zab zab : this.zain) {
                zab.zaa(status);
            }
            this.zain.clear();
        }

        public final void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiv) {
                connect();
            }
        }

        private final void zabn() {
            if (this.zaiv) {
                GoogleApiManager.this.handler.removeMessages(11, this.zafq);
                GoogleApiManager.this.handler.removeMessages(9, this.zafq);
                this.zaiv = false;
            }
        }

        public final void zaav() {
            Status status;
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiv) {
                zabn();
                if (GoogleApiManager.this.zaie.isGooglePlayServicesAvailable(GoogleApiManager.this.zaid) == 18) {
                    status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                } else {
                    status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                zac(status);
                this.zaio.disconnect();
            }
        }

        private final void zabo() {
            GoogleApiManager.this.handler.removeMessages(12, this.zafq);
            GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(12, this.zafq), GoogleApiManager.this.zaib);
        }

        public final boolean zabp() {
            return zac(true);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final boolean zac(boolean z) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zaio.isConnected() || this.zais.size() != 0) {
                return false;
            }
            if (this.zaiq.zaag()) {
                if (z) {
                    zabo();
                }
                return false;
            }
            this.zaio.disconnect();
            return true;
        }

        public final void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zaio.isConnected() && !this.zaio.isConnecting()) {
                int clientAvailability = GoogleApiManager.this.zaif.getClientAvailability(GoogleApiManager.this.zaid, this.zaio);
                if (clientAvailability != 0) {
                    onConnectionFailed(new ConnectionResult(clientAvailability, null));
                    return;
                }
                zac zac = new zac(this.zaio, this.zafq);
                if (this.zaio.requiresSignIn()) {
                    this.zaiu.zaa(zac);
                }
                this.zaio.connect(zac);
            }
        }

        public final void zaa(zak zak) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zair.add(zak);
        }

        private final void zai(ConnectionResult connectionResult) {
            for (zak zak : this.zair) {
                String str = null;
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.zaio.getEndpointPackageName();
                }
                zak.zaa(this.zafq, connectionResult, str);
            }
            this.zair.clear();
        }

        /* access modifiers changed from: package-private */
        public final boolean isConnected() {
            return this.zaio.isConnected();
        }

        public final boolean requiresSignIn() {
            return this.zaio.requiresSignIn();
        }

        public final int getInstanceId() {
            return this.zait;
        }

        /* access modifiers changed from: package-private */
        public final zad zabq() {
            zace zace = this.zaiu;
            if (zace == null) {
                return null;
            }
            return zace.zabq();
        }

        private final Feature zaa(Feature[] featureArr) {
            if (featureArr == null || featureArr.length == 0) {
                return null;
            }
            Feature[] availableFeatures = this.zaio.getAvailableFeatures();
            if (availableFeatures == null) {
                availableFeatures = new Feature[0];
            }
            a aVar = new a(availableFeatures.length);
            for (Feature feature : availableFeatures) {
                aVar.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            for (Feature feature2 : featureArr) {
                if (!aVar.containsKey(feature2.getName()) || ((Long) aVar.get(feature2.getName())).longValue() < feature2.getVersion()) {
                    return feature2;
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final void zaa(zab zab) {
            if (!this.zaiw.contains(zab) || this.zaiv) {
                return;
            }
            if (!this.zaio.isConnected()) {
                connect();
            } else {
                zabi();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final void zab(zab zab) {
            Feature[] zab2;
            if (this.zaiw.remove(zab)) {
                GoogleApiManager.this.handler.removeMessages(15, zab);
                GoogleApiManager.this.handler.removeMessages(16, zab);
                Feature feature = zab.zajc;
                ArrayList arrayList = new ArrayList(this.zain.size());
                for (zab zab3 : this.zain) {
                    if ((zab3 instanceof zac) && (zab2 = ((zac) zab3).zab((zaa<?>) this)) != null && ArrayUtils.contains(zab2, feature)) {
                        arrayList.add(zab3);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zab zab4 = (zab) obj;
                    this.zain.remove(zab4);
                    zab4.zaa(new UnsupportedApiCallException(feature));
                }
            }
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zaid = context;
        this.handler = new zap(looper, this);
        this.zaie = googleApiAvailability;
        this.zaif = new GoogleApiAvailabilityCache(googleApiAvailability);
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(6));
    }

    public final int zabd() {
        return this.zaig.getAndIncrement();
    }

    public final void zaa(GoogleApi<?> googleApi) {
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(7, googleApi));
    }

    private final void zab(GoogleApi<?> googleApi) {
        zai<?> zak = googleApi.zak();
        zaa<?> zaa2 = this.zaii.get(zak);
        if (zaa2 == null) {
            zaa2 = new zaa<>(googleApi);
            this.zaii.put(zak, zaa2);
        }
        if (zaa2.requiresSignIn()) {
            this.zail.add(zak);
        }
        zaa2.connect();
    }

    public final void zaa(zaae zaae) {
        synchronized (lock) {
            if (this.zaij != zaae) {
                this.zaij = zaae;
                this.zaik.clear();
            }
            this.zaik.addAll(zaae.zaaj());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zab(zaae zaae) {
        synchronized (lock) {
            if (this.zaij == zaae) {
                this.zaij = null;
                this.zaik.clear();
            }
        }
    }

    public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> iterable) {
        zak zak = new zak(iterable);
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(2, zak));
        return zak.getTask();
    }

    public final void zao() {
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(3));
    }

    /* access modifiers changed from: package-private */
    public final void maybeSignOut() {
        this.zaih.incrementAndGet();
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(10));
    }

    public final Task<Boolean> zac(GoogleApi<?> googleApi) {
        zaaf zaaf = new zaaf(googleApi.zak());
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(14, zaaf));
        return zaaf.zaal().getTask();
    }

    public final <O extends Api.ApiOptions> void zaa(GoogleApi<O> googleApi, int i, BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> apiMethodImpl) {
        zae zae = new zae(i, apiMethodImpl);
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(4, new zabv(zae, this.zaih.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions, ResultT> void zaa(GoogleApi<O> googleApi, int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        zag zag = new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper);
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(4, new zabv(zag, this.zaih.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions> Task<Void> zaa(GoogleApi<O> googleApi, RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaf zaf = new zaf(new zabw(registerListenerMethod, unregisterListenerMethod), taskCompletionSource);
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(8, new zabv(zaf, this.zaih.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends Api.ApiOptions> Task<Boolean> zaa(GoogleApi<O> googleApi, ListenerHolder.ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zah zah = new zah(listenerKey, taskCompletionSource);
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(13, new zabv(zah, this.zaih.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public boolean handleMessage(Message message) {
        zaa<?> zaa2;
        long j = 300000;
        switch (message.what) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.zaib = j;
                this.handler.removeMessages(12);
                for (zai<?> zai : this.zaii.keySet()) {
                    Handler handler2 = this.handler;
                    handler2.sendMessageDelayed(handler2.obtainMessage(12, zai), this.zaib);
                }
                break;
            case 2:
                zak zak = (zak) message.obj;
                Iterator<zai<?>> it = zak.zap().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        zai<?> next = it.next();
                        zaa<?> zaa3 = this.zaii.get(next);
                        if (zaa3 == null) {
                            zak.zaa(next, new ConnectionResult(13), null);
                            break;
                        } else if (zaa3.isConnected()) {
                            zak.zaa(next, ConnectionResult.RESULT_SUCCESS, zaa3.zaab().getEndpointPackageName());
                        } else if (zaa3.zabm() != null) {
                            zak.zaa(next, zaa3.zabm(), null);
                        } else {
                            zaa3.zaa(zak);
                            zaa3.connect();
                        }
                    }
                }
            case 3:
                for (zaa<?> zaa4 : this.zaii.values()) {
                    zaa4.zabl();
                    zaa4.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zabv zabv = (zabv) message.obj;
                zaa<?> zaa5 = this.zaii.get(zabv.zajt.zak());
                if (zaa5 == null) {
                    zab(zabv.zajt);
                    zaa5 = this.zaii.get(zabv.zajt.zak());
                }
                if (!zaa5.requiresSignIn() || this.zaih.get() == zabv.zajs) {
                    zaa5.zaa(zabv.zajr);
                    break;
                } else {
                    zabv.zajr.zaa(zahx);
                    zaa5.zabj();
                    break;
                }
                break;
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zaa<?>> it2 = this.zaii.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zaa2 = it2.next();
                        if (zaa2.getInstanceId() == i) {
                        }
                    } else {
                        zaa2 = null;
                    }
                }
                if (zaa2 != null) {
                    String errorString = this.zaie.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    StringBuilder sb = new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length());
                    sb.append("Error resolution was canceled by the user, original error message: ");
                    sb.append(errorString);
                    sb.append(": ");
                    sb.append(errorMessage);
                    zaa2.zac(new Status(17, sb.toString()));
                    break;
                } else {
                    StringBuilder sb2 = new StringBuilder(76);
                    sb2.append("Could not find API instance ");
                    sb2.append(i);
                    sb2.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb2.toString(), new Exception());
                    break;
                }
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zaid.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zaid.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabi(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaib = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zab((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zaii.containsKey(message.obj)) {
                    this.zaii.get(message.obj).resume();
                    break;
                }
                break;
            case 10:
                for (zai<?> zai2 : this.zail) {
                    this.zaii.remove(zai2).zabj();
                }
                this.zail.clear();
                break;
            case 11:
                if (this.zaii.containsKey(message.obj)) {
                    this.zaii.get(message.obj).zaav();
                    break;
                }
                break;
            case 12:
                if (this.zaii.containsKey(message.obj)) {
                    this.zaii.get(message.obj).zabp();
                    break;
                }
                break;
            case 14:
                zaaf zaaf = (zaaf) message.obj;
                zai<?> zak2 = zaaf.zak();
                if (!this.zaii.containsKey(zak2)) {
                    zaaf.zaal().setResult(false);
                    break;
                } else {
                    zaaf.zaal().setResult(Boolean.valueOf(this.zaii.get(zak2).zac((zaa) false)));
                    break;
                }
            case 15:
                zab zab2 = (zab) message.obj;
                if (this.zaii.containsKey(zab2.zajb)) {
                    this.zaii.get(zab2.zajb).zaa((zaa) zab2);
                    break;
                }
                break;
            case 16:
                zab zab3 = (zab) message.obj;
                if (this.zaii.containsKey(zab3.zajb)) {
                    this.zaii.get(zab3.zajb).zab((zaa) zab3);
                    break;
                }
                break;
            default:
                int i2 = message.what;
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i2);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final PendingIntent zaa(zai<?> zai, int i) {
        zad zabq;
        zaa<?> zaa2 = this.zaii.get(zai);
        if (zaa2 == null || (zabq = zaa2.zabq()) == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaid, i, zabq.getSignInIntent(), 134217728);
    }

    /* access modifiers changed from: package-private */
    public final boolean zac(ConnectionResult connectionResult, int i) {
        return this.zaie.zaa(this.zaid, connectionResult, i);
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        if (!zac(connectionResult, i)) {
            Handler handler2 = this.handler;
            handler2.sendMessage(handler2.obtainMessage(5, i, 0, connectionResult));
        }
    }
}
