package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

@KeepForSdk
public class ListenerHolders {
    private final Set<ListenerHolder<?>> zajo = Collections.newSetFromMap(new WeakHashMap());

    public final <L> ListenerHolder<L> zaa(L l, Looper looper, String str) {
        ListenerHolder<L> createListenerHolder = createListenerHolder(l, looper, str);
        this.zajo.add(createListenerHolder);
        return createListenerHolder;
    }

    public final void release() {
        for (ListenerHolder<?> listenerHolder : this.zajo) {
            listenerHolder.clear();
        }
        this.zajo.clear();
    }

    @KeepForSdk
    public static <L> ListenerHolder<L> createListenerHolder(L l, Looper looper, String str) {
        Preconditions.checkNotNull(l, "Listener must not be null");
        Preconditions.checkNotNull(looper, "Looper must not be null");
        Preconditions.checkNotNull(str, "Listener type must not be null");
        return new ListenerHolder<>(looper, l, str);
    }

    @KeepForSdk
    public static <L> ListenerHolder.ListenerKey<L> createListenerKey(L l, String str) {
        Preconditions.checkNotNull(l, "Listener must not be null");
        Preconditions.checkNotNull(str, "Listener type must not be null");
        Preconditions.checkNotEmpty(str, "Listener type must not be empty");
        return new ListenerHolder.ListenerKey<>(l, str);
    }
}
