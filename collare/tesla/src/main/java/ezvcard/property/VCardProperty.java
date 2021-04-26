package ezvcard.property;

import com.github.a.a.a;
import com.github.a.a.c.b;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.c;
import ezvcard.d;
import ezvcard.parameter.VCardParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class VCardProperty implements Comparable<VCardProperty> {
    protected String group;
    protected VCardParameters parameters;

    /* access modifiers changed from: protected */
    public void _validate(List<d> list, VCardVersion vCardVersion, VCard vCard) {
    }

    public VCardProperty() {
        this.parameters = new VCardParameters();
    }

    protected VCardProperty(VCardProperty vCardProperty) {
        this.group = vCardProperty.group;
        this.parameters = new VCardParameters(vCardProperty.parameters);
    }

    public final VCardVersion[] getSupportedVersions() {
        c cVar = (c) getClass().getAnnotation(c.class);
        return cVar == null ? VCardVersion.values() : cVar.a();
    }

    public final boolean isSupportedBy(VCardVersion vCardVersion) {
        for (VCardVersion vCardVersion2 : getSupportedVersions()) {
            if (vCardVersion2 == vCardVersion) {
                return true;
            }
        }
        return false;
    }

    public final List<d> validate(VCardVersion vCardVersion, VCard vCard) {
        ArrayList arrayList = new ArrayList(0);
        if (!isSupportedBy(vCardVersion)) {
            arrayList.add(new d(2, Arrays.toString(getSupportedVersions())));
        }
        arrayList.addAll(this.parameters.a(vCardVersion));
        if (this.group != null) {
            a syntaxStyle = vCardVersion.getSyntaxStyle();
            com.github.a.a.c.a a2 = b.a(syntaxStyle, true);
            if (!a2.a(this.group)) {
                if (syntaxStyle == a.OLD) {
                    arrayList.add(new d(32, this.group, a2.a().a(true)));
                } else {
                    arrayList.add(new d(23, this.group));
                }
            }
        }
        _validate(arrayList, vCardVersion, vCard);
        return arrayList;
    }

    public VCardParameters getParameters() {
        return this.parameters;
    }

    public void setParameters(VCardParameters vCardParameters) {
        if (vCardParameters != null) {
            this.parameters = vCardParameters;
            return;
        }
        throw new NullPointerException(ezvcard.b.INSTANCE.c(42, new Object[0]));
    }

    public String getParameter(String str) {
        return (String) this.parameters.c((Object) str);
    }

    public List<String> getParameters(String str) {
        return Collections.unmodifiableList(this.parameters.b((Object) str));
    }

    public void setParameter(String str, String str2) {
        this.parameters.b(str, str2);
    }

    public void addParameter(String str, String str2) {
        this.parameters.a(str, str2);
    }

    public void removeParameter(String str) {
        this.parameters.d((Object) str);
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public int compareTo(VCardProperty vCardProperty) {
        Integer l = getParameters().l();
        Integer l2 = vCardProperty.getParameters().l();
        if (l == null && l2 == null) {
            return 0;
        }
        if (l == null) {
            return 1;
        }
        if (l2 == null) {
            return -1;
        }
        return l2.compareTo(l);
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> toStringValues() {
        return Collections.emptyMap();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" [ group=");
        sb.append(this.group);
        sb.append(" | parameters=");
        sb.append(this.parameters);
        for (Map.Entry<String, Object> entry : toStringValues().entrySet()) {
            Object value = entry.getValue();
            sb.append(" | ");
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(value);
        }
        sb.append(" ]");
        return sb.toString();
    }

    public VCardProperty copy() {
        Class<?> cls = getClass();
        try {
            return (VCardProperty) cls.getConstructor(cls).newInstance(this);
        } catch (Exception e) {
            throw new UnsupportedOperationException(ezvcard.b.INSTANCE.c(31, cls.getName()), e);
        }
    }

    public int hashCode() {
        String str = this.group;
        return (((str == null ? 0 : str.toLowerCase().hashCode()) + 31) * 31) + this.parameters.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VCardProperty vCardProperty = (VCardProperty) obj;
        String str = this.group;
        if (str == null) {
            if (vCardProperty.group != null) {
                return false;
            }
        } else if (!str.equalsIgnoreCase(vCardProperty.group)) {
            return false;
        }
        return this.parameters.equals(vCardProperty.parameters);
    }

    /* access modifiers changed from: package-private */
    public List<ezvcard.parameter.c> getPids() {
        return this.parameters.k();
    }

    /* access modifiers changed from: package-private */
    public Integer getPref() {
        return this.parameters.l();
    }

    /* access modifiers changed from: package-private */
    public void setPref(Integer num) {
        this.parameters.b(num);
    }

    /* access modifiers changed from: package-private */
    public String getLanguage() {
        return this.parameters.h();
    }

    /* access modifiers changed from: package-private */
    public void setLanguage(String str) {
        this.parameters.c(str);
    }

    /* access modifiers changed from: package-private */
    public Integer getIndex() {
        return this.parameters.f();
    }

    /* access modifiers changed from: package-private */
    public void setIndex(Integer num) {
        this.parameters.a(num);
    }
}
