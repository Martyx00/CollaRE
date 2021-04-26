package com.facebook.react.modules.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;

@com.facebook.react.module.a.a(a = AsyncStorageModule.NAME)
public final class AsyncStorageModule extends ReactContextBaseJavaModule {
    private static final int MAX_SQL_KEYS = 999;
    public static final String NAME = "AsyncSQLiteDBStorage";
    private final a executor;
    private c mReactDatabaseSupplier;
    private boolean mShuttingDown;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    private class a implements Executor {

        /* renamed from: b  reason: collision with root package name */
        private final ArrayDeque<Runnable> f3003b = new ArrayDeque<>();

        /* renamed from: c  reason: collision with root package name */
        private Runnable f3004c;

        /* renamed from: d  reason: collision with root package name */
        private final Executor f3005d;

        a(Executor executor) {
            this.f3005d = executor;
        }

        public synchronized void execute(final Runnable runnable) {
            this.f3003b.offer(new Runnable() {
                /* class com.facebook.react.modules.storage.AsyncStorageModule.a.AnonymousClass1 */

                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        a.this.a();
                    }
                }
            });
            if (this.f3004c == null) {
                a();
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void a() {
            Runnable poll = this.f3003b.poll();
            this.f3004c = poll;
            if (poll != null) {
                this.f3005d.execute(this.f3004c);
            }
        }
    }

    public AsyncStorageModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    AsyncStorageModule(ReactApplicationContext reactApplicationContext, Executor executor2) {
        super(reactApplicationContext);
        this.mShuttingDown = false;
        this.executor = new a(executor2);
        this.mReactDatabaseSupplier = c.a(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    public void clearSensitiveData() {
        this.mReactDatabaseSupplier.c();
    }

    @ReactMethod
    public void multiGet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray == null) {
            callback.invoke(b.a(null), null);
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.storage.AsyncStorageModule.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(b.c(null), null);
                    return;
                }
                String[] strArr = {"key", FirebaseAnalytics.b.VALUE};
                HashSet hashSet = new HashSet();
                WritableArray createArray = Arguments.createArray();
                for (int i = 0; i < readableArray.size(); i += AsyncStorageModule.MAX_SQL_KEYS) {
                    int min = Math.min(readableArray.size() - i, (int) AsyncStorageModule.MAX_SQL_KEYS);
                    Cursor query = AsyncStorageModule.this.mReactDatabaseSupplier.b().query("catalystLocalStorage", strArr, a.a(min), a.a(readableArray, i, min), null, null, null);
                    hashSet.clear();
                    try {
                        if (query.getCount() != readableArray.size()) {
                            for (int i2 = i; i2 < i + min; i2++) {
                                hashSet.add(readableArray.getString(i2));
                            }
                        }
                        if (query.moveToFirst()) {
                            do {
                                WritableArray createArray2 = Arguments.createArray();
                                createArray2.pushString(query.getString(0));
                                createArray2.pushString(query.getString(1));
                                createArray.pushArray(createArray2);
                                hashSet.remove(query.getString(0));
                            } while (query.moveToNext());
                        }
                        query.close();
                        Iterator it = hashSet.iterator();
                        while (it.hasNext()) {
                            WritableArray createArray3 = Arguments.createArray();
                            createArray3.pushString((String) it.next());
                            createArray3.pushNull();
                            createArray.pushArray(createArray3);
                        }
                        hashSet.clear();
                    } catch (Exception e) {
                        com.facebook.common.e.a.b("ReactNative", e.getMessage(), e);
                        callback.invoke(b.a(null, e.getMessage()), null);
                        query.close();
                        return;
                    } catch (Throwable th) {
                        query.close();
                        throw th;
                    }
                }
                callback.invoke(null, createArray);
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @ReactMethod
    public void multiSet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(b.a(null));
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.storage.AsyncStorageModule.AnonymousClass2 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(b.c(null));
                    return;
                }
                SQLiteStatement compileStatement = AsyncStorageModule.this.mReactDatabaseSupplier.b().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.b().beginTransaction();
                    for (int i = 0; i < readableArray.size(); i++) {
                        if (readableArray.getArray(i).size() != 2) {
                            WritableMap b2 = b.b(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Exception e) {
                                com.facebook.common.e.a.b("ReactNative", e.getMessage(), e);
                                if (b2 == null) {
                                    b.a(null, e.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (readableArray.getArray(i).getString(0) == null) {
                            WritableMap a2 = b.a(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Exception e2) {
                                com.facebook.common.e.a.b("ReactNative", e2.getMessage(), e2);
                                if (a2 == null) {
                                    b.a(null, e2.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (readableArray.getArray(i).getString(1) == null) {
                            WritableMap b3 = b.b(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Exception e3) {
                                com.facebook.common.e.a.b("ReactNative", e3.getMessage(), e3);
                                if (b3 == null) {
                                    b.a(null, e3.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else {
                            compileStatement.clearBindings();
                            compileStatement.bindString(1, readableArray.getArray(i).getString(0));
                            compileStatement.bindString(2, readableArray.getArray(i).getString(1));
                            compileStatement.execute();
                        }
                    }
                    AsyncStorageModule.this.mReactDatabaseSupplier.b().setTransactionSuccessful();
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Exception e4) {
                        com.facebook.common.e.a.b("ReactNative", e4.getMessage(), e4);
                        writableMap = b.a(null, e4.getMessage());
                    }
                } catch (Exception e5) {
                    com.facebook.common.e.a.b("ReactNative", e5.getMessage(), e5);
                    WritableMap a3 = b.a(null, e5.getMessage());
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                        writableMap = a3;
                    } catch (Exception e6) {
                        com.facebook.common.e.a.b("ReactNative", e6.getMessage(), e6);
                        writableMap = a3 == null ? b.a(null, e6.getMessage()) : a3;
                    }
                } catch (Throwable th) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Exception e7) {
                        com.facebook.common.e.a.b("ReactNative", e7.getMessage(), e7);
                        b.a(null, e7.getMessage());
                    }
                    throw th;
                }
                if (writableMap != null) {
                    callback.invoke(writableMap);
                } else {
                    callback.invoke(new Object[0]);
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @ReactMethod
    public void multiRemove(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(b.a(null));
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.storage.AsyncStorageModule.AnonymousClass3 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(b.c(null));
                    return;
                }
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.b().beginTransaction();
                    for (int i = 0; i < readableArray.size(); i += AsyncStorageModule.MAX_SQL_KEYS) {
                        int min = Math.min(readableArray.size() - i, (int) AsyncStorageModule.MAX_SQL_KEYS);
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().delete("catalystLocalStorage", a.a(min), a.a(readableArray, i, min));
                    }
                    AsyncStorageModule.this.mReactDatabaseSupplier.b().setTransactionSuccessful();
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Exception e) {
                        com.facebook.common.e.a.b("ReactNative", e.getMessage(), e);
                        writableMap = b.a(null, e.getMessage());
                    }
                } catch (Exception e2) {
                    com.facebook.common.e.a.b("ReactNative", e2.getMessage(), e2);
                    WritableMap a2 = b.a(null, e2.getMessage());
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                        writableMap = a2;
                    } catch (Exception e3) {
                        com.facebook.common.e.a.b("ReactNative", e3.getMessage(), e3);
                        writableMap = a2 == null ? b.a(null, e3.getMessage()) : a2;
                    }
                } catch (Throwable th) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Exception e4) {
                        com.facebook.common.e.a.b("ReactNative", e4.getMessage(), e4);
                        b.a(null, e4.getMessage());
                    }
                    throw th;
                }
                if (writableMap != null) {
                    callback.invoke(writableMap);
                } else {
                    callback.invoke(new Object[0]);
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @ReactMethod
    public void multiMerge(final ReadableArray readableArray, final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.storage.AsyncStorageModule.AnonymousClass4 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(b.c(null));
                    return;
                }
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.b().beginTransaction();
                    for (int i = 0; i < readableArray.size(); i++) {
                        if (readableArray.getArray(i).size() != 2) {
                            WritableMap b2 = b.b(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Exception e) {
                                com.facebook.common.e.a.b("ReactNative", e.getMessage(), e);
                                if (b2 == null) {
                                    b.a(null, e.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (readableArray.getArray(i).getString(0) == null) {
                            WritableMap a2 = b.a(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Exception e2) {
                                com.facebook.common.e.a.b("ReactNative", e2.getMessage(), e2);
                                if (a2 == null) {
                                    b.a(null, e2.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (readableArray.getArray(i).getString(1) == null) {
                            WritableMap b3 = b.b(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Exception e3) {
                                com.facebook.common.e.a.b("ReactNative", e3.getMessage(), e3);
                                if (b3 == null) {
                                    b.a(null, e3.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (!a.b(AsyncStorageModule.this.mReactDatabaseSupplier.b(), readableArray.getArray(i).getString(0), readableArray.getArray(i).getString(1))) {
                            WritableMap c2 = b.c(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                                return;
                            } catch (Exception e4) {
                                com.facebook.common.e.a.b("ReactNative", e4.getMessage(), e4);
                                if (c2 == null) {
                                    b.a(null, e4.getMessage());
                                    return;
                                }
                                return;
                            }
                        }
                    }
                    AsyncStorageModule.this.mReactDatabaseSupplier.b().setTransactionSuccessful();
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Exception e5) {
                        com.facebook.common.e.a.b("ReactNative", e5.getMessage(), e5);
                        writableMap = b.a(null, e5.getMessage());
                    }
                } catch (Exception e6) {
                    com.facebook.common.e.a.b("ReactNative", e6.getMessage(), e6);
                    WritableMap a3 = b.a(null, e6.getMessage());
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                        writableMap = a3;
                    } catch (Exception e7) {
                        com.facebook.common.e.a.b("ReactNative", e7.getMessage(), e7);
                        writableMap = a3 == null ? b.a(null, e7.getMessage()) : a3;
                    }
                } catch (Throwable th) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.b().endTransaction();
                    } catch (Exception e8) {
                        com.facebook.common.e.a.b("ReactNative", e8.getMessage(), e8);
                        b.a(null, e8.getMessage());
                    }
                    throw th;
                }
                if (writableMap != null) {
                    callback.invoke(writableMap);
                } else {
                    callback.invoke(new Object[0]);
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @ReactMethod
    public void clear(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.storage.AsyncStorageModule.AnonymousClass5 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AsyncStorageModule.this.mReactDatabaseSupplier.a()) {
                    callback.invoke(b.c(null));
                    return;
                }
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.d();
                    callback.invoke(new Object[0]);
                } catch (Exception e) {
                    com.facebook.common.e.a.b("ReactNative", e.getMessage(), e);
                    callback.invoke(b.a(null, e.getMessage()));
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @ReactMethod
    public void getAllKeys(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.storage.AsyncStorageModule.AnonymousClass6 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(b.c(null), null);
                    return;
                }
                WritableArray createArray = Arguments.createArray();
                Cursor query = AsyncStorageModule.this.mReactDatabaseSupplier.b().query("catalystLocalStorage", new String[]{"key"}, null, null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        do {
                            createArray.pushString(query.getString(0));
                        } while (query.moveToNext());
                    }
                    query.close();
                    callback.invoke(null, createArray);
                } catch (Exception e) {
                    com.facebook.common.e.a.b("ReactNative", e.getMessage(), e);
                    callback.invoke(b.a(null, e.getMessage()), null);
                    query.close();
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean ensureDatabase() {
        return !this.mShuttingDown && this.mReactDatabaseSupplier.a();
    }
}
