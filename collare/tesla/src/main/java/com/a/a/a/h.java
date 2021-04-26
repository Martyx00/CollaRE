package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Program64Header */
public class h extends c.AbstractC0034c {
    public h(f fVar, c.b bVar, long j) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.f1503a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.f1505c + (j * ((long) bVar.e));
        this.f1507a = fVar.c(allocate, j2);
        this.f1508b = fVar.b(allocate, 8 + j2);
        this.f1509c = fVar.b(allocate, 16 + j2);
        this.f1510d = fVar.b(allocate, j2 + 40);
    }
}
