package com.airbnb.android.react.maps;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.common.e.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/* compiled from: FileUtil */
public class l extends AsyncTask<String, Void, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private final String f1622a = "FileUtil";

    /* renamed from: b  reason: collision with root package name */
    private final String f1623b = "temp";

    /* renamed from: c  reason: collision with root package name */
    private Exception f1624c;

    /* renamed from: d  reason: collision with root package name */
    private Context f1625d;

    public l(Context context) {
        this.f1625d = context;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public InputStream doInBackground(String... strArr) {
        try {
            Uri parse = Uri.parse(strArr[0]);
            if (parse.getScheme().startsWith("http")) {
                return a(this.f1625d, parse);
            }
            return this.f1625d.getContentResolver().openInputStream(parse);
        } catch (Exception e) {
            this.f1624c = e;
            a.c("ReactNative", "Could not retrieve file for contentUri " + strArr[0], e);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    private InputStream a(Context context, Uri uri) {
        File createTempFile = File.createTempFile("FileUtil", "temp", context.getApplicationContext().getCacheDir());
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
}
