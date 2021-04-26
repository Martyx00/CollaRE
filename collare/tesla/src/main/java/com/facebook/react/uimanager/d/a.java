package com.facebook.react.uimanager.d;

import android.view.View;
import com.facebook.react.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: ReactFindViewUtil */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final List<b> f3225a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private static final Map<AbstractC0057a, Set<String>> f3226b = new HashMap();

    /* renamed from: com.facebook.react.uimanager.d.a$a  reason: collision with other inner class name */
    /* compiled from: ReactFindViewUtil */
    public interface AbstractC0057a {
        void a(View view, String str);
    }

    /* compiled from: ReactFindViewUtil */
    public interface b {
        String a();

        void a(View view);
    }

    public static void a(View view) {
        String b2 = b(view);
        if (b2 != null) {
            Iterator<b> it = f3225a.iterator();
            while (it.hasNext()) {
                b next = it.next();
                if (b2 != null && b2.equals(next.a())) {
                    next.a(view);
                    it.remove();
                }
            }
            for (Map.Entry<AbstractC0057a, Set<String>> entry : f3226b.entrySet()) {
                Set<String> value = entry.getValue();
                if (value != null && value.contains(b2)) {
                    entry.getKey().a(view, b2);
                }
            }
        }
    }

    private static String b(View view) {
        Object tag = view.getTag(f.a.view_tag_native_id);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }
}
