package com.crashlytics.android;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import io.a.a.a.a.b.q;
import io.a.a.a.c;

public class CrashlyticsInitProvider extends ContentProvider {
    private static final String TAG = "CrashlyticsInitProvider";

    /* access modifiers changed from: package-private */
    public interface EnabledCheckStrategy {
        boolean isCrashlyticsEnabled(Context context);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public boolean onCreate() {
        Context context = getContext();
        if (shouldInitializeFabric(context, new q(), new ManifestEnabledCheckStrategy())) {
            try {
                c.a(context, new Crashlytics());
                c.g().c(TAG, "CrashlyticsInitProvider initialization successful");
            } catch (IllegalStateException unused) {
                c.g().c(TAG, "CrashlyticsInitProvider initialization unsuccessful");
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldInitializeFabric(Context context, q qVar, EnabledCheckStrategy enabledCheckStrategy) {
        return qVar.b(context) && enabledCheckStrategy.isCrashlyticsEnabled(context);
    }
}
