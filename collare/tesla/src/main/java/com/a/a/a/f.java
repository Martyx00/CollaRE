package com.a.a.a;

import com.a.a.a.c;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ElfParser */
public class f implements c, Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final int f1512a = 1179403647;

    /* renamed from: b  reason: collision with root package name */
    private final FileChannel f1513b;

    public f(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.f1513b = new FileInputStream(file).getChannel();
    }

    public c.b a() {
        this.f1513b.position(0L);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (c(allocate, 0) == 1179403647) {
            short e = e(allocate, 4);
            boolean z = e(allocate, 5) == 2;
            if (e == 1) {
                return new d(z, this);
            }
            if (e == 2) {
                return new e(z, this);
            }
            throw new IllegalStateException("Invalid class type!");
        }
        throw new IllegalArgumentException("Invalid ELF Magic!");
    }

    public List<String> b() {
        long j;
        this.f1513b.position(0L);
        ArrayList arrayList = new ArrayList();
        c.b a2 = a();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(a2.f1503a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = (long) a2.f;
        int i = 0;
        if (j2 == 65535) {
            j2 = a2.a(0).f1511a;
        }
        long j3 = 0;
        while (true) {
            if (j3 >= j2) {
                j = 0;
                break;
            }
            c.AbstractC0034c a3 = a2.a(j3);
            if (a3.f1507a == 2) {
                j = a3.f1508b;
                break;
            }
            j3++;
        }
        if (j == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList<Long> arrayList2 = new ArrayList();
        long j4 = 0;
        while (true) {
            c.a a4 = a2.a(j, i);
            if (a4.f1501a == 1) {
                arrayList2.add(Long.valueOf(a4.f1502b));
            } else if (a4.f1501a == 5) {
                j4 = a4.f1502b;
            }
            i++;
            if (a4.f1501a == 0) {
                break;
            }
            j = j;
        }
        if (j4 != 0) {
            long a5 = a(a2, j2, j4);
            for (Long l : arrayList2) {
                arrayList.add(a(allocate, l.longValue() + a5));
            }
            return arrayList;
        }
        throw new IllegalStateException("String table offset not found!");
    }

    private long a(c.b bVar, long j, long j2) {
        for (long j3 = 0; j3 < j; j3++) {
            c.AbstractC0034c a2 = bVar.a(j3);
            if (a2.f1507a == 1 && a2.f1509c <= j2 && j2 <= a2.f1509c + a2.f1510d) {
                return (j2 - a2.f1509c) + a2.f1508b;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1513b.close();
    }

    /* access modifiers changed from: protected */
    public String a(ByteBuffer byteBuffer, long j) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short e = e(byteBuffer, j);
            if (e == 0) {
                return sb.toString();
            }
            sb.append((char) e);
            j = j2;
        }
    }

    /* access modifiers changed from: protected */
    public long b(ByteBuffer byteBuffer, long j) {
        a(byteBuffer, j, 8);
        return byteBuffer.getLong();
    }

    /* access modifiers changed from: protected */
    public long c(ByteBuffer byteBuffer, long j) {
        a(byteBuffer, j, 4);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    /* access modifiers changed from: protected */
    public int d(ByteBuffer byteBuffer, long j) {
        a(byteBuffer, j, 2);
        return byteBuffer.getShort() & 65535;
    }

    /* access modifiers changed from: protected */
    public short e(ByteBuffer byteBuffer, long j) {
        a(byteBuffer, j, 1);
        return (short) (byteBuffer.get() & 255);
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer, long j, int i) {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        long j2 = 0;
        while (j2 < ((long) i)) {
            int read = this.f1513b.read(byteBuffer, j + j2);
            if (read != -1) {
                j2 += (long) read;
            } else {
                throw new EOFException();
            }
        }
        byteBuffer.position(0);
    }
}
