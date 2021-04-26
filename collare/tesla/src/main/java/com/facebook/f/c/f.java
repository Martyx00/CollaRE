package com.facebook.f.c;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ForwardingControllerListener */
public class f<INFO> implements d<INFO> {

    /* renamed from: a  reason: collision with root package name */
    private final List<d<? super INFO>> f1890a = new ArrayList(2);

    public synchronized void a(d<? super INFO> dVar) {
        this.f1890a.add(dVar);
    }

    public synchronized void b(d<? super INFO> dVar) {
        int indexOf = this.f1890a.indexOf(dVar);
        if (indexOf != -1) {
            this.f1890a.set(indexOf, null);
        }
    }

    public synchronized void a() {
        this.f1890a.clear();
    }

    private synchronized void c(String str, Throwable th) {
        Log.e("FdingControllerListener", str, th);
    }

    @Override // com.facebook.f.c.d
    public synchronized void a(String str, Object obj) {
        int size = this.f1890a.size();
        for (int i = 0; i < size; i++) {
            try {
                d<? super INFO> dVar = this.f1890a.get(i);
                if (dVar != null) {
                    dVar.a(str, obj);
                }
            } catch (Exception e) {
                c("InternalListener exception in onSubmit", e);
            }
        }
    }

    @Override // com.facebook.f.c.d
    public synchronized void a(String str, INFO info, Animatable animatable) {
        int size = this.f1890a.size();
        for (int i = 0; i < size; i++) {
            try {
                d<? super INFO> dVar = this.f1890a.get(i);
                if (dVar != null) {
                    dVar.a(str, info, animatable);
                }
            } catch (Exception e) {
                c("InternalListener exception in onFinalImageSet", e);
            }
        }
    }

    @Override // com.facebook.f.c.d
    public void b(String str, INFO info) {
        int size = this.f1890a.size();
        for (int i = 0; i < size; i++) {
            try {
                d<? super INFO> dVar = this.f1890a.get(i);
                if (dVar != null) {
                    dVar.b(str, (Object) info);
                }
            } catch (Exception e) {
                c("InternalListener exception in onIntermediateImageSet", e);
            }
        }
    }

    @Override // com.facebook.f.c.d
    public void b(String str, Throwable th) {
        int size = this.f1890a.size();
        for (int i = 0; i < size; i++) {
            try {
                d<? super INFO> dVar = this.f1890a.get(i);
                if (dVar != null) {
                    dVar.b(str, th);
                }
            } catch (Exception e) {
                c("InternalListener exception in onIntermediateImageFailed", e);
            }
        }
    }

    @Override // com.facebook.f.c.d
    public synchronized void a(String str, Throwable th) {
        int size = this.f1890a.size();
        for (int i = 0; i < size; i++) {
            try {
                d<? super INFO> dVar = this.f1890a.get(i);
                if (dVar != null) {
                    dVar.a(str, th);
                }
            } catch (Exception e) {
                c("InternalListener exception in onFailure", e);
            }
        }
    }

    @Override // com.facebook.f.c.d
    public synchronized void a(String str) {
        int size = this.f1890a.size();
        for (int i = 0; i < size; i++) {
            try {
                d<? super INFO> dVar = this.f1890a.get(i);
                if (dVar != null) {
                    dVar.a(str);
                }
            } catch (Exception e) {
                c("InternalListener exception in onRelease", e);
            }
        }
    }
}
