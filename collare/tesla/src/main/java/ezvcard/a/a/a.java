package ezvcard.a.a;

import ezvcard.VCard;
import ezvcard.a.a.a;
import ezvcard.a.b.as;
import ezvcard.a.d;
import ezvcard.a.f;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/* access modifiers changed from: package-private */
/* compiled from: ChainingParser */
public abstract class a<T extends a<?>> {

    /* renamed from: a  reason: collision with root package name */
    final String f5699a;

    /* renamed from: b  reason: collision with root package name */
    final InputStream f5700b;

    /* renamed from: c  reason: collision with root package name */
    final Reader f5701c;

    /* renamed from: d  reason: collision with root package name */
    final File f5702d;
    as e;
    List<List<d>> f;
    final T g;

    /* access modifiers changed from: package-private */
    public abstract f b();

    a(InputStream inputStream) {
        this(null, inputStream, null, null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: ezvcard.a.a.a<T extends ezvcard.a.a.a<?>> */
    /* JADX WARN: Multi-variable type inference failed */
    private a(String str, InputStream inputStream, Reader reader, File file) {
        this.g = this;
        this.f5699a = str;
        this.f5700b = inputStream;
        this.f5701c = reader;
        this.f5702d = file;
    }

    public VCard a() {
        f b2 = b();
        as asVar = this.e;
        if (asVar != null) {
            b2.a(asVar);
        }
        try {
            VCard a2 = b2.a();
            if (this.f != null) {
                this.f.add(b2.c());
            }
            return a2;
        } finally {
            if (c()) {
                b2.close();
            }
        }
    }

    private boolean c() {
        return this.f5700b == null && this.f5701c == null;
    }
}
