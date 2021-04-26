package com.facebook.common.k;

import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.net.MalformedURLException;
import java.net.URL;

/* compiled from: UriUtil */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final Uri f1792a = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo");

    public static URL a(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean b(Uri uri) {
        String k = k(uri);
        return "https".equals(k) || "http".equals(k);
    }

    public static boolean c(Uri uri) {
        return "file".equals(k(uri));
    }

    public static boolean d(Uri uri) {
        return FirebaseAnalytics.b.CONTENT.equals(k(uri));
    }

    public static boolean e(Uri uri) {
        return d(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith(f1792a.getPath());
    }

    public static boolean f(Uri uri) {
        String uri2 = uri.toString();
        return uri2.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()) || uri2.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());
    }

    public static boolean g(Uri uri) {
        return "asset".equals(k(uri));
    }

    public static boolean h(Uri uri) {
        return "res".equals(k(uri));
    }

    public static boolean i(Uri uri) {
        return "android.resource".equals(k(uri));
    }

    public static boolean j(Uri uri) {
        return "data".equals(k(uri));
    }

    public static String k(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getScheme();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.ContentResolver r8, android.net.Uri r9) {
        /*
            boolean r0 = d(r9)
            r1 = 0
            if (r0 == 0) goto L_0x0038
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r8
            r3 = r9
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0030 }
            if (r8 == 0) goto L_0x002a
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x0028 }
            if (r9 == 0) goto L_0x002a
            java.lang.String r9 = "_data"
            int r9 = r8.getColumnIndex(r9)     // Catch:{ all -> 0x0028 }
            r0 = -1
            if (r9 == r0) goto L_0x002a
            java.lang.String r9 = r8.getString(r9)     // Catch:{ all -> 0x0028 }
            r1 = r9
            goto L_0x002a
        L_0x0028:
            r9 = move-exception
            goto L_0x0032
        L_0x002a:
            if (r8 == 0) goto L_0x0042
            r8.close()
            goto L_0x0042
        L_0x0030:
            r9 = move-exception
            r8 = r1
        L_0x0032:
            if (r8 == 0) goto L_0x0037
            r8.close()
        L_0x0037:
            throw r9
        L_0x0038:
            boolean r8 = c(r9)
            if (r8 == 0) goto L_0x0042
            java.lang.String r1 = r9.getPath()
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.k.f.a(android.content.ContentResolver, android.net.Uri):java.lang.String");
    }

    public static Uri a(int i) {
        return new Uri.Builder().scheme("res").path(String.valueOf(i)).build();
    }
}
