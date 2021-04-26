package ezvcard.parameter;

import ezvcard.VCardVersion;
import ezvcard.c;
import org.spongycastle.i18n.TextBundle;
import org.webrtc.MediaStreamTrack;

public class TelephoneType extends VCardParameter {
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})

    /* renamed from: a  reason: collision with root package name */
    public static final TelephoneType f5792a = new TelephoneType("bbs");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})

    /* renamed from: b  reason: collision with root package name */
    public static final TelephoneType f5793b = new TelephoneType("car");

    /* renamed from: c  reason: collision with root package name */
    public static final TelephoneType f5794c = new TelephoneType("cell");

    /* renamed from: d  reason: collision with root package name */
    public static final TelephoneType f5795d = new TelephoneType("fax");
    public static final TelephoneType e = new TelephoneType("home");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
    public static final TelephoneType f = new TelephoneType("isdn");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
    public static final TelephoneType g = new TelephoneType("modem");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
    public static final TelephoneType h = new TelephoneType("msg");
    public static final TelephoneType i = new TelephoneType("pager");
    @c(a = {VCardVersion.V3_0})
    public static final TelephoneType j = new TelephoneType("pcs");
    @c(a = {VCardVersion.V2_1, VCardVersion.V3_0})
    public static final TelephoneType k = new TelephoneType("pref");
    @c(a = {VCardVersion.V4_0})
    public static final TelephoneType l = new TelephoneType(TextBundle.TEXT_ENTRY);
    @c(a = {VCardVersion.V4_0})
    public static final TelephoneType m = new TelephoneType("textphone");
    public static final TelephoneType n = new TelephoneType(MediaStreamTrack.VIDEO_TRACK_KIND);
    public static final TelephoneType o = new TelephoneType("voice");
    public static final TelephoneType p = new TelephoneType("work");
    private static final d<TelephoneType> q = new d<>(TelephoneType.class);

    private TelephoneType(String str) {
        super(str);
    }

    public static TelephoneType a(String str) {
        return (TelephoneType) q.c(str);
    }
}
