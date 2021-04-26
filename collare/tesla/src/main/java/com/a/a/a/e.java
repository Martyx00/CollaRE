package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Elf64Header */
public class e extends c.b {
    private final f j;

    public e(boolean z, f fVar) {
        this.f1503a = z;
        this.j = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f1504b = fVar.d(allocate, 16);
        this.f1505c = fVar.b(allocate, 32);
        this.f1506d = fVar.b(allocate, 40);
        this.e = fVar.d(allocate, 54);
        this.f = fVar.d(allocate, 56);
        this.g = fVar.d(allocate, 58);
        this.h = fVar.d(allocate, 60);
        this.i = fVar.d(allocate, 62);
    }

    @Override // com.a.a.a.c.b
    public c.d a(int i) {
        return new j(this.j, this, i);
    }

    @Override // com.a.a.a.c.b
    public c.AbstractC0034c a(long j2) {
        return new h(this.j, this, j2);
    }

    @Override // com.a.a.a.c.b
    public c.a a(long j2, int i) {
        return new b(this.j, this, j2, i);
    }
}
