package io.realm;

import java.util.Locale;

/* compiled from: OrderedCollectionChangeSet */
public interface k {

    /* compiled from: OrderedCollectionChangeSet */
    public enum b {
        INITIAL,
        UPDATE,
        ERROR
    }

    /* compiled from: OrderedCollectionChangeSet */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final int f6325a;

        /* renamed from: b  reason: collision with root package name */
        public final int f6326b;

        public a(int i, int i2) {
            this.f6325a = i;
            this.f6326b = i2;
        }

        public String toString() {
            return String.format(Locale.ENGLISH, "startIndex: %d, length: %d", Integer.valueOf(this.f6325a), Integer.valueOf(this.f6326b));
        }
    }
}
