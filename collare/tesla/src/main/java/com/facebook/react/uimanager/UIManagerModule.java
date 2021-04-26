package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.util.ArrayMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.webrtc.MediaStreamTrack;

@com.facebook.react.module.a.a(a = UIManagerModule.NAME)
public class UIManagerModule extends ReactContextBaseJavaModule implements LifecycleEventListener, OnBatchCompleteListener, UIManager {
    private static final boolean DEBUG = com.facebook.d.b.c.a().a(com.facebook.d.c.a.f);
    public static final String NAME = "UIManager";
    private int mBatchId;
    private final Map<String, Object> mCustomDirectEvents;
    private final d mEventDispatcher;
    private final List<ao> mListeners;
    private final b mMemoryTrimCallback;
    private final Map<String, Object> mModuleConstants;
    private final aj mUIImplementation;
    private Map<String, WritableMap> mViewManagerConstantsCache;
    private volatile int mViewManagerConstantsCacheSize;
    private final at mViewManagerRegistry;

    public interface a {
        String a(String str);
    }

    public interface c {
        ViewManager a(String str);

        List<String> a();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, c cVar, int i) {
        this(reactApplicationContext, cVar, new ak(), i);
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, int i) {
        this(reactApplicationContext, list, new ak(), i);
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, c cVar, ak akVar, int i) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new b();
        this.mListeners = new ArrayList();
        this.mBatchId = 0;
        c.a(reactApplicationContext);
        this.mEventDispatcher = new d(reactApplicationContext);
        this.mModuleConstants = createConstants(cVar);
        this.mCustomDirectEvents = am.b();
        this.mViewManagerRegistry = new at(cVar);
        this.mUIImplementation = akVar.a(reactApplicationContext, this.mViewManagerRegistry, this.mEventDispatcher, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, ak akVar, int i) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new b();
        this.mListeners = new ArrayList();
        this.mBatchId = 0;
        c.a(reactApplicationContext);
        this.mEventDispatcher = new d(reactApplicationContext);
        this.mCustomDirectEvents = e.a();
        this.mModuleConstants = createConstants(list, null, this.mCustomDirectEvents);
        this.mViewManagerRegistry = new at(list);
        this.mUIImplementation = akVar.a(reactApplicationContext, this.mViewManagerRegistry, this.mEventDispatcher, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public aj getUIImplementation() {
        return this.mUIImplementation;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.bridge.JSIModule
    public void initialize() {
        getReactApplicationContext().registerComponentCallbacks(this.mMemoryTrimCallback);
        this.mEventDispatcher.a(1, (RCTEventEmitter) getReactApplicationContext().getJSModule(RCTEventEmitter.class));
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mUIImplementation.g();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.mUIImplementation.h();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.mUIImplementation.i();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.bridge.JSIModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mEventDispatcher.b();
        getReactApplicationContext().unregisterComponentCallbacks(this.mMemoryTrimCallback);
        aw.a().b();
        as.a();
    }

    @Deprecated
    public at getViewManagerRegistry_DO_NOT_USE() {
        return this.mViewManagerRegistry;
    }

    private static Map<String, Object> createConstants(c cVar) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        com.facebook.systrace.b.a(0, "CreateUIManagerConstants").a("Lazy", (Object) true).a();
        try {
            return an.a(cVar);
        } finally {
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    private static Map<String, Object> createConstants(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        com.facebook.systrace.b.a(0, "CreateUIManagerConstants").a("Lazy", (Object) false).a();
        try {
            return an.a(list, map, map2);
        } finally {
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    @Deprecated
    public void preComputeConstantsForViewManager(List<String> list) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : list) {
            WritableMap computeConstantsForViewManager = computeConstantsForViewManager(str);
            if (computeConstantsForViewManager != null) {
                arrayMap.put(str, computeConstantsForViewManager);
            }
        }
        this.mViewManagerConstantsCacheSize = list.size();
        this.mViewManagerConstantsCache = Collections.unmodifiableMap(arrayMap);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getConstantsForViewManager(String str) {
        Map<String, WritableMap> map = this.mViewManagerConstantsCache;
        if (map == null || !map.containsKey(str)) {
            return computeConstantsForViewManager(str);
        }
        WritableMap writableMap = this.mViewManagerConstantsCache.get(str);
        int i = this.mViewManagerConstantsCacheSize - 1;
        this.mViewManagerConstantsCacheSize = i;
        if (i <= 0) {
            this.mViewManagerConstantsCache = null;
        }
        return writableMap;
    }

    private WritableMap computeConstantsForViewManager(String str) {
        ViewManager b2 = str != null ? this.mUIImplementation.b(str) : null;
        if (b2 == null) {
            return null;
        }
        com.facebook.systrace.b.a(0, "UIManagerModule.getConstantsForViewManager").a("ViewManager", b2.getName()).a("Lazy", (Object) true).a();
        try {
            Map<String, Object> a2 = an.a(b2, null, null, null, this.mCustomDirectEvents);
            if (a2 != null) {
                return Arguments.makeNativeMap(a2);
            }
            com.facebook.systrace.b.a(0).a();
            return null;
        } finally {
            com.facebook.systrace.b.a(0).a();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getDefaultEventTypes() {
        return Arguments.makeNativeMap(an.a());
    }

    public a getDirectEventNamesResolver() {
        return new a() {
            /* class com.facebook.react.uimanager.UIManagerModule.AnonymousClass1 */

            @Override // com.facebook.react.uimanager.UIManagerModule.a
            public String a(String str) {
                Map map = (Map) UIManagerModule.this.mCustomDirectEvents.get(str);
                return map != null ? (String) map.get("registrationName") : str;
            }
        };
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
        this.mUIImplementation.b();
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        return this.mUIImplementation.c();
    }

    public <T extends SizeMonitoringFrameLayout & com.facebook.react.uimanager.common.a> int addRootView(T t) {
        return addRootView(t, null, null);
    }

    @Override // com.facebook.react.bridge.UIManager
    public <T extends SizeMonitoringFrameLayout & com.facebook.react.uimanager.common.a> int addRootView(T t, WritableMap writableMap, String str) {
        com.facebook.systrace.a.a(0, "UIManagerModule.addRootView");
        final int a2 = v.a();
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        this.mUIImplementation.a(t, a2, new af(reactApplicationContext, t.getContext()));
        t.setOnSizeChangedListener(new SizeMonitoringFrameLayout.a() {
            /* class com.facebook.react.uimanager.UIManagerModule.AnonymousClass2 */

            @Override // com.facebook.react.uimanager.common.SizeMonitoringFrameLayout.a
            public void a(final int i, final int i2, int i3, int i4) {
                ReactApplicationContext reactApplicationContext = reactApplicationContext;
                reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) {
                    /* class com.facebook.react.uimanager.UIManagerModule.AnonymousClass2.AnonymousClass1 */

                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        UIManagerModule.this.updateNodeSize(a2, i, i2);
                    }
                });
            }
        });
        com.facebook.systrace.a.b(0);
        return a2;
    }

    @Override // com.facebook.react.bridge.UIManager
    @ReactMethod
    public void removeRootView(int i) {
        this.mUIImplementation.b(i);
    }

    public void updateNodeSize(int i, int i2, int i3) {
        getReactApplicationContext().assertOnNativeModulesQueueThread();
        this.mUIImplementation.b(i, i2, i3);
    }

    public void setViewLocalData(final int i, final Object obj) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.assertOnUiQueueThread();
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) {
            /* class com.facebook.react.uimanager.UIManagerModule.AnonymousClass3 */

            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIManagerModule.this.mUIImplementation.a(i, obj);
            }
        });
    }

    @ReactMethod
    public void createView(int i, String str, int i2, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.createView) tag: " + i + ", class: " + str + ", props: " + readableMap;
            com.facebook.common.e.a.a("ReactNative", str2);
            com.facebook.d.b.c.a().a(com.facebook.d.c.a.f, str2);
        }
        this.mUIImplementation.a(i, str, i2, readableMap);
    }

    @ReactMethod
    public void updateView(int i, String str, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.updateView) tag: " + i + ", class: " + str + ", props: " + readableMap;
            com.facebook.common.e.a.a("ReactNative", str2);
            com.facebook.d.b.c.a().a(com.facebook.d.c.a.f, str2);
        }
        this.mUIImplementation.a(i, str, readableMap);
    }

    @ReactMethod
    public void manageChildren(int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        if (DEBUG) {
            String str = "(UIManager.manageChildren) tag: " + i + ", moveFrom: " + readableArray + ", moveTo: " + readableArray2 + ", addTags: " + readableArray3 + ", atIndices: " + readableArray4 + ", removeFrom: " + readableArray5;
            com.facebook.common.e.a.a("ReactNative", str);
            com.facebook.d.b.c.a().a(com.facebook.d.c.a.f, str);
        }
        this.mUIImplementation.a(i, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }

    @ReactMethod
    public void setChildren(int i, ReadableArray readableArray) {
        if (DEBUG) {
            String str = "(UIManager.setChildren) tag: " + i + ", children: " + readableArray;
            com.facebook.common.e.a.a("ReactNative", str);
            com.facebook.d.b.c.a().a(com.facebook.d.c.a.f, str);
        }
        this.mUIImplementation.a(i, readableArray);
    }

    @ReactMethod
    public void replaceExistingNonRootView(int i, int i2) {
        this.mUIImplementation.a(i, i2);
    }

    @ReactMethod
    public void removeSubviewsFromContainerWithID(int i) {
        this.mUIImplementation.d(i);
    }

    @ReactMethod
    public void measure(int i, Callback callback) {
        this.mUIImplementation.a(i, callback);
    }

    @ReactMethod
    public void measureInWindow(int i, Callback callback) {
        this.mUIImplementation.b(i, callback);
    }

    @ReactMethod
    public void measureLayout(int i, int i2, Callback callback, Callback callback2) {
        this.mUIImplementation.a(i, i2, callback, callback2);
    }

    @ReactMethod
    public void measureLayoutRelativeToParent(int i, Callback callback, Callback callback2) {
        this.mUIImplementation.a(i, callback, callback2);
    }

    @ReactMethod
    public void findSubviewIn(int i, ReadableArray readableArray, Callback callback) {
        this.mUIImplementation.a(i, (float) Math.round(o.a(readableArray.getDouble(0))), (float) Math.round(o.a(readableArray.getDouble(1))), callback);
    }

    @ReactMethod
    public void viewIsDescendantOf(int i, int i2, Callback callback) {
        this.mUIImplementation.a(i, i2, callback);
    }

    public void registerAnimation(com.facebook.react.a.a aVar) {
        this.mUIImplementation.a(aVar);
    }

    public void addAnimation(int i, int i2, Callback callback) {
        this.mUIImplementation.b(i, i2, callback);
    }

    public void removeAnimation(int i, int i2) {
        this.mUIImplementation.b(i, i2);
    }

    @Override // com.facebook.react.bridge.UIManager
    @ReactMethod
    public void setJSResponder(int i, boolean z) {
        this.mUIImplementation.a(i, z);
    }

    @Override // com.facebook.react.bridge.UIManager
    @ReactMethod
    public void clearJSResponder() {
        this.mUIImplementation.e();
    }

    @ReactMethod
    public void dispatchViewManagerCommand(int i, int i2, ReadableArray readableArray) {
        al.a(getReactApplicationContext(), com.facebook.react.uimanager.common.b.a(i)).dispatchCommand(i, i2, readableArray);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void dispatchCommand(int i, int i2, ReadableArray readableArray) {
        this.mUIImplementation.a(i, i2, readableArray);
    }

    @ReactMethod
    public void playTouchSound() {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (audioManager != null) {
            audioManager.playSoundEffect(0);
        }
    }

    @ReactMethod
    public void showPopupMenu(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
        this.mUIImplementation.a(i, readableArray, callback, callback2);
    }

    @ReactMethod
    public void dismissPopupMenu() {
        this.mUIImplementation.f();
    }

    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean z) {
        this.mUIImplementation.a(z);
    }

    @ReactMethod
    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.mUIImplementation.a(readableMap, callback, callback2);
    }

    @Override // com.facebook.react.bridge.OnBatchCompleteListener
    public void onBatchComplete() {
        int i = this.mBatchId;
        this.mBatchId = i + 1;
        com.facebook.systrace.b.a(0, "onBatchCompleteUI").a("BatchId", i).a();
        for (ao aoVar : this.mListeners) {
            aoVar.willDispatchViewUpdates(this);
        }
        try {
            this.mUIImplementation.e(i);
        } finally {
            com.facebook.systrace.a.b(0);
        }
    }

    public void setViewHierarchyUpdateDebugListener(com.facebook.react.uimanager.b.a aVar) {
        this.mUIImplementation.a(aVar);
    }

    public d getEventDispatcher() {
        return this.mEventDispatcher;
    }

    @ReactMethod
    public void sendAccessibilityEvent(int i, int i2) {
        this.mUIImplementation.c(i, i2);
    }

    public void addUIBlock(ai aiVar) {
        this.mUIImplementation.a(aiVar);
    }

    public void prependUIBlock(ai aiVar) {
        this.mUIImplementation.b(aiVar);
    }

    public void addUIManagerListener(ao aoVar) {
        this.mListeners.add(aoVar);
    }

    public void removeUIManagerListener(ao aoVar) {
        this.mListeners.remove(aoVar);
    }

    public int resolveRootTagFromReactTag(int i) {
        return com.facebook.react.uimanager.common.b.b(i) ? i : this.mUIImplementation.f(i);
    }

    public void invalidateNodeLayout(int i) {
        w a2 = this.mUIImplementation.a(i);
        if (a2 == null) {
            com.facebook.common.e.a.c("ReactNative", "Warning : attempted to dirty a non-existent react shadow node. reactTag=" + i);
            return;
        }
        a2.dirty();
        this.mUIImplementation.e(-1);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void updateRootLayoutSpecs(final int i, final int i2, final int i3) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) {
            /* class com.facebook.react.uimanager.UIManagerModule.AnonymousClass4 */

            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIManagerModule.this.mUIImplementation.a(i, i2, i3);
                UIManagerModule.this.mUIImplementation.e(-1);
            }
        });
    }

    private class b implements ComponentCallbacks2 {
        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
        }

        private b() {
        }

        public void onTrimMemory(int i) {
            if (i >= 60) {
                aw.a().b();
            }
        }
    }
}
