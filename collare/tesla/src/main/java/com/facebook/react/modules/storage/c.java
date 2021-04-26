package com.facebook.react.modules.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: ReactDatabaseSupplier */
public class c extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static c f3008a;

    /* renamed from: b  reason: collision with root package name */
    private Context f3009b;

    /* renamed from: c  reason: collision with root package name */
    private SQLiteDatabase f3010c;

    /* renamed from: d  reason: collision with root package name */
    private long f3011d = 6291456;

    private c(Context context) {
        super(context, "RKStorage", (SQLiteDatabase.CursorFactory) null, 1);
        this.f3009b = context;
    }

    public static c a(Context context) {
        if (f3008a == null) {
            f3008a = new c(context.getApplicationContext());
        }
        return f3008a;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE catalystLocalStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
            e();
            onCreate(sQLiteDatabase);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean a() {
        if (this.f3010c != null && this.f3010c.isOpen()) {
            return true;
        }
        SQLiteException e = null;
        int i = 0;
        while (true) {
            if (i < 2) {
                if (i <= 0) {
                    break;
                }
                try {
                    e();
                    break;
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                    i++;
                }
            } else {
                break;
            }
        }
        this.f3010c = getWritableDatabase();
        if (this.f3010c != null) {
            this.f3010c.setMaximumSize(this.f3011d);
            return true;
        }
        throw e;
    }

    public synchronized SQLiteDatabase b() {
        a();
        return this.f3010c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        throw new java.lang.RuntimeException("Clearing and deleting database RKStorage failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        if (e() != false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        com.facebook.common.e.a.a("ReactNative", "Deleted Local Database RKStorage");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c() {
        /*
            r2 = this;
            monitor-enter(r2)
            r2.d()     // Catch:{ Exception -> 0x0012 }
            r2.f()     // Catch:{ Exception -> 0x0012 }
            java.lang.String r0 = "ReactNative"
            java.lang.String r1 = "Cleaned RKStorage"
            com.facebook.common.e.a.a(r0, r1)     // Catch:{ Exception -> 0x0012 }
            monitor-exit(r2)
            return
        L_0x0010:
            r0 = move-exception
            goto L_0x0029
        L_0x0012:
            boolean r0 = r2.e()     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = "ReactNative"
            java.lang.String r1 = "Deleted Local Database RKStorage"
            com.facebook.common.e.a.a(r0, r1)     // Catch:{ all -> 0x0010 }
            monitor-exit(r2)
            return
        L_0x0021:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Clearing and deleting database RKStorage failed"
            r0.<init>(r1)
            throw r0
        L_0x0029:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.storage.c.c():void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void d() {
        b().delete("catalystLocalStorage", null, null);
    }

    private synchronized boolean e() {
        f();
        return this.f3009b.deleteDatabase("RKStorage");
    }

    private synchronized void f() {
        if (this.f3010c != null && this.f3010c.isOpen()) {
            this.f3010c.close();
            this.f3010c = null;
        }
    }
}
