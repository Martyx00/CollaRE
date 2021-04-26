package io.a.a.a.a.d;

import android.content.Context;
import io.a.a.a.a.b.i;
import io.a.a.a.a.b.t;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: QueueFileEventStorage */
public class h implements c {

    /* renamed from: a  reason: collision with root package name */
    private final Context f5984a;

    /* renamed from: b  reason: collision with root package name */
    private final File f5985b;

    /* renamed from: c  reason: collision with root package name */
    private final String f5986c;

    /* renamed from: d  reason: collision with root package name */
    private final File f5987d;
    private t e = new t(this.f5987d);
    private File f;

    public h(Context context, File file, String str, String str2) {
        this.f5984a = context;
        this.f5985b = file;
        this.f5986c = str2;
        this.f5987d = new File(this.f5985b, str);
        e();
    }

    private void e() {
        this.f = new File(this.f5985b, this.f5986c);
        if (!this.f.exists()) {
            this.f.mkdirs();
        }
    }

    @Override // io.a.a.a.a.d.c
    public void a(byte[] bArr) {
        this.e.a(bArr);
    }

    @Override // io.a.a.a.a.d.c
    public int a() {
        return this.e.a();
    }

    @Override // io.a.a.a.a.d.c
    public void a(String str) {
        this.e.close();
        a(this.f5987d, new File(this.f, str));
        this.e = new t(this.f5987d);
    }

    private void a(File file, File file2) {
        Throwable th;
        FileInputStream fileInputStream;
        OutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                outputStream = a(file2);
                i.a(fileInputStream, outputStream, new byte[1024]);
                i.a((Closeable) fileInputStream, "Failed to close file input stream");
                i.a((Closeable) outputStream, "Failed to close output stream");
                file.delete();
            } catch (Throwable th2) {
                th = th2;
                i.a((Closeable) fileInputStream, "Failed to close file input stream");
                i.a((Closeable) outputStream, "Failed to close output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            i.a((Closeable) fileInputStream, "Failed to close file input stream");
            i.a((Closeable) outputStream, "Failed to close output stream");
            file.delete();
            throw th;
        }
    }

    public OutputStream a(File file) {
        return new FileOutputStream(file);
    }

    @Override // io.a.a.a.a.d.c
    public List<File> a(int i) {
        ArrayList arrayList = new ArrayList();
        for (File file : this.f.listFiles()) {
            arrayList.add(file);
            if (arrayList.size() >= i) {
                break;
            }
        }
        return arrayList;
    }

    @Override // io.a.a.a.a.d.c
    public void a(List<File> list) {
        for (File file : list) {
            i.a(this.f5984a, String.format("deleting sent analytics file %s", file.getName()));
            file.delete();
        }
    }

    @Override // io.a.a.a.a.d.c
    public List<File> c() {
        return Arrays.asList(this.f.listFiles());
    }

    @Override // io.a.a.a.a.d.c
    public void d() {
        try {
            this.e.close();
        } catch (IOException unused) {
        }
        this.f5987d.delete();
    }

    @Override // io.a.a.a.a.d.c
    public boolean b() {
        return this.e.b();
    }

    @Override // io.a.a.a.a.d.c
    public boolean a(int i, int i2) {
        return this.e.a(i, i2);
    }
}
