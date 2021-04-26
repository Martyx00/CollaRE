package a;

import java.util.Locale;

/* compiled from: CancellationToken */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final e f10a;

    public boolean a() {
        return this.f10a.a();
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(this.f10a.a()));
    }
}
