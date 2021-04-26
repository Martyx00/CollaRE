package ezvcard;

import ezvcard.property.VCardProperty;
import ezvcard.util.g;
import ezvcard.util.h;
import java.text.NumberFormat;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: ValidationWarnings */
public class e implements Iterable<Map.Entry<VCardProperty, List<d>>> {

    /* renamed from: a  reason: collision with root package name */
    private final g<VCardProperty, d> f5751a = new g<>(new IdentityHashMap());

    public void a(VCardProperty vCardProperty, d dVar) {
        this.f5751a.a(vCardProperty, dVar);
    }

    public void a(VCardProperty vCardProperty, List<d> list) {
        this.f5751a.a(vCardProperty, list);
    }

    public String toString() {
        NumberFormat integerInstance = NumberFormat.getIntegerInstance();
        integerInstance.setMinimumIntegerDigits(2);
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<VCardProperty, List<d>>> it = this.f5751a.iterator();
        while (it.hasNext()) {
            Map.Entry<VCardProperty, List<d>> next = it.next();
            VCardProperty key = next.getKey();
            for (d dVar : next.getValue()) {
                if (key != null) {
                    sb.append('[');
                    sb.append(key.getClass().getSimpleName());
                    sb.append("] | ");
                }
                Integer a2 = dVar.a();
                if (a2 != null) {
                    sb.append('W');
                    sb.append(integerInstance.format(a2));
                    sb.append(": ");
                }
                sb.append(dVar.b());
                sb.append(h.f5862a);
            }
        }
        return sb.toString();
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<VCardProperty, List<d>>> iterator() {
        return this.f5751a.iterator();
    }
}
