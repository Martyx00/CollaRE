package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.g.t;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.a.a;
import android.support.v7.widget.ah;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
public class AlertController {
    private int A;
    private boolean B = false;
    private CharSequence C;
    private Drawable D;
    private CharSequence E;
    private Drawable F;
    private CharSequence G;
    private Drawable H;
    private int I = 0;
    private Drawable J;
    private ImageView K;
    private TextView L;
    private TextView M;
    private View N;
    private int O;
    private int P;
    private boolean Q;
    private int R = 0;
    private final View.OnClickListener S = new View.OnClickListener() {
        /* class android.support.v7.app.AlertController.AnonymousClass1 */

        public void onClick(View view) {
            Message message;
            if (view == AlertController.this.f767c && AlertController.this.f768d != null) {
                message = Message.obtain(AlertController.this.f768d);
            } else if (view != AlertController.this.e || AlertController.this.f == null) {
                message = (view != AlertController.this.g || AlertController.this.h == null) ? null : Message.obtain(AlertController.this.h);
            } else {
                message = Message.obtain(AlertController.this.f);
            }
            if (message != null) {
                message.sendToTarget();
            }
            AlertController.this.p.obtainMessage(1, AlertController.this.f765a).sendToTarget();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final g f765a;

    /* renamed from: b  reason: collision with root package name */
    ListView f766b;

    /* renamed from: c  reason: collision with root package name */
    Button f767c;

    /* renamed from: d  reason: collision with root package name */
    Message f768d;
    Button e;
    Message f;
    Button g;
    Message h;
    NestedScrollView i;
    ListAdapter j;
    int k = -1;
    int l;
    int m;
    int n;
    int o;
    Handler p;
    private final Context q;
    private final Window r;
    private final int s;
    private CharSequence t;
    private CharSequence u;
    private View v;
    private int w;
    private int x;
    private int y;
    private int z;

    private static final class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<DialogInterface> f799a;

        public b(DialogInterface dialogInterface) {
            this.f799a = new WeakReference<>(dialogInterface);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case -3:
                    case -2:
                    case -1:
                        ((DialogInterface.OnClickListener) message.obj).onClick(this.f799a.get(), message.what);
                        return;
                    default:
                        return;
                }
            } else {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    private static boolean a(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.C0020a.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            return true;
        }
        return false;
    }

    public AlertController(Context context, g gVar, Window window) {
        this.q = context;
        this.f765a = gVar;
        this.r = window;
        this.p = new b(gVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, a.j.AlertDialog, a.C0020a.alertDialogStyle, 0);
        this.O = obtainStyledAttributes.getResourceId(a.j.AlertDialog_android_layout, 0);
        this.P = obtainStyledAttributes.getResourceId(a.j.AlertDialog_buttonPanelSideLayout, 0);
        this.l = obtainStyledAttributes.getResourceId(a.j.AlertDialog_listLayout, 0);
        this.m = obtainStyledAttributes.getResourceId(a.j.AlertDialog_multiChoiceItemLayout, 0);
        this.n = obtainStyledAttributes.getResourceId(a.j.AlertDialog_singleChoiceItemLayout, 0);
        this.o = obtainStyledAttributes.getResourceId(a.j.AlertDialog_listItemLayout, 0);
        this.Q = obtainStyledAttributes.getBoolean(a.j.AlertDialog_showTitle, true);
        this.s = obtainStyledAttributes.getDimensionPixelSize(a.j.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        gVar.a(1);
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        this.f765a.setContentView(b());
        c();
    }

    private int b() {
        int i2 = this.P;
        if (i2 == 0) {
            return this.O;
        }
        if (this.R == 1) {
            return i2;
        }
        return this.O;
    }

    public void a(CharSequence charSequence) {
        this.t = charSequence;
        TextView textView = this.L;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void b(View view) {
        this.N = view;
    }

    public void b(CharSequence charSequence) {
        this.u = charSequence;
        TextView textView = this.M;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void a(int i2) {
        this.v = null;
        this.w = i2;
        this.B = false;
    }

    public void c(View view) {
        this.v = view;
        this.w = 0;
        this.B = false;
    }

    public void a(View view, int i2, int i3, int i4, int i5) {
        this.v = view;
        this.w = 0;
        this.B = true;
        this.x = i2;
        this.y = i3;
        this.z = i4;
        this.A = i5;
    }

    public void a(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.p.obtainMessage(i2, onClickListener);
        }
        switch (i2) {
            case -3:
                this.G = charSequence;
                this.h = message;
                this.H = drawable;
                return;
            case -2:
                this.E = charSequence;
                this.f = message;
                this.F = drawable;
                return;
            case -1:
                this.C = charSequence;
                this.f768d = message;
                this.D = drawable;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void b(int i2) {
        this.J = null;
        this.I = i2;
        ImageView imageView = this.K;
        if (imageView == null) {
            return;
        }
        if (i2 != 0) {
            imageView.setVisibility(0);
            this.K.setImageResource(this.I);
            return;
        }
        imageView.setVisibility(8);
    }

    public void a(Drawable drawable) {
        this.J = drawable;
        this.I = 0;
        ImageView imageView = this.K;
        if (imageView == null) {
            return;
        }
        if (drawable != null) {
            imageView.setVisibility(0);
            this.K.setImageDrawable(drawable);
            return;
        }
        imageView.setVisibility(8);
    }

    public int c(int i2) {
        TypedValue typedValue = new TypedValue();
        this.q.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId;
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.i;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    public boolean b(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.i;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    private ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private void c() {
        ListAdapter listAdapter;
        View findViewById;
        View findViewById2;
        View findViewById3 = this.r.findViewById(a.f.parentPanel);
        View findViewById4 = findViewById3.findViewById(a.f.topPanel);
        View findViewById5 = findViewById3.findViewById(a.f.contentPanel);
        View findViewById6 = findViewById3.findViewById(a.f.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById3.findViewById(a.f.customPanel);
        a(viewGroup);
        View findViewById7 = viewGroup.findViewById(a.f.topPanel);
        View findViewById8 = viewGroup.findViewById(a.f.contentPanel);
        View findViewById9 = viewGroup.findViewById(a.f.buttonPanel);
        ViewGroup a2 = a(findViewById7, findViewById4);
        ViewGroup a3 = a(findViewById8, findViewById5);
        ViewGroup a4 = a(findViewById9, findViewById6);
        c(a3);
        d(a4);
        b(a2);
        int i2 = 0;
        boolean z2 = (viewGroup == null || viewGroup.getVisibility() == 8) ? false : true;
        boolean z3 = (a2 == null || a2.getVisibility() == 8) ? false : true;
        boolean z4 = (a4 == null || a4.getVisibility() == 8) ? false : true;
        if (!(z4 || a3 == null || (findViewById2 = a3.findViewById(a.f.textSpacerNoButtons)) == null)) {
            findViewById2.setVisibility(0);
        }
        if (z3) {
            NestedScrollView nestedScrollView = this.i;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View view = null;
            if (!(this.u == null && this.f766b == null)) {
                view = a2.findViewById(a.f.titleDividerNoCustom);
            }
            if (view != null) {
                view.setVisibility(0);
            }
        } else if (!(a3 == null || (findViewById = a3.findViewById(a.f.textSpacerNoTitle)) == null)) {
            findViewById.setVisibility(0);
        }
        ListView listView = this.f766b;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z3, z4);
        }
        if (!z2) {
            View view2 = this.f766b;
            if (view2 == null) {
                view2 = this.i;
            }
            if (view2 != null) {
                if (z4) {
                    i2 = 2;
                }
                int i3 = z3 ? 1 : 0;
                char c2 = z3 ? 1 : 0;
                char c3 = z3 ? 1 : 0;
                a(a3, view2, i3 | i2, 3);
            }
        }
        ListView listView2 = this.f766b;
        if (listView2 != null && (listAdapter = this.j) != null) {
            listView2.setAdapter(listAdapter);
            int i4 = this.k;
            if (i4 > -1) {
                listView2.setItemChecked(i4, true);
                listView2.setSelection(i4);
            }
        }
    }

    private void a(ViewGroup viewGroup, View view, int i2, int i3) {
        final View findViewById = this.r.findViewById(a.f.scrollIndicatorUp);
        View findViewById2 = this.r.findViewById(a.f.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            t.a(view, i2, i3);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
                return;
            }
            return;
        }
        final View view2 = null;
        if (findViewById != null && (i2 & 1) == 0) {
            viewGroup.removeView(findViewById);
            findViewById = null;
        }
        if (findViewById2 == null || (i2 & 2) != 0) {
            view2 = findViewById2;
        } else {
            viewGroup.removeView(findViewById2);
        }
        if (findViewById != null || view2 != null) {
            if (this.u != null) {
                this.i.setOnScrollChangeListener(new NestedScrollView.b() {
                    /* class android.support.v7.app.AlertController.AnonymousClass2 */

                    @Override // android.support.v4.widget.NestedScrollView.b
                    public void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                        AlertController.a(nestedScrollView, findViewById, view2);
                    }
                });
                this.i.post(new Runnable() {
                    /* class android.support.v7.app.AlertController.AnonymousClass3 */

                    public void run() {
                        AlertController.a(AlertController.this.i, findViewById, view2);
                    }
                });
                return;
            }
            ListView listView = this.f766b;
            if (listView != null) {
                listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    /* class android.support.v7.app.AlertController.AnonymousClass4 */

                    public void onScrollStateChanged(AbsListView absListView, int i) {
                    }

                    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                        AlertController.a(absListView, findViewById, view2);
                    }
                });
                this.f766b.post(new Runnable() {
                    /* class android.support.v7.app.AlertController.AnonymousClass5 */

                    public void run() {
                        AlertController.a(AlertController.this.f766b, findViewById, view2);
                    }
                });
                return;
            }
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (view2 != null) {
                viewGroup.removeView(view2);
            }
        }
    }

    private void a(ViewGroup viewGroup) {
        View view = this.v;
        boolean z2 = false;
        if (view == null) {
            view = this.w != 0 ? LayoutInflater.from(this.q).inflate(this.w, viewGroup, false) : null;
        }
        if (view != null) {
            z2 = true;
        }
        if (!z2 || !a(view)) {
            this.r.setFlags(PKIFailureInfo.unsupportedVersion, PKIFailureInfo.unsupportedVersion);
        }
        if (z2) {
            FrameLayout frameLayout = (FrameLayout) this.r.findViewById(a.f.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (this.B) {
                frameLayout.setPadding(this.x, this.y, this.z, this.A);
            }
            if (this.f766b != null) {
                ((ah.a) viewGroup.getLayoutParams()).g = BitmapDescriptorFactory.HUE_RED;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void b(ViewGroup viewGroup) {
        if (this.N != null) {
            viewGroup.addView(this.N, 0, new ViewGroup.LayoutParams(-1, -2));
            this.r.findViewById(a.f.title_template).setVisibility(8);
            return;
        }
        this.K = (ImageView) this.r.findViewById(16908294);
        if (!(!TextUtils.isEmpty(this.t)) || !this.Q) {
            this.r.findViewById(a.f.title_template).setVisibility(8);
            this.K.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        this.L = (TextView) this.r.findViewById(a.f.alertTitle);
        this.L.setText(this.t);
        int i2 = this.I;
        if (i2 != 0) {
            this.K.setImageResource(i2);
            return;
        }
        Drawable drawable = this.J;
        if (drawable != null) {
            this.K.setImageDrawable(drawable);
            return;
        }
        this.L.setPadding(this.K.getPaddingLeft(), this.K.getPaddingTop(), this.K.getPaddingRight(), this.K.getPaddingBottom());
        this.K.setVisibility(8);
    }

    private void c(ViewGroup viewGroup) {
        this.i = (NestedScrollView) this.r.findViewById(a.f.scrollView);
        this.i.setFocusable(false);
        this.i.setNestedScrollingEnabled(false);
        this.M = (TextView) viewGroup.findViewById(16908299);
        TextView textView = this.M;
        if (textView != null) {
            CharSequence charSequence = this.u;
            if (charSequence != null) {
                textView.setText(charSequence);
                return;
            }
            textView.setVisibility(8);
            this.i.removeView(this.M);
            if (this.f766b != null) {
                ViewGroup viewGroup2 = (ViewGroup) this.i.getParent();
                int indexOfChild = viewGroup2.indexOfChild(this.i);
                viewGroup2.removeViewAt(indexOfChild);
                viewGroup2.addView(this.f766b, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            viewGroup.setVisibility(8);
        }
    }

    static void a(View view, View view2, View view3) {
        int i2 = 0;
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            if (!view.canScrollVertically(1)) {
                i2 = 4;
            }
            view3.setVisibility(i2);
        }
    }

    private void d(ViewGroup viewGroup) {
        boolean z2;
        this.f767c = (Button) viewGroup.findViewById(16908313);
        this.f767c.setOnClickListener(this.S);
        boolean z3 = true;
        if (!TextUtils.isEmpty(this.C) || this.D != null) {
            this.f767c.setText(this.C);
            Drawable drawable = this.D;
            if (drawable != null) {
                int i2 = this.s;
                drawable.setBounds(0, 0, i2, i2);
                this.f767c.setCompoundDrawables(this.D, null, null, null);
            }
            this.f767c.setVisibility(0);
            z2 = true;
        } else {
            this.f767c.setVisibility(8);
            z2 = false;
        }
        this.e = (Button) viewGroup.findViewById(16908314);
        this.e.setOnClickListener(this.S);
        if (!TextUtils.isEmpty(this.E) || this.F != null) {
            this.e.setText(this.E);
            Drawable drawable2 = this.F;
            if (drawable2 != null) {
                int i3 = this.s;
                drawable2.setBounds(0, 0, i3, i3);
                this.e.setCompoundDrawables(this.F, null, null, null);
            }
            this.e.setVisibility(0);
            z2 |= true;
        } else {
            this.e.setVisibility(8);
        }
        this.g = (Button) viewGroup.findViewById(16908315);
        this.g.setOnClickListener(this.S);
        if (!TextUtils.isEmpty(this.G) || this.H != null) {
            this.g.setText(this.G);
            Drawable drawable3 = this.D;
            if (drawable3 != null) {
                int i4 = this.s;
                drawable3.setBounds(0, 0, i4, i4);
                this.f767c.setCompoundDrawables(this.D, null, null, null);
            }
            this.g.setVisibility(0);
            z2 |= true;
        } else {
            this.g.setVisibility(8);
        }
        if (a(this.q)) {
            if (z2) {
                a(this.f767c);
            } else if (z2) {
                a(this.e);
            } else if (z2) {
                a(this.g);
            }
        }
        if (!z2) {
            z3 = false;
        }
        if (!z3) {
            viewGroup.setVisibility(8);
        }
    }

    private void a(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    public static class RecycleListView extends ListView {

        /* renamed from: a  reason: collision with root package name */
        private final int f782a;

        /* renamed from: b  reason: collision with root package name */
        private final int f783b;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.RecycleListView);
            this.f783b = obtainStyledAttributes.getDimensionPixelOffset(a.j.RecycleListView_paddingBottomNoButtons, -1);
            this.f782a = obtainStyledAttributes.getDimensionPixelOffset(a.j.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean z, boolean z2) {
            if (!z2 || !z) {
                setPadding(getPaddingLeft(), z ? getPaddingTop() : this.f782a, getPaddingRight(), z2 ? getPaddingBottom() : this.f783b);
            }
        }
    }

    public static class a {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean E = false;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public int I = -1;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public AdapterView.OnItemSelectedListener N;
        public AbstractC0021a O;
        public boolean P = true;

        /* renamed from: a  reason: collision with root package name */
        public final Context f784a;

        /* renamed from: b  reason: collision with root package name */
        public final LayoutInflater f785b;

        /* renamed from: c  reason: collision with root package name */
        public int f786c = 0;

        /* renamed from: d  reason: collision with root package name */
        public Drawable f787d;
        public int e = 0;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public Drawable j;
        public DialogInterface.OnClickListener k;
        public CharSequence l;
        public Drawable m;
        public DialogInterface.OnClickListener n;
        public CharSequence o;
        public Drawable p;
        public DialogInterface.OnClickListener q;
        public boolean r;
        public DialogInterface.OnCancelListener s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;

        /* renamed from: android.support.v7.app.AlertController$a$a  reason: collision with other inner class name */
        public interface AbstractC0021a {
            void a(ListView listView);
        }

        public a(Context context) {
            this.f784a = context;
            this.r = true;
            this.f785b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void a(AlertController alertController) {
            View view = this.g;
            if (view != null) {
                alertController.b(view);
            } else {
                CharSequence charSequence = this.f;
                if (charSequence != null) {
                    alertController.a(charSequence);
                }
                Drawable drawable = this.f787d;
                if (drawable != null) {
                    alertController.a(drawable);
                }
                int i2 = this.f786c;
                if (i2 != 0) {
                    alertController.b(i2);
                }
                int i3 = this.e;
                if (i3 != 0) {
                    alertController.b(alertController.c(i3));
                }
            }
            CharSequence charSequence2 = this.h;
            if (charSequence2 != null) {
                alertController.b(charSequence2);
            }
            if (!(this.i == null && this.j == null)) {
                alertController.a(-1, this.i, this.k, (Message) null, this.j);
            }
            if (!(this.l == null && this.m == null)) {
                alertController.a(-2, this.l, this.n, (Message) null, this.m);
            }
            if (!(this.o == null && this.p == null)) {
                alertController.a(-3, this.o, this.q, (Message) null, this.p);
            }
            if (!(this.v == null && this.K == null && this.w == null)) {
                b(alertController);
            }
            View view2 = this.z;
            if (view2 == null) {
                int i4 = this.y;
                if (i4 != 0) {
                    alertController.a(i4);
                }
            } else if (this.E) {
                alertController.a(view2, this.A, this.B, this.C, this.D);
            } else {
                alertController.c(view2);
            }
        }

        private void b(final AlertController alertController) {
            ListAdapter listAdapter;
            int i2;
            final RecycleListView recycleListView = (RecycleListView) this.f785b.inflate(alertController.l, (ViewGroup) null);
            if (this.G) {
                Cursor cursor = this.K;
                if (cursor == null) {
                    listAdapter = new ArrayAdapter<CharSequence>(this.f784a, alertController.m, 16908308, this.v) {
                        /* class android.support.v7.app.AlertController.a.AnonymousClass1 */

                        public View getView(int i, View view, ViewGroup viewGroup) {
                            View view2 = super.getView(i, view, viewGroup);
                            if (a.this.F != null && a.this.F[i]) {
                                recycleListView.setItemChecked(i, true);
                            }
                            return view2;
                        }
                    };
                } else {
                    listAdapter = new CursorAdapter(this.f784a, cursor, false) {
                        /* class android.support.v7.app.AlertController.a.AnonymousClass2 */

                        /* renamed from: d  reason: collision with root package name */
                        private final int f793d;
                        private final int e;

                        {
                            Cursor cursor = getCursor();
                            this.f793d = cursor.getColumnIndexOrThrow(a.this.L);
                            this.e = cursor.getColumnIndexOrThrow(a.this.M);
                        }

                        public void bindView(View view, Context context, Cursor cursor) {
                            ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.f793d));
                            RecycleListView recycleListView = recycleListView;
                            int position = cursor.getPosition();
                            boolean z = true;
                            if (cursor.getInt(this.e) != 1) {
                                z = false;
                            }
                            recycleListView.setItemChecked(position, z);
                        }

                        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                            return a.this.f785b.inflate(alertController.m, viewGroup, false);
                        }
                    };
                }
            } else {
                if (this.H) {
                    i2 = alertController.n;
                } else {
                    i2 = alertController.o;
                }
                Cursor cursor2 = this.K;
                if (cursor2 != null) {
                    listAdapter = new SimpleCursorAdapter(this.f784a, i2, cursor2, new String[]{this.L}, new int[]{16908308});
                } else {
                    listAdapter = this.w;
                    if (listAdapter == null) {
                        listAdapter = new c(this.f784a, i2, 16908308, this.v);
                    }
                }
            }
            AbstractC0021a aVar = this.O;
            if (aVar != null) {
                aVar.a(recycleListView);
            }
            alertController.j = listAdapter;
            alertController.k = this.I;
            if (this.x != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    /* class android.support.v7.app.AlertController.a.AnonymousClass3 */

                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        a.this.x.onClick(alertController.f765a, i);
                        if (!a.this.H) {
                            alertController.f765a.dismiss();
                        }
                    }
                });
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    /* class android.support.v7.app.AlertController.a.AnonymousClass4 */

                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (a.this.F != null) {
                            a.this.F[i] = recycleListView.isItemChecked(i);
                        }
                        a.this.J.onClick(alertController.f765a, i, recycleListView.isItemChecked(i));
                    }
                });
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.N;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.f766b = recycleListView;
        }
    }

    /* access modifiers changed from: private */
    public static class c extends ArrayAdapter<CharSequence> {
        public long getItemId(int i) {
            return (long) i;
        }

        public boolean hasStableIds() {
            return true;
        }

        public c(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }
    }
}
