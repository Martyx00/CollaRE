package io.realm.internal.android;

import android.os.Looper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/* compiled from: AndroidCapabilities */
public class a implements io.realm.internal.a {
    @SuppressFBWarnings({"MS_SHOULD_BE_FINAL"})

    /* renamed from: a  reason: collision with root package name */
    public static boolean f6249a = false;

    /* renamed from: b  reason: collision with root package name */
    private final Looper f6250b = Looper.myLooper();

    /* renamed from: c  reason: collision with root package name */
    private final boolean f6251c = c();

    @Override // io.realm.internal.a
    public boolean a() {
        return b() && !this.f6251c;
    }

    @Override // io.realm.internal.a
    public void a(String str) {
        String str2;
        String str3;
        if (!b()) {
            if (str == null) {
                str3 = "";
            } else {
                str3 = str + " " + "Realm cannot be automatically updated on a thread without a looper.";
            }
            throw new IllegalStateException(str3);
        } else if (this.f6251c) {
            if (str == null) {
                str2 = "";
            } else {
                str2 = str + " " + "Realm cannot be automatically updated on an IntentService thread.";
            }
            throw new IllegalStateException(str2);
        }
    }

    private boolean b() {
        return this.f6250b != null;
    }

    private static boolean c() {
        String name = Thread.currentThread().getName();
        return name != null && name.startsWith("IntentService[");
    }
}
