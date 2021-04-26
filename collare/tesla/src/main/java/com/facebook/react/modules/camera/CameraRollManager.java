package com.facebook.react.modules.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import org.webrtc.MediaStreamTrack;

@com.facebook.react.module.a.a(a = CameraRollManager.NAME)
public class CameraRollManager extends ReactContextBaseJavaModule {
    private static final String ASSET_TYPE_ALL = "All";
    private static final String ASSET_TYPE_PHOTOS = "Photos";
    private static final String ASSET_TYPE_VIDEOS = "Videos";
    private static final String ERROR_UNABLE_TO_FILTER = "E_UNABLE_TO_FILTER";
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final String NAME = "CameraRollManager";
    private static final String[] PROJECTION = {"_id", "mime_type", "bucket_display_name", "datetaken", "width", "height", "longitude", "latitude", "_data"};
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public CameraRollManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void saveToCameraRoll(String str, String str2, Promise promise) {
        new b(getReactApplicationContext(), Uri.parse(str), promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static class b extends GuardedAsyncTask<Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private final Context f2797a;

        /* renamed from: b  reason: collision with root package name */
        private final Uri f2798b;

        /* renamed from: c  reason: collision with root package name */
        private final Promise f2799c;

        public b(ReactContext reactContext, Uri uri, Promise promise) {
            super(reactContext);
            this.f2797a = reactContext;
            this.f2798b = uri;
            this.f2799c = promise;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x0106  */
        /* JADX WARNING: Removed duplicated region for block: B:79:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void doInBackgroundGuarded(java.lang.Void... r15) {
            /*
            // Method dump skipped, instructions count: 323
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.camera.CameraRollManager.b.doInBackgroundGuarded(java.lang.Void[]):void");
        }
    }

    @ReactMethod
    public void getPhotos(ReadableMap readableMap, Promise promise) {
        int i = readableMap.getInt("first");
        String string = readableMap.hasKey("after") ? readableMap.getString("after") : null;
        String string2 = readableMap.hasKey("groupName") ? readableMap.getString("groupName") : null;
        String string3 = readableMap.hasKey("assetType") ? readableMap.getString("assetType") : ASSET_TYPE_PHOTOS;
        ReadableArray array = readableMap.hasKey("mimeTypes") ? readableMap.getArray("mimeTypes") : null;
        if (!readableMap.hasKey("groupTypes")) {
            new a(getReactApplicationContext(), i, string, string2, array, string3, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new JSApplicationIllegalArgumentException("groupTypes is not supported on Android");
    }

    private static class a extends GuardedAsyncTask<Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private final Context f2793a;

        /* renamed from: b  reason: collision with root package name */
        private final int f2794b;

        /* renamed from: c  reason: collision with root package name */
        private final String f2795c;

        /* renamed from: d  reason: collision with root package name */
        private final String f2796d;
        private final ReadableArray e;
        private final Promise f;
        private final String g;

        private a(ReactContext reactContext, int i, String str, String str2, ReadableArray readableArray, String str3, Promise promise) {
            super(reactContext);
            this.f2793a = reactContext;
            this.f2794b = i;
            this.f2795c = str;
            this.f2796d = str2;
            this.e = readableArray;
            this.f = promise;
            this.g = str3;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void doInBackgroundGuarded(Void... voidArr) {
            StringBuilder sb = new StringBuilder("1");
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.f2795c)) {
                sb.append(" AND datetaken < ?");
                arrayList.add(this.f2795c);
            }
            if (!TextUtils.isEmpty(this.f2796d)) {
                sb.append(" AND bucket_display_name = ?");
                arrayList.add(this.f2796d);
            }
            if (this.g.equals(CameraRollManager.ASSET_TYPE_PHOTOS)) {
                sb.append(" AND media_type = 1");
            } else if (this.g.equals(CameraRollManager.ASSET_TYPE_VIDEOS)) {
                sb.append(" AND media_type = 3");
            } else if (this.g.equals(CameraRollManager.ASSET_TYPE_ALL)) {
                sb.append(" AND media_type IN (3,1)");
            } else {
                Promise promise = this.f;
                promise.reject(CameraRollManager.ERROR_UNABLE_TO_FILTER, "Invalid filter option: '" + this.g + "'. Expected one of '" + CameraRollManager.ASSET_TYPE_PHOTOS + "', '" + CameraRollManager.ASSET_TYPE_VIDEOS + "' or '" + CameraRollManager.ASSET_TYPE_ALL + "'.");
                return;
            }
            ReadableArray readableArray = this.e;
            if (readableArray != null && readableArray.size() > 0) {
                sb.append(" AND mime_type IN (");
                for (int i = 0; i < this.e.size(); i++) {
                    sb.append("?,");
                    arrayList.add(this.e.getString(i));
                }
                sb.replace(sb.length() - 1, sb.length(), ")");
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            ContentResolver contentResolver = this.f2793a.getContentResolver();
            try {
                Uri contentUri = MediaStore.Files.getContentUri("external");
                String[] strArr = CameraRollManager.PROJECTION;
                String sb2 = sb.toString();
                String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
                Cursor query = contentResolver.query(contentUri, strArr, sb2, strArr2, "datetaken DESC, date_modified DESC LIMIT " + (this.f2794b + 1));
                if (query == null) {
                    this.f.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "Could not get media");
                    return;
                }
                try {
                    CameraRollManager.putEdges(contentResolver, query, writableNativeMap, this.f2794b);
                    CameraRollManager.putPageInfo(query, writableNativeMap, this.f2794b);
                } finally {
                    query.close();
                    this.f.resolve(writableNativeMap);
                }
            } catch (SecurityException e2) {
                this.f.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get media: need READ_EXTERNAL_STORAGE permission", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void putPageInfo(Cursor cursor, WritableMap writableMap, int i) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("has_next_page", i < cursor.getCount());
        if (i < cursor.getCount()) {
            cursor.moveToPosition(i - 1);
            writableNativeMap.putString("end_cursor", cursor.getString(cursor.getColumnIndex("datetaken")));
        }
        writableMap.putMap("page_info", writableNativeMap);
    }

    /* access modifiers changed from: private */
    public static void putEdges(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i) {
        WritableNativeArray writableNativeArray;
        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("mime_type");
        int columnIndex3 = cursor.getColumnIndex("bucket_display_name");
        int columnIndex4 = cursor.getColumnIndex("datetaken");
        int columnIndex5 = cursor.getColumnIndex("width");
        int columnIndex6 = cursor.getColumnIndex("height");
        int columnIndex7 = cursor.getColumnIndex("longitude");
        int columnIndex8 = cursor.getColumnIndex("latitude");
        int columnIndex9 = cursor.getColumnIndex("_data");
        int i2 = i;
        int i3 = 0;
        while (i3 < i2 && !cursor.isAfterLast()) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            int i4 = i3;
            if (putImageInfo(contentResolver, cursor, writableNativeMap2, columnIndex, columnIndex5, columnIndex6, columnIndex9)) {
                putBasicNodeInfo(cursor, writableNativeMap2, columnIndex2, columnIndex3, columnIndex4);
                putLocationInfo(cursor, writableNativeMap2, columnIndex7, columnIndex8);
                writableNativeMap.putMap("node", writableNativeMap2);
                writableNativeArray = writableNativeArray2;
                writableNativeArray.pushMap(writableNativeMap);
            } else {
                writableNativeArray = writableNativeArray2;
                i4--;
            }
            cursor.moveToNext();
            i3 = i4 + 1;
            i2 = i;
            writableNativeArray2 = writableNativeArray;
            columnIndex8 = columnIndex8;
            columnIndex = columnIndex;
        }
        writableMap.putArray("edges", writableNativeArray2);
    }

    private static void putBasicNodeInfo(Cursor cursor, WritableMap writableMap, int i, int i2, int i3) {
        writableMap.putString(AppMeasurement.Param.TYPE, cursor.getString(i));
        writableMap.putString("group_name", cursor.getString(i2));
        writableMap.putDouble(AppMeasurement.Param.TIMESTAMP, ((double) cursor.getLong(i3)) / 1000.0d);
    }

    private static boolean putImageInfo(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, int i2, int i3, int i4) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Uri parse = Uri.parse("file://" + cursor.getString(i4));
        writableNativeMap.putString("uri", parse.toString());
        float f = (float) cursor.getInt(i2);
        float f2 = (float) cursor.getInt(i3);
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(parse.toString());
        if (guessContentTypeFromName != null && guessContentTypeFromName.startsWith(MediaStreamTrack.VIDEO_TRACK_KIND)) {
            try {
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r");
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(openAssetFileDescriptor.getFileDescriptor());
                if (f <= BitmapDescriptorFactory.HUE_RED || f2 <= BitmapDescriptorFactory.HUE_RED) {
                    try {
                        f = (float) Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                        f2 = (float) Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                    } catch (NumberFormatException e) {
                        com.facebook.common.e.a.c("ReactNative", "Number format exception occurred while trying to fetch video metadata for " + parse.toString(), e);
                        mediaMetadataRetriever.release();
                        openAssetFileDescriptor.close();
                        return false;
                    } catch (Throwable th) {
                        mediaMetadataRetriever.release();
                        openAssetFileDescriptor.close();
                        throw th;
                    }
                }
                writableNativeMap.putInt("playableDuration", Integer.parseInt(mediaMetadataRetriever.extractMetadata(9)) / 1000);
                mediaMetadataRetriever.release();
                openAssetFileDescriptor.close();
            } catch (Exception e2) {
                com.facebook.common.e.a.c("ReactNative", "Could not get video metadata for " + parse.toString(), e2);
                return false;
            }
        }
        if (f <= BitmapDescriptorFactory.HUE_RED || f2 <= BitmapDescriptorFactory.HUE_RED) {
            try {
                AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(parse, "r");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(openAssetFileDescriptor2.getFileDescriptor(), null, options);
                f = (float) options.outWidth;
                f2 = (float) options.outHeight;
                openAssetFileDescriptor2.close();
            } catch (IOException e3) {
                com.facebook.common.e.a.c("ReactNative", "Could not get width/height for " + parse.toString(), e3);
                return false;
            }
        }
        writableNativeMap.putDouble("width", (double) f);
        writableNativeMap.putDouble("height", (double) f2);
        writableMap.putMap("image", writableNativeMap);
        return true;
    }

    private static void putLocationInfo(Cursor cursor, WritableMap writableMap, int i, int i2) {
        double d2 = cursor.getDouble(i);
        double d3 = cursor.getDouble(i2);
        if (d2 > 0.0d || d3 > 0.0d) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("longitude", d2);
            writableNativeMap.putDouble("latitude", d3);
            writableMap.putMap("location", writableNativeMap);
        }
    }
}
