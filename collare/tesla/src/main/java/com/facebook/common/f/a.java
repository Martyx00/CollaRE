package com.facebook.common.f;

import android.webkit.MimeTypeMap;
import com.facebook.common.d.f;
import java.util.Locale;
import java.util.Map;

/* compiled from: MediaUtils */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Map<String, String> f1753a = f.a("mkv", "video/x-matroska", "glb", "model/gltf-binary");

    public static boolean a(String str) {
        return str != null && str.startsWith("video/");
    }

    public static String b(String str) {
        String c2 = c(str);
        if (c2 == null) {
            return null;
        }
        String lowerCase = c2.toLowerCase(Locale.US);
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase);
        return mimeTypeFromExtension == null ? f1753a.get(lowerCase) : mimeTypeFromExtension;
    }

    private static String c(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0 || lastIndexOf == str.length() - 1) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }
}
