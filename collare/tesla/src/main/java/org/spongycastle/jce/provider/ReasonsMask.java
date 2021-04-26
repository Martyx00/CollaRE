package org.spongycastle.jce.provider;

import org.spongycastle.asn1.x509.ReasonFlags;

/* access modifiers changed from: package-private */
public class ReasonsMask {
    static final ReasonsMask allReasons = new ReasonsMask(33023);
    private int _reasons;

    ReasonsMask(ReasonFlags reasonFlags) {
        this._reasons = reasonFlags.intValue();
    }

    private ReasonsMask(int i) {
        this._reasons = i;
    }

    ReasonsMask() {
        this(0);
    }

    /* access modifiers changed from: package-private */
    public void addReasons(ReasonsMask reasonsMask) {
        this._reasons = reasonsMask.getReasons() | this._reasons;
    }

    /* access modifiers changed from: package-private */
    public boolean isAllReasons() {
        return this._reasons == allReasons._reasons;
    }

    /* access modifiers changed from: package-private */
    public ReasonsMask intersect(ReasonsMask reasonsMask) {
        ReasonsMask reasonsMask2 = new ReasonsMask();
        reasonsMask2.addReasons(new ReasonsMask(reasonsMask.getReasons() & this._reasons));
        return reasonsMask2;
    }

    /* access modifiers changed from: package-private */
    public boolean hasNewReasons(ReasonsMask reasonsMask) {
        return ((reasonsMask.getReasons() ^ this._reasons) | this._reasons) != 0;
    }

    /* access modifiers changed from: package-private */
    public int getReasons() {
        return this._reasons;
    }
}
