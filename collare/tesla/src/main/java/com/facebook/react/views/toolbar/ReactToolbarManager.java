package com.facebook.react.views.toolbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.g.t;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.uimanager.o;
import java.util.Map;

public class ReactToolbarManager extends ViewGroupManager<b> {
    private static final int COMMAND_DISMISS_POPUP_MENUS = 1;
    private static final String REACT_CLASS = "ToolbarAndroid";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public b createViewInstance(af afVar) {
        return new b(afVar);
    }

    @a(a = "logo")
    public void setLogo(b bVar, ReadableMap readableMap) {
        bVar.setLogoSource(readableMap);
    }

    @a(a = "navIcon")
    public void setNavIcon(b bVar, ReadableMap readableMap) {
        bVar.setNavIconSource(readableMap);
    }

    @a(a = "overflowIcon")
    public void setOverflowIcon(b bVar, ReadableMap readableMap) {
        bVar.setOverflowIconSource(readableMap);
    }

    @a(a = "rtl")
    public void setRtl(b bVar, boolean z) {
        t.b(bVar, z ? 1 : 0);
    }

    @a(a = "subtitle")
    public void setSubtitle(b bVar, String str) {
        bVar.setSubtitle(str);
    }

    @a(a = "subtitleColor", b = "Color")
    public void setSubtitleColor(b bVar, Integer num) {
        int[] defaultColors = getDefaultColors(bVar.getContext());
        if (num != null) {
            bVar.setSubtitleTextColor(num.intValue());
        } else {
            bVar.setSubtitleTextColor(defaultColors[1]);
        }
    }

    @a(a = "title")
    public void setTitle(b bVar, String str) {
        bVar.setTitle(str);
    }

    @a(a = "titleColor", b = "Color")
    public void setTitleColor(b bVar, Integer num) {
        int[] defaultColors = getDefaultColors(bVar.getContext());
        if (num != null) {
            bVar.setTitleTextColor(num.intValue());
        } else {
            bVar.setTitleTextColor(defaultColors[0]);
        }
    }

    @a(a = "contentInsetStart", d = Float.NaN)
    public void setContentInsetStart(b bVar, float f) {
        int i;
        if (Float.isNaN(f)) {
            i = getDefaultContentInsets(bVar.getContext())[0];
        } else {
            i = Math.round(o.a(f));
        }
        bVar.a(i, bVar.getContentInsetEnd());
    }

    @a(a = "contentInsetEnd", d = Float.NaN)
    public void setContentInsetEnd(b bVar, float f) {
        int i;
        if (Float.isNaN(f)) {
            i = getDefaultContentInsets(bVar.getContext())[1];
        } else {
            i = Math.round(o.a(f));
        }
        bVar.a(bVar.getContentInsetStart(), i);
    }

    @a(a = "nativeActions")
    public void setActions(b bVar, ReadableArray readableArray) {
        bVar.setActions(readableArray);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, final b bVar) {
        final d eventDispatcher = ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        bVar.setNavigationOnClickListener(new View.OnClickListener() {
            /* class com.facebook.react.views.toolbar.ReactToolbarManager.AnonymousClass1 */

            public void onClick(View view) {
                eventDispatcher.a(new com.facebook.react.views.toolbar.a.a(bVar.getId(), -1));
            }
        });
        bVar.setOnMenuItemClickListener(new Toolbar.c() {
            /* class com.facebook.react.views.toolbar.ReactToolbarManager.AnonymousClass2 */

            @Override // android.support.v7.widget.Toolbar.c
            public boolean a(MenuItem menuItem) {
                eventDispatcher.a(new com.facebook.react.views.toolbar.a.a(bVar.getId(), menuItem.getOrder()));
                return true;
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return e.a("ShowAsAction", e.a("never", 0, "always", 2, "ifRoom", 1));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return e.a("dismissPopupMenus", 1);
    }

    public void receiveCommand(b bVar, int i, ReadableArray readableArray) {
        if (i == 1) {
            bVar.f();
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), getClass().getSimpleName()));
        }
    }

    private int[] getDefaultContentInsets(Context context) {
        Throwable th;
        TypedArray typedArray;
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray2 = null;
        try {
            typedArray = theme.obtainStyledAttributes(new int[]{getIdentifier(context, "toolbarStyle")});
            try {
                typedArray2 = theme.obtainStyledAttributes(typedArray.getResourceId(0, 0), new int[]{getIdentifier(context, "contentInsetStart"), getIdentifier(context, "contentInsetEnd")});
                int[] iArr = {typedArray2.getDimensionPixelSize(0, 0), typedArray2.getDimensionPixelSize(1, 0)};
                recycleQuietly(typedArray);
                recycleQuietly(typedArray2);
                return iArr;
            } catch (Throwable th2) {
                th = th2;
                recycleQuietly(typedArray);
                recycleQuietly(typedArray2);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            typedArray = null;
            recycleQuietly(typedArray);
            recycleQuietly(typedArray2);
            throw th;
        }
    }

    private static int[] getDefaultColors(Context context) {
        TypedArray typedArray;
        TypedArray typedArray2;
        TypedArray typedArray3;
        Throwable th;
        TypedArray obtainStyledAttributes;
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray4 = null;
        try {
            typedArray3 = theme.obtainStyledAttributes(new int[]{getIdentifier(context, "toolbarStyle")});
            try {
                obtainStyledAttributes = theme.obtainStyledAttributes(typedArray3.getResourceId(0, 0), new int[]{getIdentifier(context, "titleTextAppearance"), getIdentifier(context, "subtitleTextAppearance")});
            } catch (Throwable th2) {
                th = th2;
                typedArray = null;
                typedArray2 = null;
                recycleQuietly(typedArray3);
                recycleQuietly(typedArray4);
                recycleQuietly(typedArray2);
                recycleQuietly(typedArray);
                throw th;
            }
            try {
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
                typedArray2 = theme.obtainStyledAttributes(resourceId, new int[]{16842904});
                try {
                    typedArray4 = theme.obtainStyledAttributes(resourceId2, new int[]{16842904});
                    int[] iArr = {typedArray2.getColor(0, -16777216), typedArray4.getColor(0, -16777216)};
                    recycleQuietly(typedArray3);
                    recycleQuietly(obtainStyledAttributes);
                    recycleQuietly(typedArray2);
                    recycleQuietly(typedArray4);
                    return iArr;
                } catch (Throwable th3) {
                    th = th3;
                    typedArray4 = obtainStyledAttributes;
                    typedArray = typedArray4;
                    recycleQuietly(typedArray3);
                    recycleQuietly(typedArray4);
                    recycleQuietly(typedArray2);
                    recycleQuietly(typedArray);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                typedArray2 = null;
                typedArray4 = obtainStyledAttributes;
                typedArray = null;
                recycleQuietly(typedArray3);
                recycleQuietly(typedArray4);
                recycleQuietly(typedArray2);
                recycleQuietly(typedArray);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            typedArray = null;
            typedArray3 = null;
            typedArray2 = null;
            recycleQuietly(typedArray3);
            recycleQuietly(typedArray4);
            recycleQuietly(typedArray2);
            recycleQuietly(typedArray);
            throw th;
        }
    }

    private static void recycleQuietly(TypedArray typedArray) {
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    private static int getIdentifier(Context context, String str) {
        return context.getResources().getIdentifier(str, "attr", context.getPackageName());
    }
}
