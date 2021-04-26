package com.RNFetchBlob.b;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.RNFetchBlob.h;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.webrtc.MediaStreamTrack;

/* compiled from: PathResolver */
public class a {
    @TargetApi(19)
    public static String a(Context context, Uri uri) {
        String a2;
        Uri uri2 = null;
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            if (FirebaseAnalytics.b.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (d(uri)) {
                    return uri.getLastPathSegment();
                }
                return a(context, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (a(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (b(uri)) {
            return a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else {
            if (c(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if (MediaStreamTrack.VIDEO_TRACK_KIND.equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (MediaStreamTrack.AUDIO_TRACK_KIND.equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return a(context, uri2, "_id=?", new String[]{split2[1]});
            } else if (!FirebaseAnalytics.b.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                try {
                    InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                    if (!(openInputStream == null || (a2 = a(context.getContentResolver(), uri)) == null)) {
                        File file = new File(context.getCacheDir(), a2);
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] bArr = new byte[1024];
                        while (openInputStream.read(bArr) > 0) {
                            fileOutputStream.write(bArr);
                        }
                        fileOutputStream.close();
                        openInputStream.close();
                        return file.getAbsolutePath();
                    }
                } catch (Exception e) {
                    h.b(e.toString());
                    return null;
                }
            } else if (d(uri)) {
                return uri.getLastPathSegment();
            } else {
                return a(context, uri, null, null);
            }
        }
        return null;
    }

    private static String a(ContentResolver contentResolver, Uri uri) {
        Cursor query = contentResolver.query(uri, null, null, null, null);
        query.moveToFirst();
        int columnIndex = query.getColumnIndex("_display_name");
        if (columnIndex < 0) {
            return null;
        }
        String string = query.getString(columnIndex);
        query.close();
        return string;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            if (r7 == 0) goto L_0x0028
            boolean r8 = r7.moveToFirst()     // Catch:{ Exception -> 0x0026 }
            if (r8 == 0) goto L_0x0028
            java.lang.String r8 = "_data"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ Exception -> 0x0026 }
            java.lang.String r0 = r7.getString(r8)     // Catch:{ Exception -> 0x0026 }
            goto L_0x0028
        L_0x0026:
            r8 = move-exception
            goto L_0x0033
        L_0x0028:
            if (r7 == 0) goto L_0x002d
            r7.close()
        L_0x002d:
            return r0
        L_0x002e:
            r8 = move-exception
            r7 = r0
            goto L_0x003d
        L_0x0031:
            r8 = move-exception
            r7 = r0
        L_0x0033:
            r8.printStackTrace()     // Catch:{ all -> 0x003c }
            if (r7 == 0) goto L_0x003b
            r7.close()
        L_0x003b:
            return r0
        L_0x003c:
            r8 = move-exception
        L_0x003d:
            if (r7 == 0) goto L_0x0042
            r7.close()
        L_0x0042:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.b.a.a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static boolean a(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean b(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean c(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean d(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
