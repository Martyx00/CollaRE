package android.support.v4.content.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.a.a;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: ColorStateListInflaterCompat */
public final class a {
    public static ColorStateList a(Resources resources, XmlPullParser xmlPullParser, Resources.Theme theme) {
        int next;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return a(resources, xmlPullParser, asAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static ColorStateList a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (name.equals("selector")) {
            return b(resources, xmlPullParser, attributeSet, theme);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
    }

    private static ColorStateList b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int depth;
        int i = 1;
        int depth2 = xmlPullParser.getDepth() + 1;
        int[][] iArr = new int[20][];
        int[] iArr2 = new int[iArr.length];
        int i2 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == i || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                int[] iArr3 = new int[i2];
                int[][] iArr4 = new int[i2][];
                System.arraycopy(iArr2, 0, iArr3, 0, i2);
                System.arraycopy(iArr, 0, iArr4, 0, i2);
            } else if (next != 2 || depth > depth2 || !xmlPullParser.getName().equals("item")) {
                i = 1;
            } else {
                TypedArray a2 = a(resources, theme, attributeSet, a.c.ColorStateListItem);
                int color = a2.getColor(a.c.ColorStateListItem_android_color, -65281);
                float f = 1.0f;
                if (a2.hasValue(a.c.ColorStateListItem_android_alpha)) {
                    f = a2.getFloat(a.c.ColorStateListItem_android_alpha, 1.0f);
                } else if (a2.hasValue(a.c.ColorStateListItem_alpha)) {
                    f = a2.getFloat(a.c.ColorStateListItem_alpha, 1.0f);
                }
                a2.recycle();
                int attributeCount = attributeSet.getAttributeCount();
                int[] iArr5 = new int[attributeCount];
                int i3 = 0;
                for (int i4 = 0; i4 < attributeCount; i4++) {
                    int attributeNameResource = attributeSet.getAttributeNameResource(i4);
                    if (!(attributeNameResource == 16843173 || attributeNameResource == 16843551 || attributeNameResource == a.C0003a.alpha)) {
                        int i5 = i3 + 1;
                        if (!attributeSet.getAttributeBooleanValue(i4, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        iArr5[i3] = attributeNameResource;
                        i3 = i5;
                    }
                }
                int[] trimStateSet = StateSet.trimStateSet(iArr5, i3);
                int a3 = a(color, f);
                if (i2 != 0) {
                    int length = trimStateSet.length;
                }
                iArr2 = e.a(iArr2, i2, a3);
                iArr = (int[][]) e.a(iArr, i2, trimStateSet);
                i2++;
                i = 1;
            }
        }
        int[] iArr32 = new int[i2];
        int[][] iArr42 = new int[i2][];
        System.arraycopy(iArr2, 0, iArr32, 0, i2);
        System.arraycopy(iArr, 0, iArr42, 0, i2);
        return new ColorStateList(iArr42, iArr32);
    }

    private static TypedArray a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    private static int a(int i, float f) {
        return (i & 16777215) | (Math.round(((float) Color.alpha(i)) * f) << 24);
    }
}
