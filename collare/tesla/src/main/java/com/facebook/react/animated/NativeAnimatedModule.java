package com.facebook.react.animated;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.e;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ai;
import com.facebook.react.uimanager.ao;
import com.facebook.react.uimanager.e;
import com.facebook.react.uimanager.k;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;

@com.facebook.react.module.a.a(a = NativeAnimatedModule.NAME)
public class NativeAnimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, ao {
    public static final String NAME = "NativeAnimatedModule";
    private final e mAnimatedFrameCallback;
    private l mNodesManager;
    private ArrayList<a> mOperations = new ArrayList<>();
    private ArrayList<a> mPreOperations = new ArrayList<>();
    private final com.facebook.react.modules.core.e mReactChoreographer = com.facebook.react.modules.core.e.b();

    private interface a {
        void a(l lVar);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    public NativeAnimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mAnimatedFrameCallback = new e(reactApplicationContext) {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.react.uimanager.e
            public void a(long j) {
                l nodesManager = NativeAnimatedModule.this.getNodesManager();
                if (nodesManager.a()) {
                    nodesManager.a(j);
                }
                ((com.facebook.react.modules.core.e) com.facebook.i.a.a.a(NativeAnimatedModule.this.mReactChoreographer)).a(e.a.NATIVE_ANIMATED_MODULE, NativeAnimatedModule.this.mAnimatedFrameCallback);
            }
        };
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.addLifecycleEventListener(this);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIManagerListener(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        enqueueFrameCallback();
    }

    @Override // com.facebook.react.uimanager.ao
    public void willDispatchViewUpdates(UIManagerModule uIManagerModule) {
        if (!this.mOperations.isEmpty() || !this.mPreOperations.isEmpty()) {
            final ArrayList<a> arrayList = this.mPreOperations;
            final ArrayList<a> arrayList2 = this.mOperations;
            this.mPreOperations = new ArrayList<>();
            this.mOperations = new ArrayList<>();
            uIManagerModule.prependUIBlock(new ai() {
                /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass12 */

                @Override // com.facebook.react.uimanager.ai
                public void a(k kVar) {
                    l nodesManager = NativeAnimatedModule.this.getNodesManager();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).a(nodesManager);
                    }
                }
            });
            uIManagerModule.addUIBlock(new ai() {
                /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass15 */

                @Override // com.facebook.react.uimanager.ai
                public void a(k kVar) {
                    l nodesManager = NativeAnimatedModule.this.getNodesManager();
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).a(nodesManager);
                    }
                }
            });
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        clearFrameCallback();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private l getNodesManager() {
        if (this.mNodesManager == null) {
            this.mNodesManager = new l((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class));
        }
        return this.mNodesManager;
    }

    private void clearFrameCallback() {
        ((com.facebook.react.modules.core.e) com.facebook.i.a.a.a(this.mReactChoreographer)).b(e.a.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ((com.facebook.react.modules.core.e) com.facebook.i.a.a.a(this.mReactChoreographer)).a(e.a.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    public void setNodesManager(l lVar) {
        this.mNodesManager = lVar;
    }

    @ReactMethod
    public void createAnimatedNode(final int i, final ReadableMap readableMap) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass16 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.a(i, readableMap);
            }
        });
    }

    @ReactMethod
    public void startListeningToAnimatedNodeValue(final int i) {
        final AnonymousClass17 r0 = new c() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass17 */

            @Override // com.facebook.react.animated.c
            public void a(double d2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("tag", i);
                createMap.putDouble(FirebaseAnalytics.b.VALUE, d2);
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) NativeAnimatedModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", createMap);
            }
        };
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass18 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.a(i, r0);
            }
        });
    }

    @ReactMethod
    public void stopListeningToAnimatedNodeValue(final int i) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass19 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.c(i);
            }
        });
    }

    @ReactMethod
    public void dropAnimatedNode(final int i) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass20 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.b(i);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeValue(final int i, final double d2) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass21 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.a(i, d2);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeOffset(final int i, final double d2) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass2 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.b(i, d2);
            }
        });
    }

    @ReactMethod
    public void flattenAnimatedNodeOffset(final int i) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass3 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.d(i);
            }
        });
    }

    @ReactMethod
    public void extractAnimatedNodeOffset(final int i) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass4 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.e(i);
            }
        });
    }

    @ReactMethod
    public void startAnimatingNode(final int i, final int i2, final ReadableMap readableMap, final Callback callback) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass5 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.a(i, i2, readableMap, callback);
            }
        });
    }

    @ReactMethod
    public void stopAnimation(final int i) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass6 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.f(i);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodes(final int i, final int i2) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass7 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.a(i, i2);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodes(final int i, final int i2) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass8 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.b(i, i2);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodeToView(final int i, final int i2) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass9 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.c(i, i2);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodeFromView(final int i, final int i2) {
        this.mPreOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass10 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.e(i, i2);
            }
        });
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass11 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.d(i, i2);
            }
        });
    }

    @ReactMethod
    public void addAnimatedEventToView(final int i, final String str, final ReadableMap readableMap) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass13 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.a(i, str, readableMap);
            }
        });
    }

    @ReactMethod
    public void removeAnimatedEventFromView(final int i, final String str, final int i2) {
        this.mOperations.add(new a() {
            /* class com.facebook.react.animated.NativeAnimatedModule.AnonymousClass14 */

            @Override // com.facebook.react.animated.NativeAnimatedModule.a
            public void a(l lVar) {
                lVar.a(i, str, i2);
            }
        });
    }
}
