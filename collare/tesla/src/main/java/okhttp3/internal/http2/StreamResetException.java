package okhttp3.internal.http2;

import java.io.IOException;

public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;

    public StreamResetException(ErrorCode errorCode2) {
        super("stream was reset: " + errorCode2);
        this.errorCode = errorCode2;
    }
}
