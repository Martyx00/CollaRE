package com.facebook.react.views.textinput;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.g.t;
import android.view.ViewGroup;
import android.widget.EditText;
import com.facebook.i.a.a;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.ap;
import com.facebook.react.views.text.h;
import com.facebook.react.views.text.q;
import com.facebook.react.views.view.b;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import org.spongycastle.i18n.TextBundle;

@TargetApi(23)
/* compiled from: ReactTextInputShadowNode */
public class m extends h implements YogaMeasureFunction {
    private String A = null;
    private int w = -1;
    private EditText x;
    private k y;
    private String z = null;

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.uimanager.x
    public boolean isYogaLeafNode() {
        return true;
    }

    public m() {
        this.h = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        c();
    }

    private void c() {
        setMeasureFunction(this);
    }

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public void setThemedContext(af afVar) {
        super.setThemedContext(afVar);
        EditText editText = new EditText(getThemedContext());
        setDefaultPadding(4, (float) t.f(editText));
        setDefaultPadding(1, (float) editText.getPaddingTop());
        setDefaultPadding(5, (float) t.g(editText));
        setDefaultPadding(3, (float) editText.getPaddingBottom());
        this.x = editText;
        this.x.setPadding(0, 0, 0, 0);
        this.x.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        EditText editText = (EditText) a.a(this.x);
        k kVar = this.y;
        if (kVar != null) {
            kVar.a(editText);
        } else {
            editText.setTextSize(0, (float) this.f3442a.h());
            if (this.f != -1) {
                editText.setLines(this.f);
            }
            if (Build.VERSION.SDK_INT >= 23 && editText.getBreakStrategy() != this.h) {
                editText.setBreakStrategy(this.h);
            }
        }
        editText.setHint(b());
        editText.measure(b.a(f, yogaMeasureMode), b.a(f2, yogaMeasureMode2));
        return com.facebook.yoga.b.a(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public void setLocalData(Object obj) {
        a.a(obj instanceof k);
        this.y = (k) obj;
        dirty();
    }

    @com.facebook.react.uimanager.a.a(a = "mostRecentEventCount")
    public void setMostRecentEventCount(int i) {
        this.w = i;
    }

    @com.facebook.react.uimanager.a.a(a = TextBundle.TEXT_ENTRY)
    public void setText(String str) {
        this.z = str;
        markUpdated();
    }

    public String a() {
        return this.z;
    }

    @com.facebook.react.uimanager.a.a(a = "placeholder")
    public void setPlaceholder(String str) {
        this.A = str;
        markUpdated();
    }

    public String b() {
        return this.A;
    }

    @Override // com.facebook.react.views.text.h
    public void setTextBreakStrategy(String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (str == null || "simple".equals(str)) {
                this.h = 0;
            } else if ("highQuality".equals(str)) {
                this.h = 1;
            } else if ("balanced".equals(str)) {
                this.h = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + str);
            }
        }
    }

    @Override // com.facebook.react.uimanager.x
    public void onCollectExtraUpdates(ap apVar) {
        super.onCollectExtraUpdates(apVar);
        if (this.w != -1) {
            apVar.a(getReactTag(), new q(a(this, a()), this.w, this.u, getPadding(0), getPadding(1), getPadding(2), getPadding(3), this.g, this.h, this.i));
        }
    }

    @Override // com.facebook.react.uimanager.x
    public void setPadding(int i, float f) {
        super.setPadding(i, f);
        markUpdated();
    }
}
