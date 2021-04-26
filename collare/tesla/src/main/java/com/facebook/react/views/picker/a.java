package com.facebook.react.views.picker;

import android.content.Context;
import android.support.v7.widget.x;
import android.view.View;
import android.widget.AdapterView;

/* compiled from: ReactPicker */
public class a extends x {

    /* renamed from: d  reason: collision with root package name */
    private int f3363d = 0;
    private Integer e;
    private AbstractC0058a f;
    private Integer g;
    private final AdapterView.OnItemSelectedListener h = new AdapterView.OnItemSelectedListener() {
        /* class com.facebook.react.views.picker.a.AnonymousClass1 */

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (a.this.f != null) {
                a.this.f.a(i);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (a.this.f != null) {
                a.this.f.a(-1);
            }
        }
    };
    private final Runnable i = new Runnable() {
        /* class com.facebook.react.views.picker.a.AnonymousClass2 */

        public void run() {
            a aVar = a.this;
            aVar.measure(View.MeasureSpec.makeMeasureSpec(aVar.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(a.this.getHeight(), 1073741824));
            a aVar2 = a.this;
            aVar2.layout(aVar2.getLeft(), a.this.getTop(), a.this.getRight(), a.this.getBottom());
        }
    };

    /* renamed from: com.facebook.react.views.picker.a$a  reason: collision with other inner class name */
    /* compiled from: ReactPicker */
    public interface AbstractC0058a {
        void a(int i);
    }

    public a(Context context, int i2) {
        super(context, i2);
        this.f3363d = i2;
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.i);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (getOnItemSelectedListener() == null) {
            setOnItemSelectedListener(this.h);
        }
    }

    public void setOnSelectListener(AbstractC0058a aVar) {
        this.f = aVar;
    }

    public AbstractC0058a getOnSelectListener() {
        return this.f;
    }

    public void setStagedSelection(int i2) {
        this.g = Integer.valueOf(i2);
    }

    public void a() {
        Integer num = this.g;
        if (num != null) {
            setSelectionWithSuppressEvent(num.intValue());
            this.g = null;
        }
    }

    private void setSelectionWithSuppressEvent(int i2) {
        if (i2 != getSelectedItemPosition()) {
            setOnItemSelectedListener(null);
            setSelection(i2, false);
            setOnItemSelectedListener(this.h);
        }
    }

    public Integer getPrimaryColor() {
        return this.e;
    }

    public void setPrimaryColor(Integer num) {
        this.e = num;
    }

    public int getMode() {
        return this.f3363d;
    }
}
