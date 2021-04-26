package com.facebook.react.uimanager;

import com.facebook.react.uimanager.w;
import com.facebook.yoga.YogaDirection;

/* compiled from: ReactShadowNode */
public interface w<T extends w> {
    void addChildAt(T t, int i);

    void addNativeChildAt(T t, int i);

    void calculateLayout();

    void dirty();

    boolean dispatchUpdates(float f, float f2, ap apVar, l lVar);

    void dispose();

    T getChildAt(int i);

    int getChildCount();

    float getLayoutX();

    float getLayoutY();

    int getNativeChildCount();

    int getNativeOffsetForChild(T t);

    T getNativeParent();

    T getParent();

    int getReactTag();

    int getRootTag();

    int getScreenHeight();

    int getScreenWidth();

    int getScreenX();

    int getScreenY();

    af getThemedContext();

    String getViewClass();

    boolean hasUpdates();

    int indexOf(T t);

    int indexOfNativeChild(T t);

    boolean isDescendantOf(T t);

    boolean isLayoutOnly();

    boolean isVirtual();

    boolean isVirtualAnchor();

    void markUpdateSeen();

    void onBeforeLayout();

    void removeAllNativeChildren();

    void removeAndDisposeAllChildren();

    T removeChildAt(int i);

    T removeNativeChildAt(int i);

    void setIsLayoutOnly(boolean z);

    void setLayoutDirection(YogaDirection yogaDirection);

    void setLocalData(Object obj);

    void setReactTag(int i);

    void setRootTag(int i);

    void setStyleHeight(float f);

    void setStyleHeightAuto();

    void setStyleMaxHeight(float f);

    void setStyleMaxWidth(float f);

    void setStyleWidth(float f);

    void setStyleWidthAuto();

    void setThemedContext(af afVar);

    void setViewClassName(String str);

    boolean shouldNotifyOnLayout();

    void updateProperties(y yVar);
}
