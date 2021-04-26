package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.text.l;
import com.facebook.react.views.text.q;
import com.facebook.react.views.text.w;
import com.facebook.react.views.text.x;
import com.facebook.react.views.view.e;
import java.util.ArrayList;
import java.util.Iterator;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: ReactEditText */
public class c extends EditText {
    private static final KeyListener w = QwertyKeyListener.getInstanceForFullKeyboard();

    /* renamed from: a  reason: collision with root package name */
    private final InputMethodManager f3513a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f3514b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3515c;

    /* renamed from: d  reason: collision with root package name */
    private int f3516d;
    private int e;
    private int f;
    private int g;
    private ArrayList<TextWatcher> h;
    private b i;
    private int j;
    private boolean k;
    private Boolean l;
    private boolean m;
    private String n;
    private p o;
    private a p;
    private o q;
    private final a r;
    private boolean s = false;
    private boolean t = false;
    private w u;
    private e v;

    public boolean isLayoutRequested() {
        return false;
    }

    public c(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        this.v = new e(this);
        this.f3513a = (InputMethodManager) com.facebook.i.a.a.a(getContext().getSystemService("input_method"));
        this.f3516d = getGravity() & 8388615;
        this.e = getGravity() & 112;
        this.f = 0;
        this.g = 0;
        this.f3514b = false;
        this.f3515c = false;
        this.l = null;
        this.m = false;
        this.h = null;
        this.i = null;
        this.j = getInputType();
        this.r = new a();
        this.q = null;
        this.u = new w();
        e();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        k();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.s = true;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2 && this.s) {
            if (!canScrollVertically(-1) && !canScrollVertically(1) && !canScrollHorizontally(-1) && !canScrollHorizontally(1)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.s = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 66 || i()) {
            return super.onKeyUp(i2, keyEvent);
        }
        h();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        o oVar = this.q;
        if (oVar != null) {
            oVar.a(i2, i3, i4, i5);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        ReactContext reactContext = (ReactContext) getContext();
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && this.t) {
            onCreateInputConnection = new d(onCreateInputConnection, reactContext, this);
        }
        if (i() && getBlurOnSubmit()) {
            editorInfo.imeOptions &= -1073741825;
        }
        return onCreateInputConnection;
    }

    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        h();
    }

    public boolean requestFocus(int i2, Rect rect) {
        if (isFocused()) {
            return true;
        }
        if (!this.f3515c) {
            return false;
        }
        setFocusableInTouchMode(true);
        boolean requestFocus = super.requestFocus(i2, rect);
        g();
        return requestFocus;
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.h == null) {
            this.h = new ArrayList<>();
            super.addTextChangedListener(getTextWatcherDelegator());
        }
        this.h.add(textWatcher);
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        ArrayList<TextWatcher> arrayList = this.h;
        if (arrayList != null) {
            arrayList.remove(textWatcher);
            if (this.h.isEmpty()) {
                this.h = null;
                super.removeTextChangedListener(getTextWatcherDelegator());
            }
        }
    }

    public void setContentSizeWatcher(a aVar) {
        this.p = aVar;
    }

    public void setMostRecentEventCount(int i2) {
        this.g = i2;
    }

    public void setScrollWatcher(o oVar) {
        this.q = oVar;
    }

    public void setSelection(int i2, int i3) {
        if (this.g >= this.f) {
            super.setSelection(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void onSelectionChanged(int i2, int i3) {
        super.onSelectionChanged(i2, i3);
        if (this.o != null && hasFocus()) {
            this.o.a(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i2, Rect rect) {
        p pVar;
        super.onFocusChanged(z, i2, rect);
        if (z && (pVar = this.o) != null) {
            pVar.a(getSelectionStart(), getSelectionEnd());
        }
    }

    public void setSelectionWatcher(p pVar) {
        this.o = pVar;
    }

    public void setBlurOnSubmit(Boolean bool) {
        this.l = bool;
    }

    public void setOnKeyPress(boolean z) {
        this.t = z;
    }

    public boolean getBlurOnSubmit() {
        Boolean bool = this.l;
        if (bool == null) {
            return !i();
        }
        return bool.booleanValue();
    }

    public void setDisableFullscreenUI(boolean z) {
        this.m = z;
        m();
    }

    public boolean getDisableFullscreenUI() {
        return this.m;
    }

    public void setReturnKeyType(String str) {
        this.n = str;
        m();
    }

    public String getReturnKeyType() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public int getStagedInputType() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public void setStagedInputType(int i2) {
        this.j = i2;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (getInputType() != this.j) {
            int selectionStart = getSelectionStart();
            int selectionEnd = getSelectionEnd();
            setInputType(this.j);
            setSelection(selectionStart, selectionEnd);
        }
    }

    public void setInputType(int i2) {
        Typeface typeface = super.getTypeface();
        super.setInputType(i2);
        this.j = i2;
        super.setTypeface(typeface);
        this.r.a(i2);
        setKeyListener(this.r);
    }

    public void b() {
        this.f3515c = true;
        requestFocus();
        this.f3515c = false;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        clearFocus();
    }

    public int d() {
        int i2 = this.f + 1;
        this.f = i2;
        return i2;
    }

    public void a(q qVar) {
        if (!j() || !TextUtils.equals(getText(), qVar.a())) {
            this.g = qVar.b();
            if (this.g >= this.f) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(qVar.a());
                a(spannableStringBuilder);
                this.k = qVar.c();
                this.f3514b = true;
                getText().replace(0, length(), spannableStringBuilder);
                this.f3514b = false;
                if (Build.VERSION.SDK_INT >= 23 && getBreakStrategy() != qVar.i()) {
                    setBreakStrategy(qVar.i());
                }
            }
        }
    }

    private void a(SpannableStringBuilder spannableStringBuilder) {
        Object[] spans = getText().getSpans(0, length(), Object.class);
        for (int i2 = 0; i2 < spans.length; i2++) {
            if (spans[i2] instanceof l) {
                getText().removeSpan(spans[i2]);
            }
            if ((getText().getSpanFlags(spans[i2]) & 33) == 33) {
                Object obj = spans[i2];
                int spanStart = getText().getSpanStart(spans[i2]);
                int spanEnd = getText().getSpanEnd(spans[i2]);
                int spanFlags = getText().getSpanFlags(spans[i2]);
                getText().removeSpan(spans[i2]);
                if (a(getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(obj, spanStart, spanEnd, spanFlags);
                }
            }
        }
    }

    private static boolean a(Editable editable, SpannableStringBuilder spannableStringBuilder, int i2, int i3) {
        if (i2 > spannableStringBuilder.length() || i3 > spannableStringBuilder.length()) {
            return false;
        }
        while (i2 < i3) {
            if (editable.charAt(i2) != spannableStringBuilder.charAt(i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private boolean g() {
        return this.f3513a.showSoftInput(this, 0);
    }

    private void h() {
        this.f3513a.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private b getTextWatcherDelegator() {
        if (this.i == null) {
            this.i = new b();
        }
        return this.i;
    }

    private boolean i() {
        return (getInputType() & PKIFailureInfo.unsupportedVersion) != 0;
    }

    private boolean j() {
        return (getInputType() & CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA) != 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void k() {
        a aVar = this.p;
        if (aVar != null) {
            aVar.a();
        }
        l();
    }

    private void l() {
        ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).setViewLocalData(getId(), new k(this));
    }

    /* access modifiers changed from: package-private */
    public void setGravityHorizontal(int i2) {
        if (i2 == 0) {
            i2 = this.f3516d;
        }
        setGravity(i2 | (getGravity() & -8 & -8388616));
    }

    /* access modifiers changed from: package-private */
    public void setGravityVertical(int i2) {
        if (i2 == 0) {
            i2 = this.e;
        }
        setGravity(i2 | (getGravity() & -113));
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m() {
        /*
        // Method dump skipped, instructions count: 170
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.c.m():void");
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.k) {
            Editable text = getText();
            for (x xVar : (x[]) text.getSpans(0, text.length(), x.class)) {
                if (xVar.a() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.k) {
            Editable text = getText();
            for (x xVar : (x[]) text.getSpans(0, text.length(), x.class)) {
                if (xVar.a() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.k) {
            Editable text = getText();
            for (x xVar : (x[]) text.getSpans(0, text.length(), x.class)) {
                xVar.b();
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.k) {
            Editable text = getText();
            for (x xVar : (x[]) text.getSpans(0, text.length(), x.class)) {
                xVar.c();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.k) {
            Editable text = getText();
            for (x xVar : (x[]) text.getSpans(0, text.length(), x.class)) {
                xVar.d();
            }
        }
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.k) {
            Editable text = getText();
            for (x xVar : (x[]) text.getSpans(0, text.length(), x.class)) {
                xVar.e();
            }
        }
    }

    public void setBackgroundColor(int i2) {
        this.v.a(i2);
    }

    public void a(int i2, float f2) {
        this.v.a(i2, f2);
    }

    public void a(int i2, float f2, float f3) {
        this.v.a(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        this.v.a(f2);
    }

    public void a(float f2, int i2) {
        this.v.a(f2, i2);
    }

    public void setBorderStyle(String str) {
        this.v.a(str);
    }

    public void setLetterSpacingPt(float f2) {
        this.u.c(f2);
        e();
    }

    public void setAllowFontScaling(boolean z) {
        if (this.u.a() != z) {
            this.u.a(z);
            e();
        }
    }

    public void setFontSize(float f2) {
        this.u.a(f2);
        e();
    }

    public void setMaxFontSizeMultiplier(float f2) {
        if (f2 != this.u.e()) {
            this.u.d(f2);
            e();
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        setTextSize(0, (float) this.u.h());
        if (Build.VERSION.SDK_INT >= 21) {
            float j2 = this.u.j();
            if (!Float.isNaN(j2)) {
                setLetterSpacing(j2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactEditText */
    public class b implements TextWatcher {
        private b() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!c.this.f3514b && c.this.h != null) {
                Iterator it = c.this.h.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).beforeTextChanged(charSequence, i, i2, i3);
                }
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!c.this.f3514b && c.this.h != null) {
                Iterator it = c.this.h.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).onTextChanged(charSequence, i, i2, i3);
                }
            }
            c.this.k();
        }

        public void afterTextChanged(Editable editable) {
            if (!c.this.f3514b && c.this.h != null) {
                Iterator it = c.this.h.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).afterTextChanged(editable);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactEditText */
    public static class a implements KeyListener {

        /* renamed from: a  reason: collision with root package name */
        private int f3517a = 0;

        public void a(int i) {
            this.f3517a = i;
        }

        public int getInputType() {
            return this.f3517a;
        }

        public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
            return c.w.onKeyDown(view, editable, i, keyEvent);
        }

        public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
            return c.w.onKeyUp(view, editable, i, keyEvent);
        }

        public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
            return c.w.onKeyOther(view, editable, keyEvent);
        }

        public void clearMetaKeyState(View view, Editable editable, int i) {
            c.w.clearMetaKeyState(view, editable, i);
        }
    }
}
