package ezvcard.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StringUtils */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5862a = System.getProperty("line.separator");

    public static String a(Collection<?> collection, String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : collection) {
            if (!z) {
                sb.append(str);
            }
            sb.append(obj);
            z = false;
        }
        return sb.toString();
    }

    public static Map<String, String> a(Map<String, String> map) {
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String str = null;
            String lowerCase = key == null ? null : key.toLowerCase();
            String value = entry.getValue();
            if (value != null) {
                str = value.toLowerCase();
            }
            hashMap.put(lowerCase, str);
        }
        return hashMap;
    }
}
