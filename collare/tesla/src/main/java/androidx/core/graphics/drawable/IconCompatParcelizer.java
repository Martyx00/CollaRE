package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.a;

public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f551a = aVar.b(iconCompat.f551a, 1);
        iconCompat.f553c = aVar.b(iconCompat.f553c, 2);
        iconCompat.f554d = aVar.b(iconCompat.f554d, 3);
        iconCompat.e = aVar.b(iconCompat.e, 4);
        iconCompat.f = aVar.b(iconCompat.f, 5);
        iconCompat.g = (ColorStateList) aVar.b(iconCompat.g, 6);
        iconCompat.j = aVar.b(iconCompat.j, 7);
        iconCompat.c();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.a(true, true);
        iconCompat.a(aVar.a());
        aVar.a(iconCompat.f551a, 1);
        aVar.a(iconCompat.f553c, 2);
        aVar.a(iconCompat.f554d, 3);
        aVar.a(iconCompat.e, 4);
        aVar.a(iconCompat.f, 5);
        aVar.a(iconCompat.g, 6);
        aVar.a(iconCompat.j, 7);
    }
}
