package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import android.support.v4.d.d;

/* compiled from: ContentResolverCompat */
public final class b {
    public static Cursor a(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, android.support.v4.d.b bVar) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 16) {
            if (bVar != null) {
                try {
                    obj = bVar.d();
                } catch (Exception e) {
                    if (e instanceof OperationCanceledException) {
                        throw new d();
                    }
                    throw e;
                }
            } else {
                obj = null;
            }
            return contentResolver.query(uri, strArr, str, strArr2, str2, (CancellationSignal) obj);
        }
        if (bVar != null) {
            bVar.b();
        }
        return contentResolver.query(uri, strArr, str, strArr2, str2);
    }
}
