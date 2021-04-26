package android.support.v4.d;

/* compiled from: OperationCanceledException */
public class d extends RuntimeException {
    public d() {
        this(null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(String str) {
        super(str == null ? "The operation has been canceled." : str);
    }
}
