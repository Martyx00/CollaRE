package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.widget.g;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

/* compiled from: CursorAdapter */
public abstract class f extends BaseAdapter implements g.a, Filterable {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f712a;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f713b;

    /* renamed from: c  reason: collision with root package name */
    protected Cursor f714c;

    /* renamed from: d  reason: collision with root package name */
    protected Context f715d;
    protected int e;
    protected a f;
    protected DataSetObserver g;
    protected g h;
    protected FilterQueryProvider i;

    public abstract View a(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract void a(View view, Context context, Cursor cursor);

    public boolean hasStableIds() {
        return true;
    }

    public f(Context context, Cursor cursor, boolean z) {
        a(context, cursor, z ? 1 : 2);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, Cursor cursor, int i2) {
        boolean z = false;
        if ((i2 & 1) == 1) {
            i2 |= 2;
            this.f713b = true;
        } else {
            this.f713b = false;
        }
        if (cursor != null) {
            z = true;
        }
        this.f714c = cursor;
        this.f712a = z;
        this.f715d = context;
        this.e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i2 & 2) == 2) {
            this.f = new a();
            this.g = new b();
        } else {
            this.f = null;
            this.g = null;
        }
        if (z) {
            a aVar = this.f;
            if (aVar != null) {
                cursor.registerContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    @Override // android.support.v4.widget.g.a
    public Cursor a() {
        return this.f714c;
    }

    public int getCount() {
        Cursor cursor;
        if (!this.f712a || (cursor = this.f714c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.f712a || (cursor = this.f714c) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f714c;
    }

    public long getItemId(int i2) {
        Cursor cursor;
        if (!this.f712a || (cursor = this.f714c) == null || !cursor.moveToPosition(i2)) {
            return 0;
        }
        return this.f714c.getLong(this.e);
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f712a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.f714c.moveToPosition(i2)) {
            if (view == null) {
                view = a(this.f715d, this.f714c, viewGroup);
            }
            a(view, this.f715d, this.f714c);
            return view;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i2);
        }
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f712a) {
            return null;
        }
        this.f714c.moveToPosition(i2);
        if (view == null) {
            view = b(this.f715d, this.f714c, viewGroup);
        }
        a(view, this.f715d, this.f714c);
        return view;
    }

    public View b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return a(context, cursor, viewGroup);
    }

    @Override // android.support.v4.widget.g.a
    public void a(Cursor cursor) {
        Cursor b2 = b(cursor);
        if (b2 != null) {
            b2.close();
        }
    }

    public Cursor b(Cursor cursor) {
        Cursor cursor2 = this.f714c;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            a aVar = this.f;
            if (aVar != null) {
                cursor2.unregisterContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f714c = cursor;
        if (cursor != null) {
            a aVar2 = this.f;
            if (aVar2 != null) {
                cursor.registerContentObserver(aVar2);
            }
            DataSetObserver dataSetObserver2 = this.g;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.e = cursor.getColumnIndexOrThrow("_id");
            this.f712a = true;
            notifyDataSetChanged();
        } else {
            this.e = -1;
            this.f712a = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.support.v4.widget.g.a
    public CharSequence c(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    @Override // android.support.v4.widget.g.a
    public Cursor a(CharSequence charSequence) {
        FilterQueryProvider filterQueryProvider = this.i;
        if (filterQueryProvider != null) {
            return filterQueryProvider.runQuery(charSequence);
        }
        return this.f714c;
    }

    public Filter getFilter() {
        if (this.h == null) {
            this.h = new g(this);
        }
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void b() {
        Cursor cursor;
        if (this.f713b && (cursor = this.f714c) != null && !cursor.isClosed()) {
            this.f712a = this.f714c.requery();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: CursorAdapter */
    public class a extends ContentObserver {
        public boolean deliverSelfNotifications() {
            return true;
        }

        a() {
            super(new Handler());
        }

        public void onChange(boolean z) {
            f.this.b();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: CursorAdapter */
    public class b extends DataSetObserver {
        b() {
        }

        public void onChanged() {
            f fVar = f.this;
            fVar.f712a = true;
            fVar.notifyDataSetChanged();
        }

        public void onInvalidated() {
            f fVar = f.this;
            fVar.f712a = false;
            fVar.notifyDataSetInvalidated();
        }
    }
}
