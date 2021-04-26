package com.teslamotors.plugins.ble.a;

import com.teslamotors.a.a;
import com.teslamotors.plugins.ble.b.i;
import java.util.LinkedList;

/* compiled from: VehicleInfo */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private a.ec f5390a = null;

    /* renamed from: b  reason: collision with root package name */
    private double f5391b = 0.0d;

    public void a(a.ec ecVar) {
        this.f5390a = ecVar;
    }

    public void a(double d2) {
        this.f5391b = d2;
    }

    public double a() {
        return this.f5391b;
    }

    public static String b(a.ec ecVar) {
        if (ecVar == null) {
            return "unknown";
        }
        LinkedList linkedList = new LinkedList();
        a.eb f = ecVar.f();
        if (f == null) {
            linkedList.add("unknown locked state");
        } else if (f == a.eb.VEHICLELOCKSTATE_LOCKED || f == a.eb.VEHICLELOCKSTATE_INTERNAL_LOCKED) {
            linkedList.add("locked");
        } else if (f == a.eb.VEHICLELOCKSTATE_SELECTIVE_UNLOCKED) {
            linkedList.add("selective-unlocked");
        } else {
            linkedList.add("unlocked");
        }
        a.m d2 = ecVar.d();
        if (d2 != null) {
            linkedList.add("open closures:");
            if (d2.d() == a.l.CLOSURESTATE_OPEN) {
                linkedList.add("df");
            }
            if (d2.h() == a.l.CLOSURESTATE_OPEN) {
                linkedList.add("dr");
            }
            if (d2.f() == a.l.CLOSURESTATE_OPEN) {
                linkedList.add("pf");
            }
            if (d2.j() == a.l.CLOSURESTATE_OPEN) {
                linkedList.add("pr");
            }
            if (d2.n() == a.l.CLOSURESTATE_OPEN) {
                linkedList.add("ft");
            }
            if (d2.l() == a.l.CLOSURESTATE_OPEN) {
                linkedList.add("rt");
            }
            if (d2.p() == a.l.CLOSURESTATE_OPEN) {
                linkedList.add("cp");
            }
        }
        return linkedList.toString();
    }

    public String b() {
        return b(this.f5390a);
    }

    public void a(i iVar) {
        a.ec ecVar = this.f5390a;
        if (ecVar != null && ecVar.d() != null && this.f5390a.f() != null) {
            a.m d2 = this.f5390a.d();
            iVar.a(a(d2.d()), a(d2.f()), a(d2.h()), a(d2.j()), a(d2.n()), a(d2.l()), a(d2.p()));
            a.eb f = this.f5390a.f();
            boolean z = false;
            iVar.h = f.equals(a.eb.VEHICLELOCKSTATE_LOCKED) || f.equals(a.eb.VEHICLELOCKSTATE_INTERNAL_LOCKED) || f.equals(a.eb.VEHICLELOCKSTATE_SELECTIVE_UNLOCKED);
            if (iVar.h && !f.equals(a.eb.VEHICLELOCKSTATE_SELECTIVE_UNLOCKED)) {
                z = true;
            }
            iVar.i = z;
        }
    }

    private static Boolean a(a.l lVar) {
        if (lVar == a.l.CLOSURESTATE_OPEN) {
            return true;
        }
        return lVar == a.l.CLOSURESTATE_CLOSED ? false : null;
    }
}
