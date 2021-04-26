package okhttp3;

public interface Authenticator {
    public static final Authenticator NONE = new Authenticator() {
        /* class okhttp3.Authenticator.AnonymousClass1 */

        @Override // okhttp3.Authenticator
        public Request authenticate(Route route, Response response) {
            return null;
        }
    };

    Request authenticate(Route route, Response response);
}
