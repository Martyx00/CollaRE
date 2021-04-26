package ezvcard.a.b;

import ezvcard.property.VCardProperty;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;

/* compiled from: ScribeIndex */
public class as {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, bg<? extends VCardProperty>> f5705a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private static final Map<Class<? extends VCardProperty>, bg<? extends VCardProperty>> f5706b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private static final Map<QName, bg<? extends VCardProperty>> f5707c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, bg<? extends VCardProperty>> f5708d = new HashMap(0);
    private final Map<Class<? extends VCardProperty>, bg<? extends VCardProperty>> e = new HashMap(0);
    private final Map<QName, bg<? extends VCardProperty>> f = new HashMap(0);

    static {
        a(new a());
        a(new b());
        a(new c());
        a(new e());
        a(new g());
        a(new h());
        a(new i());
        a(new j());
        a(new k());
        a(new o());
        a(new r());
        a(new q());
        a(new s());
        a(new t());
        a(new w());
        a(new y());
        a(new z());
        a(new aa());
        a(new ab());
        a(new ad());
        a(new ae());
        a(new af());
        a(new ag());
        a(new ah());
        a(new aj());
        a(new ak());
        a(new am());
        a(new an());
        a(new ap());
        a(new aq());
        a(new ar());
        a(new au());
        a(new av());
        a(new aw());
        a(new ax());
        a(new az());
        a(new ba());
        a(new bb());
        a(new bc());
        a(new bd());
        a(new bf());
        a(new bh());
        a(new f());
        a(new m());
        a(new n());
        a(new p());
        a(new ai());
        a(new x());
        a(new u());
    }

    public bg<? extends VCardProperty> a(String str) {
        String upperCase = str.toUpperCase();
        bg<? extends VCardProperty> bgVar = this.f5708d.get(upperCase);
        if (bgVar != null) {
            return bgVar;
        }
        return f5705a.get(upperCase);
    }

    private static void a(bg<? extends VCardProperty> bgVar) {
        f5705a.put(bgVar.d().toUpperCase(), bgVar);
        f5706b.put(bgVar.c(), bgVar);
        f5707c.put(bgVar.e(), bgVar);
    }
}
