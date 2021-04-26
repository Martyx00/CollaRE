package com.RNFetchBlob;

import android.content.res.AssetFileDescriptor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.Base64;
import com.RNFetchBlob.b.a;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tagmanager.DataLayer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* access modifiers changed from: package-private */
/* compiled from: RNFetchBlobFS */
public class d {
    private static HashMap<String, d> e = new HashMap<>();

    /* renamed from: a  reason: collision with root package name */
    private ReactApplicationContext f1463a;

    /* renamed from: b  reason: collision with root package name */
    private DeviceEventManagerModule.RCTDeviceEventEmitter f1464b;

    /* renamed from: c  reason: collision with root package name */
    private String f1465c = "base64";

    /* renamed from: d  reason: collision with root package name */
    private OutputStream f1466d = null;

    d(ReactApplicationContext reactApplicationContext) {
        this.f1463a = reactApplicationContext;
        this.f1464b = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    static void a(String str, String str2, String str3, boolean z, Promise promise) {
        int i;
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    promise.reject("EUNSPECIFIED", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            if (str2.equalsIgnoreCase("uri")) {
                String d2 = d(str3);
                File file2 = new File(d2);
                if (!file2.exists()) {
                    promise.reject("ENOENT", "No such file '" + str + "' ('" + d2 + "')");
                    fileOutputStream.close();
                    return;
                }
                FileInputStream fileInputStream = new FileInputStream(file2);
                byte[] bArr = new byte[Task.EXTRAS_LIMIT_BYTES];
                i = 0;
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    i += read;
                }
                fileInputStream.close();
            } else {
                byte[] a2 = a(str3, str2);
                fileOutputStream.write(a2);
                i = a2.length;
            }
            fileOutputStream.close();
            promise.resolve(Integer.valueOf(i));
        } catch (FileNotFoundException unused) {
            promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created, or it is a directory");
        } catch (Exception e2) {
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    static void a(String str, ReadableArray readableArray, boolean z, Promise promise) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    promise.reject("ENOTDIR", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            byte[] bArr = new byte[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                bArr[i] = (byte) readableArray.getInt(i);
            }
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            promise.resolve(Integer.valueOf(readableArray.size()));
        } catch (FileNotFoundException unused) {
            promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
        } catch (Exception e2) {
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007c A[Catch:{ FileNotFoundException -> 0x0040, Exception -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009b A[Catch:{ FileNotFoundException -> 0x0040, Exception -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d7 A[Catch:{ FileNotFoundException -> 0x0040, Exception -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00da A[Catch:{ FileNotFoundException -> 0x0040, Exception -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e4 A[Catch:{ FileNotFoundException -> 0x0040, Exception -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f7 A[Catch:{ FileNotFoundException -> 0x0040, Exception -> 0x003d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(java.lang.String r6, java.lang.String r7, com.facebook.react.bridge.Promise r8) {
        /*
        // Method dump skipped, instructions count: 356
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.d.a(java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    static Map<String, Object> a(ReactApplicationContext reactApplicationContext) {
        HashMap hashMap = new HashMap();
        hashMap.put("DocumentDir", reactApplicationContext.getFilesDir().getAbsolutePath());
        hashMap.put("CacheDir", reactApplicationContext.getCacheDir().getAbsolutePath());
        hashMap.put("DCIMDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        hashMap.put("PictureDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        hashMap.put("MusicDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        hashMap.put("DownloadDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        hashMap.put("MovieDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        hashMap.put("RingtoneDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
        if (Environment.getExternalStorageState().equals("mounted")) {
            hashMap.put("SDCardDir", Environment.getExternalStorageDirectory().getAbsolutePath());
            File externalFilesDir = reactApplicationContext.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                hashMap.put("SDCardApplicationDir", externalFilesDir.getParentFile().getAbsolutePath());
            } else {
                hashMap.put("SDCardApplicationDir", "");
            }
        }
        hashMap.put("MainBundleDir", reactApplicationContext.getApplicationInfo().dataDir);
        return hashMap;
    }

    public static void a(Promise promise) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            promise.resolve(Environment.getExternalStorageDirectory().getAbsolutePath());
        } else {
            promise.reject("RNFetchBlob.getSDCardDir", "External storage not mounted");
        }
    }

    public static void a(ReactApplicationContext reactApplicationContext, Promise promise) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                promise.resolve(reactApplicationContext.getExternalFilesDir(null).getParentFile().getAbsolutePath());
            } catch (Exception e2) {
                promise.reject("RNFetchBlob.getSDCardApplicationDir", e2.getLocalizedMessage());
            }
        } else {
            promise.reject("RNFetchBlob.getSDCardApplicationDir", "External storage not mounted");
        }
    }

    static String a(String str) {
        return RNFetchBlob.RCTContext.getFilesDir() + "/RNFetchBlobTmp_" + str;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, int i, int i2, String str3) {
        InputStream inputStream;
        String d2 = d(str);
        if (d2 != null) {
            str = d2;
        }
        try {
            int i3 = str2.equalsIgnoreCase("base64") ? 4095 : 4096;
            if (i <= 0) {
                i = i3;
            }
            if (d2 != null && str.startsWith("bundle-assets://")) {
                inputStream = RNFetchBlob.RCTContext.getAssets().open(str.replace("bundle-assets://", ""));
            } else if (d2 == null) {
                inputStream = RNFetchBlob.RCTContext.getContentResolver().openInputStream(Uri.parse(str));
            } else {
                inputStream = new FileInputStream(new File(str));
            }
            byte[] bArr = new byte[i];
            boolean z = false;
            if (str2.equalsIgnoreCase("utf8")) {
                CharsetEncoder newEncoder = Charset.forName("UTF-8").newEncoder();
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    newEncoder.encode(ByteBuffer.wrap(bArr).asCharBuffer());
                    a(str3, "data", new String(bArr, 0, read));
                    if (i2 > 0) {
                        SystemClock.sleep((long) i2);
                    }
                }
            } else if (str2.equalsIgnoreCase("ascii")) {
                while (true) {
                    int read2 = inputStream.read(bArr);
                    if (read2 == -1) {
                        break;
                    }
                    WritableArray createArray = Arguments.createArray();
                    for (int i4 = 0; i4 < read2; i4++) {
                        createArray.pushInt(bArr[i4]);
                    }
                    a(str3, "data", createArray);
                    if (i2 > 0) {
                        SystemClock.sleep((long) i2);
                    }
                }
            } else if (str2.equalsIgnoreCase("base64")) {
                while (true) {
                    int read3 = inputStream.read(bArr);
                    if (read3 == -1) {
                        break;
                    }
                    if (read3 < i) {
                        byte[] bArr2 = new byte[read3];
                        System.arraycopy(bArr, 0, bArr2, 0, read3);
                        a(str3, "data", Base64.encodeToString(bArr2, 2));
                    } else {
                        a(str3, "data", Base64.encodeToString(bArr, 2));
                    }
                    if (i2 > 0) {
                        SystemClock.sleep((long) i2);
                    }
                }
            } else {
                a(str3, "error", "EINVAL", "Unrecognized encoding `" + str2 + "`, should be one of `base64`, `utf8`, `ascii`");
                z = true;
            }
            if (!z) {
                a(str3, "end", "");
            }
            inputStream.close();
        } catch (FileNotFoundException unused) {
            a(str3, "error", "ENOENT", "No such file '" + str + "'");
        } catch (Exception e2) {
            a(str3, "error", "EUNSPECIFIED", "Failed to convert data to " + str2 + " encoded string. This might be because this encoding cannot be used for this data.");
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, boolean z, Callback callback) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    callback.invoke("ENOTDIR", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    callback.invoke("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            } else if (file.isDirectory()) {
                callback.invoke("EISDIR", "Expecting a file but '" + str + "' is a directory");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str, z);
            this.f1465c = str2;
            String uuid = UUID.randomUUID().toString();
            e.put(uuid, this);
            this.f1466d = fileOutputStream;
            callback.invoke(null, null, uuid);
        } catch (Exception e2) {
            callback.invoke("EUNSPECIFIED", "Failed to create write stream at path `" + str + "`; " + e2.getLocalizedMessage());
        }
    }

    static void a(String str, String str2, Callback callback) {
        d dVar = e.get(str);
        try {
            dVar.f1466d.write(a(str2, dVar.f1465c));
            callback.invoke(new Object[0]);
        } catch (Exception e2) {
            callback.invoke(e2.getLocalizedMessage());
        }
    }

    static void a(String str, ReadableArray readableArray, Callback callback) {
        try {
            OutputStream outputStream = e.get(str).f1466d;
            byte[] bArr = new byte[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                bArr[i] = (byte) readableArray.getInt(i);
            }
            outputStream.write(bArr);
            callback.invoke(new Object[0]);
        } catch (Exception e2) {
            callback.invoke(e2.getLocalizedMessage());
        }
    }

    static void a(String str, Callback callback) {
        try {
            OutputStream outputStream = e.get(str).f1466d;
            e.remove(str);
            outputStream.close();
            callback.invoke(new Object[0]);
        } catch (Exception e2) {
            callback.invoke(e2.getLocalizedMessage());
        }
    }

    static void b(String str, Callback callback) {
        try {
            a(new File(d(str)));
            callback.invoke(null, true);
        } catch (Exception e2) {
            callback.invoke(e2.getLocalizedMessage(), false);
        }
    }

    private static void a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2);
                }
            } else {
                throw new NullPointerException("Received null trying to list files of directory '" + file + "'");
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete '" + file + "'");
        }
    }

    static void a(String str, Promise promise) {
        String str2;
        File file = new File(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                str2 = "Folder";
            } else {
                str2 = "File '" + str + "' already exists";
            }
            promise.reject("EEXIST", str2);
            return;
        }
        try {
            if (!file.mkdirs()) {
                promise.reject("EUNSPECIFIED", "mkdir failed to create some or all directories in '" + str + "'");
                return;
            }
            promise.resolve(true);
        } catch (Exception e2) {
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b2 A[SYNTHETIC, Splitter:B:43:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ba A[Catch:{ Exception -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e6 A[SYNTHETIC, Splitter:B:57:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ee A[Catch:{ Exception -> 0x00ea }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void b(java.lang.String r6, java.lang.String r7, com.facebook.react.bridge.Callback r8) {
        /*
        // Method dump skipped, instructions count: 261
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.d.b(java.lang.String, java.lang.String, com.facebook.react.bridge.Callback):void");
    }

    static void c(String str, String str2, Callback callback) {
        File file = new File(str);
        if (!file.exists()) {
            callback.invoke("Source file at path `" + str + "` does not exist");
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    file.delete();
                    callback.invoke(new Object[0]);
                    return;
                }
            }
        } catch (FileNotFoundException unused) {
            callback.invoke("Source file not found.");
        } catch (Exception e2) {
            callback.invoke(e2.toString());
        }
    }

    static void c(String str, Callback callback) {
        if (c(str)) {
            try {
                RNFetchBlob.RCTContext.getAssets().openFd(str.replace("bundle-assets://", ""));
                callback.invoke(true, false);
            } catch (IOException unused) {
                callback.invoke(false, false);
            }
        } else {
            String d2 = d(str);
            callback.invoke(Boolean.valueOf(new File(d2).exists()), Boolean.valueOf(new File(d2).isDirectory()));
        }
    }

    static void b(String str, Promise promise) {
        try {
            String d2 = d(str);
            File file = new File(d2);
            if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + d2 + "'");
            } else if (!file.isDirectory()) {
                promise.reject("ENOTDIR", "Not a directory '" + d2 + "'");
            } else {
                String[] list = new File(d2).list();
                WritableArray createArray = Arguments.createArray();
                for (String str2 : list) {
                    createArray.pushString(str2);
                }
                promise.resolve(createArray);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    static void a(String str, String str2, int i, int i2, String str3, Promise promise) {
        try {
            String d2 = d(str);
            File file = new File(d2);
            if (file.isDirectory()) {
                promise.reject("EISDIR", "Expecting a file but '" + d2 + "' is a directory");
            } else if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + d2 + "'");
            } else {
                int length = (int) file.length();
                int min = Math.min(length, i2) - i;
                FileInputStream fileInputStream = new FileInputStream(new File(d2));
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
                int skip = (int) fileInputStream.skip((long) i);
                if (skip != i) {
                    promise.reject("EUNSPECIFIED", "Skipped " + skip + " instead of the specified " + i + " bytes, size is " + length);
                    return;
                }
                byte[] bArr = new byte[Task.EXTRAS_LIMIT_BYTES];
                int i3 = 0;
                while (true) {
                    if (i3 >= min) {
                        break;
                    }
                    int read = fileInputStream.read(bArr, 0, Task.EXTRAS_LIMIT_BYTES);
                    int i4 = min - i3;
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, Math.min(i4, read));
                    i3 += read;
                }
                fileInputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                promise.resolve(str2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    static void d(String str, final Callback callback) {
        String d2 = d(str);
        new AsyncTask<String, Integer, Integer>() {
            /* class com.RNFetchBlob.d.AnonymousClass1 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Integer doInBackground(String... strArr) {
                WritableArray createArray = Arguments.createArray();
                if (strArr[0] == null) {
                    callback.invoke("the path specified for lstat is either `null` or `undefined`.");
                    return 0;
                }
                File file = new File(strArr[0]);
                if (!file.exists()) {
                    callback.invoke("failed to lstat path `" + strArr[0] + "` because it does not exist or it is not a folder");
                    return 0;
                }
                if (file.isDirectory()) {
                    String[] list = file.list();
                    for (String str : list) {
                        createArray.pushMap(d.b(file.getPath() + "/" + str));
                    }
                } else {
                    createArray.pushMap(d.b(file.getAbsolutePath()));
                }
                callback.invoke(null, createArray);
                return 0;
            }
        }.execute(d2);
    }

    static void e(String str, Callback callback) {
        try {
            String d2 = d(str);
            WritableMap b2 = b(d2);
            if (b2 == null) {
                callback.invoke("failed to stat path `" + d2 + "` because it does not exist or it is not a folder", null);
                return;
            }
            callback.invoke(null, b2);
        } catch (Exception e2) {
            callback.invoke(e2.getLocalizedMessage());
        }
    }

    static WritableMap b(String str) {
        try {
            String d2 = d(str);
            WritableMap createMap = Arguments.createMap();
            if (c(d2)) {
                String replace = d2.replace("bundle-assets://", "");
                AssetFileDescriptor openFd = RNFetchBlob.RCTContext.getAssets().openFd(replace);
                createMap.putString("filename", replace);
                createMap.putString("path", d2);
                createMap.putString(AppMeasurement.Param.TYPE, "asset");
                createMap.putString("size", String.valueOf(openFd.getLength()));
                createMap.putInt("lastModified", 0);
            } else {
                File file = new File(d2);
                if (!file.exists()) {
                    return null;
                }
                createMap.putString("filename", file.getName());
                createMap.putString("path", file.getPath());
                createMap.putString(AppMeasurement.Param.TYPE, file.isDirectory() ? "directory" : "file");
                createMap.putString("size", String.valueOf(file.length()));
                createMap.putString("lastModified", String.valueOf(file.lastModified()));
            }
            return createMap;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String[] strArr, String[] strArr2, final Callback callback) {
        try {
            MediaScannerConnection.scanFile(this.f1463a, strArr, strArr2, new MediaScannerConnection.OnScanCompletedListener() {
                /* class com.RNFetchBlob.d.AnonymousClass2 */

                public void onScanCompleted(String str, Uri uri) {
                    callback.invoke(null, true);
                }
            });
        } catch (Exception e2) {
            callback.invoke(e2.getLocalizedMessage(), null);
        }
    }

    static void b(String str, String str2, Promise promise) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("md5", "MD5");
            hashMap.put("sha1", McElieceCCA2KeyGenParameterSpec.SHA1);
            hashMap.put("sha224", McElieceCCA2KeyGenParameterSpec.SHA224);
            hashMap.put("sha256", McElieceCCA2KeyGenParameterSpec.SHA256);
            hashMap.put("sha384", McElieceCCA2KeyGenParameterSpec.SHA384);
            hashMap.put("sha512", McElieceCCA2KeyGenParameterSpec.SHA512);
            if (!hashMap.containsKey(str2)) {
                promise.reject("EINVAL", "Invalid algorithm '" + str2 + "', must be one of md5, sha1, sha224, sha256, sha384, sha512");
                return;
            }
            File file = new File(str);
            if (file.isDirectory()) {
                promise.reject("EISDIR", "Expecting a file but '" + str + "' is a directory");
            } else if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + str + "'");
            } else {
                MessageDigest instance = MessageDigest.getInstance((String) hashMap.get(str2));
                FileInputStream fileInputStream = new FileInputStream(str);
                byte[] bArr = new byte[((int) file.length())];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                StringBuilder sb = new StringBuilder();
                byte[] digest = instance.digest();
                int length = digest.length;
                for (int i = 0; i < length; i++) {
                    sb.append(String.format("%02x", Byte.valueOf(digest[i])));
                }
                promise.resolve(sb.toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    static void a(String str, String str2, String str3, Promise promise) {
        try {
            File file = new File(str);
            boolean createNewFile = file.createNewFile();
            if (str3.equals("uri")) {
                File file2 = new File(str2.replace("RNFetchBlob-file://", ""));
                if (!file2.exists()) {
                    promise.reject("ENOENT", "Source file : " + str2 + " does not exist");
                    return;
                }
                FileInputStream fileInputStream = new FileInputStream(file2);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[Task.EXTRAS_LIMIT_BYTES];
                for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                    fileOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                fileOutputStream.close();
            } else if (!createNewFile) {
                promise.reject("EEXIST", "File `" + str + "` already exists");
                return;
            } else {
                new FileOutputStream(file).write(a(str2, str3));
            }
            promise.resolve(str);
        } catch (Exception e2) {
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    static void a(String str, ReadableArray readableArray, Promise promise) {
        try {
            File file = new File(str);
            if (!file.createNewFile()) {
                promise.reject("EEXIST", "File at path `" + str + "` already exists");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                bArr[i] = (byte) readableArray.getInt(i);
            }
            fileOutputStream.write(bArr);
            promise.resolve(str);
        } catch (Exception e2) {
            promise.reject("EUNSPECIFIED", e2.getLocalizedMessage());
        }
    }

    static void a(Callback callback) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        WritableMap createMap = Arguments.createMap();
        if (Build.VERSION.SDK_INT >= 18) {
            createMap.putString("internal_free", String.valueOf(statFs.getFreeBytes()));
            createMap.putString("internal_total", String.valueOf(statFs.getTotalBytes()));
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getPath());
            createMap.putString("external_free", String.valueOf(statFs2.getFreeBytes()));
            createMap.putString("external_total", String.valueOf(statFs2.getTotalBytes()));
        }
        callback.invoke(null, createMap);
    }

    static void a(ReadableArray readableArray, final Callback callback) {
        new AsyncTask<ReadableArray, Integer, Integer>() {
            /* class com.RNFetchBlob.d.AnonymousClass3 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Integer doInBackground(ReadableArray... readableArrayArr) {
                try {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < readableArrayArr[0].size(); i++) {
                        String string = readableArrayArr[0].getString(i);
                        File file = new File(string);
                        if (file.exists() && !file.delete()) {
                            arrayList.add(string);
                        }
                    }
                    if (arrayList.isEmpty()) {
                        callback.invoke(null, true);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to delete: ");
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            sb.append((String) it.next());
                            sb.append(", ");
                        }
                        callback.invoke(sb.toString());
                    }
                } catch (Exception e) {
                    callback.invoke(e.getLocalizedMessage());
                }
                return Integer.valueOf(readableArrayArr[0].size());
            }
        }.execute(readableArray);
    }

    private static byte[] a(String str, String str2) {
        if (str2.equalsIgnoreCase("ascii")) {
            return str.getBytes(Charset.forName("US-ASCII"));
        }
        if (str2.toLowerCase().contains("base64")) {
            return Base64.decode(str, 2);
        }
        if (str2.equalsIgnoreCase("utf8")) {
            return str.getBytes(Charset.forName("UTF-8"));
        }
        return str.getBytes(Charset.forName("US-ASCII"));
    }

    private void a(String str, String str2, String str3) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(DataLayer.EVENT_KEY, str2);
        createMap.putString(ProductAction.ACTION_DETAIL, str3);
        this.f1464b.emit(str, createMap);
    }

    private void a(String str, String str2, WritableArray writableArray) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(DataLayer.EVENT_KEY, str2);
        createMap.putArray(ProductAction.ACTION_DETAIL, writableArray);
        this.f1464b.emit(str, createMap);
    }

    private void a(String str, String str2, String str3, String str4) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(DataLayer.EVENT_KEY, str2);
        createMap.putString("code", str3);
        createMap.putString(ProductAction.ACTION_DETAIL, str4);
        this.f1464b.emit(str, createMap);
    }

    private static InputStream e(String str) {
        if (str.startsWith("bundle-assets://")) {
            return RNFetchBlob.RCTContext.getAssets().open(str.replace("bundle-assets://", ""));
        }
        return new FileInputStream(new File(str));
    }

    private static boolean f(String str) {
        if (!str.startsWith("bundle-assets://")) {
            return new File(str).exists();
        }
        try {
            RNFetchBlob.RCTContext.getAssets().open(str.replace("bundle-assets://", ""));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    static boolean c(String str) {
        return str != null && str.startsWith("bundle-assets://");
    }

    static String d(String str) {
        if (str == null) {
            return null;
        }
        if (!str.matches("\\w+\\:.*")) {
            return str;
        }
        if (str.startsWith("file://")) {
            return str.replace("file://", "");
        }
        Uri parse = Uri.parse(str);
        if (str.startsWith("bundle-assets://")) {
            return str;
        }
        return a.a(RNFetchBlob.RCTContext, parse);
    }
}
