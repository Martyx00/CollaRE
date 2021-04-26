package ezvcard.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;

/* compiled from: Gobble */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final File f5847a;

    /* renamed from: b  reason: collision with root package name */
    private final InputStream f5848b;

    /* renamed from: c  reason: collision with root package name */
    private final Reader f5849c;

    public e(InputStream inputStream) {
        this(null, inputStream, null);
    }

    private e(File file, InputStream inputStream, Reader reader) {
        this.f5847a = file;
        this.f5848b = inputStream;
        this.f5849c = reader;
    }

    public byte[] a() {
        if (this.f5849c == null) {
            return a(b());
        }
        throw new IllegalStateException("Cannot get raw bytes from a Reader object.");
    }

    private InputStream b() {
        InputStream inputStream = this.f5848b;
        return inputStream == null ? new BufferedInputStream(new FileInputStream(this.f5847a)) : inputStream;
    }

    /* JADX INFO: finally extract failed */
    private byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            } catch (Throwable th) {
                inputStream.close();
                throw th;
            }
        }
    }
}
