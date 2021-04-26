package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Organization;

/* compiled from: OrganizationScribe */
public class aj extends bg<Organization> {
    public aj() {
        super(Organization.class, "ORG");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Organization b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        Organization organization = new Organization();
        organization.getValues().addAll(e.c(str));
        return organization;
    }
}
