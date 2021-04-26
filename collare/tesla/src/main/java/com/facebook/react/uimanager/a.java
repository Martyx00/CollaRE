package com.facebook.react.uimanager;

import android.content.Context;
import android.support.v4.g.a.a;
import android.support.v4.g.b;
import android.support.v4.g.t;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.view.View;
import com.facebook.react.f;
import java.util.Locale;

/* compiled from: AccessibilityDelegateUtil */
public class a {

    /* renamed from: com.facebook.react.uimanager.a$a  reason: collision with other inner class name */
    /* compiled from: AccessibilityDelegateUtil */
    public enum EnumC0056a {
        NONE,
        BUTTON,
        LINK,
        SEARCH,
        IMAGE,
        IMAGEBUTTON,
        KEYBOARDKEY,
        TEXT,
        ADJUSTABLE,
        SUMMARY,
        HEADER;

        public static String a(EnumC0056a aVar) {
            switch (aVar) {
                case NONE:
                    return null;
                case BUTTON:
                    return "android.widget.Button";
                case LINK:
                    return "android.widget.ViewGroup";
                case SEARCH:
                    return "android.widget.EditText";
                case IMAGE:
                    return "android.widget.ImageView";
                case IMAGEBUTTON:
                    return "android.widget.ImageView";
                case KEYBOARDKEY:
                    return "android.inputmethodservice.Keyboard$Key";
                case TEXT:
                    return "android.widget.ViewGroup";
                case ADJUSTABLE:
                    return "android.widget.SeekBar";
                case SUMMARY:
                    return "android.widget.ViewGroup";
                case HEADER:
                    return "android.widget.ViewGroup";
                default:
                    throw new IllegalArgumentException("Invalid accessibility role value: " + aVar);
            }
        }

        public static EnumC0056a a(String str) {
            EnumC0056a[] values = values();
            for (EnumC0056a aVar : values) {
                if (aVar.name().equalsIgnoreCase(str)) {
                    return aVar;
                }
            }
            throw new IllegalArgumentException("Invalid accessibility role value: " + str);
        }
    }

    public static void a(final View view) {
        final String str = (String) view.getTag(f.a.accessibility_hint);
        final EnumC0056a aVar = (EnumC0056a) view.getTag(f.a.accessibility_role);
        if (t.a(view)) {
            return;
        }
        if (str != null || aVar != null) {
            t.a(view, new b() {
                /* class com.facebook.react.uimanager.a.AnonymousClass1 */

                @Override // android.support.v4.g.b
                public void a(View view, android.support.v4.g.a.a aVar) {
                    super.a(view, aVar);
                    if (str != null) {
                        String str = (String) aVar.r();
                        if (str != null) {
                            aVar.d(str + ", " + str);
                        } else {
                            aVar.d(str);
                        }
                    }
                    a.a(aVar, aVar, view.getContext());
                }
            });
        }
    }

    public static void a(android.support.v4.g.a.a aVar, EnumC0056a aVar2, Context context) {
        if (aVar2 == null) {
            aVar2 = EnumC0056a.NONE;
        }
        aVar.b(EnumC0056a.a(aVar2));
        if (Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
            if (aVar2.equals(EnumC0056a.LINK)) {
                aVar.e(context.getString(f.b.link_description));
                if (aVar.r() != null) {
                    SpannableString spannableString = new SpannableString(aVar.r());
                    spannableString.setSpan(new URLSpan(""), 0, spannableString.length(), 0);
                    aVar.d(spannableString);
                }
                if (aVar.q() != null) {
                    SpannableString spannableString2 = new SpannableString(aVar.q());
                    spannableString2.setSpan(new URLSpan(""), 0, spannableString2.length(), 0);
                    aVar.c(spannableString2);
                }
            }
            if (aVar2.equals(EnumC0056a.SEARCH)) {
                aVar.e(context.getString(f.b.search_description));
            }
            if (aVar2.equals(EnumC0056a.IMAGE)) {
                aVar.e(context.getString(f.b.image_description));
            }
            if (aVar2.equals(EnumC0056a.IMAGEBUTTON)) {
                aVar.e(context.getString(f.b.image_button_description));
            }
            if (aVar2.equals(EnumC0056a.ADJUSTABLE)) {
                aVar.e(context.getString(f.b.adjustable_description));
            }
            if (aVar2.equals(EnumC0056a.HEADER)) {
                aVar.e(context.getString(f.b.header_description));
                aVar.a(a.b.a(0, 1, 0, 1, true));
            }
        }
        if (aVar2.equals(EnumC0056a.IMAGEBUTTON)) {
            aVar.f(true);
        }
    }
}
