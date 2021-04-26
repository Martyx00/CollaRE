package com.google.android.gms.common.api.internal;

final class zaai extends zabf {
    private final /* synthetic */ zaah zafv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaai(zaah zaah, zabd zabd) {
        super(zabd);
        this.zafv = zaah;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zaan() {
        this.zafv.onConnectionSuspended(1);
    }
}
