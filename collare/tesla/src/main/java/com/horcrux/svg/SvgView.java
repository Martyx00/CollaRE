package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.r;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.uimanager.events.g;
import com.facebook.react.uimanager.events.h;
import com.facebook.react.uimanager.events.i;
import com.facebook.react.uimanager.x;
import com.facebook.react.views.view.f;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@SuppressLint({"ViewConstructor"})
public class SvgView extends ViewGroup {
    private Bitmap mBitmap;
    private final d mEventDispatcher;
    private long mGestureStartTime = Long.MIN_VALUE;
    private int mTargetTag;
    private final h mTouchEventCoalescingKeyHelper = new h();

    public enum Events {
        EVENT_DATA_URL("onDataURL");
        
        private final String mName;

        private Events(String str) {
            this.mName = str;
        }

        public String toString() {
            return this.mName;
        }
    }

    public SvgView(ReactContext reactContext) {
        super(reactContext);
        this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
    }

    public void setId(int i) {
        super.setId(i);
        SvgViewManager.setSvgView(this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SvgViewManager.dropSvgView(this);
    }

    public void setBitmap(Bitmap bitmap) {
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.mBitmap = bitmap;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
        }
    }

    private SvgViewShadowNode getShadowNode() {
        return SvgViewManager.getShadowNodeByTag(getId());
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        SvgViewShadowNode shadowNode = getShadowNode();
        if (shadowNode != null) {
            this.mTargetTag = shadowNode.hitTest(new Point((int) motionEvent.getX(), (int) motionEvent.getY()));
            if (this.mTargetTag != -1) {
                handleTouchEvent(motionEvent);
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        SvgViewShadowNode shadowNode = getShadowNode();
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt instanceof f) {
                int id = childAt.getId();
                int i6 = 0;
                while (true) {
                    if (i6 >= shadowNode.getChildCount()) {
                        break;
                    }
                    x childAt2 = shadowNode.getChildAt(i6);
                    if (childAt2.getReactTag() == id) {
                        float layoutX = childAt2.getLayoutX();
                        float layoutY = childAt2.getLayoutY();
                        childAt.layout(Math.round(layoutX), Math.round(layoutY), Math.round(childAt2.getLayoutWidth() + layoutX), Math.round(childAt2.getLayoutHeight() + layoutY));
                        break;
                    }
                    i6++;
                }
            }
        }
    }

    private int getAbsoluteLeft(View view) {
        int left = view.getLeft() - view.getScrollX();
        return (view.getParent() == view.getRootView() || (view.getParent() instanceof r)) ? left : left + getAbsoluteLeft((View) view.getParent());
    }

    private int getAbsoluteTop(View view) {
        int top = view.getTop() - view.getScrollY();
        return (view.getParent() == view.getRootView() || (view.getParent() instanceof r)) ? top : top + getAbsoluteTop((View) view.getParent());
    }

    private void dispatch(MotionEvent motionEvent, i iVar) {
        motionEvent.offsetLocation((float) getAbsoluteLeft(this), (float) getAbsoluteTop(this));
        this.mEventDispatcher.a(g.a(this.mTargetTag, iVar, motionEvent, this.mGestureStartTime, motionEvent.getX(), motionEvent.getY(), this.mTouchEventCoalescingKeyHelper));
    }

    private void handleTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.mGestureStartTime = motionEvent.getEventTime();
            dispatch(motionEvent, i.START);
        } else if (this.mTargetTag == -1) {
            Log.e("error", "Unexpected state: received touch event but didn't get starting ACTION_DOWN for this gesture before");
        } else if (action == 1) {
            dispatch(motionEvent, i.END);
            this.mTargetTag = -1;
            this.mGestureStartTime = Long.MIN_VALUE;
        } else if (action == 2) {
            dispatch(motionEvent, i.MOVE);
        } else if (action == 5) {
            dispatch(motionEvent, i.START);
        } else if (action == 6) {
            dispatch(motionEvent, i.END);
        } else if (action == 3) {
            dispatchCancelEvent(motionEvent);
            this.mTargetTag = -1;
            this.mGestureStartTime = Long.MIN_VALUE;
        } else {
            Log.w("IGNORE", "Warning : touch event was ignored. Action=" + action + " Target=" + this.mTargetTag);
        }
    }

    private void dispatchCancelEvent(MotionEvent motionEvent) {
        if (this.mTargetTag == -1) {
            Log.w("error", "Can't cancel already finished gesture. Is a child View trying to start a gesture from an UP/CANCEL event?");
        } else {
            dispatch(motionEvent, i.CANCEL);
        }
    }
}
