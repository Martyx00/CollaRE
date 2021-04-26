package com.facebook.soloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/* compiled from: FileLocker */
public final class g implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final FileOutputStream f3634a;

    /* renamed from: b  reason: collision with root package name */
    private final FileLock f3635b;

    public static g a(File file) {
        return new g(file);
    }

    private g(File file) {
        this.f3634a = new FileOutputStream(file);
        try {
            FileLock lock = this.f3634a.getChannel().lock();
            if (lock == null) {
            }
            this.f3635b = lock;
        } finally {
            this.f3634a.close();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            if (this.f3635b != null) {
                this.f3635b.release();
            }
        } finally {
            this.f3634a.close();
        }
    }
}
