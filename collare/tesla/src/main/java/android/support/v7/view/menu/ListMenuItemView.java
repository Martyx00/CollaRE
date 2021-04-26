package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.support.v7.view.menu.p;
import android.support.v7.widget.av;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListMenuItemView extends LinearLayout implements p.a, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: a  reason: collision with root package name */
    private j f951a;

    /* renamed from: b  reason: collision with root package name */
    private ImageView f952b;

    /* renamed from: c  reason: collision with root package name */
    private RadioButton f953c;

    /* renamed from: d  reason: collision with root package name */
    private TextView f954d;
    private CheckBox e;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private LinearLayout i;
    private Drawable j;
    private int k;
    private Context l;
    private boolean m;
    private Drawable n;
    private boolean o;
    private int p;
    private LayoutInflater q;
    private boolean r;

    @Override // android.support.v7.view.menu.p.a
    public boolean a() {
        return false;
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        av a2 = av.a(getContext(), attributeSet, a.j.MenuView, i2, 0);
        this.j = a2.a(a.j.MenuView_android_itemBackground);
        this.k = a2.g(a.j.MenuView_android_itemTextAppearance, -1);
        this.m = a2.a(a.j.MenuView_preserveIconSpacing, false);
        this.l = context;
        this.n = a2.a(a.j.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{16843049}, a.C0020a.dropDownListViewStyle, 0);
        this.o = obtainStyledAttributes.hasValue(0);
        a2.a();
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        t.a(this, this.j);
        this.f954d = (TextView) findViewById(a.f.title);
        int i2 = this.k;
        if (i2 != -1) {
            this.f954d.setTextAppearance(this.l, i2);
        }
        this.f = (TextView) findViewById(a.f.shortcut);
        this.g = (ImageView) findViewById(a.f.submenuarrow);
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setImageDrawable(this.n);
        }
        this.h = (ImageView) findViewById(a.f.group_divider);
        this.i = (LinearLayout) findViewById(a.f.content);
    }

    @Override // android.support.v7.view.menu.p.a
    public void a(j jVar, int i2) {
        this.f951a = jVar;
        this.p = i2;
        setVisibility(jVar.isVisible() ? 0 : 8);
        setTitle(jVar.a((p.a) this));
        setCheckable(jVar.isCheckable());
        a(jVar.f(), jVar.d());
        setIcon(jVar.getIcon());
        setEnabled(jVar.isEnabled());
        setSubMenuArrowVisible(jVar.hasSubMenu());
        setContentDescription(jVar.getContentDescription());
    }

    private void a(View view) {
        a(view, -1);
    }

    private void a(View view, int i2) {
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(view, i2);
        } else {
            addView(view, i2);
        }
    }

    public void setForceShowIcon(boolean z) {
        this.r = z;
        this.m = z;
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.f954d.setText(charSequence);
            if (this.f954d.getVisibility() != 0) {
                this.f954d.setVisibility(0);
            }
        } else if (this.f954d.getVisibility() != 8) {
            this.f954d.setVisibility(8);
        }
    }

    @Override // android.support.v7.view.menu.p.a
    public j getItemData() {
        return this.f951a;
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (z || this.f953c != null || this.e != null) {
            if (this.f951a.g()) {
                if (this.f953c == null) {
                    c();
                }
                compoundButton2 = this.f953c;
                compoundButton = this.e;
            } else {
                if (this.e == null) {
                    d();
                }
                compoundButton2 = this.e;
                compoundButton = this.f953c;
            }
            if (z) {
                compoundButton2.setChecked(this.f951a.isChecked());
                if (compoundButton2.getVisibility() != 0) {
                    compoundButton2.setVisibility(0);
                }
                if (compoundButton != null && compoundButton.getVisibility() != 8) {
                    compoundButton.setVisibility(8);
                    return;
                }
                return;
            }
            CheckBox checkBox = this.e;
            if (checkBox != null) {
                checkBox.setVisibility(8);
            }
            RadioButton radioButton = this.f953c;
            if (radioButton != null) {
                radioButton.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f951a.g()) {
            if (this.f953c == null) {
                c();
            }
            compoundButton = this.f953c;
        } else {
            if (this.e == null) {
                d();
            }
            compoundButton = this.e;
        }
        compoundButton.setChecked(z);
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    public void a(boolean z, char c2) {
        int i2 = (!z || !this.f951a.f()) ? 8 : 0;
        if (i2 == 0) {
            this.f.setText(this.f951a.e());
        }
        if (this.f.getVisibility() != i2) {
            this.f.setVisibility(i2);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f951a.i() || this.r;
        if (!z && !this.m) {
            return;
        }
        if (this.f952b != null || drawable != null || this.m) {
            if (this.f952b == null) {
                b();
            }
            if (drawable != null || this.m) {
                ImageView imageView = this.f952b;
                if (!z) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.f952b.getVisibility() != 0) {
                    this.f952b.setVisibility(0);
                    return;
                }
                return;
            }
            this.f952b.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.f952b != null && this.m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f952b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i2, i3);
    }

    private void b() {
        this.f952b = (ImageView) getInflater().inflate(a.g.abc_list_menu_item_icon, (ViewGroup) this, false);
        a(this.f952b, 0);
    }

    private void c() {
        this.f953c = (RadioButton) getInflater().inflate(a.g.abc_list_menu_item_radio, (ViewGroup) this, false);
        a(this.f953c);
    }

    private void d() {
        this.e = (CheckBox) getInflater().inflate(a.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        a(this.e);
    }

    private LayoutInflater getInflater() {
        if (this.q == null) {
            this.q = LayoutInflater.from(getContext());
        }
        return this.q;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility((this.o || !z) ? 8 : 0);
        }
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.h;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
            rect.top += this.h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
    }
}
