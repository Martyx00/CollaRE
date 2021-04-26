package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

@VisibleForTesting
public final class zzcc {
    private static zzcd<Boolean> zzyj = zzcd.zza("analytics.service_enabled", false, false);
    public static zzcd<Boolean> zzyk = zzcd.zza("analytics.service_client_enabled", true, true);
    public static zzcd<String> zzyl = zzcd.zza("analytics.log_tag", "GAv4", "GAv4-SVC");
    private static zzcd<Long> zzym = zzcd.zza("analytics.max_tokens", 60L, 60L);
    private static zzcd<Float> zzyn = zzcd.zza("analytics.tokens_per_sec", 0.5f, 0.5f);
    public static zzcd<Integer> zzyo = zzcd.zza("analytics.max_stored_hits", 2000, 20000);
    private static zzcd<Integer> zzyp = zzcd.zza("analytics.max_stored_hits_per_app", 2000, 2000);
    public static zzcd<Integer> zzyq = zzcd.zza("analytics.max_stored_properties_per_app", 100, 100);
    public static zzcd<Long> zzyr = zzcd.zza("analytics.local_dispatch_millis", 1800000L, 120000L);
    public static zzcd<Long> zzys = zzcd.zza("analytics.initial_local_dispatch_millis", 5000L, 5000L);
    private static zzcd<Long> zzyt = zzcd.zza("analytics.min_local_dispatch_millis", 120000L, 120000L);
    private static zzcd<Long> zzyu = zzcd.zza("analytics.max_local_dispatch_millis", 7200000L, 7200000L);
    public static zzcd<Long> zzyv = zzcd.zza("analytics.dispatch_alarm_millis", 7200000L, 7200000L);
    public static zzcd<Long> zzyw = zzcd.zza("analytics.max_dispatch_alarm_millis", 32400000L, 32400000L);
    public static zzcd<Integer> zzyx = zzcd.zza("analytics.max_hits_per_dispatch", 20, 20);
    public static zzcd<Integer> zzyy = zzcd.zza("analytics.max_hits_per_batch", 20, 20);
    public static zzcd<String> zzyz = zzcd.zza("analytics.insecure_host", "http://www.google-analytics.com", "http://www.google-analytics.com");
    public static zzcd<String> zzza = zzcd.zza("analytics.secure_host", "https://ssl.google-analytics.com", "https://ssl.google-analytics.com");
    public static zzcd<String> zzzb = zzcd.zza("analytics.simple_endpoint", "/collect", "/collect");
    public static zzcd<String> zzzc = zzcd.zza("analytics.batching_endpoint", "/batch", "/batch");
    public static zzcd<Integer> zzzd = zzcd.zza("analytics.max_get_length", 2036, 2036);
    public static zzcd<String> zzze = zzcd.zza("analytics.batching_strategy.k", zzbk.BATCH_BY_COUNT.name(), zzbk.BATCH_BY_COUNT.name());
    public static zzcd<String> zzzf;
    private static zzcd<Integer> zzzg = zzcd.zza("analytics.max_hits_per_request.k", 20, 20);
    public static zzcd<Integer> zzzh = zzcd.zza("analytics.max_hit_length.k", (int) PKIFailureInfo.certRevoked, (int) PKIFailureInfo.certRevoked);
    public static zzcd<Integer> zzzi = zzcd.zza("analytics.max_post_length.k", (int) PKIFailureInfo.certRevoked, (int) PKIFailureInfo.certRevoked);
    public static zzcd<Integer> zzzj = zzcd.zza("analytics.max_batch_post_length", (int) PKIFailureInfo.certRevoked, (int) PKIFailureInfo.certRevoked);
    public static zzcd<String> zzzk = zzcd.zza("analytics.fallback_responses.k", "404,502", "404,502");
    public static zzcd<Integer> zzzl = zzcd.zza("analytics.batch_retry_interval.seconds.k", 3600, 3600);
    private static zzcd<Long> zzzm = zzcd.zza("analytics.service_monitor_interval", 86400000L, 86400000L);
    public static zzcd<Integer> zzzn = zzcd.zza("analytics.http_connection.connect_timeout_millis", 60000, 60000);
    public static zzcd<Integer> zzzo = zzcd.zza("analytics.http_connection.read_timeout_millis", 61000, 61000);
    public static zzcd<Long> zzzp = zzcd.zza("analytics.campaigns.time_limit", 86400000L, 86400000L);
    private static zzcd<String> zzzq = zzcd.zza("analytics.first_party_experiment_id", "", "");
    private static zzcd<Integer> zzzr = zzcd.zza("analytics.first_party_experiment_variant", 0, 0);
    public static zzcd<Boolean> zzzs = zzcd.zza("analytics.test.disable_receiver", false, false);
    public static zzcd<Long> zzzt = zzcd.zza("analytics.service_client.idle_disconnect_millis", 10000L, 10000L);
    public static zzcd<Long> zzzu = zzcd.zza("analytics.service_client.connect_timeout_millis", 5000L, 5000L);
    private static zzcd<Long> zzzv = zzcd.zza("analytics.service_client.second_connect_delay_millis", 5000L, 5000L);
    private static zzcd<Long> zzzw = zzcd.zza("analytics.service_client.unexpected_reconnect_millis", 60000L, 60000L);
    public static zzcd<Long> zzzx = zzcd.zza("analytics.service_client.reconnect_throttle_millis", 1800000L, 1800000L);
    public static zzcd<Long> zzzy = zzcd.zza("analytics.monitoring.sample_period_millis", 86400000L, 86400000L);
    public static zzcd<Long> zzzz = zzcd.zza("analytics.initialization_warning_threshold", 5000L, 5000L);

    static {
        String name = zzbq.GZIP.name();
        zzzf = zzcd.zza("analytics.compression_strategy.k", name, name);
    }
}
