package ezvcard.a;

import ezvcard.VCard;
import ezvcard.property.VCardProperty;

/* compiled from: EmbeddedVCardException */
public class b extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    private final VCard f5703a = null;

    /* renamed from: b  reason: collision with root package name */
    private final a f5704b;

    /* compiled from: EmbeddedVCardException */
    public interface a {
        VCardProperty a();

        void a(VCard vCard);
    }

    public b(a aVar) {
        this.f5704b = aVar;
    }

    public void a(VCard vCard) {
        a aVar = this.f5704b;
        if (aVar != null) {
            aVar.a(vCard);
        }
    }

    public VCardProperty a() {
        a aVar = this.f5704b;
        if (aVar == null) {
            return null;
        }
        return aVar.a();
    }
}
