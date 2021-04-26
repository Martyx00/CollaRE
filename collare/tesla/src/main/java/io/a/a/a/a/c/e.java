package io.a.a.a.a.c;

/* compiled from: Priority */
public enum e {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE;

    static <Y> int a(i iVar, Y y) {
        e eVar;
        if (y instanceof i) {
            eVar = y.getPriority();
        } else {
            eVar = NORMAL;
        }
        return eVar.ordinal() - iVar.getPriority().ordinal();
    }
}
