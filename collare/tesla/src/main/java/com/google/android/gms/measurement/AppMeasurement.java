package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v4.util.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzig;
import com.google.android.gms.internal.measurement.zzka;
import com.google.android.gms.internal.measurement.zzkd;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;

@Keep
@Deprecated
public class AppMeasurement {
    @KeepForSdk
    public static final String CRASH_ORIGIN = "crash";
    @KeepForSdk
    public static final String FCM_ORIGIN = "fcm";
    @KeepForSdk
    public static final String FIAM_ORIGIN = "fiam";
    private final zzgn zzacv;

    @KeepForSdk
    public static class ConditionalUserProperty {
        @Keep
        @KeepForSdk
        public boolean mActive;
        @Keep
        @KeepForSdk
        public String mAppId;
        @Keep
        @KeepForSdk
        public long mCreationTimestamp;
        @Keep
        public String mExpiredEventName;
        @Keep
        public Bundle mExpiredEventParams;
        @Keep
        @KeepForSdk
        public String mName;
        @Keep
        @KeepForSdk
        public String mOrigin;
        @Keep
        @KeepForSdk
        public long mTimeToLive;
        @Keep
        public String mTimedOutEventName;
        @Keep
        public Bundle mTimedOutEventParams;
        @Keep
        @KeepForSdk
        public String mTriggerEventName;
        @Keep
        @KeepForSdk
        public long mTriggerTimeout;
        @Keep
        public String mTriggeredEventName;
        @Keep
        public Bundle mTriggeredEventParams;
        @Keep
        @KeepForSdk
        public long mTriggeredTimestamp;
        @Keep
        @KeepForSdk
        public Object mValue;

        public ConditionalUserProperty() {
        }

        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            Preconditions.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            Object obj = conditionalUserProperty.mValue;
            if (obj != null) {
                this.mValue = zzkd.zzf(obj);
                if (this.mValue == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            Bundle bundle = conditionalUserProperty.mTimedOutEventParams;
            if (bundle != null) {
                this.mTimedOutEventParams = new Bundle(bundle);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            Bundle bundle2 = conditionalUserProperty.mTriggeredEventParams;
            if (bundle2 != null) {
                this.mTriggeredEventParams = new Bundle(bundle2);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            Bundle bundle3 = conditionalUserProperty.mExpiredEventParams;
            if (bundle3 != null) {
                this.mExpiredEventParams = new Bundle(bundle3);
            }
        }
    }

    @KeepForSdk
    public static final class Event extends FirebaseAnalytics.a {
        @KeepForSdk
        public static final String AD_REWARD = "_ar";
        @KeepForSdk
        public static final String APP_EXCEPTION = "_ae";
        public static final String[] zzacw = {"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "ad_reward", "screen_view", "ga_extra_parameter"};
        public static final String[] zzacx = {"_cd", APP_EXCEPTION, "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", AD_REWARD, "_vs", "_ep"};

        private Event() {
        }

        public static String zzak(String str) {
            return zzkd.zza(str, zzacx, zzacw);
        }

        public static String zzal(String str) {
            return zzkd.zza(str, zzacw, zzacx);
        }
    }

    @KeepForSdk
    public interface EventInterceptor {
        @KeepForSdk
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    @KeepForSdk
    public interface OnEventListener {
        @KeepForSdk
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    @KeepForSdk
    public static final class Param extends FirebaseAnalytics.b {
        @KeepForSdk
        public static final String FATAL = "fatal";
        @KeepForSdk
        public static final String TIMESTAMP = "timestamp";
        @KeepForSdk
        public static final String TYPE = "type";
        public static final String[] zzacy = {"firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "firebase_previous_screen", "firebase_previous_class", "firebase_previous_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "ga_event_id", "ga_extra_params_ct", "ga_group_name", "ga_list_length", "ga_index", "ga_event_name", "campaign_info_source", "deferred_analytics_collection"};
        public static final String[] zzacz = {"_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_o", "_sn", "_sc", "_si", "_pn", "_pc", "_pi", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en", "_cis", "_dac"};

        private Param() {
        }

        public static String zzal(String str) {
            return zzkd.zza(str, zzacy, zzacz);
        }
    }

    @KeepForSdk
    public static final class UserProperty extends FirebaseAnalytics.c {
        @KeepForSdk
        public static final String FIREBASE_LAST_NOTIFICATION = "_ln";
        public static final String[] zzada = {"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "first_open_after_install", "lifetime_user_engagement"};
        public static final String[] zzadb = {FIREBASE_LAST_NOTIFICATION, "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte"};

        private UserProperty() {
        }

        public static String zzal(String str) {
            return zzkd.zza(str, zzada, zzadb);
        }
    }

    public AppMeasurement(zzgn zzgn) {
        Preconditions.checkNotNull(zzgn);
        this.zzacv = zzgn;
    }

    @Keep
    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zzgn.zza(context, null, null).zzkb();
    }

    @Keep
    public void beginAdUnitExposure(String str) {
        this.zzacv.zzfx().beginAdUnitExposure(str);
    }

    @Keep
    @KeepForSdk
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        this.zzacv.zzfy().clearConditionalUserProperty(str, str2, bundle);
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        this.zzacv.zzfy().clearConditionalUserPropertyAs(str, str2, str3, bundle);
    }

    @Keep
    public void endAdUnitExposure(String str) {
        this.zzacv.zzfx().endAdUnitExposure(str);
    }

    @Keep
    public long generateEventId() {
        return this.zzacv.zzgg().zzln();
    }

    @Keep
    public String getAppInstanceId() {
        return this.zzacv.zzfy().zzjk();
    }

    @KeepForSdk
    public Boolean getBoolean() {
        return this.zzacv.zzfy().zzkh();
    }

    @Keep
    @KeepForSdk
    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        return this.zzacv.zzfy().getConditionalUserProperties(str, str2);
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        return this.zzacv.zzfy().getConditionalUserPropertiesAs(str, str2, str3);
    }

    @Keep
    public String getCurrentScreenClass() {
        zzig zzko = this.zzacv.zzgb().zzko();
        if (zzko != null) {
            return zzko.zzaqa;
        }
        return null;
    }

    @Keep
    public String getCurrentScreenName() {
        zzig zzko = this.zzacv.zzgb().zzko();
        if (zzko != null) {
            return zzko.zzuk;
        }
        return null;
    }

    @KeepForSdk
    public Double getDouble() {
        return this.zzacv.zzfy().zzkl();
    }

    @Keep
    public String getGmpAppId() {
        if (this.zzacv.zzkd() != null) {
            return this.zzacv.zzkd();
        }
        try {
            return GoogleServices.getGoogleAppId();
        } catch (IllegalStateException e) {
            this.zzacv.zzgi().zziv().zzg("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    @KeepForSdk
    public Integer getInteger() {
        return this.zzacv.zzfy().zzkk();
    }

    @KeepForSdk
    public Long getLong() {
        return this.zzacv.zzfy().zzkj();
    }

    @Keep
    @KeepForSdk
    public int getMaxUserProperties(String str) {
        this.zzacv.zzfy();
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    @KeepForSdk
    public String getString() {
        return this.zzacv.zzfy().zzki();
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return this.zzacv.zzfy().getUserProperties(str, str2, z);
    }

    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        List<zzka> zzj = this.zzacv.zzfy().zzj(z);
        a aVar = new a(zzj.size());
        for (zzka zzka : zzj) {
            aVar.put(zzka.name, zzka.getValue());
        }
        return aVar;
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        return this.zzacv.zzfy().getUserPropertiesAs(str, str2, str3, z);
    }

    public final void logEvent(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzacv.zzfy().zza("app", str, bundle, true);
    }

    @Keep
    public void logEventInternal(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzacv.zzfy().logEvent(str, str2, bundle);
    }

    @KeepForSdk
    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzacv.zzfy().logEventNoInterceptor(str, str2, bundle, j);
    }

    @KeepForSdk
    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzacv.zzfy().registerOnMeasurementEventListener(onEventListener);
    }

    @Keep
    @KeepForSdk
    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        this.zzacv.zzfy().setConditionalUserProperty(conditionalUserProperty);
    }

    /* access modifiers changed from: protected */
    @Keep
    @VisibleForTesting
    public void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        this.zzacv.zzfy().setConditionalUserPropertyAs(conditionalUserProperty);
    }

    @KeepForSdk
    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zzacv.zzfy().setEventInterceptor(eventInterceptor);
    }

    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        this.zzacv.zzfy().setMeasurementEnabled(z);
    }

    public final void setMinimumSessionDuration(long j) {
        this.zzacv.zzfy().setMinimumSessionDuration(j);
    }

    public final void setSessionTimeoutDuration(long j) {
        this.zzacv.zzfy().setSessionTimeoutDuration(j);
    }

    public final void setUserProperty(String str, String str2) {
        int zzci = this.zzacv.zzgg().zzci(str);
        if (zzci != 0) {
            this.zzacv.zzgg();
            this.zzacv.zzgg().zza(zzci, "_ev", zzkd.zza(str, 24, true), str != null ? str.length() : 0);
            return;
        }
        setUserPropertyInternal("app", str, str2);
    }

    @KeepForSdk
    public void setUserPropertyInternal(String str, String str2, Object obj) {
        this.zzacv.zzfy().setUserProperty(str, str2, obj);
    }

    @KeepForSdk
    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzacv.zzfy().unregisterOnMeasurementEventListener(onEventListener);
    }
}
