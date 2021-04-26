package com.crashlytics.android.core;

import android.content.Context;
import io.a.a.a.a.b.i;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

final class NativeFileUtils {
    private NativeFileUtils() {
    }

    static byte[] readFile(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] readBytes = readBytes(fileInputStream);
                i.a((Closeable) fileInputStream);
                return readBytes;
            } catch (FileNotFoundException unused) {
                i.a((Closeable) fileInputStream);
                return null;
            } catch (IOException unused2) {
                i.a((Closeable) fileInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                i.a((Closeable) fileInputStream2);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            fileInputStream = null;
            i.a((Closeable) fileInputStream);
            return null;
        } catch (IOException unused4) {
            fileInputStream = null;
            i.a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            i.a((Closeable) fileInputStream2);
            throw th;
        }
    }

    private static byte[] readBytes(InputStream inputStream) {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private static File filter(File file, String str) {
        File[] listFiles = file.listFiles();
        for (File file2 : listFiles) {
            if (file2.getName().endsWith(str)) {
                return file2;
            }
        }
        return null;
    }

    static byte[] minidumpFromDirectory(File file) {
        File filter = filter(file, ".dmp");
        return filter == null ? new byte[0] : minidumpFromFile(filter);
    }

    private static byte[] minidumpFromFile(File file) {
        return readFile(file);
    }

    static byte[] binaryImagesJsonFromDirectory(File file, Context context) {
        File filter = filter(file, ".maps");
        if (filter != null) {
            return binaryImagesJsonFromMapsFile(filter, context);
        }
        File filter2 = filter(file, ".binary_libs");
        if (filter2 != null) {
            return binaryImagesJsonFromBinaryLibsFile(filter2, context);
        }
        return null;
    }

    private static byte[] binaryImagesJsonFromBinaryLibsFile(File file, Context context) {
        byte[] readFile = readFile(file);
        if (readFile == null || readFile.length == 0) {
            return null;
        }
        return processBinaryImages(context, new String(readFile));
    }

    private static byte[] binaryImagesJsonFromMapsFile(File file, Context context) {
        Throwable th;
        BufferedReader bufferedReader;
        if (!file.exists()) {
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            try {
                byte[] convert = new BinaryImagesConverter(context, new Sha1FileIdStrategy()).convert(bufferedReader);
                i.a(bufferedReader);
                return convert;
            } catch (Throwable th2) {
                th = th2;
                i.a(bufferedReader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            i.a(bufferedReader);
            throw th;
        }
    }

    static byte[] metadataJsonFromDirectory(File file) {
        File filter = filter(file, ".device_info");
        if (filter == null) {
            return null;
        }
        return readFile(filter);
    }

    private static byte[] processBinaryImages(Context context, String str) {
        return new BinaryImagesConverter(context, new Sha1FileIdStrategy()).convert(str);
    }
}
