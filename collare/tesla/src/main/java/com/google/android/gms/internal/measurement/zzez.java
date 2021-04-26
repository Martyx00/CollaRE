package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.asn1.x509.DisplayText;

@VisibleForTesting
public final class zzez {
    static zzee zzahs;
    static List<zza<Integer>> zzaht = new ArrayList();
    static List<zza<Long>> zzahu = new ArrayList();
    static List<zza<Boolean>> zzahv = new ArrayList();
    static List<zza<String>> zzahw = new ArrayList();
    static List<zza<Double>> zzahx = new ArrayList();
    private static final zzxh zzahy;
    private static zza<Boolean> zzahz = zza.zzb("measurement.log_third_party_store_events_enabled", false, false);
    private static zza<Boolean> zzaia = zza.zzb("measurement.log_installs_enabled", false, false);
    private static zza<Boolean> zzaib = zza.zzb("measurement.log_upgrades_enabled", false, false);
    public static zza<Boolean> zzaic = zza.zzb("measurement.log_androidId_enabled", false, false);
    public static zza<Boolean> zzaid = zza.zzb("measurement.upload_dsid_enabled", false, false);
    public static zza<String> zzaie = zza.zzd("measurement.log_tag", "FA", "FA-SVC");
    public static zza<Long> zzaif = zza.zzb("measurement.ad_id_cache_time", 10000, 10000);
    public static zza<Long> zzaig = zza.zzb("measurement.monitoring.sample_period_millis", 86400000, 86400000);
    public static zza<Long> zzaih = zza.zzb("measurement.config.cache_time", 86400000, 3600000);
    public static zza<String> zzaii = zza.zzd("measurement.config.url_scheme", "https", "https");
    public static zza<String> zzaij = zza.zzd("measurement.config.url_authority", "app-measurement.com", "app-measurement.com");
    public static zza<Integer> zzaik = zza.zzc("measurement.upload.max_bundles", 100, 100);
    public static zza<Integer> zzail = zza.zzc("measurement.upload.max_batch_size", PKIFailureInfo.notAuthorized, PKIFailureInfo.notAuthorized);
    public static zza<Integer> zzaim = zza.zzc("measurement.upload.max_bundle_size", PKIFailureInfo.notAuthorized, PKIFailureInfo.notAuthorized);
    public static zza<Integer> zzain = zza.zzc("measurement.upload.max_events_per_bundle", 1000, 1000);
    public static zza<Integer> zzaio = zza.zzc("measurement.upload.max_events_per_day", 100000, 100000);
    public static zza<Integer> zzaip = zza.zzc("measurement.upload.max_error_events_per_day", 1000, 1000);
    public static zza<Integer> zzaiq = zza.zzc("measurement.upload.max_public_events_per_day", 50000, 50000);
    public static zza<Integer> zzair = zza.zzc("measurement.upload.max_conversions_per_day", 500, 500);
    public static zza<Integer> zzais = zza.zzc("measurement.upload.max_realtime_events_per_day", 10, 10);
    public static zza<Integer> zzait = zza.zzc("measurement.store.max_stored_events_per_app", 100000, 100000);
    public static zza<String> zzaiu = zza.zzd("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a");
    public static zza<Long> zzaiv = zza.zzb("measurement.upload.backoff_period", 43200000, 43200000);
    public static zza<Long> zzaiw = zza.zzb("measurement.upload.window_interval", 3600000, 3600000);
    public static zza<Long> zzaix = zza.zzb("measurement.upload.interval", 3600000, 3600000);
    public static zza<Long> zzaiy = zza.zzb("measurement.upload.realtime_upload_interval", 10000, 10000);
    public static zza<Long> zzaiz = zza.zzb("measurement.upload.debug_upload_interval", 1000, 1000);
    public static zza<Long> zzaja = zza.zzb("measurement.upload.minimum_delay", 500, 500);
    public static zza<Long> zzajb = zza.zzb("measurement.alarm_manager.minimum_interval", 60000, 60000);
    public static zza<Long> zzajc = zza.zzb("measurement.upload.stale_data_deletion_interval", 86400000, 86400000);
    public static zza<Long> zzajd = zza.zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000, 604800000);
    public static zza<Long> zzaje = zza.zzb("measurement.upload.initial_upload_delay_time", 15000, 15000);
    public static zza<Long> zzajf = zza.zzb("measurement.upload.retry_time", 1800000, 1800000);
    public static zza<Integer> zzajg = zza.zzc("measurement.upload.retry_count", 6, 6);
    public static zza<Long> zzajh = zza.zzb("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
    public static zza<Integer> zzaji = zza.zzc("measurement.lifetimevalue.max_currency_tracked", 4, 4);
    public static zza<Integer> zzajj = zza.zzc("measurement.audience.filter_result_max_count", DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE, DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE);
    public static zza<Long> zzajk = zza.zzb("measurement.service_client.idle_disconnect_millis", 5000, 5000);
    public static zza<Boolean> zzajl = zza.zzb("measurement.test.boolean_flag", false, false);
    public static zza<String> zzajm = zza.zzd("measurement.test.string_flag", "---", "---");
    public static zza<Long> zzajn = zza.zzb("measurement.test.long_flag", -1, -1);
    public static zza<Integer> zzajo = zza.zzc("measurement.test.int_flag", -2, -2);
    public static zza<Double> zzajp = zza.zza("measurement.test.double_flag", -3.0d, -3.0d);
    public static zza<Boolean> zzajq = zza.zzb("measurement.lifetimevalue.user_engagement_tracking_enabled", false, false);
    public static zza<Boolean> zzajr = zza.zzb("measurement.audience.complex_param_evaluation", false, false);
    public static zza<Boolean> zzajs = zza.zzb("measurement.validation.internal_limits_internal_event_params", false, false);
    public static zza<Boolean> zzajt = zza.zzb("measurement.quality.unsuccessful_update_retry_counter", false, false);
    public static zza<Boolean> zzaju = zza.zzb("measurement.iid.disable_on_collection_disabled", true, true);
    public static zza<Boolean> zzajv = zza.zzb("measurement.app_launch.call_only_when_enabled", true, true);
    public static zza<Boolean> zzajw = zza.zzb("measurement.run_on_worker_inline", true, false);
    public static zza<Boolean> zzajx = zza.zzb("measurement.audience.dynamic_filters", false, false);
    public static zza<Boolean> zzajy = zza.zzb("measurement.reset_analytics.persist_time", false, false);
    private static zza<Boolean> zzajz = zza.zzb("measurement.validation.value_and_currency_params", false, false);

    @VisibleForTesting
    public static final class zza<V> {
        private final V zzaaa;
        private zzwx<V> zzaka;
        private final V zzakb;
        private volatile V zzakc;
        private final String zzny;

        private zza(String str, V v, V v2) {
            this.zzny = str;
            this.zzaaa = v;
            this.zzakb = v2;
        }

        static zza<Double> zza(String str, double d2, double d3) {
            zza<Double> zza = new zza<>(str, Double.valueOf(-3.0d), Double.valueOf(-3.0d));
            zzez.zzahx.add(zza);
            return zza;
        }

        static zza<Long> zzb(String str, long j, long j2) {
            zza<Long> zza = new zza<>(str, Long.valueOf(j), Long.valueOf(j2));
            zzez.zzahu.add(zza);
            return zza;
        }

        static zza<Boolean> zzb(String str, boolean z, boolean z2) {
            zza<Boolean> zza = new zza<>(str, Boolean.valueOf(z), Boolean.valueOf(z2));
            zzez.zzahv.add(zza);
            return zza;
        }

        static zza<Integer> zzc(String str, int i, int i2) {
            zza<Integer> zza = new zza<>(str, Integer.valueOf(i), Integer.valueOf(i2));
            zzez.zzaht.add(zza);
            return zza;
        }

        static zza<String> zzd(String str, String str2, String str3) {
            zza<String> zza = new zza<>(str, str2, str3);
            zzez.zzahw.add(zza);
            return zza;
        }

        private static void zzip() {
            synchronized (zza.class) {
                if (!zzee.isMainThread()) {
                    zzee zzee = zzez.zzahs;
                    for (zza<Boolean> zza : zzez.zzahv) {
                        ((zza) zza).zzakc = ((zza) zza).zzaka.get();
                    }
                    for (zza<String> zza2 : zzez.zzahw) {
                        ((zza) zza2).zzakc = ((zza) zza2).zzaka.get();
                    }
                    for (zza<Long> zza3 : zzez.zzahu) {
                        ((zza) zza3).zzakc = ((zza) zza3).zzaka.get();
                    }
                    for (zza<Integer> zza4 : zzez.zzaht) {
                        ((zza) zza4).zzakc = ((zza) zza4).zzaka.get();
                    }
                    for (zza<Double> zza5 : zzez.zzahx) {
                        ((zza) zza5).zzakc = ((zza) zza5).zzaka.get();
                    }
                } else {
                    throw new IllegalStateException("Tried to refresh flag cache on main thread or on package side.");
                }
            }
        }

        /* access modifiers changed from: private */
        public static void zzm() {
            synchronized (zza.class) {
                for (zza<Boolean> zza : zzez.zzahv) {
                    zzxh zzxh = zzez.zzahy;
                    String str = ((zza) zza).zzny;
                    zzee zzee = zzez.zzahs;
                    ((zza) zza).zzaka = (zzwx<V>) zzxh.zzf(str, ((zza) zza).zzaaa.booleanValue());
                }
                for (zza<String> zza2 : zzez.zzahw) {
                    zzxh zzxh2 = zzez.zzahy;
                    String str2 = ((zza) zza2).zzny;
                    zzee zzee2 = zzez.zzahs;
                    ((zza) zza2).zzaka = (zzwx<V>) zzxh2.zzv(str2, ((zza) zza2).zzaaa);
                }
                for (zza<Long> zza3 : zzez.zzahu) {
                    zzxh zzxh3 = zzez.zzahy;
                    String str3 = ((zza) zza3).zzny;
                    zzee zzee3 = zzez.zzahs;
                    ((zza) zza3).zzaka = (zzwx<V>) zzxh3.zze(str3, ((zza) zza3).zzaaa.longValue());
                }
                for (zza<Integer> zza4 : zzez.zzaht) {
                    zzxh zzxh4 = zzez.zzahy;
                    String str4 = ((zza) zza4).zzny;
                    zzee zzee4 = zzez.zzahs;
                    ((zza) zza4).zzaka = (zzwx<V>) zzxh4.zzd(str4, ((zza) zza4).zzaaa.intValue());
                }
                for (zza<Double> zza5 : zzez.zzahx) {
                    zzxh zzxh5 = zzez.zzahy;
                    String str5 = ((zza) zza5).zzny;
                    zzee zzee5 = zzez.zzahs;
                    ((zza) zza5).zzaka = (zzwx<V>) zzxh5.zzb(str5, ((zza) zza5).zzaaa.doubleValue());
                }
            }
        }

        public final V get() {
            if (zzez.zzahs == null) {
                return this.zzaaa;
            }
            zzee zzee = zzez.zzahs;
            if (zzee.isMainThread()) {
                return this.zzakc == null ? this.zzaaa : this.zzakc;
            }
            zzip();
            return this.zzaka.get();
        }

        public final V get(V v) {
            if (v != null) {
                return v;
            }
            if (zzez.zzahs == null) {
                return this.zzaaa;
            }
            zzee zzee = zzez.zzahs;
            if (zzee.isMainThread()) {
                return this.zzakc == null ? this.zzaaa : this.zzakc;
            }
            zzip();
            return this.zzaka.get();
        }

        public final String getKey() {
            return this.zzny;
        }
    }

    static {
        String valueOf = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
        zzahy = new zzxh(Uri.parse(valueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(valueOf) : new String("content://com.google.android.gms.phenotype/")));
    }

    static void zza(zzee zzee) {
        zzahs = zzee;
        zza.zzm();
    }
}
