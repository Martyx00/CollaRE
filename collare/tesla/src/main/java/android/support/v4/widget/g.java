package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;

/* compiled from: CursorFilter */
class g extends Filter {

    /* renamed from: a  reason: collision with root package name */
    a f718a;

    /* compiled from: CursorFilter */
    interface a {
        Cursor a();

        Cursor a(CharSequence charSequence);

        void a(Cursor cursor);

        CharSequence c(Cursor cursor);
    }

    g(a aVar) {
        this.f718a = aVar;
    }

    public CharSequence convertResultToString(Object obj) {
        return this.f718a.c((Cursor) obj);
    }

    /* access modifiers changed from: protected */
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor a2 = this.f718a.a(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (a2 != null) {
            filterResults.count = a2.getCount();
            filterResults.values = a2;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        Cursor a2 = this.f718a.a();
        if (filterResults.values != null && filterResults.values != a2) {
            this.f718a.a((Cursor) filterResults.values);
        }
    }
}
