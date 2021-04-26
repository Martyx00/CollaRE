package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.devsupport.a.b;
import java.util.Map;

/* compiled from: DevSupportManagerFactory */
public class a {
    public static b a(Context context, d dVar, String str, boolean z, e eVar, com.facebook.react.devsupport.a.a aVar, int i, Map<String, Object> map) {
        if (!z) {
            return new b();
        }
        try {
            return (b) Class.forName("com.facebook.react.devsupport" + "." + "DevSupportManagerImpl").getConstructor(Context.class, d.class, String.class, Boolean.TYPE, e.class, com.facebook.react.devsupport.a.a.class, Integer.TYPE, Map.class).newInstance(context, dVar, str, true, eVar, aVar, Integer.valueOf(i), map);
        } catch (Exception e) {
            throw new RuntimeException("Requested enabled DevSupportManager, but DevSupportManagerImpl class was not found or could not be created", e);
        }
    }
}
