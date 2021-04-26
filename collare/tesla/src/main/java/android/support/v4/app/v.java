package android.support.v4.app;

import android.arch.lifecycle.q;
import android.support.v4.content.e;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: LoaderManager */
public abstract class v {

    /* compiled from: LoaderManager */
    public interface a<D> {
        void a(e<D> eVar);

        void a(e<D> eVar, D d2);
    }

    public abstract void a();

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public static <T extends android.arch.lifecycle.e & q> v a(T t) {
        return new LoaderManagerImpl(t, t.getViewModelStore());
    }
}
