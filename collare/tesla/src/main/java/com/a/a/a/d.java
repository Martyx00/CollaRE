package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Elf32Header */
public class d extends c.b {
    private final f j;

    public d(boolean z, f fVar) {
        this.f1503a = z;
        this.j = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f1504b = fVar.d(allocate, 16);
        this.f1505c = fVar.c(allocate, 28);
        this.f1506d = fVar.c(allocate, 32);
        this.e = fVar.d(allocate, 42);
        this.f = fVar.d(allocate, 44);
        this.g = fVar.d(allocate, 46);
        this.h = fVar.d(allocate, 48);
        this.i = fVar.d(allocate, 50);
    }

    @Override // com.a.a.a.c.b
    public c.d a(int i) {
        return new i(this.j, this, i);
    }

    @Override // com.a.a.a.c.b
    public c.AbstractC0034c a(long j2) {
        return new g(this.j, this, j2);
    }

    @Override // com.a.a.a.c.b
    public c.a a(long j2, int i) {
        return new a(this.j, this, j2, i);
    }
}
