package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.g.t;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

public class ContentFrameLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private TypedValue f1060a;

    /* renamed from: b  reason: collision with root package name */
    private TypedValue f1061b;

    /* renamed from: c  reason: collision with root package name */
    private TypedValue f1062c;

    /* renamed from: d  reason: collision with root package name */
    private TypedValue f1063d;
    private TypedValue e;
    private TypedValue f;
    private final Rect g;
    private a h;

    public interface a {
        void a();

        void b();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new Rect();
    }

    public void a(Rect rect) {
        fitSystemWindows(rect);
    }

    public void setAttachListener(a aVar) {
        this.h = aVar;
    }

    public void a(int i, int i2, int i3, int i4) {
        this.g.set(i, i2, i3, i4);
        if (t.t(this)) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public TypedValue getMinWidthMajor() {
        if (this.f1060a == null) {
            this.f1060a = new TypedValue();
        }
        return this.f1060a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f1061b == null) {
            this.f1061b = new TypedValue();
        }
        return this.f1061b;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f1062c == null) {
            this.f1062c = new TypedValue();
        }
        return this.f1062c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f1063d == null) {
            this.f1063d = new TypedValue();
        }
        return this.f1063d;
    }

    public TypedValue getFixedHeightMajor() {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        return this.e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f == null) {
            this.f = new TypedValue();
        }
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.h;
        if (aVar != null) {
            aVar.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.h;
        if (aVar != null) {
            aVar.b();
        }
    }
}
