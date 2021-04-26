package android.support.v7.widget;

import android.content.Context;
import android.support.v4.widget.p;
import android.support.v7.b.a.a;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* compiled from: AppCompatCheckedTextView */
public class i extends CheckedTextView {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1249a = {16843016};

    /* renamed from: b  reason: collision with root package name */
    private final y f1250b;

    public i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public i(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.f1250b = new y(this);
        this.f1250b.a(attributeSet, i);
        this.f1250b.a();
        av a2 = av.a(getContext(), attributeSet, f1249a, i, 0);
        setCheckMarkDrawable(a2.a(0));
        a2.a();
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(a.b(getContext(), i));
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f1250b;
        if (yVar != null) {
            yVar.a(context, i);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        y yVar = this.f1250b;
        if (yVar != null) {
            yVar.a();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(p.a(this, callback));
    }
}
