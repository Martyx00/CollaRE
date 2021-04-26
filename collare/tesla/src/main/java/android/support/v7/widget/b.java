package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

/* compiled from: ActionBarBackgroundDrawable */
class b extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    final ActionBarContainer f1215a;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public b(ActionBarContainer actionBarContainer) {
        this.f1215a = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        if (!this.f1215a.f1028d) {
            if (this.f1215a.f1025a != null) {
                this.f1215a.f1025a.draw(canvas);
            }
            if (this.f1215a.f1026b != null && this.f1215a.e) {
                this.f1215a.f1026b.draw(canvas);
            }
        } else if (this.f1215a.f1027c != null) {
            this.f1215a.f1027c.draw(canvas);
        }
    }

    public void getOutline(Outline outline) {
        if (this.f1215a.f1028d) {
            if (this.f1215a.f1027c != null) {
                this.f1215a.f1027c.getOutline(outline);
            }
        } else if (this.f1215a.f1025a != null) {
            this.f1215a.f1025a.getOutline(outline);
        }
    }
}
