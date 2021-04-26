package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.a.a;
import android.support.v7.view.d;
import android.support.v7.widget.as;
import android.support.v7.widget.e;
import android.support.v7.widget.g;
import android.support.v7.widget.h;
import android.support.v7.widget.i;
import android.support.v7.widget.l;
import android.support.v7.widget.n;
import android.support.v7.widget.p;
import android.support.v7.widget.q;
import android.support.v7.widget.t;
import android.support.v7.widget.u;
import android.support.v7.widget.v;
import android.support.v7.widget.x;
import android.support.v7.widget.z;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class AppCompatViewInflater {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?>[] f800a = {Context.class, AttributeSet.class};

    /* renamed from: b  reason: collision with root package name */
    private static final int[] f801b = {16843375};

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f802c = {"android.widget.", "android.view.", "android.webkit."};

    /* renamed from: d  reason: collision with root package name */
    private static final Map<String, Constructor<? extends View>> f803d = new android.support.v4.util.a();
    private final Object[] e = new Object[2];

    /* access modifiers changed from: protected */
    public View a(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public final View a(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        View view2;
        Context context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            context2 = a(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = as.a(context2);
        }
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c2 = 11;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c2 = 0;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c2 = 5;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c2 = '\f';
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c2 = 4;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c2 = 7;
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c2 = 3;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                view2 = a(context2, attributeSet);
                a(view2, str);
                break;
            case 1:
                view2 = b(context2, attributeSet);
                a(view2, str);
                break;
            case 2:
                view2 = c(context2, attributeSet);
                a(view2, str);
                break;
            case 3:
                view2 = d(context2, attributeSet);
                a(view2, str);
                break;
            case 4:
                view2 = e(context2, attributeSet);
                a(view2, str);
                break;
            case 5:
                view2 = f(context2, attributeSet);
                a(view2, str);
                break;
            case 6:
                view2 = g(context2, attributeSet);
                a(view2, str);
                break;
            case 7:
                view2 = h(context2, attributeSet);
                a(view2, str);
                break;
            case '\b':
                view2 = i(context2, attributeSet);
                a(view2, str);
                break;
            case '\t':
                view2 = j(context2, attributeSet);
                a(view2, str);
                break;
            case '\n':
                view2 = k(context2, attributeSet);
                a(view2, str);
                break;
            case 11:
                view2 = l(context2, attributeSet);
                a(view2, str);
                break;
            case '\f':
                view2 = m(context2, attributeSet);
                a(view2, str);
                break;
            default:
                view2 = a(context2, str, attributeSet);
                break;
        }
        if (view2 == null && context != context2) {
            view2 = b(context2, str, attributeSet);
        }
        if (view2 != null) {
            a(view2, attributeSet);
        }
        return view2;
    }

    /* access modifiers changed from: protected */
    public z a(Context context, AttributeSet attributeSet) {
        return new z(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public p b(Context context, AttributeSet attributeSet) {
        return new p(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public g c(Context context, AttributeSet attributeSet) {
        return new g(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public l d(Context context, AttributeSet attributeSet) {
        return new l(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public x e(Context context, AttributeSet attributeSet) {
        return new x(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public n f(Context context, AttributeSet attributeSet) {
        return new n(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public h g(Context context, AttributeSet attributeSet) {
        return new h(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public t h(Context context, AttributeSet attributeSet) {
        return new t(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public i i(Context context, AttributeSet attributeSet) {
        return new i(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public e j(Context context, AttributeSet attributeSet) {
        return new e(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public q k(Context context, AttributeSet attributeSet) {
        return new q(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public u l(Context context, AttributeSet attributeSet) {
        return new u(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public v m(Context context, AttributeSet attributeSet) {
        return new v(context, attributeSet);
    }

    private void a(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }

    private View b(Context context, String str, AttributeSet attributeSet) {
        if (str.equals(Promotion.ACTION_VIEW)) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.e[0] = context;
            this.e[1] = attributeSet;
            if (-1 == str.indexOf(46)) {
                for (int i = 0; i < f802c.length; i++) {
                    View a2 = a(context, str, f802c[i]);
                    if (a2 != null) {
                        return a2;
                    }
                }
                Object[] objArr = this.e;
                objArr[0] = null;
                objArr[1] = null;
                return null;
            }
            View a3 = a(context, str, (String) null);
            Object[] objArr2 = this.e;
            objArr2[0] = null;
            objArr2[1] = null;
            return a3;
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr3 = this.e;
            objArr3[0] = null;
            objArr3[1] = null;
        }
    }

    private void a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (!(context instanceof ContextWrapper)) {
            return;
        }
        if (Build.VERSION.SDK_INT < 15 || android.support.v4.g.t.v(view)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f801b);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new a(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    private View a(Context context, String str, String str2) {
        String str3;
        Constructor<? extends View> constructor = f803d.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(f800a);
                f803d.put(str, constructor);
            } catch (Exception unused) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.e);
    }

    private static Context a(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.View, 0, 0);
        int resourceId = z ? obtainStyledAttributes.getResourceId(a.j.View_android_theme, 0) : 0;
        if (z2 && resourceId == 0 && (resourceId = obtainStyledAttributes.getResourceId(a.j.View_theme, 0)) != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        obtainStyledAttributes.recycle();
        if (resourceId != 0) {
            return (!(context instanceof d) || ((d) context).a() != resourceId) ? new d(context, resourceId) : context;
        }
        return context;
    }

    /* access modifiers changed from: private */
    public static class a implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        private final View f804a;

        /* renamed from: b  reason: collision with root package name */
        private final String f805b;

        /* renamed from: c  reason: collision with root package name */
        private Method f806c;

        /* renamed from: d  reason: collision with root package name */
        private Context f807d;

        public a(View view, String str) {
            this.f804a = view;
            this.f805b = str;
        }

        public void onClick(View view) {
            if (this.f806c == null) {
                a(this.f804a.getContext(), this.f805b);
            }
            try {
                this.f806c.invoke(this.f807d, view);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }

        private void a(Context context, String str) {
            String str2;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f805b, View.class)) != null) {
                        this.f806c = method;
                        this.f807d = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.f804a.getId();
            if (id == -1) {
                str2 = "";
            } else {
                str2 = " with id '" + this.f804a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.f805b + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.f804a.getClass() + str2);
        }
    }
}
