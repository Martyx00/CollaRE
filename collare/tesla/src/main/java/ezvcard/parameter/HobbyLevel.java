package ezvcard.parameter;

import com.google.firebase.analytics.FirebaseAnalytics;

public class HobbyLevel extends VCardParameter {

    /* renamed from: a  reason: collision with root package name */
    public static final HobbyLevel f5766a = new HobbyLevel("low");

    /* renamed from: b  reason: collision with root package name */
    public static final HobbyLevel f5767b = new HobbyLevel(FirebaseAnalytics.b.MEDIUM);

    /* renamed from: c  reason: collision with root package name */
    public static final HobbyLevel f5768c = new HobbyLevel("high");

    /* renamed from: d  reason: collision with root package name */
    private static final d<HobbyLevel> f5769d = new d<>(HobbyLevel.class);

    private HobbyLevel(String str) {
        super(str);
    }

    public static HobbyLevel a(String str) {
        return (HobbyLevel) f5769d.c(str);
    }
}
