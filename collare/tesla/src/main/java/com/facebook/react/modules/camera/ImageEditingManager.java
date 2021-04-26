package com.facebook.react.modules.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@com.facebook.react.module.a.a(a = ImageEditingManager.NAME)
public class ImageEditingManager extends ReactContextBaseJavaModule {
    private static final int COMPRESS_QUALITY = 90;
    @SuppressLint({"InlinedApi"})
    private static final String[] EXIF_ATTRIBUTES = {"FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance"};
    private static final List<String> LOCAL_URI_PREFIXES = Arrays.asList("file://", "content://");
    protected static final String NAME = "ImageEditingManager";
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public ImageEditingManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        new a(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        new a(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static class a extends GuardedAsyncTask<Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private final Context f2801a;

        private a(ReactContext reactContext) {
            super(reactContext);
            this.f2801a = reactContext;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void doInBackgroundGuarded(Void... voidArr) {
            a(this.f2801a.getCacheDir());
            File externalCacheDir = this.f2801a.getExternalCacheDir();
            if (externalCacheDir != null) {
                a(externalCacheDir);
            }
        }

        private void a(File file) {
            File[] listFiles = file.listFiles(new FilenameFilter() {
                /* class com.facebook.react.modules.camera.ImageEditingManager.a.AnonymousClass1 */

                public boolean accept(File file, String str) {
                    return str.startsWith(ImageEditingManager.TEMP_FILE_PREFIX);
                }
            });
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    file2.delete();
                }
            }
        }
    }

    @ReactMethod
    public void cropImage(String str, ReadableMap readableMap, Callback callback, Callback callback2) {
        ReadableMap readableMap2 = null;
        ReadableMap map = readableMap.hasKey("offset") ? readableMap.getMap("offset") : null;
        if (readableMap.hasKey("size")) {
            readableMap2 = readableMap.getMap("size");
        }
        if (map == null || readableMap2 == null || !map.hasKey("x") || !map.hasKey("y") || !readableMap2.hasKey("width") || !readableMap2.hasKey("height")) {
            throw new JSApplicationIllegalArgumentException("Please specify offset and size");
        } else if (str == null || str.isEmpty()) {
            throw new JSApplicationIllegalArgumentException("Please specify a URI");
        } else {
            b bVar = new b(getReactApplicationContext(), str, (int) map.getDouble("x"), (int) map.getDouble("y"), (int) readableMap2.getDouble("width"), (int) readableMap2.getDouble("height"), callback, callback2);
            if (readableMap.hasKey("displaySize")) {
                ReadableMap map2 = readableMap.getMap("displaySize");
                bVar.a((int) map2.getDouble("width"), (int) map2.getDouble("height"));
            }
            bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private static class b extends GuardedAsyncTask<Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        final Context f2803a;

        /* renamed from: b  reason: collision with root package name */
        final String f2804b;

        /* renamed from: c  reason: collision with root package name */
        final int f2805c;

        /* renamed from: d  reason: collision with root package name */
        final int f2806d;
        final int e;
        final int f;
        int g;
        int h;
        final Callback i;
        final Callback j;

        private b(ReactContext reactContext, String str, int i2, int i3, int i4, int i5, Callback callback, Callback callback2) {
            super(reactContext);
            this.g = 0;
            this.h = 0;
            if (i2 < 0 || i3 < 0 || i4 <= 0 || i5 <= 0) {
                throw new JSApplicationIllegalArgumentException(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
            }
            this.f2803a = reactContext;
            this.f2804b = str;
            this.f2805c = i2;
            this.f2806d = i3;
            this.e = i4;
            this.f = i5;
            this.i = callback;
            this.j = callback2;
        }

        public void a(int i2, int i3) {
            if (i2 <= 0 || i3 <= 0) {
                throw new JSApplicationIllegalArgumentException(String.format("Invalid target size: [%d, %d]", Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            this.g = i2;
            this.h = i3;
        }

        private InputStream a() {
            InputStream inputStream;
            if (ImageEditingManager.isLocalUri(this.f2804b)) {
                inputStream = this.f2803a.getContentResolver().openInputStream(Uri.parse(this.f2804b));
            } else {
                inputStream = new URL(this.f2804b).openConnection().getInputStream();
            }
            if (inputStream != null) {
                return inputStream;
            }
            throw new IOException("Cannot open bitmap: " + this.f2804b);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void doInBackgroundGuarded(Void... voidArr) {
            Bitmap bitmap;
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                if (this.g > 0 && this.h > 0) {
                    bitmap = a(this.g, this.h, options);
                } else {
                    bitmap = a(options);
                }
                String str = options.outMimeType;
                if (str == null || str.isEmpty()) {
                    throw new IOException("Could not determine MIME type");
                }
                File createTempFile = ImageEditingManager.createTempFile(this.f2803a, str);
                ImageEditingManager.writeCompressedBitmapToFile(bitmap, str, createTempFile);
                if (str.equals("image/jpeg")) {
                    ImageEditingManager.copyExif(this.f2803a, Uri.parse(this.f2804b), createTempFile);
                }
                this.i.invoke(Uri.fromFile(createTempFile).toString());
            } catch (Exception e2) {
                this.j.invoke(e2.getMessage());
            }
        }

        private Bitmap a(BitmapFactory.Options options) {
            InputStream a2 = a();
            BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(a2, false);
            try {
                return newInstance.decodeRegion(new Rect(this.f2805c, this.f2806d, this.f2805c + this.e, this.f2806d + this.f), options);
            } finally {
                if (a2 != null) {
                    a2.close();
                }
                newInstance.recycle();
            }
        }

        private Bitmap a(int i2, int i3, BitmapFactory.Options options) {
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            com.facebook.i.a.a.a(options);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            InputStream a2 = a();
            try {
                BitmapFactory.decodeStream(a2, null, options2);
                if (a2 != null) {
                    a2.close();
                }
                int i4 = this.e;
                int i5 = this.f;
                float f7 = (float) i2;
                float f8 = (float) i3;
                float f9 = f7 / f8;
                if (((float) i4) / ((float) i5) > f9) {
                    f5 = ((float) i5) * f9;
                    f4 = (float) i5;
                    f2 = ((float) this.f2805c) + ((((float) i4) - f5) / 2.0f);
                    f6 = (float) this.f2806d;
                    f3 = f8 / ((float) i5);
                } else {
                    f5 = (float) i4;
                    float f10 = ((float) i4) / f9;
                    f2 = (float) this.f2805c;
                    float f11 = (((float) i5) - f10) / 2.0f;
                    float f12 = f7 / ((float) i4);
                    f4 = f10;
                    f3 = f12;
                    f6 = f11 + ((float) this.f2806d);
                }
                options.inSampleSize = ImageEditingManager.getDecodeSampleSize(this.e, this.f, i2, i3);
                options2.inJustDecodeBounds = false;
                a2 = a();
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(a2, null, options);
                    if (decodeStream != null) {
                        float f13 = f3 * ((float) options.inSampleSize);
                        Matrix matrix = new Matrix();
                        matrix.setScale(f13, f13);
                        return Bitmap.createBitmap(decodeStream, (int) Math.floor((double) (f2 / ((float) options.inSampleSize))), (int) Math.floor((double) (f6 / ((float) options.inSampleSize))), (int) Math.floor((double) (f5 / ((float) options.inSampleSize))), (int) Math.floor((double) (f4 / ((float) options.inSampleSize))), matrix, true);
                    }
                    throw new IOException("Cannot decode bitmap: " + this.f2804b);
                } finally {
                }
            } finally {
                if (a2 != null) {
                    a2.close();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void copyExif(Context context, Uri uri, File file) {
        File fileFromUri = getFileFromUri(context, uri);
        if (fileFromUri == null) {
            com.facebook.common.e.a.c("ReactNative", "Couldn't get real path for uri: " + uri);
            return;
        }
        ExifInterface exifInterface = new ExifInterface(fileFromUri.getAbsolutePath());
        ExifInterface exifInterface2 = new ExifInterface(file.getAbsolutePath());
        String[] strArr = EXIF_ATTRIBUTES;
        for (String str : strArr) {
            String attribute = exifInterface.getAttribute(str);
            if (attribute != null) {
                exifInterface2.setAttribute(str, attribute);
            }
        }
        exifInterface2.saveAttributes();
    }

    private static File getFileFromUri(Context context, Uri uri) {
        Cursor query;
        if (uri.getScheme().equals("file")) {
            return new File(uri.getPath());
        }
        if (!uri.getScheme().equals(FirebaseAnalytics.b.CONTENT) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        try {
            if (query.moveToFirst()) {
                String string = query.getString(0);
                if (!TextUtils.isEmpty(string)) {
                    return new File(string);
                }
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    /* access modifiers changed from: private */
    public static boolean isLocalUri(String str) {
        for (String str2 : LOCAL_URI_PREFIXES) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private static String getFileExtensionForType(String str) {
        if ("image/png".equals(str)) {
            return ".png";
        }
        return "image/webp".equals(str) ? ".webp" : ".jpg";
    }

    private static Bitmap.CompressFormat getCompressFormatForType(String str) {
        if ("image/png".equals(str)) {
            return Bitmap.CompressFormat.PNG;
        }
        if ("image/webp".equals(str)) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    /* access modifiers changed from: private */
    public static void writeCompressedBitmapToFile(Bitmap bitmap, String str, File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(getCompressFormatForType(str), 90, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    /* access modifiers changed from: private */
    public static File createTempFile(Context context, String str) {
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        if (externalCacheDir == null) {
            externalCacheDir = cacheDir;
        } else if (cacheDir != null && externalCacheDir.getFreeSpace() <= cacheDir.getFreeSpace()) {
            externalCacheDir = cacheDir;
        }
        return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(str), externalCacheDir);
    }

    /* access modifiers changed from: private */
    public static int getDecodeSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i3 || i > i4) {
            int i6 = i2 / 2;
            int i7 = i / 2;
            while (i7 / i5 >= i3 && i6 / i5 >= i4) {
                i5 *= 2;
            }
        }
        return i5;
    }
}
