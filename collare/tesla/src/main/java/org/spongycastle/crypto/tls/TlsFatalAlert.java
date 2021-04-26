package org.spongycastle.crypto.tls;

import java.io.IOException;

public class TlsFatalAlert extends IOException {
    private static final long serialVersionUID = 3584313123679111168L;
    protected Throwable alertCause;
    protected short alertDescription;

    public TlsFatalAlert(short s) {
        this(s, null);
    }

    public TlsFatalAlert(short s, Throwable th) {
        super(AlertDescription.getText(s));
        this.alertDescription = s;
        this.alertCause = th;
    }

    public short getAlertDescription() {
        return this.alertDescription;
    }

    public Throwable getCause() {
        return this.alertCause;
    }
}
