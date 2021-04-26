package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.v4.content.a.c;
import android.support.v4.e.b;
import com.google.android.gms.common.api.Api;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* access modifiers changed from: package-private */
/* compiled from: TypefaceCompatBaseImpl */
public class h {

    /* access modifiers changed from: private */
    /* compiled from: TypefaceCompatBaseImpl */
    public interface a<T> {
        boolean a(T t);

        int b(T t);
    }

    h() {
    }

    private static <T> T a(T[] tArr, int i, a<T> aVar) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        for (T t2 : tArr) {
            int abs = (Math.abs(aVar.b(t2) - i2) * 2) + (aVar.a(t2) == z ? 0 : 1);
            if (t == null || i3 > abs) {
                t = t2;
                i3 = abs;
            }
        }
        return t;
    }

    /* access modifiers changed from: protected */
    public b.C0008b a(b.C0008b[] bVarArr, int i) {
        return (b.C0008b) a(bVarArr, i, new a<b.C0008b>() {
            /* class android.support.v4.graphics.h.AnonymousClass1 */

            /* renamed from: a */
            public int b(b.C0008b bVar) {
                return bVar.c();
            }

            /* renamed from: b */
            public boolean a(b.C0008b bVar) {
                return bVar.d();
            }
        });
    }

    /* access modifiers changed from: protected */
    public Typeface a(Context context, InputStream inputStream) {
        File a2 = i.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (!i.a(a2, inputStream)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(a2.getPath());
            a2.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }

    public Typeface a(Context context, CancellationSignal cancellationSignal, b.C0008b[] bVarArr, int i) {
        InputStream inputStream;
        Throwable th;
        InputStream inputStream2 = null;
        if (bVarArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(a(bVarArr, i).a());
            try {
                Typeface a2 = a(context, inputStream);
                i.a(inputStream);
                return a2;
            } catch (IOException unused) {
                i.a(inputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                inputStream2 = inputStream;
                i.a(inputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            inputStream = null;
            i.a(inputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            i.a(inputStream2);
            throw th;
        }
    }

    private c.C0006c a(c.b bVar, int i) {
        return (c.C0006c) a(bVar.a(), i, new a<c.C0006c>() {
            /* class android.support.v4.graphics.h.AnonymousClass2 */

            /* renamed from: a */
            public int b(c.C0006c cVar) {
                return cVar.b();
            }

            /* renamed from: b */
            public boolean a(c.C0006c cVar) {
                return cVar.c();
            }
        });
    }

    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        c.C0006c a2 = a(bVar, i);
        if (a2 == null) {
            return null;
        }
        return c.a(context, resources, a2.f(), a2.a(), i);
    }

    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        File a2 = i.a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (!i.a(a2, resources, i)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(a2.getPath());
            a2.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            a2.delete();
        }
    }
}
