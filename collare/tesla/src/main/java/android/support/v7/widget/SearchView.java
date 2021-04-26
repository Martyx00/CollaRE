package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.tls.CipherSuite;

public class SearchView extends ah implements android.support.v7.view.c {
    static final a i = new a();
    private c A;
    private b B;
    private d C;
    private View.OnClickListener D;
    private boolean E;
    private boolean F;
    private boolean G;
    private CharSequence H;
    private boolean I;
    private boolean J;
    private int K;
    private boolean L;
    private CharSequence M;
    private CharSequence N;
    private boolean O;
    private int P;
    private Bundle Q;
    private final Runnable R;
    private Runnable S;
    private final WeakHashMap<String, Drawable.ConstantState> T;
    private final View.OnClickListener U;
    private final TextView.OnEditorActionListener V;
    private final AdapterView.OnItemClickListener W;

    /* renamed from: a  reason: collision with root package name */
    final SearchAutoComplete f1066a;
    private final AdapterView.OnItemSelectedListener aa;
    private TextWatcher ab;

    /* renamed from: b  reason: collision with root package name */
    final ImageView f1067b;

    /* renamed from: c  reason: collision with root package name */
    final ImageView f1068c;

    /* renamed from: d  reason: collision with root package name */
    final ImageView f1069d;
    final ImageView e;
    View.OnFocusChangeListener f;
    android.support.v4.widget.f g;
    SearchableInfo h;
    View.OnKeyListener j;
    private final View k;
    private final View l;
    private final View m;
    private final View n;
    private f o;
    private Rect p;
    private Rect q;
    private int[] r;
    private int[] s;
    private final ImageView t;
    private final Drawable u;
    private final int v;
    private final int w;
    private final Intent x;
    private final Intent y;
    private final CharSequence z;

    public interface b {
        boolean a();
    }

    public interface c {
        boolean a(String str);

        boolean b(String str);
    }

    public interface d {
        boolean a(int i);

        boolean b(int i);
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.p = new Rect();
        this.q = new Rect();
        this.r = new int[2];
        this.s = new int[2];
        this.R = new Runnable() {
            /* class android.support.v7.widget.SearchView.AnonymousClass1 */

            public void run() {
                SearchView.this.d();
            }
        };
        this.S = new Runnable() {
            /* class android.support.v7.widget.SearchView.AnonymousClass3 */

            public void run() {
                if (SearchView.this.g != null && (SearchView.this.g instanceof ao)) {
                    SearchView.this.g.a((Cursor) null);
                }
            }
        };
        this.T = new WeakHashMap<>();
        this.U = new View.OnClickListener() {
            /* class android.support.v7.widget.SearchView.AnonymousClass6 */

            public void onClick(View view) {
                if (view == SearchView.this.f1067b) {
                    SearchView.this.g();
                } else if (view == SearchView.this.f1069d) {
                    SearchView.this.f();
                } else if (view == SearchView.this.f1068c) {
                    SearchView.this.e();
                } else if (view == SearchView.this.e) {
                    SearchView.this.h();
                } else if (view == SearchView.this.f1066a) {
                    SearchView.this.l();
                }
            }
        };
        this.j = new View.OnKeyListener() {
            /* class android.support.v7.widget.SearchView.AnonymousClass7 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (SearchView.this.h == null) {
                    return false;
                }
                if (SearchView.this.f1066a.isPopupShowing() && SearchView.this.f1066a.getListSelection() != -1) {
                    return SearchView.this.a(view, i, keyEvent);
                }
                if (SearchView.this.f1066a.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                    return false;
                }
                view.cancelLongPress();
                SearchView searchView = SearchView.this;
                searchView.a(0, (String) null, searchView.f1066a.getText().toString());
                return true;
            }
        };
        this.V = new TextView.OnEditorActionListener() {
            /* class android.support.v7.widget.SearchView.AnonymousClass8 */

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                SearchView.this.e();
                return true;
            }
        };
        this.W = new AdapterView.OnItemClickListener() {
            /* class android.support.v7.widget.SearchView.AnonymousClass9 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SearchView.this.a(i, 0, (String) null);
            }
        };
        this.aa = new AdapterView.OnItemSelectedListener() {
            /* class android.support.v7.widget.SearchView.AnonymousClass10 */

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                SearchView.this.a(i);
            }
        };
        this.ab = new TextWatcher() {
            /* class android.support.v7.widget.SearchView.AnonymousClass2 */

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SearchView.this.b(charSequence);
            }
        };
        av a2 = av.a(context, attributeSet, a.j.SearchView, i2, 0);
        LayoutInflater.from(context).inflate(a2.g(a.j.SearchView_layout, a.g.abc_search_view), (ViewGroup) this, true);
        this.f1066a = (SearchAutoComplete) findViewById(a.f.search_src_text);
        this.f1066a.setSearchView(this);
        this.k = findViewById(a.f.search_edit_frame);
        this.l = findViewById(a.f.search_plate);
        this.m = findViewById(a.f.submit_area);
        this.f1067b = (ImageView) findViewById(a.f.search_button);
        this.f1068c = (ImageView) findViewById(a.f.search_go_btn);
        this.f1069d = (ImageView) findViewById(a.f.search_close_btn);
        this.e = (ImageView) findViewById(a.f.search_voice_btn);
        this.t = (ImageView) findViewById(a.f.search_mag_icon);
        t.a(this.l, a2.a(a.j.SearchView_queryBackground));
        t.a(this.m, a2.a(a.j.SearchView_submitBackground));
        this.f1067b.setImageDrawable(a2.a(a.j.SearchView_searchIcon));
        this.f1068c.setImageDrawable(a2.a(a.j.SearchView_goIcon));
        this.f1069d.setImageDrawable(a2.a(a.j.SearchView_closeIcon));
        this.e.setImageDrawable(a2.a(a.j.SearchView_voiceIcon));
        this.t.setImageDrawable(a2.a(a.j.SearchView_searchIcon));
        this.u = a2.a(a.j.SearchView_searchHintIcon);
        ax.a(this.f1067b, getResources().getString(a.h.abc_searchview_description_search));
        this.v = a2.g(a.j.SearchView_suggestionRowLayout, a.g.abc_search_dropdown_item_icons_2line);
        this.w = a2.g(a.j.SearchView_commitIcon, 0);
        this.f1067b.setOnClickListener(this.U);
        this.f1069d.setOnClickListener(this.U);
        this.f1068c.setOnClickListener(this.U);
        this.e.setOnClickListener(this.U);
        this.f1066a.setOnClickListener(this.U);
        this.f1066a.addTextChangedListener(this.ab);
        this.f1066a.setOnEditorActionListener(this.V);
        this.f1066a.setOnItemClickListener(this.W);
        this.f1066a.setOnItemSelectedListener(this.aa);
        this.f1066a.setOnKeyListener(this.j);
        this.f1066a.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /* class android.support.v7.widget.SearchView.AnonymousClass4 */

            public void onFocusChange(View view, boolean z) {
                if (SearchView.this.f != null) {
                    SearchView.this.f.onFocusChange(SearchView.this, z);
                }
            }
        });
        setIconifiedByDefault(a2.a(a.j.SearchView_iconifiedByDefault, true));
        int e2 = a2.e(a.j.SearchView_android_maxWidth, -1);
        if (e2 != -1) {
            setMaxWidth(e2);
        }
        this.z = a2.c(a.j.SearchView_defaultQueryHint);
        this.H = a2.c(a.j.SearchView_queryHint);
        int a3 = a2.a(a.j.SearchView_android_imeOptions, -1);
        if (a3 != -1) {
            setImeOptions(a3);
        }
        int a4 = a2.a(a.j.SearchView_android_inputType, -1);
        if (a4 != -1) {
            setInputType(a4);
        }
        setFocusable(a2.a(a.j.SearchView_android_focusable, true));
        a2.a();
        this.x = new Intent("android.speech.action.WEB_SEARCH");
        this.x.addFlags(268435456);
        this.x.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.y = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.y.addFlags(268435456);
        this.n = findViewById(this.f1066a.getDropDownAnchor());
        View view = this.n;
        if (view != null) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                /* class android.support.v7.widget.SearchView.AnonymousClass5 */

                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    SearchView.this.k();
                }
            });
        }
        a(this.E);
        r();
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionRowLayout() {
        return this.v;
    }

    /* access modifiers changed from: package-private */
    public int getSuggestionCommitIconResId() {
        return this.w;
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.h = searchableInfo;
        if (this.h != null) {
            s();
            r();
        }
        this.L = m();
        if (this.L) {
            this.f1066a.setPrivateImeOptions("nm");
        }
        a(c());
    }

    public void setAppSearchData(Bundle bundle) {
        this.Q = bundle;
    }

    public void setImeOptions(int i2) {
        this.f1066a.setImeOptions(i2);
    }

    public int getImeOptions() {
        return this.f1066a.getImeOptions();
    }

    public void setInputType(int i2) {
        this.f1066a.setInputType(i2);
    }

    public int getInputType() {
        return this.f1066a.getInputType();
    }

    public boolean requestFocus(int i2, Rect rect) {
        if (this.J || !isFocusable()) {
            return false;
        }
        if (c()) {
            return super.requestFocus(i2, rect);
        }
        boolean requestFocus = this.f1066a.requestFocus(i2, rect);
        if (requestFocus) {
            a(false);
        }
        return requestFocus;
    }

    public void clearFocus() {
        this.J = true;
        super.clearFocus();
        this.f1066a.clearFocus();
        this.f1066a.setImeVisibility(false);
        this.J = false;
    }

    public void setOnQueryTextListener(c cVar) {
        this.A = cVar;
    }

    public void setOnCloseListener(b bVar) {
        this.B = bVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.f = onFocusChangeListener;
    }

    public void setOnSuggestionListener(d dVar) {
        this.C = dVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.D = onClickListener;
    }

    public CharSequence getQuery() {
        return this.f1066a.getText();
    }

    public void a(CharSequence charSequence, boolean z2) {
        this.f1066a.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.f1066a;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.N = charSequence;
        }
        if (z2 && !TextUtils.isEmpty(charSequence)) {
            e();
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.H = charSequence;
        r();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.H;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.h;
        if (searchableInfo == null || searchableInfo.getHintId() == 0) {
            return this.z;
        }
        return getContext().getText(this.h.getHintId());
    }

    public void setIconifiedByDefault(boolean z2) {
        if (this.E != z2) {
            this.E = z2;
            a(z2);
            r();
        }
    }

    public void setIconified(boolean z2) {
        if (z2) {
            f();
        } else {
            g();
        }
    }

    public boolean c() {
        return this.F;
    }

    public void setSubmitButtonEnabled(boolean z2) {
        this.G = z2;
        a(c());
    }

    public void setQueryRefinementEnabled(boolean z2) {
        this.I = z2;
        android.support.v4.widget.f fVar = this.g;
        if (fVar instanceof ao) {
            ((ao) fVar).a(z2 ? 2 : 1);
        }
    }

    public void setSuggestionsAdapter(android.support.v4.widget.f fVar) {
        this.g = fVar;
        this.f1066a.setAdapter(this.g);
    }

    public android.support.v4.widget.f getSuggestionsAdapter() {
        return this.g;
    }

    public void setMaxWidth(int i2) {
        this.K = i2;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.K;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.ah
    public void onMeasure(int i2, int i3) {
        int i4;
        if (c()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.K;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.K;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i4 = this.K) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.ah
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (z2) {
            a(this.f1066a, this.p);
            this.q.set(this.p.left, 0, this.p.right, i5 - i3);
            f fVar = this.o;
            if (fVar == null) {
                this.o = new f(this.q, this.p, this.f1066a);
                setTouchDelegate(this.o);
                return;
            }
            fVar.a(this.q, this.p);
        }
    }

    private void a(View view, Rect rect) {
        view.getLocationInWindow(this.r);
        getLocationInWindow(this.s);
        int[] iArr = this.r;
        int i2 = iArr[1];
        int[] iArr2 = this.s;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(a.d.abc_search_view_preferred_width);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(a.d.abc_search_view_preferred_height);
    }

    private void a(boolean z2) {
        this.F = z2;
        int i2 = 8;
        boolean z3 = false;
        int i3 = z2 ? 0 : 8;
        boolean z4 = !TextUtils.isEmpty(this.f1066a.getText());
        this.f1067b.setVisibility(i3);
        b(z4);
        this.k.setVisibility(z2 ? 8 : 0);
        if (this.t.getDrawable() != null && !this.E) {
            i2 = 0;
        }
        this.t.setVisibility(i2);
        p();
        if (!z4) {
            z3 = true;
        }
        c(z3);
        o();
    }

    private boolean m() {
        SearchableInfo searchableInfo = this.h;
        if (searchableInfo != null && searchableInfo.getVoiceSearchEnabled()) {
            Intent intent = null;
            if (this.h.getVoiceSearchLaunchWebSearch()) {
                intent = this.x;
            } else if (this.h.getVoiceSearchLaunchRecognizer()) {
                intent = this.y;
            }
            if (intent == null || getContext().getPackageManager().resolveActivity(intent, PKIFailureInfo.notAuthorized) == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean n() {
        return (this.G || this.L) && !c();
    }

    private void b(boolean z2) {
        this.f1068c.setVisibility((!this.G || !n() || !hasFocus() || (!z2 && this.L)) ? 8 : 0);
    }

    private void o() {
        this.m.setVisibility((!n() || !(this.f1068c.getVisibility() == 0 || this.e.getVisibility() == 0)) ? 8 : 0);
    }

    private void p() {
        boolean z2 = true;
        boolean z3 = !TextUtils.isEmpty(this.f1066a.getText());
        int i2 = 0;
        if (!z3 && (!this.E || this.O)) {
            z2 = false;
        }
        ImageView imageView = this.f1069d;
        if (!z2) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        Drawable drawable = this.f1069d.getDrawable();
        if (drawable != null) {
            drawable.setState(z3 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void q() {
        post(this.R);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        int[] iArr = this.f1066a.hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
        Drawable background = this.l.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.m.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.R);
        post(this.S);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: package-private */
    public void a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    /* access modifiers changed from: package-private */
    public boolean a(View view, int i2, KeyEvent keyEvent) {
        int i3;
        if (this.h != null && this.g != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return a(this.f1066a.getListSelection(), 0, (String) null);
            }
            if (i2 == 21 || i2 == 22) {
                if (i2 == 21) {
                    i3 = 0;
                } else {
                    i3 = this.f1066a.length();
                }
                this.f1066a.setSelection(i3);
                this.f1066a.setListSelection(0);
                this.f1066a.clearListSelection();
                i.a(this.f1066a, true);
                return true;
            } else if (i2 != 19 || this.f1066a.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    private CharSequence c(CharSequence charSequence) {
        if (!this.E || this.u == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.f1066a.getTextSize()) * 1.25d);
        this.u.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.u), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private void r() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f1066a;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(c(queryHint));
    }

    private void s() {
        this.f1066a.setThreshold(this.h.getSuggestThreshold());
        this.f1066a.setImeOptions(this.h.getImeOptions());
        int inputType = this.h.getInputType();
        int i2 = 1;
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.h.getSuggestAuthority() != null) {
                inputType = inputType | PKIFailureInfo.notAuthorized | PKIFailureInfo.signerNotTrusted;
            }
        }
        this.f1066a.setInputType(inputType);
        android.support.v4.widget.f fVar = this.g;
        if (fVar != null) {
            fVar.a((Cursor) null);
        }
        if (this.h.getSuggestAuthority() != null) {
            this.g = new ao(getContext(), this, this.h, this.T);
            this.f1066a.setAdapter(this.g);
            ao aoVar = (ao) this.g;
            if (this.I) {
                i2 = 2;
            }
            aoVar.a(i2);
        }
    }

    private void c(boolean z2) {
        int i2;
        if (!this.L || c() || !z2) {
            i2 = 8;
        } else {
            i2 = 0;
            this.f1068c.setVisibility(8);
        }
        this.e.setVisibility(i2);
    }

    /* access modifiers changed from: package-private */
    public void b(CharSequence charSequence) {
        Editable text = this.f1066a.getText();
        this.N = text;
        boolean z2 = true;
        boolean z3 = !TextUtils.isEmpty(text);
        b(z3);
        if (z3) {
            z2 = false;
        }
        c(z2);
        p();
        o();
        if (this.A != null && !TextUtils.equals(charSequence, this.M)) {
            this.A.b(charSequence.toString());
        }
        this.M = charSequence.toString();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        Editable text = this.f1066a.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            c cVar = this.A;
            if (cVar == null || !cVar.a(text.toString())) {
                if (this.h != null) {
                    a(0, (String) null, text.toString());
                }
                this.f1066a.setImeVisibility(false);
                t();
            }
        }
    }

    private void t() {
        this.f1066a.dismissDropDown();
    }

    /* access modifiers changed from: package-private */
    public void f() {
        if (!TextUtils.isEmpty(this.f1066a.getText())) {
            this.f1066a.setText("");
            this.f1066a.requestFocus();
            this.f1066a.setImeVisibility(true);
        } else if (this.E) {
            b bVar = this.B;
            if (bVar == null || !bVar.a()) {
                clearFocus();
                a(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void g() {
        a(false);
        this.f1066a.requestFocus();
        this.f1066a.setImeVisibility(true);
        View.OnClickListener onClickListener = this.D;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        SearchableInfo searchableInfo = this.h;
        if (searchableInfo != null) {
            try {
                if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    getContext().startActivity(a(this.x, searchableInfo));
                } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    getContext().startActivity(b(this.y, searchableInfo));
                }
            } catch (ActivityNotFoundException unused) {
                Log.w("SearchView", "Could not find voice search activity");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void i() {
        a(c());
        q();
        if (this.f1066a.hasFocus()) {
            l();
        }
    }

    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        q();
    }

    @Override // android.support.v7.view.c
    public void b() {
        a("", false);
        clearFocus();
        a(true);
        this.f1066a.setImeOptions(this.P);
        this.O = false;
    }

    @Override // android.support.v7.view.c
    public void a() {
        if (!this.O) {
            this.O = true;
            this.P = this.f1066a.getImeOptions();
            this.f1066a.setImeOptions(this.P | 33554432);
            this.f1066a.setText("");
            setIconified(false);
        }
    }

    /* access modifiers changed from: package-private */
    public static class e extends android.support.v4.g.a {
        public static final Parcelable.Creator<e> CREATOR = new Parcelable.ClassLoaderCreator<e>() {
            /* class android.support.v7.widget.SearchView.e.AnonymousClass1 */

            /* renamed from: a */
            public e createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new e(parcel, classLoader);
            }

            /* renamed from: a */
            public e createFromParcel(Parcel parcel) {
                return new e(parcel, null);
            }

            /* renamed from: a */
            public e[] newArray(int i) {
                return new e[i];
            }
        };

        /* renamed from: b  reason: collision with root package name */
        boolean f1088b;

        e(Parcelable parcelable) {
            super(parcelable);
        }

        public e(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1088b = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        @Override // android.support.v4.g.a
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.f1088b));
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f1088b + "}";
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        e eVar = new e(super.onSaveInstanceState());
        eVar.f1088b = c();
        return eVar;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof e)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        e eVar = (e) parcelable;
        super.onRestoreInstanceState(eVar.a());
        a(eVar.f1088b);
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void k() {
        int i2;
        if (this.n.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.l.getPaddingLeft();
            Rect rect = new Rect();
            boolean a2 = bb.a(this);
            int dimensionPixelSize = this.E ? resources.getDimensionPixelSize(a.d.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(a.d.abc_dropdownitem_text_padding_left) : 0;
            this.f1066a.getDropDownBackground().getPadding(rect);
            if (a2) {
                i2 = -rect.left;
            } else {
                i2 = paddingLeft - (rect.left + dimensionPixelSize);
            }
            this.f1066a.setDropDownHorizontalOffset(i2);
            this.f1066a.setDropDownWidth((((this.n.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, int i3, String str) {
        d dVar = this.C;
        if (dVar != null && dVar.b(i2)) {
            return false;
        }
        b(i2, 0, null);
        this.f1066a.setImeVisibility(false);
        t();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2) {
        d dVar = this.C;
        if (dVar != null && dVar.a(i2)) {
            return false;
        }
        e(i2);
        return true;
    }

    private void e(int i2) {
        Editable text = this.f1066a.getText();
        Cursor a2 = this.g.a();
        if (a2 != null) {
            if (a2.moveToPosition(i2)) {
                CharSequence c2 = this.g.c(a2);
                if (c2 != null) {
                    setQuery(c2);
                } else {
                    setQuery(text);
                }
            } else {
                setQuery(text);
            }
        }
    }

    private boolean b(int i2, int i3, String str) {
        Cursor a2 = this.g.a();
        if (a2 == null || !a2.moveToPosition(i2)) {
            return false;
        }
        a(a(a2, i3, str));
        return true;
    }

    private void a(Intent intent) {
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (RuntimeException e2) {
                Log.e("SearchView", "Failed launch activity: " + intent, e2);
            }
        }
    }

    private void setQuery(CharSequence charSequence) {
        this.f1066a.setText(charSequence);
        this.f1066a.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str, String str2) {
        getContext().startActivity(a("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i2, str));
    }

    private Intent a(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.N);
        if (str3 != null) {
            intent.putExtra(SearchIntents.EXTRA_QUERY, str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.Q;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.h.getSearchActivity());
        return intent;
    }

    private Intent a(Intent intent, SearchableInfo searchableInfo) {
        String str;
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        if (searchActivity == null) {
            str = null;
        } else {
            str = searchActivity.flattenToShortString();
        }
        intent2.putExtra("calling_package", str);
        return intent2;
    }

    private Intent b(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.Q;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        String str = "free_form";
        int i2 = 1;
        Resources resources = getResources();
        if (searchableInfo.getVoiceLanguageModeId() != 0) {
            str = resources.getString(searchableInfo.getVoiceLanguageModeId());
        }
        String str2 = null;
        String string = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string2 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        if (searchableInfo.getVoiceMaxResults() != 0) {
            i2 = searchableInfo.getVoiceMaxResults();
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", str);
        intent3.putExtra("android.speech.extra.PROMPT", string);
        intent3.putExtra("android.speech.extra.LANGUAGE", string2);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", i2);
        if (searchActivity != null) {
            str2 = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", str2);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent a(Cursor cursor, int i2, String str) {
        int i3;
        Uri uri;
        String a2;
        try {
            String a3 = ao.a(cursor, "suggest_intent_action");
            if (a3 == null) {
                a3 = this.h.getSuggestIntentAction();
            }
            String str2 = a3 == null ? "android.intent.action.SEARCH" : a3;
            String a4 = ao.a(cursor, "suggest_intent_data");
            if (a4 == null) {
                a4 = this.h.getSuggestIntentData();
            }
            if (!(a4 == null || (a2 = ao.a(cursor, "suggest_intent_data_id")) == null)) {
                a4 = a4 + "/" + Uri.encode(a2);
            }
            if (a4 == null) {
                uri = null;
            } else {
                uri = Uri.parse(a4);
            }
            return a(str2, uri, ao.a(cursor, "suggest_intent_extra_data"), ao.a(cursor, "suggest_intent_query"), i2, str);
        } catch (RuntimeException e2) {
            try {
                i3 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i3 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + i3 + " returned exception.", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void l() {
        i.a(this.f1066a);
        i.b(this.f1066a);
    }

    static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private static class f extends TouchDelegate {

        /* renamed from: a  reason: collision with root package name */
        private final View f1089a;

        /* renamed from: b  reason: collision with root package name */
        private final Rect f1090b = new Rect();

        /* renamed from: c  reason: collision with root package name */
        private final Rect f1091c = new Rect();

        /* renamed from: d  reason: collision with root package name */
        private final Rect f1092d = new Rect();
        private final int e;
        private boolean f;

        public f(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            a(rect, rect2);
            this.f1089a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f1090b.set(rect);
            this.f1092d.set(rect);
            Rect rect3 = this.f1092d;
            int i = this.e;
            rect3.inset(-i, -i);
            this.f1091c.set(rect2);
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            boolean z2 = true;
            switch (motionEvent.getAction()) {
                case 0:
                    if (this.f1090b.contains(x, y)) {
                        this.f = true;
                        z = true;
                        break;
                    }
                    z = false;
                    break;
                case 1:
                case 2:
                    z = this.f;
                    if (z && !this.f1092d.contains(x, y)) {
                        z2 = false;
                        break;
                    }
                case 3:
                    z = this.f;
                    this.f = false;
                    break;
                default:
                    z = false;
                    break;
            }
            if (!z) {
                return false;
            }
            if (!z2 || this.f1091c.contains(x, y)) {
                motionEvent.setLocation((float) (x - this.f1091c.left), (float) (y - this.f1091c.top));
            } else {
                motionEvent.setLocation((float) (this.f1089a.getWidth() / 2), (float) (this.f1089a.getHeight() / 2));
            }
            return this.f1089a.dispatchTouchEvent(motionEvent);
        }
    }

    public static class SearchAutoComplete extends e {

        /* renamed from: a  reason: collision with root package name */
        final Runnable f1080a;

        /* renamed from: b  reason: collision with root package name */
        private int f1081b;

        /* renamed from: c  reason: collision with root package name */
        private SearchView f1082c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f1083d;

        public void performCompletion() {
        }

        /* access modifiers changed from: protected */
        public void replaceText(CharSequence charSequence) {
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, a.C0020a.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.f1080a = new Runnable() {
                /* class android.support.v7.widget.SearchView.SearchAutoComplete.AnonymousClass1 */

                public void run() {
                    SearchAutoComplete.this.b();
                }
            };
            this.f1081b = getThreshold();
        }

        /* access modifiers changed from: protected */
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, (float) getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        /* access modifiers changed from: package-private */
        public void setSearchView(SearchView searchView) {
            this.f1082c = searchView;
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f1081b = i;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f1082c.hasFocus() && getVisibility() == 0) {
                this.f1083d = true;
                if (SearchView.a(getContext())) {
                    SearchView.i.a(this, true);
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.f1082c.i();
        }

        public boolean enoughToFilter() {
            return this.f1081b <= 0 || super.enoughToFilter();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f1082c.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i >= 600) {
                return 192;
            }
            if (i < 640 || i2 < 480) {
                return CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
            }
            return 192;
        }

        @Override // android.support.v7.widget.e
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f1083d) {
                removeCallbacks(this.f1080a);
                post(this.f1080a);
            }
            return onCreateInputConnection;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.f1083d) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f1083d = false;
            }
        }

        /* access modifiers changed from: package-private */
        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.f1083d = false;
                removeCallbacks(this.f1080a);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.f1083d = false;
                removeCallbacks(this.f1080a);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.f1083d = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private Method f1085a;

        /* renamed from: b  reason: collision with root package name */
        private Method f1086b;

        /* renamed from: c  reason: collision with root package name */
        private Method f1087c;

        a() {
            try {
                this.f1085a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f1085a.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                this.f1086b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f1086b.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                this.f1087c = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f1087c.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        /* access modifiers changed from: package-private */
        public void a(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f1085a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void b(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f1086b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method method = this.f1087c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.valueOf(z));
                } catch (Exception unused) {
                }
            }
        }
    }
}
