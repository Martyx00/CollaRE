package com.facebook.imagepipeline.n;

import android.net.Uri;
import android.util.Base64;
import com.facebook.common.b.a;
import com.facebook.common.d.i;
import com.facebook.common.g.h;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;
import java.io.ByteArrayInputStream;

/* compiled from: DataFetchProducer */
public class l extends aa {
    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public String a() {
        return "DataFetchProducer";
    }

    public l(h hVar) {
        super(a.a(), hVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.aa
    public d a(b bVar) {
        byte[] a2 = a(bVar.b().toString());
        return a(new ByteArrayInputStream(a2), a2.length);
    }

    static byte[] a(String str) {
        i.a(str.substring(0, 5).equals("data:"));
        int indexOf = str.indexOf(44);
        String substring = str.substring(indexOf + 1, str.length());
        if (b(str.substring(0, indexOf))) {
            return Base64.decode(substring, 0);
        }
        return Uri.decode(substring).getBytes();
    }

    static boolean b(String str) {
        if (!str.contains(";")) {
            return false;
        }
        String[] split = str.split(";");
        return split[split.length - 1].equals("base64");
    }
}
