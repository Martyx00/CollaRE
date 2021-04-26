package ezvcard.a.c;

import com.github.a.a.b.c;
import com.github.a.a.b.d;
import com.github.a.a.b.g;
import ezvcard.VCard;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.b.ao;
import ezvcard.a.b.bg;
import ezvcard.a.d;
import ezvcard.a.e;
import ezvcard.a.f;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Address;
import ezvcard.property.Label;
import ezvcard.property.VCardProperty;
import ezvcard.util.h;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: VCardReader */
public class a extends f {

    /* renamed from: d  reason: collision with root package name */
    private final com.github.a.a.b.f f5727d;
    private final VCardVersion e;

    public a(String str) {
        this(str, VCardVersion.V2_1);
    }

    public a(String str, VCardVersion vCardVersion) {
        this(new StringReader(str), vCardVersion);
    }

    public a(InputStream inputStream) {
        this(inputStream, VCardVersion.V2_1);
    }

    public a(InputStream inputStream, VCardVersion vCardVersion) {
        this(new InputStreamReader(inputStream), vCardVersion);
    }

    public a(File file) {
        this(file, VCardVersion.V2_1);
    }

    public a(File file, VCardVersion vCardVersion) {
        this(new BufferedReader(new FileReader(file)), vCardVersion);
    }

    public a(Reader reader) {
        this(reader, VCardVersion.V2_1);
    }

    public a(Reader reader, VCardVersion vCardVersion) {
        c b2 = c.b();
        b2.a(vCardVersion.getSyntaxStyle());
        this.f5727d = new com.github.a.a.b.f(reader, b2);
        this.e = vCardVersion;
    }

    public boolean d() {
        return this.f5727d.b();
    }

    public void a(boolean z) {
        this.f5727d.a(z);
    }

    public Charset e() {
        return this.f5727d.a();
    }

    public void a(Charset charset) {
        this.f5727d.a(charset);
    }

    /* access modifiers changed from: protected */
    @Override // ezvcard.a.f
    public VCard b() {
        b bVar = new b();
        this.f5727d.a(bVar);
        return bVar.f5732b;
    }

    /* compiled from: VCardReader */
    private class b implements d {

        /* renamed from: b  reason: collision with root package name */
        private VCard f5732b;

        /* renamed from: c  reason: collision with root package name */
        private final C0158a f5733c;

        /* renamed from: d  reason: collision with root package name */
        private ezvcard.a.b f5734d;

        private b() {
            this.f5733c = new C0158a();
        }

        @Override // com.github.a.a.b.d
        public void a(String str, com.github.a.a.b.b bVar) {
            if (a(str)) {
                VCard vCard = new VCard(a.this.e);
                if (this.f5733c.c()) {
                    this.f5732b = vCard;
                }
                this.f5733c.a(vCard);
                ezvcard.a.b bVar2 = this.f5734d;
                if (bVar2 != null) {
                    bVar2.a(vCard);
                    this.f5734d = null;
                }
            }
        }

        @Override // com.github.a.a.b.d
        public void b(String str, com.github.a.a.b.b bVar) {
            if (a(str)) {
                C0158a.C0159a a2 = this.f5733c.a();
                a.this.a(a2.f5729a, a2.f5730b);
                if (this.f5733c.c()) {
                    bVar.d();
                }
            }
        }

        @Override // com.github.a.a.b.d
        public void a(com.github.a.a.d dVar, com.github.a.a.b.b bVar) {
            if (a(bVar.a())) {
                ezvcard.a.b bVar2 = this.f5734d;
                if (bVar2 != null) {
                    bVar2.a(null);
                    this.f5734d = null;
                }
                VCard vCard = this.f5733c.b().f5729a;
                VCardProperty a2 = a(dVar, vCard.a(), bVar.c());
                if (a2 != null) {
                    vCard.a(a2);
                }
            }
        }

        private VCardProperty a(com.github.a.a.d dVar, VCardVersion vCardVersion, int i) {
            VCardProperty vCardProperty;
            String a2 = dVar.a();
            String b2 = dVar.b();
            VCardParameters vCardParameters = new VCardParameters(dVar.c().c());
            String d2 = dVar.d();
            a.this.f5745c.d().clear();
            a.this.f5745c.a(vCardVersion);
            a.this.f5745c.a(Integer.valueOf(i));
            a.this.f5745c.a(b2);
            a(vCardParameters);
            a(vCardParameters, vCardVersion);
            bg<? extends VCardProperty> a3 = a.this.f5744b.a(b2);
            if (a3 == null) {
                a3 = new ao(b2);
            }
            VCardDataType q = vCardParameters.q();
            vCardParameters.a((VCardDataType) null);
            VCardDataType b3 = q == null ? a3.b(vCardVersion) : q;
            try {
                vCardProperty = a3.c(d2, b3, vCardParameters, a.this.f5745c);
                a.this.f5743a.addAll(a.this.f5745c.d());
            } catch (e e) {
                a(b2, i, e);
                return null;
            } catch (ezvcard.a.a e2) {
                vCardProperty = a(b2, vCardParameters, d2, b3, i, vCardVersion, e2);
            } catch (ezvcard.a.b e3) {
                a(b2, d2, i, e3);
                vCardProperty = e3.a();
            }
            vCardProperty.setGroup(a2);
            if (vCardProperty instanceof Label) {
                this.f5733c.b().f5730b.add((Label) vCardProperty);
                return null;
            }
            a(vCardProperty);
            return vCardProperty;
        }

        private void a(String str, int i, e eVar) {
            a.this.f5743a.add(new d.a(a.this.f5745c).a(22, eVar.getMessage()).a());
        }

        private VCardProperty a(String str, VCardParameters vCardParameters, String str2, VCardDataType vCardDataType, int i, VCardVersion vCardVersion, ezvcard.a.a aVar) {
            a.this.f5743a.add(new d.a(a.this.f5745c).a(aVar).a());
            return new ao(str).c(str2, vCardDataType, vCardParameters, null);
        }

        private void a(String str, String str2, int i, ezvcard.a.b bVar) {
            if (str2.trim().length() == 0) {
                this.f5734d = bVar;
                return;
            }
            a aVar = new a(com.github.a.a.b.e.a(str2));
            aVar.a(a.this.d());
            aVar.a(a.this.e());
            aVar.a(a.this.f5744b);
            try {
                VCard a2 = aVar.a();
                if (a2 != null) {
                    bVar.a(a2);
                }
            } catch (IOException unused) {
            } catch (Throwable th) {
                a.this.f5743a.addAll(aVar.c());
                ezvcard.util.f.a(aVar);
                throw th;
            }
            a.this.f5743a.addAll(aVar.c());
            ezvcard.util.f.a(aVar);
        }

        private void a(VCardProperty vCardProperty) {
            Address address;
            String label;
            if ((vCardProperty instanceof Address) && (label = (address = (Address) vCardProperty).getLabel()) != null) {
                address.setLabel(label.replace("\\n", h.f5862a));
            }
        }

        @Override // com.github.a.a.b.d
        public void c(String str, com.github.a.a.b.b bVar) {
            VCardVersion valueOfByStr = VCardVersion.valueOfByStr(str);
            a.this.f5745c.a(valueOfByStr);
            this.f5733c.b().f5729a.a(valueOfByStr);
        }

        @Override // com.github.a.a.b.d
        public void a(g gVar, com.github.a.a.d dVar, Exception exc, com.github.a.a.b.b bVar) {
            String str;
            if (a(bVar.a())) {
                List list = a.this.f5743a;
                d.a a2 = new d.a(a.this.f5745c).a(Integer.valueOf(bVar.c()));
                if (dVar == null) {
                    str = null;
                } else {
                    str = dVar.b();
                }
                list.add(a2.a(str).a(27, gVar.a(), bVar.b()).a());
            }
        }

        private boolean a(List<String> list) {
            if (list.isEmpty()) {
                return false;
            }
            return a(list.get(list.size() - 1));
        }

        private boolean a(String str) {
            return "VCARD".equals(str);
        }

        private void a(VCardParameters vCardParameters) {
            for (String str : vCardParameters.d((Object) null)) {
                vCardParameters.a(b(str), str);
            }
        }

        private String b(String str) {
            if (VCardDataType.a(str) != null) {
                return "VALUE";
            }
            return ezvcard.parameter.a.a(str) != null ? "ENCODING" : "TYPE";
        }

        private void a(VCardParameters vCardParameters, VCardVersion vCardVersion) {
            if (vCardVersion != VCardVersion.V2_1) {
                List<String> n = vCardParameters.n();
                if (!n.isEmpty()) {
                    String str = null;
                    Iterator<String> it = n.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String next = it.next();
                        if (next.indexOf(44) >= 0) {
                            str = next;
                            break;
                        }
                    }
                    if (str != null) {
                        n.clear();
                        int i = -1;
                        while (true) {
                            int i2 = i + 1;
                            int indexOf = str.indexOf(44, i2);
                            if (indexOf >= 0) {
                                n.add(str.substring(i2, indexOf));
                                i = indexOf;
                            } else {
                                n.add(str.substring(i2));
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: ezvcard.a.c.a$a  reason: collision with other inner class name */
    /* compiled from: VCardReader */
    public static class C0158a {

        /* renamed from: a  reason: collision with root package name */
        private final List<C0159a> f5728a;

        private C0158a() {
            this.f5728a = new ArrayList();
        }

        public void a(VCard vCard) {
            this.f5728a.add(new C0159a(vCard, new ArrayList()));
        }

        public C0159a a() {
            if (c()) {
                return null;
            }
            List<C0159a> list = this.f5728a;
            return list.remove(list.size() - 1);
        }

        public C0159a b() {
            if (c()) {
                return null;
            }
            List<C0159a> list = this.f5728a;
            return list.get(list.size() - 1);
        }

        public boolean c() {
            return this.f5728a.isEmpty();
        }

        /* access modifiers changed from: private */
        /* renamed from: ezvcard.a.c.a$a$a  reason: collision with other inner class name */
        /* compiled from: VCardReader */
        public static class C0159a {

            /* renamed from: a  reason: collision with root package name */
            public final VCard f5729a;

            /* renamed from: b  reason: collision with root package name */
            public final List<Label> f5730b;

            public C0159a(VCard vCard, List<Label> list) {
                this.f5729a = vCard;
                this.f5730b = list;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5727d.close();
    }
}
