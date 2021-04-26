package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Section32Header */
public class i extends c.d {
    public i(f fVar, c.b bVar, int i) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.f1503a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f1511a = fVar.c(allocate, bVar.f1506d + ((long) (i * bVar.g)) + 28);
    }
}
