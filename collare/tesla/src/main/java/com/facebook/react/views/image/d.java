package com.facebook.react.views.image;

import android.graphics.Shader;
import com.facebook.f.e.q;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;

/* compiled from: ImageResizeMode */
public class d {
    public static q.b a(String str) {
        if ("contain".equals(str)) {
            return q.b.f1945c;
        }
        if ("cover".equals(str)) {
            return q.b.g;
        }
        if ("stretch".equals(str)) {
            return q.b.f1943a;
        }
        if ("center".equals(str)) {
            return q.b.f;
        }
        if ("repeat".equals(str)) {
            return g.j;
        }
        if (str == null) {
            return a();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    public static Shader.TileMode b(String str) {
        if ("contain".equals(str) || "cover".equals(str) || "stretch".equals(str) || "center".equals(str)) {
            return Shader.TileMode.CLAMP;
        }
        if ("repeat".equals(str)) {
            return Shader.TileMode.REPEAT;
        }
        if (str == null) {
            return b();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    public static q.b a() {
        return q.b.g;
    }

    public static Shader.TileMode b() {
        return Shader.TileMode.CLAMP;
    }
}
