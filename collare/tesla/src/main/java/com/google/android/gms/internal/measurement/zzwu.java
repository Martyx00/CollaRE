package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zzwu {
    private static final ConcurrentHashMap<Uri, zzwu> zzbox = new ConcurrentHashMap<>();
    private static final String[] zzbpe = {"key", FirebaseAnalytics.b.VALUE};
    private final Uri uri;
    private final ContentResolver zzboy;
    private final ContentObserver zzboz;
    private final Object zzbpa = new Object();
    private volatile Map<String, String> zzbpb;
    private final Object zzbpc = new Object();
    private final List<zzww> zzbpd = new ArrayList();

    private zzwu(ContentResolver contentResolver, Uri uri2) {
        this.zzboy = contentResolver;
        this.uri = uri2;
        this.zzboz = new zzwv(this, null);
    }

    public static zzwu zza(ContentResolver contentResolver, Uri uri2) {
        zzwu zzwu = zzbox.get(uri2);
        if (zzwu != null) {
            return zzwu;
        }
        zzwu zzwu2 = new zzwu(contentResolver, uri2);
        zzwu putIfAbsent = zzbox.putIfAbsent(uri2, zzwu2);
        if (putIfAbsent != null) {
            return putIfAbsent;
        }
        zzwu2.zzboy.registerContentObserver(zzwu2.uri, false, zzwu2.zzboz);
        return zzwu2;
    }

    private final Map<String, String> zzsj() {
        HashMap hashMap = new HashMap();
        Cursor query = this.zzboy.query(this.uri, zzbpe, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    hashMap.put(query.getString(0), query.getString(1));
                } catch (SQLiteException | SecurityException unused) {
                    Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                    return null;
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            query.close();
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public final void zzsk() {
        synchronized (this.zzbpc) {
            for (zzww zzww : this.zzbpd) {
                zzww.zzsl();
            }
        }
    }

    public final Map<String, String> zzsh() {
        Map<String, String> zzsj = zzwx.zzd("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? zzsj() : this.zzbpb;
        if (zzsj == null) {
            synchronized (this.zzbpa) {
                zzsj = this.zzbpb;
                if (zzsj == null) {
                    zzsj = zzsj();
                    this.zzbpb = zzsj;
                }
            }
        }
        return zzsj != null ? zzsj : Collections.emptyMap();
    }

    public final void zzsi() {
        synchronized (this.zzbpa) {
            this.zzbpb = null;
        }
    }
}
