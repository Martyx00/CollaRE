package okhttp3.internal.http2;

import c.e;
import java.util.List;

public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() {
        /* class okhttp3.internal.http2.PushObserver.AnonymousClass1 */

        @Override // okhttp3.internal.http2.PushObserver
        public boolean onHeaders(int i, List<Header> list, boolean z) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean onRequest(int i, List<Header> list) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public void onReset(int i, ErrorCode errorCode) {
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean onData(int i, e eVar, int i2, boolean z) {
            eVar.i((long) i2);
            return true;
        }
    };

    boolean onData(int i, e eVar, int i2, boolean z);

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
