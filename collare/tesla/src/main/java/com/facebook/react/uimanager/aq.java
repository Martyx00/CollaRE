package com.facebook.react.uimanager;

import java.util.Comparator;

/* compiled from: ViewAtIndex */
public class aq {

    /* renamed from: a  reason: collision with root package name */
    public static Comparator<aq> f3158a = new Comparator<aq>() {
        /* class com.facebook.react.uimanager.aq.AnonymousClass1 */

        /* renamed from: a */
        public int compare(aq aqVar, aq aqVar2) {
            return aqVar.f3160c - aqVar2.f3160c;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    public final int f3159b;

    /* renamed from: c  reason: collision with root package name */
    public final int f3160c;

    public aq(int i, int i2) {
        this.f3159b = i;
        this.f3160c = i2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        aq aqVar = (aq) obj;
        if (this.f3160c == aqVar.f3160c && this.f3159b == aqVar.f3159b) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "[" + this.f3159b + ", " + this.f3160c + "]";
    }
}
