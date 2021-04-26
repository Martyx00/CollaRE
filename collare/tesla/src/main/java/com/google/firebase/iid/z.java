package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.c;
import android.support.v4.util.a;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class z {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f3959a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f3960b;

    /* renamed from: c  reason: collision with root package name */
    private final bb f3961c;

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, bc> f3962d;

    public z(Context context) {
        this(context, new bb());
    }

    private z(Context context, bb bbVar) {
        this.f3962d = new a();
        this.f3960b = context;
        this.f3959a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.f3961c = bbVar;
        File file = new File(c.b(this.f3960b), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !c()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    b();
                    FirebaseInstanceId.a().i();
                }
            } catch (IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
                }
            }
        }
    }

    public final synchronized String a() {
        return this.f3959a.getString("topic_operaion_queue", "");
    }

    public final synchronized void a(String str) {
        this.f3959a.edit().putString("topic_operaion_queue", str).apply();
    }

    private final synchronized boolean c() {
        return this.f3959a.getAll().isEmpty();
    }

    private static String b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    public final synchronized void b() {
        this.f3962d.clear();
        bb.a(this.f3960b);
        this.f3959a.edit().clear().commit();
    }

    public final synchronized aa a(String str, String str2, String str3) {
        return aa.a(this.f3959a.getString(b(str, str2, str3), null));
    }

    public final synchronized void a(String str, String str2, String str3, String str4, String str5) {
        String a2 = aa.a(str4, str5, System.currentTimeMillis());
        if (a2 != null) {
            SharedPreferences.Editor edit = this.f3959a.edit();
            edit.putString(b(str, str2, str3), a2);
            edit.commit();
        }
    }

    public final synchronized bc b(String str) {
        bc bcVar;
        bc bcVar2 = this.f3962d.get(str);
        if (bcVar2 != null) {
            return bcVar2;
        }
        try {
            bcVar = this.f3961c.a(this.f3960b, str);
        } catch (d unused) {
            Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
            FirebaseInstanceId.a().i();
            bcVar = this.f3961c.b(this.f3960b, str);
        }
        this.f3962d.put(str, bcVar);
        return bcVar;
    }

    public final synchronized void c(String str) {
        String concat = String.valueOf(str).concat("|T|");
        SharedPreferences.Editor edit = this.f3959a.edit();
        for (String str2 : this.f3959a.getAll().keySet()) {
            if (str2.startsWith(concat)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }
}
