package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zaaq extends zaau {
    private final /* synthetic */ zaak zagj;
    private final ArrayList<Api.Client> zagp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaaq(zaak zaak, ArrayList<Api.Client> arrayList) {
        super(zaak, null);
        this.zagj = zaak;
        this.zagp = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaau
    public final void zaan() {
        this.zagj.zaft.zaee.zaha = this.zagj.zaat();
        ArrayList<Api.Client> arrayList = this.zagp;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Api.Client client = arrayList.get(i);
            i++;
            client.getRemoteService(this.zagj.zagf, this.zagj.zaft.zaee.zaha);
        }
    }
}
