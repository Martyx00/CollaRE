package com.crashlytics.android.answers;

import io.a.a.a.a.c.a.a;
import java.util.Random;

/* access modifiers changed from: package-private */
public class RandomBackoff implements a {
    final a backoff;
    final double jitterPercent;
    final Random random;

    public RandomBackoff(a aVar, double d2) {
        this(aVar, d2, new Random());
    }

    public RandomBackoff(a aVar, double d2, Random random2) {
        if (d2 < 0.0d || d2 > 1.0d) {
            throw new IllegalArgumentException("jitterPercent must be between 0.0 and 1.0");
        } else if (aVar == null) {
            throw new NullPointerException("backoff must not be null");
        } else if (random2 != null) {
            this.backoff = aVar;
            this.jitterPercent = d2;
            this.random = random2;
        } else {
            throw new NullPointerException("random must not be null");
        }
    }

    @Override // io.a.a.a.a.c.a.a
    public long getDelayMillis(int i) {
        return (long) (randomJitter() * ((double) this.backoff.getDelayMillis(i)));
    }

    /* access modifiers changed from: package-private */
    public double randomJitter() {
        double d2 = this.jitterPercent;
        double d3 = 1.0d - d2;
        return d3 + (((d2 + 1.0d) - d3) * this.random.nextDouble());
    }
}
