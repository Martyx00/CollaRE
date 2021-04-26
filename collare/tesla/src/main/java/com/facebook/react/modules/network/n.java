package com.facebook.react.modules.network;

import android.content.Context;
import android.net.Uri;
import c.d;
import c.f;
import c.l;
import c.t;
import com.facebook.common.e.a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.GZIPOutputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

/* access modifiers changed from: package-private */
/* compiled from: RequestBodyUtil */
public class n {
    public static boolean a(String str) {
        return "gzip".equalsIgnoreCase(str);
    }

    public static InputStream a(Context context, String str) {
        try {
            Uri parse = Uri.parse(str);
            if (parse.getScheme().startsWith("http")) {
                return a(context, parse);
            }
            return context.getContentResolver().openInputStream(parse);
        } catch (Exception e) {
            a.c("ReactNative", "Could not retrieve file for contentUri " + str, e);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    private static InputStream a(Context context, Uri uri) {
        File createTempFile = File.createTempFile("RequestBodyUtil", "temp", context.getApplicationContext().getCacheDir());
        createTempFile.deleteOnExit();
        InputStream openStream = new URL(uri.toString()).openStream();
        try {
            ReadableByteChannel newChannel = Channels.newChannel(openStream);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                try {
                    fileOutputStream.getChannel().transferFrom(newChannel, 0, Long.MAX_VALUE);
                    FileInputStream fileInputStream = new FileInputStream(createTempFile);
                    fileOutputStream.close();
                    newChannel.close();
                    return fileInputStream;
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } catch (Throwable th2) {
                newChannel.close();
                throw th2;
            }
        } finally {
            openStream.close();
        }
    }

    public static RequestBody a(MediaType mediaType, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            return RequestBody.create(mediaType, byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            return null;
        }
    }

    public static RequestBody a(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() {
            /* class com.facebook.react.modules.network.n.AnonymousClass1 */

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                try {
                    return (long) inputStream.available();
                } catch (IOException unused) {
                    return 0;
                }
            }

            @Override // okhttp3.RequestBody
            public void writeTo(d dVar) {
                t tVar = null;
                try {
                    tVar = l.a(inputStream);
                    dVar.a(tVar);
                } finally {
                    Util.closeQuietly(tVar);
                }
            }
        };
    }

    public static j a(RequestBody requestBody, i iVar) {
        return new j(requestBody, iVar);
    }

    public static RequestBody b(String str) {
        if (str.equals("POST") || str.equals("PUT") || str.equals("PATCH")) {
            return RequestBody.create((MediaType) null, f.f1330b);
        }
        return null;
    }
}
