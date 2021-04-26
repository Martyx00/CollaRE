package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.c;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: TaskStackBuilder */
public final class ah implements Iterable<Intent> {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<Intent> f171a = new ArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    private final Context f172b;

    /* compiled from: TaskStackBuilder */
    public interface a {
        Intent a();
    }

    private ah(Context context) {
        this.f172b = context;
    }

    public static ah a(Context context) {
        return new ah(context);
    }

    public ah a(Intent intent) {
        this.f171a.add(intent);
        return this;
    }

    public ah a(Activity activity) {
        Intent a2 = activity instanceof a ? ((a) activity).a() : null;
        if (a2 == null) {
            a2 = w.a(activity);
        }
        if (a2 != null) {
            ComponentName component = a2.getComponent();
            if (component == null) {
                component = a2.resolveActivity(this.f172b.getPackageManager());
            }
            a(component);
            a(a2);
        }
        return this;
    }

    public ah a(ComponentName componentName) {
        int size = this.f171a.size();
        try {
            Intent a2 = w.a(this.f172b, componentName);
            while (a2 != null) {
                this.f171a.add(size, a2);
                a2 = w.a(this.f172b, a2.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f171a.iterator();
    }

    public void a() {
        a((Bundle) null);
    }

    public void a(Bundle bundle) {
        if (!this.f171a.isEmpty()) {
            ArrayList<Intent> arrayList = this.f171a;
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            if (!c.a(this.f172b, intentArr, bundle)) {
                Intent intent = new Intent(intentArr[intentArr.length - 1]);
                intent.addFlags(268435456);
                this.f172b.startActivity(intent);
                return;
            }
            return;
        }
        throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
}
