package com.a.a.a;

import com.a.a.a.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Dynamic64Structure */
public class b extends c.a {
    public b(f fVar, c.b bVar, long j, int i) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.f1503a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + ((long) (i * 16));
        this.f1501a = fVar.b(allocate, j2);
        this.f1502b = fVar.b(allocate, j2 + 8);
    }
}
