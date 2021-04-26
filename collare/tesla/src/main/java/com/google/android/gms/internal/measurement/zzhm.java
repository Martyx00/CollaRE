package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.a;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzhm extends zzdz {
    @VisibleForTesting
    protected zzif zzapl;
    private AppMeasurement.EventInterceptor zzapm;
    private final Set<AppMeasurement.OnEventListener> zzapn = new CopyOnWriteArraySet();
    private boolean zzapo;
    private final AtomicReference<String> zzapp = new AtomicReference<>();
    @VisibleForTesting
    protected boolean zzapq = true;

    protected zzhm(zzgn zzgn) {
        super(zzgn);
    }

    private final void zza(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzgg().zzcj(str) != 0) {
            zzgi().zziv().zzg("Invalid conditional user property name", zzgf().zzbo(str));
        } else if (zzgg().zzi(str, obj) != 0) {
            zzgi().zziv().zze("Invalid conditional user property value", zzgf().zzbo(str), obj);
        } else {
            Object zzj = zzgg().zzj(str, obj);
            if (zzj == null) {
                zzgi().zziv().zze("Unable to normalize conditional user property value", zzgf().zzbo(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzj;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                long j2 = conditionalUserProperty.mTimeToLive;
                if (j2 > 15552000000L || j2 < 1) {
                    zzgi().zziv().zze("Invalid conditional user property time to live", zzgf().zzbo(str), Long.valueOf(j2));
                } else {
                    zzgh().zzc(new zzht(this, conditionalUserProperty));
                }
            } else {
                zzgi().zziv().zze("Invalid conditional user property timeout", zzgf().zzbo(str), Long.valueOf(j));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        List<String> list;
        String[] strArr;
        zzig zzig;
        int i;
        int i2;
        long j2;
        String str4 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(bundle);
        zzab();
        zzch();
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("Event not sent since app measurement is disabled");
            return;
        }
        int i3 = 0;
        if (!this.zzapo) {
            this.zzapo = true;
            try {
                try {
                    Class.forName("com.google.android.gms.tagmanager.TagManagerService").getDeclaredMethod("initialize", Context.class).invoke(null, getContext());
                } catch (Exception e) {
                    zzgi().zziy().zzg("Failed to invoke Tag Manager's initialize() method", e);
                }
            } catch (ClassNotFoundException unused) {
                zzgi().zzja().log("Tag Manager is not found and thus will not be used");
            }
        }
        if (z3) {
            zzgl();
            if (!"_iap".equals(str4)) {
                zzkd zzgg = this.zzacv.zzgg();
                int i4 = 2;
                if (zzgg.zzq(DataLayer.EVENT_KEY, str4)) {
                    if (!zzgg.zza(DataLayer.EVENT_KEY, AppMeasurement.Event.zzacw, str4)) {
                        i4 = 13;
                    } else if (zzgg.zza(DataLayer.EVENT_KEY, 40, str4)) {
                        i4 = 0;
                    }
                }
                if (i4 != 0) {
                    zzgi().zzix().zzg("Invalid public event name. Event will not be logged (FE)", zzgf().zzbm(str4));
                    this.zzacv.zzgg();
                    this.zzacv.zzgg().zza(i4, "_ev", zzkd.zza(str4, 40, true), str4 != null ? str2.length() : 0);
                    return;
                }
            }
        }
        zzgl();
        zzig zzkn = zzgb().zzkn();
        if (zzkn != null && !bundle.containsKey("_sc")) {
            zzkn.zzaqc = true;
        }
        zzih.zza(zzkn, bundle, z && z3);
        boolean equals = "am".equals(str);
        boolean zzcm = zzkd.zzcm(str2);
        if (z && this.zzapm != null && !zzcm && !equals) {
            zzgi().zzjb().zze("Passing event to registered event handler (FE)", zzgf().zzbm(str4), zzgf().zzb(bundle));
            this.zzapm.interceptEvent(str, str2, bundle, j);
        } else if (this.zzacv.zzkg()) {
            int zzch = zzgg().zzch(str4);
            if (zzch != 0) {
                zzgi().zzix().zzg("Invalid event name. Event will not be logged (FE)", zzgf().zzbm(str4));
                zzgg();
                String zza = zzkd.zza(str4, 40, true);
                if (str4 != null) {
                    i3 = str2.length();
                }
                this.zzacv.zzgg().zza(str3, zzch, "_ev", zza, i3);
                return;
            }
            List<String> listOf = CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"});
            Bundle zza2 = zzgg().zza(str3, str2, bundle, listOf, z3, true);
            zzig zzig2 = (zza2 == null || !zza2.containsKey("_sc") || !zza2.containsKey("_si")) ? null : new zzig(zza2.getString("_sn"), zza2.getString("_sc"), Long.valueOf(zza2.getLong("_si")).longValue());
            if (zzig2 != null) {
                zzkn = zzig2;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(zza2);
            long nextLong = zzgg().zzlo().nextLong();
            String[] strArr2 = (String[]) zza2.keySet().toArray(new String[bundle.size()]);
            Arrays.sort(strArr2);
            int length = strArr2.length;
            int i5 = 0;
            int i6 = 0;
            while (i6 < length) {
                String str5 = strArr2[i6];
                Object obj = zza2.get(str5);
                zzgg();
                Bundle[] zze = zzkd.zze(obj);
                if (zze != null) {
                    zza2.putInt(str5, zze.length);
                    strArr = strArr2;
                    int i7 = 0;
                    while (i7 < zze.length) {
                        Bundle bundle2 = zze[i7];
                        zzih.zza(zzkn, bundle2, true);
                        Bundle zza3 = zzgg().zza(str3, "_ep", bundle2, listOf, z3, false);
                        zza3.putString("_en", str4);
                        zza3.putLong("_eid", nextLong);
                        zza3.putString("_gn", str5);
                        zza3.putInt("_ll", zze.length);
                        zza3.putInt("_i", i7);
                        arrayList.add(zza3);
                        i7++;
                        nextLong = nextLong;
                        str5 = str5;
                        length = length;
                        zze = zze;
                        i6 = i6;
                        zzkn = zzkn;
                        i5 = i5;
                        listOf = listOf;
                    }
                    list = listOf;
                    zzig = zzkn;
                    i = i6;
                    i2 = length;
                    j2 = nextLong;
                    i5 += zze.length;
                } else {
                    list = listOf;
                    zzig = zzkn;
                    strArr = strArr2;
                    i = i6;
                    i2 = length;
                    j2 = nextLong;
                }
                i6 = i + 1;
                nextLong = j2;
                length = i2;
                zzkn = zzig;
                strArr2 = strArr;
                listOf = list;
            }
            if (i5 != 0) {
                zza2.putLong("_eid", nextLong);
                zza2.putInt("_epc", i5);
            }
            int i8 = 0;
            while (i8 < arrayList.size()) {
                Bundle bundle3 = (Bundle) arrayList.get(i8);
                String str6 = i8 != 0 ? "_ep" : str4;
                bundle3.putString("_o", str);
                if (z2) {
                    bundle3 = zzgg().zzd(bundle3);
                }
                zzgi().zzjb().zze("Logging event (FE)", zzgf().zzbm(str4), zzgf().zzb(bundle3));
                zzga().zzb(new zzex(str6, new zzeu(bundle3), str, j), str3);
                if (!equals) {
                    for (AppMeasurement.OnEventListener onEventListener : this.zzapn) {
                        onEventListener.onEvent(str, str2, new Bundle(bundle3), j);
                    }
                }
                i8++;
                arrayList = arrayList;
                str4 = str4;
            }
            zzgl();
            if (zzgb().zzkn() != null && AppMeasurement.Event.APP_EXCEPTION.equals(str4)) {
                zzgd().zzl(true);
            }
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzgh().zzc(new zzho(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzb(str, str2, zzbt().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    /* access modifiers changed from: private */
    public final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzfv();
        zzch();
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("User property not set since app measurement is disabled");
        } else if (this.zzacv.zzkg()) {
            zzgi().zzjb().zze("Setting user property (FE)", zzgf().zzbm(str2), obj);
            zzga().zzb(new zzka(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzgh().zzc(new zzhu(this, conditionalUserProperty));
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        zzfk zziy;
        String str4;
        if (zzgh().zzju()) {
            zziy = zzgi().zziv();
            str4 = "Cannot get user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziy = zzgi().zziv();
            str4 = "Cannot get user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacv.zzgh().zzc(new zzhw(this, atomicReference, str, str2, str3, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgi().zziy().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list == null) {
                zziy = zzgi().zziy();
                str4 = "Timed out waiting for get user properties";
            } else {
                a aVar = new a(list.size());
                for (zzka zzka : list) {
                    aVar.put(zzka.name, zzka.getValue());
                }
                return aVar;
            }
        }
        zziy.log(str4);
        return Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    public final void zzb(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzka zzka = new zzka(conditionalUserProperty.mName, conditionalUserProperty.mTriggeredTimestamp, conditionalUserProperty.mValue, conditionalUserProperty.mOrigin);
        try {
            zzex zza = zzgg().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mTriggeredEventName, conditionalUserProperty.mTriggeredEventParams, conditionalUserProperty.mOrigin, 0, true, false);
            zzga().zzd(new zzef(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzka, conditionalUserProperty.mCreationTimestamp, false, conditionalUserProperty.mTriggerEventName, zzgg().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mTimedOutEventName, conditionalUserProperty.mTimedOutEventParams, conditionalUserProperty.mOrigin, 0, true, false), conditionalUserProperty.mTriggerTimeout, zza, conditionalUserProperty.mTimeToLive, zzgg().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, 0, true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            Bundle bundle3 = new Bundle(bundle);
            for (String str4 : bundle3.keySet()) {
                Object obj = bundle3.get(str4);
                if (obj instanceof Bundle) {
                    bundle3.putBundle(str4, new Bundle((Bundle) obj));
                } else {
                    int i = 0;
                    if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        while (i < parcelableArr.length) {
                            if (parcelableArr[i] instanceof Bundle) {
                                parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                            }
                            i++;
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        while (i < arrayList.size()) {
                            Object obj2 = arrayList.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.set(i, new Bundle((Bundle) obj2));
                            }
                            i++;
                        }
                    }
                }
            }
            bundle2 = bundle3;
        }
        zzgh().zzc(new zzie(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    /* access modifiers changed from: private */
    public final void zzc(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        try {
            zzga().zzd(new zzef(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, new zzka(conditionalUserProperty.mName, 0, null, null), conditionalUserProperty.mCreationTimestamp, conditionalUserProperty.mActive, conditionalUserProperty.mTriggerEventName, null, conditionalUserProperty.mTriggerTimeout, null, conditionalUserProperty.mTimeToLive, zzgg().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, conditionalUserProperty.mCreationTimestamp, true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    @VisibleForTesting
    private final List<AppMeasurement.ConditionalUserProperty> zzf(String str, String str2, String str3) {
        zzfk zziv;
        String str4;
        if (zzgh().zzju()) {
            zziv = zzgi().zziv();
            str4 = "Cannot get conditional user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziv = zzgi().zziv();
            str4 = "Cannot get conditional user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacv.zzgh().zzc(new zzhv(this, atomicReference, str, str2, str3));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgi().zziy().zze("Interrupted waiting for get conditional user properties", str, e);
                }
            }
            List<zzef> list = (List) atomicReference.get();
            if (list == null) {
                zzgi().zziy().zzg("Timed out waiting for get conditional user properties", str);
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (zzef zzef : list) {
                AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
                conditionalUserProperty.mAppId = zzef.packageName;
                conditionalUserProperty.mOrigin = zzef.origin;
                conditionalUserProperty.mCreationTimestamp = zzef.creationTimestamp;
                conditionalUserProperty.mName = zzef.zzage.name;
                conditionalUserProperty.mValue = zzef.zzage.getValue();
                conditionalUserProperty.mActive = zzef.active;
                conditionalUserProperty.mTriggerEventName = zzef.triggerEventName;
                if (zzef.zzagf != null) {
                    conditionalUserProperty.mTimedOutEventName = zzef.zzagf.name;
                    if (zzef.zzagf.zzahg != null) {
                        conditionalUserProperty.mTimedOutEventParams = zzef.zzagf.zzahg.zzin();
                    }
                }
                conditionalUserProperty.mTriggerTimeout = zzef.triggerTimeout;
                if (zzef.zzagg != null) {
                    conditionalUserProperty.mTriggeredEventName = zzef.zzagg.name;
                    if (zzef.zzagg.zzahg != null) {
                        conditionalUserProperty.mTriggeredEventParams = zzef.zzagg.zzahg.zzin();
                    }
                }
                conditionalUserProperty.mTriggeredTimestamp = zzef.zzage.zzast;
                conditionalUserProperty.mTimeToLive = zzef.timeToLive;
                if (zzef.zzagh != null) {
                    conditionalUserProperty.mExpiredEventName = zzef.zzagh.name;
                    if (zzef.zzagh.zzahg != null) {
                        conditionalUserProperty.mExpiredEventParams = zzef.zzagh.zzahg.zzin();
                    }
                }
                arrayList.add(conditionalUserProperty);
            }
            return arrayList;
        }
        zziv.log(str4);
        return Collections.emptyList();
    }

    /* access modifiers changed from: private */
    public final void zzi(boolean z) {
        zzab();
        zzfv();
        zzch();
        zzgi().zzjb().zzg("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzgj().setMeasurementEnabled(z);
        if (!zzgk().zzbc(zzfz().zzah())) {
            zzga().zzkp();
        } else if (!this.zzacv.isEnabled() || !this.zzapq) {
            zzga().zzkp();
        } else {
            zzgi().zzjb().log("Recording app launch after enabling measurement for the first time (FE)");
            zzkm();
        }
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzfv();
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzjk = zzgj().zzjk();
            return zzjk != null ? Tasks.forResult(zzjk) : Tasks.call(zzgh().zzjv(), new zzhq(this));
        } catch (Exception e) {
            zzgi().zziy().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzfv();
        return zzf(null, str, str2);
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        return zzf(str, str2, str3);
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzfv();
        return zzb(null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        return zzb(str, str2, str3, z);
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        zzfv();
        zza(str, str2, bundle, true, this.zzapm == null || zzkd.zzcm(str2), false, null);
    }

    public final void logEventNoInterceptor(String str, String str2, Bundle bundle, long j) {
        zzfv();
        zzb(str, str2, j, bundle, false, true, true, null);
    }

    public final void registerOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzfv();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzapn.add(onEventListener)) {
            zzgi().zziy().log("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData() {
        zzgh().zzc(new zzhs(this, zzbt().currentTimeMillis()));
    }

    public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        zzfv();
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzgi().zziy().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mAppId);
        zzfu();
        zza(new AppMeasurement.ConditionalUserProperty(conditionalUserProperty));
    }

    public final void setEventInterceptor(AppMeasurement.EventInterceptor eventInterceptor) {
        AppMeasurement.EventInterceptor eventInterceptor2;
        zzab();
        zzfv();
        zzch();
        if (!(eventInterceptor == null || eventInterceptor == (eventInterceptor2 = this.zzapm))) {
            Preconditions.checkState(eventInterceptor2 == null, "EventInterceptor already set.");
        }
        this.zzapm = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzch();
        zzfv();
        zzgh().zzc(new zzib(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzfv();
        zzgh().zzc(new zzic(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzfv();
        zzgh().zzc(new zzid(this, j));
    }

    public final void setUserProperty(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzbt().currentTimeMillis();
        int zzcj = zzgg().zzcj(str2);
        int i = 0;
        if (zzcj != 0) {
            zzgg();
            String zza = zzkd.zza(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzacv.zzgg().zza(zzcj, "_ev", zza, i);
        } else if (obj != null) {
            int zzi = zzgg().zzi(str2, obj);
            if (zzi != 0) {
                zzgg();
                String zza2 = zzkd.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzacv.zzgg().zza(zzi, "_ev", zza2, i);
                return;
            }
            Object zzj = zzgg().zzj(str2, obj);
            if (zzj != null) {
                zza(str, str2, currentTimeMillis, zzj);
            }
        } else {
            zza(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public final void unregisterOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzfv();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzapn.remove(onEventListener)) {
            zzgi().zziy().log("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, String str2, Bundle bundle) {
        zzfv();
        zzab();
        zza(str, str2, zzbt().currentTimeMillis(), bundle, true, this.zzapm == null || zzkd.zzcm(str2), false, null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zzfv();
        zza(str, str2, bundle, true, this.zzapm == null || zzkd.zzcm(str2), true, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: package-private */
    public final String zzaj(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzgh().zzc(new zzhr(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException unused) {
                zzgi().zziy().log("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: package-private */
    public final void zzbu(String str) {
        this.zzapp.set(str);
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzdu zzfx() {
        return super.zzfx();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzhm zzfy() {
        return super.zzfy();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzfd zzfz() {
        return super.zzfz();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzik zzga() {
        return super.zzga();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzih zzgb() {
        return super.zzgb();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzfe zzgc() {
        return super.zzgc();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzjj zzgd() {
        return super.zzgd();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzdz
    public final boolean zzgn() {
        return false;
    }

    public final List<zzka> zzj(boolean z) {
        zzfk zziy;
        String str;
        zzfv();
        zzch();
        zzgi().zzjb().log("Fetching user attributes (FE)");
        if (zzgh().zzju()) {
            zziy = zzgi().zziv();
            str = "Cannot get all user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziy = zzgi().zziv();
            str = "Cannot get all user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacv.zzgh().zzc(new zzhp(this, atomicReference, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgi().zziy().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zziy = zzgi().zziy();
            str = "Timed out waiting for get user properties";
        }
        zziy.log(str);
        return Collections.emptyList();
    }

    public final String zzjk() {
        zzfv();
        return this.zzapp.get();
    }

    public final Boolean zzkh() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzgh().zza(atomicReference, 15000, "boolean test flag value", new zzhn(this, atomicReference));
    }

    public final String zzki() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzgh().zza(atomicReference, 15000, "String test flag value", new zzhx(this, atomicReference));
    }

    public final Long zzkj() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzgh().zza(atomicReference, 15000, "long test flag value", new zzhy(this, atomicReference));
    }

    public final Integer zzkk() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzgh().zza(atomicReference, 15000, "int test flag value", new zzhz(this, atomicReference));
    }

    public final Double zzkl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzgh().zza(atomicReference, 15000, "double test flag value", new zzia(this, atomicReference));
    }

    public final void zzkm() {
        zzab();
        zzfv();
        zzch();
        if (this.zzacv.zzkg()) {
            zzga().zzkm();
            this.zzapq = false;
            String zzjn = zzgj().zzjn();
            if (!TextUtils.isEmpty(zzjn)) {
                zzge().zzch();
                if (!zzjn.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzjn);
                    logEvent("auto", "_ou", bundle);
                }
            }
        }
    }
}
