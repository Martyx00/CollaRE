package android.support.v4.g;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: PagerAdapter */
public abstract class r {

    /* renamed from: a  reason: collision with root package name */
    private final DataSetObservable f500a = new DataSetObservable();

    /* renamed from: b  reason: collision with root package name */
    private DataSetObserver f501b;

    public float a(int i) {
        return 1.0f;
    }

    public abstract int a();

    public int a(Object obj) {
        return -1;
    }

    public void a(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Deprecated
    public void a(View view) {
    }

    public abstract boolean a(View view, Object obj);

    public Parcelable b() {
        return null;
    }

    @Deprecated
    public void b(View view) {
    }

    @Deprecated
    public void b(View view, int i, Object obj) {
    }

    public void a(ViewGroup viewGroup) {
        a((View) viewGroup);
    }

    public Object a(ViewGroup viewGroup, int i) {
        return a((View) viewGroup, i);
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        a((View) viewGroup, i, obj);
    }

    public void b(ViewGroup viewGroup, int i, Object obj) {
        b((View) viewGroup, i, obj);
    }

    public void b(ViewGroup viewGroup) {
        b((View) viewGroup);
    }

    @Deprecated
    public Object a(View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    @Deprecated
    public void a(View view, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void c() {
        synchronized (this) {
            if (this.f501b != null) {
                this.f501b.onChanged();
            }
        }
        this.f500a.notifyChanged();
    }

    /* access modifiers changed from: package-private */
    public void a(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.f501b = dataSetObserver;
        }
    }
}
