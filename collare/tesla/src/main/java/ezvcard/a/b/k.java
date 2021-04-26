package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.a;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.ClientPidMap;

/* compiled from: ClientPidMapScribe */
public class k extends bg<ClientPidMap> {
    public k() {
        super(ClientPidMap.class, "CLIENTPIDMAP");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public ClientPidMap b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        e.a aVar = new e.a(str, 2);
        String a2 = aVar.a();
        String a3 = aVar.a();
        if (a2 != null && a3 != null) {
            return a(a2, a3);
        }
        throw new a(3, new Object[0]);
    }

    private ClientPidMap a(String str, String str2) {
        try {
            return new ClientPidMap(Integer.valueOf(Integer.parseInt(str)), str2);
        } catch (NumberFormatException unused) {
            throw new a(4, new Object[0]);
        }
    }
}
