package com.facebook.imagepipeline.n;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.facebook.common.g.h;
import com.facebook.common.k.f;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* compiled from: LocalContentUriFetchProducer */
public class x extends aa {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f2446a = {"_id", "_data"};

    /* renamed from: b  reason: collision with root package name */
    private final ContentResolver f2447b;

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public String a() {
        return "LocalContentUriFetchProducer";
    }

    public x(Executor executor, h hVar, ContentResolver contentResolver) {
        super(executor, hVar);
        this.f2447b = contentResolver;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public d a(b bVar) {
        d a2;
        InputStream inputStream;
        Uri b2 = bVar.b();
        if (f.e(b2)) {
            if (b2.toString().endsWith("/photo")) {
                inputStream = this.f2447b.openInputStream(b2);
            } else if (b2.toString().endsWith("/display_photo")) {
                try {
                    inputStream = this.f2447b.openAssetFileDescriptor(b2, "r").createInputStream();
                } catch (IOException unused) {
                    throw new IOException("Contact photo does not exist: " + b2);
                }
            } else {
                InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.f2447b, b2);
                if (openContactPhotoInputStream != null) {
                    inputStream = openContactPhotoInputStream;
                } else {
                    throw new IOException("Contact photo does not exist: " + b2);
                }
            }
            return b(inputStream, -1);
        } else if (!f.f(b2) || (a2 = a(b2)) == null) {
            return b(this.f2447b.openInputStream(b2), -1);
        } else {
            return a2;
        }
    }

    private d a(Uri uri) {
        Cursor query = this.f2447b.query(uri, f2446a, null, null, null);
        if (query == null) {
            return null;
        }
        try {
            if (query.getCount() == 0) {
                return null;
            }
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("_data"));
            if (string != null) {
                d b2 = b(new FileInputStream(string), a(string));
                query.close();
                return b2;
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    private static int a(String str) {
        if (str == null) {
            return -1;
        }
        return (int) new File(str).length();
    }
}
