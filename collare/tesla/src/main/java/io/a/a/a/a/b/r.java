package io.a.a.a.a.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import io.a.a.a.c;
import io.a.a.a.i;
import io.a.a.a.l;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/* compiled from: IdManager */
public class r {
    private static final Pattern e = Pattern.compile("[^\\p{Alnum}]");
    private static final String f = Pattern.quote("/");

    /* renamed from: a  reason: collision with root package name */
    c f5913a;

    /* renamed from: b  reason: collision with root package name */
    b f5914b;

    /* renamed from: c  reason: collision with root package name */
    boolean f5915c;

    /* renamed from: d  reason: collision with root package name */
    q f5916d;
    private final ReentrantLock g = new ReentrantLock();
    private final s h;
    private final boolean i;
    private final boolean j;
    private final Context k;
    private final String l;
    private final String m;
    private final Collection<i> n;

    /* compiled from: IdManager */
    public enum a {
        WIFI_MAC_ADDRESS(1),
        BLUETOOTH_MAC_ADDRESS(2),
        FONT_TOKEN(53),
        ANDROID_ID(100),
        ANDROID_DEVICE_ID(101),
        ANDROID_SERIAL(102),
        ANDROID_ADVERTISING_ID(103);
        
        public final int h;

        private a(int i2) {
            this.h = i2;
        }
    }

    public r(Context context, String str, String str2, Collection<i> collection) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection != null) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = collection;
            this.h = new s();
            this.f5913a = new c(context);
            this.f5916d = new q();
            this.i = i.a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.i) {
                l g2 = c.g();
                g2.a("Fabric", "Device ID collection disabled for " + context.getPackageName());
            }
            this.j = i.a(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.j) {
                l g3 = c.g();
                g3.a("Fabric", "User information collection disabled for " + context.getPackageName());
            }
        } else {
            throw new IllegalArgumentException("kits must not be null");
        }
    }

    public boolean a() {
        return this.j;
    }

    private String a(String str) {
        if (str == null) {
            return null;
        }
        return e.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public String b() {
        String str = this.m;
        if (str != null) {
            return str;
        }
        SharedPreferences a2 = i.a(this.k);
        b(a2);
        String string = a2.getString("crashlytics.installation.id", null);
        return string == null ? a(a2) : string;
    }

    public String c() {
        return this.l;
    }

    public String d() {
        return e() + "/" + f();
    }

    public String e() {
        return b(Build.VERSION.RELEASE);
    }

    public String f() {
        return b(Build.VERSION.INCREMENTAL);
    }

    public String g() {
        return String.format(Locale.US, "%s/%s", b(Build.MANUFACTURER), b(Build.MODEL));
    }

    private String b(String str) {
        return str.replaceAll(f, "");
    }

    @SuppressLint({"CommitPrefEdits"})
    private String a(SharedPreferences sharedPreferences) {
        this.g.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = a(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            return string;
        } finally {
            this.g.unlock();
        }
    }

    private void b(SharedPreferences sharedPreferences) {
        b l2 = l();
        if (l2 != null) {
            a(sharedPreferences, l2.f5878a);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private void a(SharedPreferences sharedPreferences, String str) {
        this.g.lock();
        try {
            if (!TextUtils.isEmpty(str)) {
                String string = sharedPreferences.getString("crashlytics.advertising.id", null);
                if (TextUtils.isEmpty(string)) {
                    sharedPreferences.edit().putString("crashlytics.advertising.id", str).commit();
                } else if (!string.equals(str)) {
                    sharedPreferences.edit().remove("crashlytics.installation.id").putString("crashlytics.advertising.id", str).commit();
                }
                this.g.unlock();
            }
        } finally {
            this.g.unlock();
        }
    }

    public Map<a, String> h() {
        HashMap hashMap = new HashMap();
        for (i iVar : this.n) {
            if (iVar instanceof m) {
                for (Map.Entry<a, String> entry : ((m) iVar).getDeviceIdentifiers().entrySet()) {
                    a(hashMap, entry.getKey(), entry.getValue());
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public String i() {
        return this.h.a(this.k);
    }

    public Boolean j() {
        if (k()) {
            return m();
        }
        return null;
    }

    private void a(Map<a, String> map, a aVar, String str) {
        if (str != null) {
            map.put(aVar, str);
        }
    }

    /* access modifiers changed from: protected */
    public boolean k() {
        return this.i && !this.f5916d.b(this.k);
    }

    /* access modifiers changed from: package-private */
    public synchronized b l() {
        if (!this.f5915c) {
            this.f5914b = this.f5913a.a();
            this.f5915c = true;
        }
        return this.f5914b;
    }

    private Boolean m() {
        b l2 = l();
        if (l2 != null) {
            return Boolean.valueOf(l2.f5879b);
        }
        return null;
    }
}
