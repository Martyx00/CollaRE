package com.horcrux.svg;

import android.graphics.Paint;
import android.graphics.Path;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
/* compiled from: GlyphPathBag */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<Path> f4637a = new ArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    private final int[][] f4638b = new int[256][];

    /* renamed from: c  reason: collision with root package name */
    private final Paint f4639c;

    n(Paint paint) {
        this.f4639c = paint;
        this.f4637a.add(new Path());
    }

    /* access modifiers changed from: package-private */
    public Path a(char c2, String str) {
        Path path;
        int a2 = a(c2);
        if (a2 != 0) {
            path = this.f4637a.get(a2);
        } else {
            Path path2 = new Path();
            this.f4639c.getTextPath(str, 0, 1, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, path2);
            int[][] iArr = this.f4638b;
            int i = c2 >> '\b';
            int[] iArr2 = iArr[i];
            if (iArr2 == null) {
                iArr2 = new int[256];
                iArr[i] = iArr2;
            }
            iArr2[c2 & 255] = this.f4637a.size();
            this.f4637a.add(path2);
            path = path2;
        }
        Path path3 = new Path();
        path3.addPath(path);
        return path3;
    }

    private int a(char c2) {
        int[] iArr = this.f4638b[c2 >> '\b'];
        if (iArr == null) {
            return 0;
        }
        return iArr[c2 & 255];
    }
}
