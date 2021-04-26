package com.teslamotors.plugins.client.d;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: SharedPreferencesHelper */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static e f5545a;

    /* renamed from: b  reason: collision with root package name */
    private ArrayList<SharedPreferences.Editor> f5546b = new ArrayList<>();

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (f5545a == null) {
                f5545a = new e();
            }
            eVar = f5545a;
        }
        return eVar;
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences("secure", 0);
    }

    public static SharedPreferences b(Context context) {
        return context.getSharedPreferences("non_secure", 0);
    }

    public static SharedPreferences c(Context context) {
        return context.getSharedPreferences("svc_data", 0);
    }

    public long a(String str, long j, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(str, j);
        edit.apply();
        return j;
    }

    public long a(String str, SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(str, 0);
    }

    public String b(String str, SharedPreferences sharedPreferences) {
        if (!sharedPreferences.contains(str)) {
            return null;
        }
        try {
            return sharedPreferences.getString(str, null);
        } catch (Exception unused) {
            Map<String, ?> all = sharedPreferences.getAll();
            if (all.containsKey(str)) {
                return all.get(str).toString();
            }
            return null;
        }
    }

    public void a(String str, Set<String> set, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet(str, set);
        edit.commit();
    }

    public Set<String> a(String str, SharedPreferences sharedPreferences, Set<String> set) {
        return sharedPreferences.getStringSet(str, set);
    }

    public boolean a(SharedPreferences sharedPreferences, boolean z) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        if (!z) {
            this.f5546b.add(edit);
        }
        return !z || edit.commit();
    }

    public boolean b() {
        Iterator<SharedPreferences.Editor> it = this.f5546b.iterator();
        boolean z = true;
        while (it.hasNext()) {
            z = it.next().commit() && z;
        }
        this.f5546b.clear();
        return z;
    }
}
