package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Dynamic32Structure */
public class a extends c.a {
    public a(f fVar, c.b bVar, long j, int i) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.f1503a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + ((long) (i * 8));
        this.f1501a = fVar.c(allocate, j2);
        this.f1502b = fVar.c(allocate, j2 + 4);
    }
}
