package okhttp3.internal.cache;

import c.c;
import c.g;
import c.s;
import java.io.IOException;

/* access modifiers changed from: package-private */
public class FaultHidingSink extends g {
    private boolean hasErrors;

    /* access modifiers changed from: protected */
    public void onException(IOException iOException) {
    }

    FaultHidingSink(s sVar) {
        super(sVar);
    }

    @Override // c.s, c.g
    public void write(c cVar, long j) {
        if (this.hasErrors) {
            cVar.i(j);
            return;
        }
        try {
            super.write(cVar, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    @Override // c.s, c.g, java.io.Flushable
    public void flush() {
        if (!this.hasErrors) {
            try {
                super.flush();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    @Override // java.io.Closeable, c.s, c.g, java.lang.AutoCloseable
    public void close() {
        if (!this.hasErrors) {
            try {
                super.close();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }
}
