package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;
import com.facebook.soloader.f;
import com.facebook.soloader.m;
import java.io.File;
import java.util.zip.ZipEntry;

/* compiled from: ApkSoSource */
public class a extends f {
    private final int g;

    public a(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.g = i;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m, com.facebook.soloader.f
    public m.e a() {
        return new C0061a(this);
    }

    /* renamed from: com.facebook.soloader.a$a  reason: collision with other inner class name */
    /* compiled from: ApkSoSource */
    protected class C0061a extends f.b {

        /* renamed from: c  reason: collision with root package name */
        private File f3612c;

        /* renamed from: d  reason: collision with root package name */
        private final int f3613d;

        C0061a(f fVar) {
            super(fVar);
            this.f3612c = new File(a.this.e.getApplicationInfo().nativeLibraryDir);
            this.f3613d = a.this.g;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.f.b
        public boolean a(ZipEntry zipEntry, String str) {
            String str2;
            String name = zipEntry.getName();
            boolean z = true;
            if (str.equals(a.this.f)) {
                a.this.f = null;
                str2 = String.format("allowing consideration of corrupted lib %s", str);
            } else if ((this.f3613d & 1) == 0) {
                str2 = "allowing consideration of " + name + ": self-extraction preferred";
            } else {
                File file = new File(this.f3612c, str);
                if (!file.isFile()) {
                    str2 = String.format("allowing considering of %s: %s not in system lib dir", name, str);
                } else {
                    long length = file.length();
                    long size = zipEntry.getSize();
                    if (length != size) {
                        str2 = String.format("allowing consideration of %s: sysdir file length is %s, but the file is %s bytes long in the APK", file, Long.valueOf(length), Long.valueOf(size));
                    } else {
                        str2 = "not allowing consideration of " + name + ": deferring to libdir";
                        z = false;
                    }
                }
            }
            Log.d("ApkSoSource", str2);
            return z;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m
    public byte[] b() {
        File canonicalFile = this.f3624c.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 2);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            obtain.writeInt(SysUtil.a(this.e));
            if ((this.g & 1) == 0) {
                obtain.writeByte((byte) 0);
                return obtain.marshall();
            }
            String str = this.e.getApplicationInfo().nativeLibraryDir;
            if (str == null) {
                obtain.writeByte((byte) 1);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                return marshall;
            }
            File canonicalFile2 = new File(str).getCanonicalFile();
            if (!canonicalFile2.exists()) {
                obtain.writeByte((byte) 1);
                byte[] marshall2 = obtain.marshall();
                obtain.recycle();
                return marshall2;
            }
            obtain.writeByte((byte) 2);
            obtain.writeString(canonicalFile2.getPath());
            obtain.writeLong(canonicalFile2.lastModified());
            byte[] marshall3 = obtain.marshall();
            obtain.recycle();
            return marshall3;
        } finally {
            obtain.recycle();
        }
    }
}
