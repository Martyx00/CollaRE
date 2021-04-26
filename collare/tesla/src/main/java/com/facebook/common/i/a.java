package com.facebook.common.i;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.d.n;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: StatFsHelper */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f1775a;

    /* renamed from: b  reason: collision with root package name */
    private static final long f1776b = TimeUnit.MINUTES.toMillis(2);

    /* renamed from: c  reason: collision with root package name */
    private volatile StatFs f1777c = null;

    /* renamed from: d  reason: collision with root package name */
    private volatile File f1778d;
    private volatile StatFs e = null;
    private volatile File f;
    private long g;
    private final Lock h = new ReentrantLock();
    private volatile boolean i = false;

    /* renamed from: com.facebook.common.i.a$a  reason: collision with other inner class name */
    /* compiled from: StatFsHelper */
    public enum EnumC0043a {
        INTERNAL,
        EXTERNAL
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (f1775a == null) {
                f1775a = new a();
            }
            aVar = f1775a;
        }
        return aVar;
    }

    protected a() {
    }

    private void b() {
        if (!this.i) {
            this.h.lock();
            try {
                if (!this.i) {
                    this.f1778d = Environment.getDataDirectory();
                    this.f = Environment.getExternalStorageDirectory();
                    d();
                    this.i = true;
                }
            } finally {
                this.h.unlock();
            }
        }
    }

    public boolean a(EnumC0043a aVar, long j) {
        b();
        long a2 = a(aVar);
        return a2 <= 0 || a2 < j;
    }

    @SuppressLint({"DeprecatedMethod"})
    public long a(EnumC0043a aVar) {
        long j;
        long j2;
        b();
        c();
        StatFs statFs = aVar == EnumC0043a.INTERNAL ? this.f1777c : this.e;
        if (statFs == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            j2 = statFs.getBlockSizeLong();
            j = statFs.getAvailableBlocksLong();
        } else {
            j2 = (long) statFs.getBlockSize();
            j = (long) statFs.getAvailableBlocks();
        }
        return j2 * j;
    }

    private void c() {
        if (this.h.tryLock()) {
            try {
                if (SystemClock.uptimeMillis() - this.g > f1776b) {
                    d();
                }
            } finally {
                this.h.unlock();
            }
        }
    }

    private void d() {
        this.f1777c = a(this.f1777c, this.f1778d);
        this.e = a(this.e, this.f);
        this.g = SystemClock.uptimeMillis();
    }

    private StatFs a(StatFs statFs, File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        if (statFs == null) {
            try {
                return a(file.getAbsolutePath());
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Throwable th) {
                throw n.b(th);
            }
        } else {
            statFs.restat(file.getAbsolutePath());
            return statFs;
        }
    }

    protected static StatFs a(String str) {
        return new StatFs(str);
    }
}
