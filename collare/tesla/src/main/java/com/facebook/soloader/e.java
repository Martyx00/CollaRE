package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.m;
import java.io.File;
import java.io.FileInputStream;

/* compiled from: ExoSoSource */
public final class e extends m {
    public e(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m
    public m.e a() {
        return new a(this);
    }

    /* compiled from: ExoSoSource */
    private final class a extends m.e {

        /* renamed from: b  reason: collision with root package name */
        private final b[] f3620b;

        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fe, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ff, code lost:
            r8.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0108, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0109, code lost:
            r2 = r0;
            r8 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
            r10.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0117, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0118, code lost:
            r8.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x011d, code lost:
            r10.close();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x0108 A[ExcHandler: all (r0v11 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:7:0x0063] */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x0113 A[SYNTHETIC, Splitter:B:55:0x0113] */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x011d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        a(com.facebook.soloader.m r19) {
            /*
            // Method dump skipped, instructions count: 321
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.e.a.<init>(com.facebook.soloader.e, com.facebook.soloader.m):void");
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public m.b a() {
            return new m.b(this.f3620b);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public m.d b() {
            return new C0062a();
        }

        /* renamed from: com.facebook.soloader.e$a$a  reason: collision with other inner class name */
        /* compiled from: ExoSoSource */
        private final class C0062a extends m.d {

            /* renamed from: b  reason: collision with root package name */
            private int f3622b;

            private C0062a() {
            }

            @Override // com.facebook.soloader.m.d
            public boolean a() {
                return this.f3622b < a.this.f3620b.length;
            }

            @Override // com.facebook.soloader.m.d
            public m.c b() {
                b[] bVarArr = a.this.f3620b;
                int i = this.f3622b;
                this.f3622b = i + 1;
                b bVar = bVarArr[i];
                FileInputStream fileInputStream = new FileInputStream(bVar.f3623a);
                try {
                    return new m.c(bVar, fileInputStream);
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ExoSoSource */
    public static final class b extends m.a {

        /* renamed from: a  reason: collision with root package name */
        final File f3623a;

        b(String str, String str2, File file) {
            super(str, str2);
            this.f3623a = file;
        }
    }
}
