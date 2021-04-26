package com.google.maps.android.a.b;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.a.d;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: KmlLayer */
public class f extends d {
    public f(GoogleMap googleMap, InputStream inputStream, Context context) {
        if (inputStream != null) {
            m mVar = new m(googleMap, context);
            i iVar = new i(a(inputStream));
            iVar.a();
            inputStream.close();
            mVar.b(iVar.b(), iVar.d(), iVar.c(), iVar.e(), iVar.f());
            a(mVar);
            return;
        }
        throw new IllegalArgumentException("KML InputStream cannot be null");
    }

    private static XmlPullParser a(InputStream inputStream) {
        XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
        newInstance.setNamespaceAware(true);
        XmlPullParser newPullParser = newInstance.newPullParser();
        newPullParser.setInput(inputStream, null);
        return newPullParser;
    }

    public void c() {
        super.a();
    }

    @Override // com.google.maps.android.a.d
    public Iterable<b> b() {
        return super.b();
    }
}
