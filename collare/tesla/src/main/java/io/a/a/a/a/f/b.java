package io.a.a.a.a.f;

import android.content.Context;
import io.a.a.a.c;
import io.a.a.a.i;
import java.io.File;

/* compiled from: FileStoreImpl */
public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f6016a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6017b;

    /* renamed from: c  reason: collision with root package name */
    private final String f6018c;

    public b(i iVar) {
        if (iVar.getContext() != null) {
            this.f6016a = iVar.getContext();
            this.f6017b = iVar.getPath();
            this.f6018c = "Android/" + this.f6016a.getPackageName();
            return;
        }
        throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }

    @Override // io.a.a.a.a.f.a
    public File a() {
        return a(this.f6016a.getFilesDir());
    }

    /* access modifiers changed from: package-private */
    public File a(File file) {
        if (file == null) {
            c.g().a("Fabric", "Null File");
            return null;
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            c.g().d("Fabric", "Couldn't create file");
            return null;
        }
    }
}
