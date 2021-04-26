package ezvcard.parameter;

import ezvcard.VCardVersion;
import ezvcard.c;

public class AddressType extends VCardParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final AddressType f5752a = new AddressType("home");

    /* renamed from: b  reason: collision with root package name */
    public static final AddressType f5753b = new AddressType("work");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})

    /* renamed from: c  reason: collision with root package name */
    public static final AddressType f5754c = new AddressType("dom");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})

    /* renamed from: d  reason: collision with root package name */
    public static final AddressType f5755d = new AddressType("intl");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
    public static final AddressType e = new AddressType("postal");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
    public static final AddressType f = new AddressType("parcel");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
    public static final AddressType g = new AddressType("pref");
    private static final d<AddressType> h = new d<>(AddressType.class);

    private AddressType(String str) {
        super(str);
    }

    public static AddressType a(String str) {
        return (AddressType) h.c(str);
    }
}
