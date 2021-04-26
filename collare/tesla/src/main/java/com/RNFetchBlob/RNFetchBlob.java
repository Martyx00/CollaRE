package com.RNFetchBlob;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.SparseArray;
import com.RNFetchBlob.f;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.network.a;
import com.facebook.react.modules.network.c;
import com.facebook.react.modules.network.h;
import java.io.File;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

public class RNFetchBlob extends ReactContextBaseJavaModule {
    private static boolean ActionViewVisible = false;
    static ReactApplicationContext RCTContext;
    static LinkedBlockingQueue<Runnable> fsTaskQueue = new LinkedBlockingQueue<>();
    private static ThreadPoolExecutor fsThreadPool = new ThreadPoolExecutor(2, 10, 5000, TimeUnit.MILLISECONDS, taskQueue);
    private static SparseArray<Promise> promiseTable = new SparseArray<>();
    private static LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 5000, TimeUnit.MILLISECONDS, taskQueue);
    private final OkHttpClient mClient = h.a();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNFetchBlob";
    }

    public RNFetchBlob(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        ((a) this.mClient.cookieJar()).a(new JavaNetCookieJar(new c(reactApplicationContext)));
        RCTContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(new ActivityEventListener() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass1 */

            @Override // com.facebook.react.bridge.ActivityEventListener
            public void onNewIntent(Intent intent) {
            }

            @Override // com.facebook.react.bridge.ActivityEventListener
            public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
                if (i == c.f1462a.intValue() && i2 == -1) {
                    ((Promise) RNFetchBlob.promiseTable.get(c.f1462a.intValue())).resolve(intent.getData().toString());
                    RNFetchBlob.promiseTable.remove(c.f1462a.intValue());
                }
            }
        });
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return d.a(getReactApplicationContext());
    }

    @ReactMethod
    public void createFile(final String str, final String str2, final String str3, final Promise promise) {
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass5 */

            public void run() {
                d.a(str, str2, str3, promise);
            }
        });
    }

    @ReactMethod
    public void createFileASCII(final String str, final ReadableArray readableArray, final Promise promise) {
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass6 */

            public void run() {
                d.a(str, readableArray, promise);
            }
        });
    }

    @ReactMethod
    public void actionViewIntent(String str, String str2, final Promise promise) {
        try {
            Activity currentActivity = getCurrentActivity();
            Uri a2 = FileProvider.a(currentActivity, getReactApplicationContext().getPackageName() + ".provider", new File(str));
            if (Build.VERSION.SDK_INT >= 24) {
                Intent dataAndType = new Intent("android.intent.action.VIEW").setDataAndType(a2, str2);
                dataAndType.setFlags(1);
                if (dataAndType.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
                    getReactApplicationContext().startActivity(dataAndType);
                }
            } else {
                Intent intent = new Intent("android.intent.action.VIEW");
                getReactApplicationContext().startActivity(intent.setDataAndType(Uri.parse("file://" + str), str2).setFlags(268435456));
            }
            ActionViewVisible = true;
            RCTContext.addLifecycleEventListener(new LifecycleEventListener() {
                /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass7 */

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostDestroy() {
                }

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostPause() {
                }

                @Override // com.facebook.react.bridge.LifecycleEventListener
                public void onHostResume() {
                    if (RNFetchBlob.ActionViewVisible) {
                        promise.resolve(null);
                    }
                    RNFetchBlob.RCTContext.removeLifecycleEventListener(this);
                }
            });
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    @ReactMethod
    public void writeArrayChunk(String str, ReadableArray readableArray, Callback callback) {
        d.a(str, readableArray, callback);
    }

    @ReactMethod
    public void unlink(String str, Callback callback) {
        d.b(str, callback);
    }

    @ReactMethod
    public void mkdir(String str, Promise promise) {
        d.a(str, promise);
    }

    @ReactMethod
    public void exists(String str, Callback callback) {
        d.c(str, callback);
    }

    @ReactMethod
    public void cp(final String str, final String str2, final Callback callback) {
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass8 */

            public void run() {
                d.b(str, str2, callback);
            }
        });
    }

    @ReactMethod
    public void mv(String str, String str2, Callback callback) {
        d.c(str, str2, callback);
    }

    @ReactMethod
    public void ls(String str, Promise promise) {
        d.b(str, promise);
    }

    @ReactMethod
    public void writeStream(String str, String str2, boolean z, Callback callback) {
        new d(getReactApplicationContext()).a(str, str2, z, callback);
    }

    @ReactMethod
    public void writeChunk(String str, String str2, Callback callback) {
        d.a(str, str2, callback);
    }

    @ReactMethod
    public void closeStream(String str, Callback callback) {
        d.a(str, callback);
    }

    @ReactMethod
    public void removeSession(ReadableArray readableArray, Callback callback) {
        d.a(readableArray, callback);
    }

    @ReactMethod
    public void readFile(final String str, final String str2, final Promise promise) {
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass9 */

            public void run() {
                d.a(str, str2, promise);
            }
        });
    }

    @ReactMethod
    public void writeFileArray(final String str, final ReadableArray readableArray, final boolean z, final Promise promise) {
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass10 */

            public void run() {
                d.a(str, readableArray, z, promise);
            }
        });
    }

    @ReactMethod
    public void writeFile(final String str, final String str2, final String str3, final boolean z, final Promise promise) {
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass11 */

            public void run() {
                d.a(str, str2, str3, z, promise);
            }
        });
    }

    @ReactMethod
    public void lstat(String str, Callback callback) {
        d.d(str, callback);
    }

    @ReactMethod
    public void stat(String str, Callback callback) {
        d.e(str, callback);
    }

    @ReactMethod
    public void scanFile(final ReadableArray readableArray, final Callback callback) {
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass12 */

            public void run() {
                int size = readableArray.size();
                String[] strArr = new String[size];
                String[] strArr2 = new String[size];
                for (int i = 0; i < size; i++) {
                    ReadableMap map = readableArray.getMap(i);
                    if (map.hasKey("path")) {
                        strArr[i] = map.getString("path");
                        if (map.hasKey("mime")) {
                            strArr2[i] = map.getString("mime");
                        } else {
                            strArr2[i] = null;
                        }
                    }
                }
                new d(reactApplicationContext).a(strArr, strArr2, callback);
            }
        });
    }

    @ReactMethod
    public void hash(final String str, final String str2, final Promise promise) {
        threadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass2 */

            public void run() {
                d.b(str, str2, promise);
            }
        });
    }

    @ReactMethod
    public void readStream(final String str, final String str2, final int i, final int i2, final String str3) {
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        fsThreadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass3 */

            public void run() {
                new d(reactApplicationContext).a(str, str2, i, i2, str3);
            }
        });
    }

    @ReactMethod
    public void cancelRequest(String str, Callback callback) {
        try {
            g.a(str);
            callback.invoke(null, str);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage(), null);
        }
    }

    @ReactMethod
    public void slice(String str, String str2, int i, int i2, Promise promise) {
        d.a(str, str2, i, i2, "", promise);
    }

    @ReactMethod
    public void enableProgressReport(String str, int i, int i2) {
        g.f1480c.put(str, new f(true, i, i2, f.a.Download));
    }

    @ReactMethod
    public void df(final Callback callback) {
        fsThreadPool.execute(new Runnable() {
            /* class com.RNFetchBlob.RNFetchBlob.AnonymousClass4 */

            public void run() {
                d.a(callback);
            }
        });
    }

    @ReactMethod
    public void enableUploadProgressReport(String str, int i, int i2) {
        g.f1481d.put(str, new f(true, i, i2, f.a.Upload));
    }

    @ReactMethod
    public void fetchBlob(ReadableMap readableMap, String str, String str2, String str3, ReadableMap readableMap2, String str4, Callback callback) {
        new g(readableMap, str, str2, str3, readableMap2, str4, null, this.mClient, callback).run();
    }

    @ReactMethod
    public void fetchBlobForm(ReadableMap readableMap, String str, String str2, String str3, ReadableMap readableMap2, ReadableArray readableArray, Callback callback) {
        new g(readableMap, str, str2, str3, readableMap2, null, readableArray, this.mClient, callback).run();
    }

    @ReactMethod
    public void getContentIntent(String str, Promise promise) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        if (str != null) {
            intent.setType(str);
        } else {
            intent.setType("*/*");
        }
        promiseTable.put(c.f1462a.intValue(), promise);
        getReactApplicationContext().startActivityForResult(intent, c.f1462a.intValue(), null);
    }

    @ReactMethod
    public void addCompleteDownload(ReadableMap readableMap, Promise promise) {
        DownloadManager downloadManager = (DownloadManager) RCTContext.getSystemService("download");
        if (readableMap == null || !readableMap.hasKey("path")) {
            promise.reject("EINVAL", "RNFetchblob.addCompleteDownload config or path missing.");
            return;
        }
        String d2 = d.d(readableMap.getString("path"));
        if (d2 == null) {
            promise.reject("EINVAL", "RNFetchblob.addCompleteDownload can not resolve URI:" + readableMap.getString("path"));
            return;
        }
        try {
            downloadManager.addCompletedDownload(readableMap.hasKey("title") ? readableMap.getString("title") : "", readableMap.hasKey("description") ? readableMap.getString("description") : "", true, readableMap.hasKey("mime") ? readableMap.getString("mime") : null, d2, Long.valueOf(d.b(d2).getString("size")).longValue(), readableMap.hasKey("showNotification") && readableMap.getBoolean("showNotification"));
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    @ReactMethod
    public void getSDCardDir(Promise promise) {
        d.a(promise);
    }

    @ReactMethod
    public void getSDCardApplicationDir(Promise promise) {
        d.a(getReactApplicationContext(), promise);
    }
}
