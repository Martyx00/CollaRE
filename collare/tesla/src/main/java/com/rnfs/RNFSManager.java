package com.rnfs;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.rnfs.a;
import com.rnfs.f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public class RNFSManager extends ReactContextBaseJavaModule {
    private static final String RNFSCachesDirectoryPath = "RNFSCachesDirectoryPath";
    private static final String RNFSDocumentDirectory = "RNFSDocumentDirectory";
    private static final String RNFSDocumentDirectoryPath = "RNFSDocumentDirectoryPath";
    private static final String RNFSExternalCachesDirectoryPath = "RNFSExternalCachesDirectoryPath";
    private static final String RNFSExternalDirectoryPath = "RNFSExternalDirectoryPath";
    private static final String RNFSExternalStorageDirectoryPath = "RNFSExternalStorageDirectoryPath";
    private static final String RNFSFileTypeDirectory = "RNFSFileTypeDirectory";
    private static final String RNFSFileTypeRegular = "RNFSFileTypeRegular";
    private static final String RNFSPicturesDirectoryPath = "RNFSPicturesDirectoryPath";
    private static final String RNFSTemporaryDirectoryPath = "RNFSTemporaryDirectoryPath";
    private SparseArray<c> downloaders = new SparseArray<>();
    private ReactApplicationContext reactContext;
    private SparseArray<h> uploaders = new SparseArray<>();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNFSManager";
    }

    @ReactMethod
    public void pathForBundle(String str, Promise promise) {
    }

    @ReactMethod
    public void pathForGroup(String str, Promise promise) {
    }

    public RNFSManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    private Uri getFileUri(String str, boolean z) {
        Uri parse = Uri.parse(str);
        if (parse.getScheme() != null) {
            return parse;
        }
        File file = new File(str);
        if (z || !file.isDirectory()) {
            return Uri.parse("file://" + str);
        }
        throw new d("EISDIR", "EISDIR: illegal operation on a directory, read '" + str + "'");
    }

    private String getOriginalFilepath(String str, boolean z) {
        Uri fileUri = getFileUri(str, z);
        if (!fileUri.getScheme().equals(FirebaseAnalytics.b.CONTENT)) {
            return str;
        }
        try {
            Cursor query = this.reactContext.getContentResolver().query(fileUri, null, null, null, null);
            return query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow("_data")) : str;
        } catch (IllegalArgumentException unused) {
            return str;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private InputStream getInputStream(String str) {
        try {
            InputStream openInputStream = this.reactContext.getContentResolver().openInputStream(getFileUri(str, false));
            if (openInputStream != null) {
                return openInputStream;
            }
            throw new d("ENOENT", "ENOENT: could not open an input stream for '" + str + "'");
        } catch (FileNotFoundException unused) {
            throw new d("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private OutputStream getOutputStream(String str, boolean z) {
        try {
            OutputStream openOutputStream = this.reactContext.getContentResolver().openOutputStream(getFileUri(str, false), z ? "wa" : "w");
            if (openOutputStream != null) {
                return openOutputStream;
            }
            throw new d("ENOENT", "ENOENT: could not open an output stream for '" + str + "'");
        } catch (FileNotFoundException unused) {
            throw new d("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
        }
    }

    private static byte[] getInputStreamBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @ReactMethod
    public void writeFile(String str, String str2, ReadableMap readableMap, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, false);
            outputStream.write(decode);
            outputStream.close();
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void appendFile(String str, String str2, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, true);
            outputStream.write(decode);
            outputStream.close();
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void write(String str, String str2, int i, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            if (i < 0) {
                OutputStream outputStream = getOutputStream(str, true);
                outputStream.write(decode);
                outputStream.close();
            } else {
                RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
                randomAccessFile.seek((long) i);
                randomAccessFile.write(decode);
                randomAccessFile.close();
            }
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void exists(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).exists()));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readFile(String str, Promise promise) {
        try {
            promise.resolve(Base64.encodeToString(getInputStreamBytes(getInputStream(str)), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void read(String str, int i, int i2, Promise promise) {
        try {
            InputStream inputStream = getInputStream(str);
            byte[] bArr = new byte[i];
            inputStream.skip((long) i2);
            promise.resolve(Base64.encodeToString(bArr, 0, inputStream.read(bArr, 0, i), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readFileAssets(String str, Promise promise) {
        InputStream inputStream = null;
        try {
            inputStream = getReactApplicationContext().getAssets().open(str, 0);
            if (inputStream == null) {
                reject(promise, str, new Exception("Failed to open file"));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
            } else {
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                promise.resolve(Base64.encodeToString(bArr, 2));
                if (inputStream == null) {
                    return;
                }
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
            if (0 == 0) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    @ReactMethod
    public void hash(String str, String str2, Promise promise) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("md5", "MD5");
            hashMap.put("sha1", McElieceCCA2KeyGenParameterSpec.SHA1);
            hashMap.put("sha224", McElieceCCA2KeyGenParameterSpec.SHA224);
            hashMap.put("sha256", McElieceCCA2KeyGenParameterSpec.SHA256);
            hashMap.put("sha384", McElieceCCA2KeyGenParameterSpec.SHA384);
            hashMap.put("sha512", McElieceCCA2KeyGenParameterSpec.SHA512);
            if (hashMap.containsKey(str2)) {
                File file = new File(str);
                if (file.isDirectory()) {
                    rejectFileIsDirectory(promise);
                } else if (!file.exists()) {
                    rejectFileNotFound(promise, str);
                } else {
                    MessageDigest instance = MessageDigest.getInstance((String) hashMap.get(str2));
                    FileInputStream fileInputStream = new FileInputStream(str);
                    byte[] bArr = new byte[Task.EXTRAS_LIMIT_BYTES];
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
            } else {
                throw new Exception("Invalid hash algorithm");
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void moveFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        try {
            final File file = new File(str);
            if (!file.renameTo(new File(str2))) {
                new a() {
                    /* class com.rnfs.RNFSManager.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Exception exc) {
                        if (exc == null) {
                            file.delete();
                            promise.resolve(true);
                            return;
                        }
                        exc.printStackTrace();
                        RNFSManager.this.reject(promise, str, exc);
                    }
                }.execute(new String[]{str, str2});
                return;
            }
            promise.resolve(true);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void copyFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        new a() {
            /* class com.rnfs.RNFSManager.AnonymousClass2 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Exception exc) {
                if (exc == null) {
                    promise.resolve(null);
                    return;
                }
                exc.printStackTrace();
                RNFSManager.this.reject(promise, str, exc);
            }
        }.execute(new String[]{str, str2});
    }

    private class a extends AsyncTask<String, Void, Exception> {
        private a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Exception doInBackground(String... strArr) {
            try {
                String str = strArr[0];
                String str2 = strArr[1];
                InputStream inputStream = RNFSManager.this.getInputStream(str);
                OutputStream outputStream = RNFSManager.this.getOutputStream(str2, false);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        Thread.yield();
                    } else {
                        inputStream.close();
                        outputStream.close();
                        return null;
                    }
                }
            } catch (Exception e2) {
                return e2;
            }
        }
    }

    @ReactMethod
    public void readDir(String str, Promise promise) {
        try {
            File file = new File(str);
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                WritableArray createArray = Arguments.createArray();
                for (File file2 : listFiles) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putDouble("mtime", ((double) file2.lastModified()) / 1000.0d);
                    createMap.putString("name", file2.getName());
                    createMap.putString("path", file2.getAbsolutePath());
                    createMap.putInt("size", (int) file2.length());
                    createMap.putInt(AppMeasurement.Param.TYPE, file2.isDirectory() ? 1 : 0);
                    createArray.pushMap(createMap);
                }
                promise.resolve(createArray);
                return;
            }
            throw new Exception("Folder does not exist");
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006c A[SYNTHETIC] */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readDirAssets(java.lang.String r13, com.facebook.react.bridge.Promise r14) {
        /*
        // Method dump skipped, instructions count: 126
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.RNFSManager.readDirAssets(java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void copyFileAssets(String str, String str2, Promise promise) {
        try {
            copyInputStream(getReactApplicationContext().getAssets().open(str), str, str2, promise);
        } catch (IOException unused) {
            reject(promise, str, new Exception(String.format("Asset '%s' could not be opened", str)));
        }
    }

    @ReactMethod
    public void existsAssets(String str, Promise promise) {
        try {
            AssetManager assets = getReactApplicationContext().getAssets();
            try {
                String[] list = assets.list(str);
                if (list != null && list.length > 0) {
                    promise.resolve(true);
                    return;
                }
            } catch (Exception unused) {
            }
            InputStream inputStream = null;
            try {
                inputStream = assets.open(str);
                promise.resolve(true);
                if (inputStream == null) {
                    return;
                }
            } catch (Exception unused2) {
                promise.resolve(false);
                if (inputStream == null) {
                    return;
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
            try {
                inputStream.close();
            } catch (Exception unused4) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x004f A[SYNTHETIC, Splitter:B:26:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005a A[SYNTHETIC, Splitter:B:32:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0061 A[SYNTHETIC, Splitter:B:36:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void copyInputStream(java.io.InputStream r8, java.lang.String r9, java.lang.String r10, com.facebook.react.bridge.Promise r11) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.RNFSManager.copyInputStream(java.io.InputStream, java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void setReadable(String str, Boolean bool, Boolean bool2, Promise promise) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.setReadable(bool.booleanValue(), bool2.booleanValue());
                promise.resolve(true);
                return;
            }
            throw new Exception("File does not exist");
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void stat(String str, Promise promise) {
        int i = 1;
        try {
            String originalFilepath = getOriginalFilepath(str, true);
            File file = new File(originalFilepath);
            if (file.exists()) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("ctime", (int) (file.lastModified() / 1000));
                createMap.putInt("mtime", (int) (file.lastModified() / 1000));
                createMap.putInt("size", (int) file.length());
                if (!file.isDirectory()) {
                    i = 0;
                }
                createMap.putInt(AppMeasurement.Param.TYPE, i);
                createMap.putString("originalFilepath", originalFilepath);
                promise.resolve(createMap);
                return;
            }
            throw new Exception("File does not exist");
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void unlink(String str, Promise promise) {
        try {
            File file = new File(str);
            if (file.exists()) {
                DeleteRecursive(file);
                promise.resolve(null);
                return;
            }
            throw new Exception("File does not exist");
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    private void DeleteRecursive(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                DeleteRecursive(file2);
            }
        }
        file.delete();
    }

    @ReactMethod
    public void mkdir(String str, ReadableMap readableMap, Promise promise) {
        try {
            File file = new File(str);
            file.mkdirs();
            if (file.exists()) {
                promise.resolve(null);
                return;
            }
            throw new Exception("Directory could not be created");
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendEvent(ReactContext reactContext2, String str, WritableMap writableMap) {
        ((RCTNativeAppEventEmitter) reactContext2.getJSModule(RCTNativeAppEventEmitter.class)).emit(str, writableMap);
    }

    @ReactMethod
    public void downloadFile(final ReadableMap readableMap, final Promise promise) {
        try {
            File file = new File(readableMap.getString("toFile"));
            URL url = new URL(readableMap.getString("fromUrl"));
            final int i = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            int i2 = readableMap.getInt("progressDivider");
            int i3 = readableMap.getInt("readTimeout");
            int i4 = readableMap.getInt("connectionTimeout");
            a aVar = new a();
            aVar.f4806a = url;
            aVar.f4807b = file;
            aVar.f4808c = map;
            aVar.f4809d = (float) i2;
            aVar.e = i3;
            aVar.f = i4;
            aVar.g = new a.c() {
                /* class com.rnfs.RNFSManager.AnonymousClass3 */

                @Override // com.rnfs.a.c
                public void a(b bVar) {
                    if (bVar.f4812c == null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("jobId", i);
                        createMap.putInt("statusCode", bVar.f4810a);
                        createMap.putDouble("bytesWritten", (double) bVar.f4811b);
                        promise.resolve(createMap);
                        return;
                    }
                    RNFSManager.this.reject(promise, readableMap.getString("toFile"), bVar.f4812c);
                }
            };
            aVar.h = new a.AbstractC0087a() {
                /* class com.rnfs.RNFSManager.AnonymousClass4 */

                @Override // com.rnfs.a.AbstractC0087a
                public void a(int i, long j, Map<String, String> map) {
                    WritableMap createMap = Arguments.createMap();
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        createMap.putString(entry.getKey(), entry.getValue());
                    }
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putInt("jobId", i);
                    createMap2.putInt("statusCode", i);
                    createMap2.putDouble("contentLength", (double) j);
                    createMap2.putMap("headers", createMap);
                    RNFSManager rNFSManager = RNFSManager.this;
                    ReactApplicationContext reactApplicationContext = rNFSManager.getReactApplicationContext();
                    rNFSManager.sendEvent(reactApplicationContext, "DownloadBegin-" + i, createMap2);
                }
            };
            aVar.i = new a.b() {
                /* class com.rnfs.RNFSManager.AnonymousClass5 */

                @Override // com.rnfs.a.b
                public void a(long j, long j2) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("jobId", i);
                    createMap.putDouble("contentLength", (double) j);
                    createMap.putDouble("bytesWritten", (double) j2);
                    RNFSManager rNFSManager = RNFSManager.this;
                    ReactApplicationContext reactApplicationContext = rNFSManager.getReactApplicationContext();
                    rNFSManager.sendEvent(reactApplicationContext, "DownloadProgress-" + i, createMap);
                }
            };
            c cVar = new c();
            cVar.execute(aVar);
            this.downloaders.put(i, cVar);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, readableMap.getString("toFile"), e);
        }
    }

    @ReactMethod
    public void stopDownload(int i) {
        c cVar = this.downloaders.get(i);
        if (cVar != null) {
            cVar.a();
        }
    }

    @ReactMethod
    public void uploadFiles(final ReadableMap readableMap, final Promise promise) {
        try {
            ReadableArray array = readableMap.getArray("files");
            URL url = new URL(readableMap.getString("toUrl"));
            final int i = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            ReadableMap map2 = readableMap.getMap("fields");
            String string = readableMap.getString(FirebaseAnalytics.b.METHOD);
            ArrayList<ReadableMap> arrayList = new ArrayList<>();
            f fVar = new f();
            for (int i2 = 0; i2 < array.size(); i2++) {
                arrayList.add(array.getMap(i2));
            }
            fVar.f4818a = url;
            fVar.f4819b = arrayList;
            fVar.f4820c = map;
            fVar.e = string;
            fVar.f4821d = map2;
            fVar.f = new f.b() {
                /* class com.rnfs.RNFSManager.AnonymousClass6 */

                @Override // com.rnfs.f.b
                public void a(g gVar) {
                    if (gVar.f4824c == null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("jobId", i);
                        createMap.putInt("statusCode", gVar.f4822a);
                        createMap.putMap("headers", gVar.f4823b);
                        createMap.putString("body", gVar.f4825d);
                        promise.resolve(createMap);
                        return;
                    }
                    RNFSManager.this.reject(promise, readableMap.getString("toUrl"), gVar.f4824c);
                }
            };
            fVar.h = new f.a() {
                /* class com.rnfs.RNFSManager.AnonymousClass7 */

                @Override // com.rnfs.f.a
                public void a() {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("jobId", i);
                    RNFSManager rNFSManager = RNFSManager.this;
                    ReactApplicationContext reactApplicationContext = rNFSManager.getReactApplicationContext();
                    rNFSManager.sendEvent(reactApplicationContext, "UploadBegin-" + i, createMap);
                }
            };
            fVar.g = new f.c() {
                /* class com.rnfs.RNFSManager.AnonymousClass8 */

                @Override // com.rnfs.f.c
                public void a(int i, int i2) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("jobId", i);
                    createMap.putInt("totalBytesExpectedToSend", i);
                    createMap.putInt("totalBytesSent", i2);
                    RNFSManager rNFSManager = RNFSManager.this;
                    ReactApplicationContext reactApplicationContext = rNFSManager.getReactApplicationContext();
                    rNFSManager.sendEvent(reactApplicationContext, "UploadProgress-" + i, createMap);
                }
            };
            h hVar = new h();
            hVar.execute(fVar);
            this.uploaders.put(i, hVar);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, readableMap.getString("toUrl"), e);
        }
    }

    @ReactMethod
    public void stopUpload(int i) {
        h hVar = this.uploaders.get(i);
        if (hVar != null) {
            hVar.a();
        }
    }

    @ReactMethod
    public void getFSInfo(Promise promise) {
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            j = statFs.getTotalBytes();
            j2 = statFs.getFreeBytes();
        } else {
            long blockSize = (long) statFs.getBlockSize();
            j2 = ((long) statFs.getAvailableBlocks()) * blockSize;
            j = ((long) statFs.getBlockCount()) * blockSize;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("totalSpace", (double) j);
        createMap.putDouble("freeSpace", (double) j2);
        promise.resolve(createMap);
    }

    @ReactMethod
    public void touch(String str, double d2, double d3, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).setLastModified((long) d2)));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void getAllExternalFilesDirs(Promise promise) {
        File[] externalFilesDirs = getReactApplicationContext().getExternalFilesDirs(null);
        WritableArray createArray = Arguments.createArray();
        for (File file : externalFilesDirs) {
            createArray.pushString(file.getAbsolutePath());
        }
        promise.resolve(createArray);
    }

    @ReactMethod
    public void scanFile(String str, final Promise promise) {
        MediaScannerConnection.scanFile(getReactApplicationContext(), new String[]{str}, null, new MediaScannerConnection.MediaScannerConnectionClient() {
            /* class com.rnfs.RNFSManager.AnonymousClass9 */

            public void onMediaScannerConnected() {
            }

            public void onScanCompleted(String str, Uri uri) {
                promise.resolve(str);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void reject(Promise promise, String str, Exception exc) {
        if (exc instanceof FileNotFoundException) {
            rejectFileNotFound(promise, str);
        } else if (exc instanceof d) {
            d dVar = (d) exc;
            promise.reject(dVar.a(), dVar.getMessage());
        } else {
            promise.reject((String) null, exc.getMessage());
        }
    }

    private void rejectFileNotFound(Promise promise, String str) {
        promise.reject("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
    }

    private void rejectFileIsDirectory(Promise promise) {
        promise.reject("EISDIR", "EISDIR: illegal operation on a directory, read");
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(RNFSDocumentDirectory, 0);
        hashMap.put(RNFSDocumentDirectoryPath, getReactApplicationContext().getFilesDir().getAbsolutePath());
        hashMap.put(RNFSTemporaryDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        hashMap.put(RNFSPicturesDirectoryPath, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        hashMap.put(RNFSCachesDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        hashMap.put(RNFSFileTypeRegular, 0);
        hashMap.put(RNFSFileTypeDirectory, 1);
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            hashMap.put(RNFSExternalStorageDirectoryPath, externalStorageDirectory.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalStorageDirectoryPath, null);
        }
        File externalFilesDir = getReactApplicationContext().getExternalFilesDir(null);
        if (externalFilesDir != null) {
            hashMap.put(RNFSExternalDirectoryPath, externalFilesDir.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalDirectoryPath, null);
        }
        File externalCacheDir = getReactApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            hashMap.put(RNFSExternalCachesDirectoryPath, externalCacheDir.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalCachesDirectoryPath, null);
        }
        return hashMap;
    }
}
