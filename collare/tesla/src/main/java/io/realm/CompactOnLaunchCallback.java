package io.realm;

import io.realm.internal.Keep;

@Keep
public interface CompactOnLaunchCallback {
    boolean shouldCompact(long j, long j2);
}
