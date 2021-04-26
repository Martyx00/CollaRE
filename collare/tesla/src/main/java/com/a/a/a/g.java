package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Program32Header */
public class g extends c.AbstractC0034c {
    public g(f fVar, c.b bVar, long j) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.f1503a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.f1505c + (j * ((long) bVar.e));
        this.f1507a = fVar.c(allocate, j2);
        this.f1508b = fVar.c(allocate, 4 + j2);
        this.f1509c = fVar.c(allocate, 8 + j2);
        this.f1510d = fVar.c(allocate, j2 + 20);
    }
}
