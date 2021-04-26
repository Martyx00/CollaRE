package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.e;
import android.support.v4.d.b;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* compiled from: CursorLoader */
public class d extends a<Cursor> {
    final e<Cursor>.a f = new e.a();
    Uri g;
    String[] h;
    String i;
    String[] j;
    String k;
    Cursor l;
    b m;

    /* renamed from: h */
    public Cursor d() {
        synchronized (this) {
            if (!g()) {
                this.m = new b();
            } else {
                throw new android.support.v4.d.d();
            }
        }
        try {
            Cursor a2 = b.a(m().getContentResolver(), this.g, this.h, this.i, this.j, this.k, this.m);
            if (a2 != null) {
                try {
                    a2.getCount();
                    a2.registerContentObserver(this.f);
                } catch (RuntimeException e) {
                    a2.close();
                    throw e;
                }
            }
            synchronized (this) {
                this.m = null;
            }
            return a2;
        } catch (Throwable th) {
            synchronized (this) {
                this.m = null;
                throw th;
            }
        }
    }

    @Override // android.support.v4.content.a
    public void f() {
        super.f();
        synchronized (this) {
            if (this.m != null) {
                this.m.c();
            }
        }
    }

    /* renamed from: a */
    public void b(Cursor cursor) {
        if (!p()) {
            Cursor cursor2 = this.l;
            this.l = cursor;
            if (n()) {
                super.b((Object) cursor);
            }
            if (cursor2 != null && cursor2 != cursor && !cursor2.isClosed()) {
                cursor2.close();
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    public d(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        super(context);
        this.g = uri;
        this.h = strArr;
        this.i = str;
        this.j = strArr2;
        this.k = str2;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.content.e
    public void i() {
        Cursor cursor = this.l;
        if (cursor != null) {
            b(cursor);
        }
        if (x() || this.l == null) {
            s();
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.content.e
    public void j() {
        r();
    }

    /* renamed from: b */
    public void a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.content.e
    public void k() {
        super.k();
        j();
        Cursor cursor = this.l;
        if (cursor != null && !cursor.isClosed()) {
            this.l.close();
        }
        this.l = null;
    }

    @Override // android.support.v4.content.e, android.support.v4.content.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.a(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.g);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.h));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.i);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.j));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.k);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.l);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.u);
    }
}
