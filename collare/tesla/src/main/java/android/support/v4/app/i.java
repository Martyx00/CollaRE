package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* compiled from: FragmentContainer */
public abstract class i {
    public abstract View a(int i);

    public abstract boolean a();

    public g a(Context context, String str, Bundle bundle) {
        return g.instantiate(context, str, bundle);
    }
}
