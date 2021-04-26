package ezvcard.parameter;

import com.google.firebase.analytics.FirebaseAnalytics;

public class InterestLevel extends VCardParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final InterestLevel f5777a = new InterestLevel("low");

    /* renamed from: b  reason: collision with root package name */
    public static final InterestLevel f5778b = new InterestLevel(FirebaseAnalytics.b.MEDIUM);

    /* renamed from: c  reason: collision with root package name */
    public static final InterestLevel f5779c = new InterestLevel("high");

    /* renamed from: d  reason: collision with root package name */
    private static final d<InterestLevel> f5780d = new d<>(InterestLevel.class);

    private InterestLevel(String str) {
        super(str);
    }

    public static InterestLevel a(String str) {
        return (InterestLevel) f5780d.c(str);
    }
}
