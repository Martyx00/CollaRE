package com.facebook.react.modules.image;

import android.net.Uri;
import android.util.SparseArray;
import com.facebook.c.b;
import com.facebook.c.c;
import com.facebook.imagepipeline.f.g;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.a.a;

@a(a = ImageLoaderModule.NAME)
public class ImageLoaderModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    public static final String NAME = "ImageLoader";
    private final Object mCallerContext;
    private final Object mEnqueuedRequestMonitor;
    private final SparseArray<c<Void>> mEnqueuedRequests;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = this;
    }

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext, Object obj) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = obj;
    }

    @ReactMethod
    public void getSize(String str, final Promise promise) {
        if (str == null || str.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        com.facebook.f.a.a.c.c().b(com.facebook.imagepipeline.o.c.a(Uri.parse(str)).o(), this.mCallerContext).a(new b<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>>() {
            /* class com.facebook.react.modules.image.ImageLoaderModule.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.c.b
            public void e(c<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> cVar) {
                if (cVar.b()) {
                    com.facebook.common.h.a<com.facebook.imagepipeline.j.b> d2 = cVar.d();
                    if (d2 != null) {
                        try {
                            com.facebook.imagepipeline.j.b a2 = d2.a();
                            WritableMap createMap = Arguments.createMap();
                            createMap.putInt("width", a2.f());
                            createMap.putInt("height", a2.g());
                            promise.resolve(createMap);
                        } catch (Exception e) {
                            promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, e);
                        } catch (Throwable th) {
                            com.facebook.common.h.a.c(d2);
                            throw th;
                        }
                        com.facebook.common.h.a.c(d2);
                        return;
                    }
                    promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                }
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.c.b
            public void f(c<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> cVar) {
                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, cVar.f());
            }
        }, com.facebook.common.b.a.a());
    }

    @ReactMethod
    public void prefetchImage(String str, final int i, final Promise promise) {
        if (str == null || str.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
            return;
        }
        c<Void> c2 = com.facebook.f.a.a.c.c().c(com.facebook.imagepipeline.o.c.a(Uri.parse(str)).o(), this.mCallerContext);
        AnonymousClass2 r0 = new b<Void>() {
            /* class com.facebook.react.modules.image.ImageLoaderModule.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.c.b
            public void e(c<Void> cVar) {
                if (cVar.b()) {
                    try {
                        ImageLoaderModule.this.removeRequest(i);
                        promise.resolve(true);
                    } finally {
                        cVar.h();
                    }
                }
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.c.b
            public void f(c<Void> cVar) {
                try {
                    ImageLoaderModule.this.removeRequest(i);
                    promise.reject(ImageLoaderModule.ERROR_PREFETCH_FAILURE, cVar.f());
                } finally {
                    cVar.h();
                }
            }
        };
        registerRequest(i, c2);
        c2.a(r0, com.facebook.common.b.a.a());
    }

    @ReactMethod
    public void abortRequest(int i) {
        c<Void> removeRequest = removeRequest(i);
        if (removeRequest != null) {
            removeRequest.h();
        }
    }

    @ReactMethod
    public void queryCache(final ReadableArray readableArray, final Promise promise) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.image.ImageLoaderModule.AnonymousClass3 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap createMap = Arguments.createMap();
                g c2 = com.facebook.f.a.a.c.c();
                for (int i = 0; i < readableArray.size(); i++) {
                    String string = readableArray.getString(i);
                    Uri parse = Uri.parse(string);
                    if (c2.a(parse)) {
                        createMap.putString(string, "memory");
                    } else if (c2.b(parse)) {
                        createMap.putString(string, "disk");
                    }
                }
                promise.resolve(createMap);
            }
        }.executeOnExecutor(GuardedAsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void registerRequest(int i, c<Void> cVar) {
        synchronized (this.mEnqueuedRequestMonitor) {
            this.mEnqueuedRequests.put(i, cVar);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private c<Void> removeRequest(int i) {
        c<Void> cVar;
        synchronized (this.mEnqueuedRequestMonitor) {
            cVar = this.mEnqueuedRequests.get(i);
            this.mEnqueuedRequests.remove(i);
        }
        return cVar;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        synchronized (this.mEnqueuedRequestMonitor) {
            int size = this.mEnqueuedRequests.size();
            for (int i = 0; i < size; i++) {
                c<Void> valueAt = this.mEnqueuedRequests.valueAt(i);
                if (valueAt != null) {
                    valueAt.h();
                }
            }
            this.mEnqueuedRequests.clear();
        }
    }
}
