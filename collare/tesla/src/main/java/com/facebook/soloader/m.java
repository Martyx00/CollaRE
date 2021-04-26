package com.facebook.soloader;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/* compiled from: UnpackingSoSource */
public abstract class m extends c {

    /* renamed from: c  reason: collision with root package name */
    private String[] f3636c;

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, Object> f3637d = new HashMap();
    protected final Context e;
    protected String f;

    /* access modifiers changed from: protected */
    public abstract e a();

    protected m(Context context, String str) {
        super(a(context, str), 1);
        this.e = context;
    }

    public static File a(Context context, String str) {
        return new File(context.getApplicationInfo().dataDir + "/" + str);
    }

    public void a(String[] strArr) {
        this.f3636c = strArr;
    }

    /* compiled from: UnpackingSoSource */
    public static class a {

        /* renamed from: c  reason: collision with root package name */
        public final String f3642c;

        /* renamed from: d  reason: collision with root package name */
        public final String f3643d;

        public a(String str, String str2) {
            this.f3642c = str;
            this.f3643d = str2;
        }
    }

    /* compiled from: UnpackingSoSource */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final a[] f3644a;

        public b(a[] aVarArr) {
            this.f3644a = aVarArr;
        }

        static final b a(DataInput dataInput) {
            if (dataInput.readByte() == 1) {
                int readInt = dataInput.readInt();
                if (readInt >= 0) {
                    a[] aVarArr = new a[readInt];
                    for (int i = 0; i < readInt; i++) {
                        aVarArr[i] = new a(dataInput.readUTF(), dataInput.readUTF());
                    }
                    return new b(aVarArr);
                }
                throw new RuntimeException("illegal number of shared libraries");
            }
            throw new RuntimeException("wrong dso manifest version");
        }

        public final void a(DataOutput dataOutput) {
            dataOutput.writeByte(1);
            dataOutput.writeInt(this.f3644a.length);
            int i = 0;
            while (true) {
                a[] aVarArr = this.f3644a;
                if (i < aVarArr.length) {
                    dataOutput.writeUTF(aVarArr[i].f3642c);
                    dataOutput.writeUTF(this.f3644a[i].f3643d);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* compiled from: UnpackingSoSource */
    public static final class c implements Closeable {

        /* renamed from: a  reason: collision with root package name */
        public final a f3645a;

        /* renamed from: b  reason: collision with root package name */
        public final InputStream f3646b;

        public c(a aVar, InputStream inputStream) {
            this.f3645a = aVar;
            this.f3646b = inputStream;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f3646b.close();
        }
    }

    /* access modifiers changed from: protected */
    /* compiled from: UnpackingSoSource */
    public static abstract class d implements Closeable {
        public abstract boolean a();

        public abstract c b();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        protected d() {
        }
    }

    /* access modifiers changed from: protected */
    /* compiled from: UnpackingSoSource */
    public static abstract class e implements Closeable {
        /* access modifiers changed from: protected */
        public abstract b a();

        /* access modifiers changed from: protected */
        public abstract d b();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        protected e() {
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0022, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r3 != null) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(java.io.File r3, byte r4) {
        /*
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile
            java.lang.String r1 = "rw"
            r0.<init>(r3, r1)
            r1 = 0
            r3 = 0
            r0.seek(r1)     // Catch:{ Throwable -> 0x0024 }
            r0.write(r4)     // Catch:{ Throwable -> 0x0024 }
            long r1 = r0.getFilePointer()     // Catch:{ Throwable -> 0x0024 }
            r0.setLength(r1)     // Catch:{ Throwable -> 0x0024 }
            java.io.FileDescriptor r4 = r0.getFD()     // Catch:{ Throwable -> 0x0024 }
            r4.sync()     // Catch:{ Throwable -> 0x0024 }
            r0.close()
            return
        L_0x0022:
            r4 = move-exception
            goto L_0x0026
        L_0x0024:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0022 }
        L_0x0026:
            if (r3 == 0) goto L_0x0031
            r0.close()     // Catch:{ Throwable -> 0x002c }
            goto L_0x0034
        L_0x002c:
            r0 = move-exception
            r3.addSuppressed(r0)
            goto L_0x0034
        L_0x0031:
            r0.close()
        L_0x0034:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.m.b(java.io.File, byte):void");
    }

    private void a(a[] aVarArr) {
        String[] list = this.f3617a.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals("dso_state") && !str.equals("dso_lock") && !str.equals("dso_deps") && !str.equals("dso_manifest")) {
                    boolean z = false;
                    int i = 0;
                    while (!z && i < aVarArr.length) {
                        if (aVarArr[i].f3642c.equals(str)) {
                            z = true;
                        }
                        i++;
                    }
                    if (!z) {
                        File file = new File(this.f3617a, str);
                        Log.v("fb-UnpackingSoSource", "deleting unaccounted-for file " + file);
                        SysUtil.a(file);
                    }
                }
            }
            return;
        }
        throw new IOException("unable to list directory " + this.f3617a);
    }

    private void a(c cVar, byte[] bArr) {
        RandomAccessFile randomAccessFile;
        Log.i("fb-UnpackingSoSource", "extracting DSO " + cVar.f3645a.f3642c);
        if (this.f3617a.setWritable(true, true)) {
            File file = new File(this.f3617a, cVar.f3645a.f3642c);
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (IOException e2) {
                Log.w("fb-UnpackingSoSource", "error overwriting " + file + " trying to delete and start over", e2);
                SysUtil.a(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
            }
            try {
                int available = cVar.f3646b.available();
                if (available > 1) {
                    SysUtil.a(randomAccessFile.getFD(), (long) available);
                }
                SysUtil.a(randomAccessFile, cVar.f3646b, Api.BaseClientBuilder.API_PRIORITY_OTHER, bArr);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                if (file.setExecutable(true, false)) {
                    randomAccessFile.close();
                    return;
                }
                throw new IOException("cannot make file executable: " + file);
            } catch (IOException e3) {
                SysUtil.a(file);
                throw e3;
            } catch (Throwable th) {
                randomAccessFile.close();
                throw th;
            }
        } else {
            throw new IOException("cannot make directory writable for us: " + this.f3617a);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0037, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00dc, code lost:
        if (r11 != null) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e2, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e3, code lost:
        r11.addSuppressed(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e7, code lost:
        r1.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a A[Catch:{ all -> 0x0037, Throwable -> 0x00e2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0061 A[Catch:{ all -> 0x0037, Throwable -> 0x00e2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte r11, com.facebook.soloader.m.b r12, com.facebook.soloader.m.d r13) {
        /*
        // Method dump skipped, instructions count: 235
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.m.a(byte, com.facebook.soloader.m$b, com.facebook.soloader.m$d):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        if (r12 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r12.addSuppressed(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e9, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ea, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ee, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ef, code lost:
        r13 = r12;
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0103, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0104, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0038, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0108, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0109, code lost:
        r13 = r12;
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x011d, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0122, code lost:
        if (r12 != null) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0128, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0129, code lost:
        r12.addSuppressed(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x012d, code lost:
        r0.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0103 A[ExcHandler: all (th java.lang.Throwable)] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(final com.facebook.soloader.g r12, int r13, final byte[] r14) {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.m.a(com.facebook.soloader.g, int, byte[]):boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r1 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        if (r0 != null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        r1.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] b() {
        /*
            r6 = this;
            android.os.Parcel r0 = android.os.Parcel.obtain()
            com.facebook.soloader.m$e r1 = r6.a()
            r2 = 0
            com.facebook.soloader.m$b r3 = r1.a()     // Catch:{ Throwable -> 0x003b }
            com.facebook.soloader.m$a[] r3 = r3.f3644a     // Catch:{ Throwable -> 0x003b }
            r4 = 1
            r0.writeByte(r4)     // Catch:{ Throwable -> 0x003b }
            int r4 = r3.length     // Catch:{ Throwable -> 0x003b }
            r0.writeInt(r4)     // Catch:{ Throwable -> 0x003b }
            r4 = 0
        L_0x0018:
            int r5 = r3.length     // Catch:{ Throwable -> 0x003b }
            if (r4 >= r5) goto L_0x002c
            r5 = r3[r4]     // Catch:{ Throwable -> 0x003b }
            java.lang.String r5 = r5.f3642c     // Catch:{ Throwable -> 0x003b }
            r0.writeString(r5)     // Catch:{ Throwable -> 0x003b }
            r5 = r3[r4]     // Catch:{ Throwable -> 0x003b }
            java.lang.String r5 = r5.f3643d     // Catch:{ Throwable -> 0x003b }
            r0.writeString(r5)     // Catch:{ Throwable -> 0x003b }
            int r4 = r4 + 1
            goto L_0x0018
        L_0x002c:
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            byte[] r1 = r0.marshall()
            r0.recycle()
            return r1
        L_0x0039:
            r0 = move-exception
            goto L_0x003e
        L_0x003b:
            r0 = move-exception
            r2 = r0
            throw r2     // Catch:{ all -> 0x0039 }
        L_0x003e:
            if (r1 == 0) goto L_0x004e
            if (r2 == 0) goto L_0x004b
            r1.close()     // Catch:{ Throwable -> 0x0046 }
            goto L_0x004e
        L_0x0046:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x004e
        L_0x004b:
            r1.close()
        L_0x004e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.m.b():byte[]");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.k
    public void a(int i) {
        SysUtil.b(this.f3617a);
        g a2 = g.a(new File(this.f3617a, "dso_lock"));
        try {
            Log.v("fb-UnpackingSoSource", "locked dso store " + this.f3617a);
            if (a(a2, i, b())) {
                a2 = null;
            } else {
                Log.i("fb-UnpackingSoSource", "dso store is up-to-date: " + this.f3617a);
            }
            if (a2 != null) {
                Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + this.f3617a);
                a2.close();
                return;
            }
            Log.v("fb-UnpackingSoSource", "not releasing dso store lock for " + this.f3617a + " (syncer thread started)");
        } catch (Throwable th) {
            if (a2 != null) {
                Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + this.f3617a);
                a2.close();
            } else {
                Log.v("fb-UnpackingSoSource", "not releasing dso store lock for " + this.f3617a + " (syncer thread started)");
            }
            throw th;
        }
    }

    private Object c(String str) {
        Object obj;
        synchronized (this.f3637d) {
            obj = this.f3637d.get(str);
            if (obj == null) {
                obj = new Object();
                this.f3637d.put(str, obj);
            }
        }
        return obj;
    }

    /* access modifiers changed from: protected */
    public synchronized void b(String str) {
        synchronized (c(str)) {
            this.f = str;
            a(2);
        }
    }

    @Override // com.facebook.soloader.c, com.facebook.soloader.k
    public int a(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        int a2;
        synchronized (c(str)) {
            a2 = a(str, i, this.f3617a, threadPolicy);
        }
        return a2;
    }
}
