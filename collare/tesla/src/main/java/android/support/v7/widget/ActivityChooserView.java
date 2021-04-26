package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.g.c;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.gms.common.api.Api;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class ActivityChooserView extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    final a f1047a;

    /* renamed from: b  reason: collision with root package name */
    final FrameLayout f1048b;

    /* renamed from: c  reason: collision with root package name */
    final FrameLayout f1049c;

    /* renamed from: d  reason: collision with root package name */
    c f1050d;
    final DataSetObserver e;
    PopupWindow.OnDismissListener f;
    boolean g;
    int h;
    private final b i;
    private final View j;
    private final ImageView k;
    private final int l;
    private final ViewTreeObserver.OnGlobalLayoutListener m;
    private ai n;
    private boolean o;
    private int p;

    public void setActivityChooserModel(d dVar) {
        this.f1047a.a(dVar);
        if (c()) {
            b();
            a();
        }
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.k.setImageDrawable(drawable);
    }

    public void setExpandActivityOverflowButtonContentDescription(int i2) {
        this.k.setContentDescription(getContext().getString(i2));
    }

    public void setProvider(c cVar) {
        this.f1050d = cVar;
    }

    public boolean a() {
        if (c() || !this.o) {
            return false;
        }
        this.g = false;
        a(this.h);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r6) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActivityChooserView.a(int):void");
    }

    public boolean b() {
        if (!c()) {
            return true;
        }
        getListPopupWindow().c();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(this.m);
        return true;
    }

    public boolean c() {
        return getListPopupWindow().d();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        d d2 = this.f1047a.d();
        if (d2 != null) {
            d2.registerObserver(this.e);
        }
        this.o = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d d2 = this.f1047a.d();
        if (d2 != null) {
            d2.unregisterObserver(this.e);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.m);
        }
        if (c()) {
            b();
        }
        this.o = false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        View view = this.j;
        if (this.f1049c.getVisibility() != 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3), 1073741824);
        }
        measureChild(view, i2, i3);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.j.layout(0, 0, i4 - i2, i5 - i3);
        if (!c()) {
            b();
        }
    }

    public d getDataModel() {
        return this.f1047a.d();
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    public void setInitialActivityCount(int i2) {
        this.h = i2;
    }

    public void setDefaultActionButtonContentDescription(int i2) {
        this.p = i2;
    }

    /* access modifiers changed from: package-private */
    public ai getListPopupWindow() {
        if (this.n == null) {
            this.n = new ai(getContext());
            this.n.a(this.f1047a);
            this.n.b(this);
            this.n.a(true);
            this.n.a((AdapterView.OnItemClickListener) this.i);
            this.n.a((PopupWindow.OnDismissListener) this.i);
        }
        return this.n;
    }

    /* access modifiers changed from: private */
    public class b implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ActivityChooserView f1056a;

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            switch (((a) adapterView.getAdapter()).getItemViewType(i)) {
                case 0:
                    this.f1056a.b();
                    if (!this.f1056a.g) {
                        if (!this.f1056a.f1047a.e()) {
                            i++;
                        }
                        Intent b2 = this.f1056a.f1047a.d().b(i);
                        if (b2 != null) {
                            b2.addFlags(PKIFailureInfo.signerNotTrusted);
                            this.f1056a.getContext().startActivity(b2);
                            return;
                        }
                        return;
                    } else if (i > 0) {
                        this.f1056a.f1047a.d().c(i);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    this.f1056a.a(Api.BaseClientBuilder.API_PRIORITY_OTHER);
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void onClick(View view) {
            if (view == this.f1056a.f1049c) {
                this.f1056a.b();
                Intent b2 = this.f1056a.f1047a.d().b(this.f1056a.f1047a.d().a(this.f1056a.f1047a.b()));
                if (b2 != null) {
                    b2.addFlags(PKIFailureInfo.signerNotTrusted);
                    this.f1056a.getContext().startActivity(b2);
                }
            } else if (view == this.f1056a.f1048b) {
                ActivityChooserView activityChooserView = this.f1056a;
                activityChooserView.g = false;
                activityChooserView.a(activityChooserView.h);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public boolean onLongClick(View view) {
            if (view == this.f1056a.f1049c) {
                if (this.f1056a.f1047a.getCount() > 0) {
                    ActivityChooserView activityChooserView = this.f1056a;
                    activityChooserView.g = true;
                    activityChooserView.a(activityChooserView.h);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }

        public void onDismiss() {
            a();
            if (this.f1056a.f1050d != null) {
                this.f1056a.f1050d.a(false);
            }
        }

        private void a() {
            if (this.f1056a.f != null) {
                this.f1056a.f.onDismiss();
            }
        }
    }

    /* access modifiers changed from: private */
    public class a extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ActivityChooserView f1052a;

        /* renamed from: b  reason: collision with root package name */
        private d f1053b;

        /* renamed from: c  reason: collision with root package name */
        private int f1054c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f1055d;
        private boolean e;
        private boolean f;

        public long getItemId(int i) {
            return (long) i;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public void a(d dVar) {
            d d2 = this.f1052a.f1047a.d();
            if (d2 != null && this.f1052a.isShown()) {
                d2.unregisterObserver(this.f1052a.e);
            }
            this.f1053b = dVar;
            if (dVar != null && this.f1052a.isShown()) {
                dVar.registerObserver(this.f1052a.e);
            }
            notifyDataSetChanged();
        }

        public int getItemViewType(int i) {
            return (!this.f || i != getCount() - 1) ? 0 : 1;
        }

        public int getCount() {
            int a2 = this.f1053b.a();
            if (!this.f1055d && this.f1053b.b() != null) {
                a2--;
            }
            int min = Math.min(a2, this.f1054c);
            return this.f ? min + 1 : min;
        }

        public Object getItem(int i) {
            switch (getItemViewType(i)) {
                case 0:
                    if (!this.f1055d && this.f1053b.b() != null) {
                        i++;
                    }
                    return this.f1053b.a(i);
                case 1:
                    return null;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case 0:
                    if (view == null || view.getId() != a.f.list_item) {
                        view = LayoutInflater.from(this.f1052a.getContext()).inflate(a.g.abc_activity_chooser_view_list_item, viewGroup, false);
                    }
                    PackageManager packageManager = this.f1052a.getContext().getPackageManager();
                    ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
                    ((ImageView) view.findViewById(a.f.icon)).setImageDrawable(resolveInfo.loadIcon(packageManager));
                    ((TextView) view.findViewById(a.f.title)).setText(resolveInfo.loadLabel(packageManager));
                    if (!this.f1055d || i != 0 || !this.e) {
                        view.setActivated(false);
                    } else {
                        view.setActivated(true);
                    }
                    return view;
                case 1:
                    if (view != null && view.getId() == 1) {
                        return view;
                    }
                    View inflate = LayoutInflater.from(this.f1052a.getContext()).inflate(a.g.abc_activity_chooser_view_list_item, viewGroup, false);
                    inflate.setId(1);
                    ((TextView) inflate.findViewById(a.f.title)).setText(this.f1052a.getContext().getString(a.h.abc_activity_chooser_view_see_all));
                    return inflate;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public int a() {
            int i = this.f1054c;
            this.f1054c = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            View view = null;
            int i2 = 0;
            for (int i3 = 0; i3 < count; i3++) {
                view = getView(i3, view, null);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i2 = Math.max(i2, view.getMeasuredWidth());
            }
            this.f1054c = i;
            return i2;
        }

        public void a(int i) {
            if (this.f1054c != i) {
                this.f1054c = i;
                notifyDataSetChanged();
            }
        }

        public ResolveInfo b() {
            return this.f1053b.b();
        }

        public void a(boolean z) {
            if (this.f != z) {
                this.f = z;
                notifyDataSetChanged();
            }
        }

        public int c() {
            return this.f1053b.a();
        }

        public d d() {
            return this.f1053b;
        }

        public void a(boolean z, boolean z2) {
            if (this.f1055d != z || this.e != z2) {
                this.f1055d = z;
                this.e = z2;
                notifyDataSetChanged();
            }
        }

        public boolean e() {
            return this.f1055d;
        }
    }

    public static class InnerLayout extends LinearLayout {

        /* renamed from: a  reason: collision with root package name */
        private static final int[] f1051a = {16842964};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            av a2 = av.a(context, attributeSet, f1051a);
            setBackgroundDrawable(a2.a(0));
            a2.a();
        }
    }
}
