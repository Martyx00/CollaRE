package android.support.v4.app;

import android.app.RemoteInput;
import android.os.Bundle;
import java.util.Set;

/* compiled from: RemoteInput */
public final class ad {

    /* renamed from: a  reason: collision with root package name */
    private final String f165a;

    /* renamed from: b  reason: collision with root package name */
    private final CharSequence f166b;

    /* renamed from: c  reason: collision with root package name */
    private final CharSequence[] f167c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f168d;
    private final Bundle e;
    private final Set<String> f;

    public String a() {
        return this.f165a;
    }

    public CharSequence b() {
        return this.f166b;
    }

    public CharSequence[] c() {
        return this.f167c;
    }

    public Set<String> d() {
        return this.f;
    }

    public boolean e() {
        return this.f168d;
    }

    public Bundle f() {
        return this.e;
    }

    static RemoteInput[] a(ad[] adVarArr) {
        if (adVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[adVarArr.length];
        for (int i = 0; i < adVarArr.length; i++) {
            remoteInputArr[i] = a(adVarArr[i]);
        }
        return remoteInputArr;
    }

    static RemoteInput a(ad adVar) {
        return new RemoteInput.Builder(adVar.a()).setLabel(adVar.b()).setChoices(adVar.c()).setAllowFreeFormInput(adVar.e()).addExtras(adVar.f()).build();
    }
}
