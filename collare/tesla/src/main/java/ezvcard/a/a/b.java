package ezvcard.a.a;

import ezvcard.VCard;
import ezvcard.a.a.b;
import ezvcard.a.c.a;
import ezvcard.a.f;
import java.io.InputStream;

/* compiled from: ChainingTextParser */
public class b<T extends b<?>> extends a<T> {
    private boolean h = true;

    @Override // ezvcard.a.a.a
    public /* bridge */ /* synthetic */ VCard a() {
        return super.a();
    }

    public b(InputStream inputStream) {
        super(inputStream);
    }

    /* access modifiers changed from: package-private */
    @Override // ezvcard.a.a.a
    public f b() {
        a c2 = c();
        c2.a(this.h);
        return c2;
    }

    private a c() {
        if (this.f5699a != null) {
            return new a(this.f5699a);
        }
        if (this.f5700b != null) {
            return new a(this.f5700b);
        }
        if (this.f5701c != null) {
            return new a(this.f5701c);
        }
        return new a(this.f5702d);
    }
}
