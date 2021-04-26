package android.support.v4.content.a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.a.a;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: FontResourcesParserCompat */
public class c {

    /* compiled from: FontResourcesParserCompat */
    public interface a {
    }

    /* compiled from: FontResourcesParserCompat */
    public static final class d implements a {

        /* renamed from: a  reason: collision with root package name */
        private final android.support.v4.e.a f353a;

        /* renamed from: b  reason: collision with root package name */
        private final int f354b;

        /* renamed from: c  reason: collision with root package name */
        private final int f355c;

        public d(android.support.v4.e.a aVar, int i, int i2) {
            this.f353a = aVar;
            this.f355c = i;
            this.f354b = i2;
        }

        public android.support.v4.e.a a() {
            return this.f353a;
        }

        public int b() {
            return this.f355c;
        }

        public int c() {
            return this.f354b;
        }
    }

    /* renamed from: android.support.v4.content.a.c$c  reason: collision with other inner class name */
    /* compiled from: FontResourcesParserCompat */
    public static final class C0006c {

        /* renamed from: a  reason: collision with root package name */
        private final String f349a;

        /* renamed from: b  reason: collision with root package name */
        private int f350b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f351c;

        /* renamed from: d  reason: collision with root package name */
        private String f352d;
        private int e;
        private int f;

        public C0006c(String str, int i, boolean z, String str2, int i2, int i3) {
            this.f349a = str;
            this.f350b = i;
            this.f351c = z;
            this.f352d = str2;
            this.e = i2;
            this.f = i3;
        }

        public String a() {
            return this.f349a;
        }

        public int b() {
            return this.f350b;
        }

        public boolean c() {
            return this.f351c;
        }

        public String d() {
            return this.f352d;
        }

        public int e() {
            return this.e;
        }

        public int f() {
            return this.f;
        }
    }

    /* compiled from: FontResourcesParserCompat */
    public static final class b implements a {

        /* renamed from: a  reason: collision with root package name */
        private final C0006c[] f348a;

        public b(C0006c[] cVarArr) {
            this.f348a = cVarArr;
        }

        public C0006c[] a() {
            return this.f348a;
        }
    }

    public static a a(XmlPullParser xmlPullParser, Resources resources) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return b(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    private static a b(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return c(xmlPullParser, resources);
        }
        a(xmlPullParser);
        return null;
    }

    private static a c(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.c.FontFamily);
        String string = obtainAttributes.getString(a.c.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(a.c.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(a.c.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(a.c.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(a.c.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(a.c.FontFamily_fontProviderFetchTimeout, 500);
        obtainAttributes.recycle();
        if (string == null || string2 == null || string3 == null) {
            ArrayList arrayList = new ArrayList();
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("font")) {
                        arrayList.add(d(xmlPullParser, resources));
                    } else {
                        a(xmlPullParser);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return new b((C0006c[]) arrayList.toArray(new C0006c[arrayList.size()]));
        }
        while (xmlPullParser.next() != 3) {
            a(xmlPullParser);
        }
        return new d(new android.support.v4.e.a(string, string2, string3, a(resources, resourceId)), integer, integer2);
    }

    private static int a(TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i, typedValue);
        return typedValue.type;
    }

    public static List<List<byte[]>> a(Resources resources, int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(obtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
                    int resourceId = obtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        arrayList.add(a(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(a(resources.getStringArray(i)));
            }
            obtainTypedArray.recycle();
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    private static List<byte[]> a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }

    private static C0006c d(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.c.FontFamilyFont);
        int i = obtainAttributes.getInt(obtainAttributes.hasValue(a.c.FontFamilyFont_fontWeight) ? a.c.FontFamilyFont_fontWeight : a.c.FontFamilyFont_android_fontWeight, 400);
        boolean z = 1 == obtainAttributes.getInt(obtainAttributes.hasValue(a.c.FontFamilyFont_fontStyle) ? a.c.FontFamilyFont_fontStyle : a.c.FontFamilyFont_android_fontStyle, 0);
        int i2 = obtainAttributes.hasValue(a.c.FontFamilyFont_ttcIndex) ? a.c.FontFamilyFont_ttcIndex : a.c.FontFamilyFont_android_ttcIndex;
        String string = obtainAttributes.getString(obtainAttributes.hasValue(a.c.FontFamilyFont_fontVariationSettings) ? a.c.FontFamilyFont_fontVariationSettings : a.c.FontFamilyFont_android_fontVariationSettings);
        int i3 = obtainAttributes.getInt(i2, 0);
        int i4 = obtainAttributes.hasValue(a.c.FontFamilyFont_font) ? a.c.FontFamilyFont_font : a.c.FontFamilyFont_android_font;
        int resourceId = obtainAttributes.getResourceId(i4, 0);
        String string2 = obtainAttributes.getString(i4);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            a(xmlPullParser);
        }
        return new C0006c(string2, i, z, string, i3, resourceId);
    }

    private static void a(XmlPullParser xmlPullParser) {
        int i = 1;
        while (i > 0) {
            switch (xmlPullParser.next()) {
                case 2:
                    i++;
                    break;
                case 3:
                    i--;
                    break;
            }
        }
    }
}
