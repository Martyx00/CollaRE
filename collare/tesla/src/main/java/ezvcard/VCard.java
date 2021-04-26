package ezvcard;

import ezvcard.property.Address;
import ezvcard.property.Email;
import ezvcard.property.FormattedName;
import ezvcard.property.Kind;
import ezvcard.property.Label;
import ezvcard.property.Organization;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import ezvcard.property.VCardProperty;
import ezvcard.util.g;
import ezvcard.util.h;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VCard implements Iterable<VCardProperty> {

    /* renamed from: a  reason: collision with root package name */
    private VCardVersion f5684a;

    /* renamed from: b  reason: collision with root package name */
    private final g<Class<? extends VCardProperty>, VCardProperty> f5685b;

    public VCard() {
        this(VCardVersion.V3_0);
    }

    public VCard(VCardVersion vCardVersion) {
        this.f5685b = new g<>();
        this.f5684a = vCardVersion;
    }

    public VCard(VCard vCard) {
        this.f5685b = new g<>();
        this.f5684a = vCard.f5684a;
        for (VCardProperty vCardProperty : vCard.i()) {
            a(vCardProperty.copy());
        }
    }

    public VCardVersion a() {
        return this.f5684a;
    }

    public void a(VCardVersion vCardVersion) {
        this.f5684a = vCardVersion;
    }

    public Kind b() {
        return (Kind) a(Kind.class);
    }

    public FormattedName c() {
        return (FormattedName) a(FormattedName.class);
    }

    public StructuredName d() {
        return (StructuredName) a(StructuredName.class);
    }

    public List<Address> e() {
        return b(Address.class);
    }

    public void a(Label label) {
        a((VCardProperty) label);
    }

    public List<Email> f() {
        return b(Email.class);
    }

    public List<Telephone> g() {
        return b(Telephone.class);
    }

    public Organization h() {
        return (Organization) a(Organization.class);
    }

    @Override // java.lang.Iterable
    public Iterator<VCardProperty> iterator() {
        return this.f5685b.r().iterator();
    }

    public <T extends VCardProperty> T a(Class<T> cls) {
        return cls.cast(this.f5685b.c(cls));
    }

    public <T extends VCardProperty> List<T> b(Class<T> cls) {
        return new a(cls);
    }

    public Collection<VCardProperty> i() {
        return this.f5685b.r();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: ezvcard.util.g<java.lang.Class<? extends ezvcard.property.VCardProperty>, ezvcard.property.VCardProperty> */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(VCardProperty vCardProperty) {
        this.f5685b.a((Class<? extends VCardProperty>) vCardProperty.getClass(), vCardProperty);
    }

    public e b(VCardVersion vCardVersion) {
        e eVar = new e();
        if (d() == null && (vCardVersion == VCardVersion.V2_1 || vCardVersion == VCardVersion.V3_0)) {
            eVar.a((VCardProperty) null, new d(0, new Object[0]));
        }
        if (c() == null && (vCardVersion == VCardVersion.V3_0 || vCardVersion == VCardVersion.V4_0)) {
            eVar.a((VCardProperty) null, new d(1, new Object[0]));
        }
        Iterator<VCardProperty> it = iterator();
        while (it.hasNext()) {
            VCardProperty next = it.next();
            List<d> validate = next.validate(vCardVersion, this);
            if (!validate.isEmpty()) {
                eVar.a(next, validate);
            }
        }
        return eVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("version=");
        sb.append(this.f5684a);
        for (VCardProperty vCardProperty : this.f5685b.r()) {
            sb.append(h.f5862a);
            sb.append(vCardProperty);
        }
        return sb.toString();
    }

    public int hashCode() {
        VCardVersion vCardVersion = this.f5684a;
        int hashCode = (vCardVersion == null ? 0 : vCardVersion.hashCode()) + 31;
        int i = 1;
        for (VCardProperty vCardProperty : this.f5685b.r()) {
            i += vCardProperty.hashCode();
        }
        return (hashCode * 31) + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VCard vCard = (VCard) obj;
        if (this.f5684a != vCard.f5684a || this.f5685b.s() != vCard.f5685b.s()) {
            return false;
        }
        Iterator<Map.Entry<Class<? extends VCardProperty>, List<VCardProperty>>> it = this.f5685b.iterator();
        while (it.hasNext()) {
            Map.Entry<Class<? extends VCardProperty>, List<VCardProperty>> next = it.next();
            List<VCardProperty> value = next.getValue();
            List<VCardProperty> b2 = vCard.f5685b.b(next.getKey());
            if (value.size() != b2.size()) {
                return false;
            }
            ArrayList arrayList = new ArrayList(b2);
            Iterator<VCardProperty> it2 = value.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (!arrayList.remove(it2.next())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public class a<T extends VCardProperty> extends AbstractList<T> {

        /* renamed from: a  reason: collision with root package name */
        protected final Class<T> f5686a;

        /* renamed from: b  reason: collision with root package name */
        protected final List<VCardProperty> f5687b;

        public a(Class<T> cls) {
            this.f5686a = cls;
            this.f5687b = VCard.this.f5685b.b(cls);
        }

        /* renamed from: a */
        public void add(int i, T t) {
            this.f5687b.add(i, t);
        }

        /* renamed from: a */
        public T remove(int i) {
            return a(this.f5687b.remove(i));
        }

        /* renamed from: b */
        public T get(int i) {
            return a(this.f5687b.get(i));
        }

        /* renamed from: b */
        public T set(int i, T t) {
            return a(this.f5687b.set(i, t));
        }

        public int size() {
            return this.f5687b.size();
        }

        private T a(VCardProperty vCardProperty) {
            return this.f5686a.cast(vCardProperty);
        }
    }
}
