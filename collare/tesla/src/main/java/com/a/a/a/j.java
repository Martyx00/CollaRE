package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Section64Header */
public class j extends c.d {
    public j(f fVar, c.b bVar, int i) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.f1503a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f1511a = fVar.c(allocate, bVar.f1506d + ((long) (i * bVar.g)) + 44);
    }
}
