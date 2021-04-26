package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Impp;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: ImppScribe */
public class w extends bg<Impp> {

    /* renamed from: a  reason: collision with root package name */
    private static final List<a> f5718a;

    public w() {
        super(Impp.class, "IMPP");
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return VCardDataType.f5692d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Impp b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        return a(e.a(str));
    }

    private Impp a(String str) {
        if (str == null || str.length() == 0) {
            return new Impp((URI) null);
        }
        try {
            return new Impp(str);
        } catch (IllegalArgumentException e) {
            throw new ezvcard.a.a(15, str, e.getMessage());
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new a("aim", "(goim|addbuddy)\\?.*?\\bscreenname=(.*?)(&|$)", 2, "goim?screenname=%s"));
        arrayList.add(new a("ymsgr", "(sendim|addfriend|sendfile|call)\\?(.*)", 2, "sendim?%s"));
        arrayList.add(new a("skype", "(.*?)(\\?|$)", 1, "%s"));
        arrayList.add(new a("msnim", "(chat|add|voice|video)\\?contact=(.*?)(&|$)", 2, "chat?contact=%s"));
        arrayList.add(new a("xmpp", "(.*?)(\\?|$)", 1, "%s?message"));
        arrayList.add(new a("icq", "message\\?uin=(\\d+)", 1, "message?uin=%s"));
        arrayList.add(new a("sip"));
        arrayList.add(new a("irc"));
        f5718a = Collections.unmodifiableList(arrayList);
    }

    /* compiled from: ImppScribe */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Pattern f5719a;

        /* renamed from: b  reason: collision with root package name */
        private final String f5720b;

        /* renamed from: c  reason: collision with root package name */
        private final int f5721c;

        /* renamed from: d  reason: collision with root package name */
        private final String f5722d;

        public a(String str) {
            this(str, "(.*)", 1, "%s");
        }

        public a(String str, String str2, int i, String str3) {
            this.f5719a = Pattern.compile('^' + str + ':' + str2, 2);
            this.f5720b = str;
            this.f5721c = i;
            this.f5722d = str + ':' + str3;
        }
    }
}
