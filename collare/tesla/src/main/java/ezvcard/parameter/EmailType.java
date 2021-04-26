package ezvcard.parameter;

import ezvcard.VCardVersion;
import ezvcard.c;

public class EmailType extends VCardParameter {
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})

    /* renamed from: a  reason: collision with root package name */
    public static final EmailType f5758a = new EmailType("internet");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})

    /* renamed from: b  reason: collision with root package name */
    public static final EmailType f5759b = new EmailType("x400");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})

    /* renamed from: c  reason: collision with root package name */
    public static final EmailType f5760c = new EmailType("pref");
    @c(a = {VCardVersion.V2_1})

    /* renamed from: d  reason: collision with root package name */
    public static final EmailType f5761d = new EmailType("aol");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType e = new EmailType("applelink");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType f = new EmailType("attmail");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType g = new EmailType("cis");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType h = new EmailType("eworld");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType i = new EmailType("ibmmail");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType j = new EmailType("mcimail");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType k = new EmailType("powershare");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType l = new EmailType("prodigy");
    @c(a = {VCardVersion.V2_1})
    public static final EmailType m = new EmailType("tlx");
    @c(a = {VCardVersion.V4_0})
    public static final EmailType n = new EmailType("home");
    @c(a = {VCardVersion.V4_0})
    public static final EmailType o = new EmailType("work");
    private static final d<EmailType> p = new d<>(EmailType.class);

    private EmailType(String str) {
        super(str);
    }

    public static EmailType a(String str) {
        return (EmailType) p.c(str);
    }
}
