package okhttp3.internal;

public abstract class NamedRunnable implements Runnable {
    protected final String name;

    /* access modifiers changed from: protected */
    public abstract void execute();

    public NamedRunnable(String str, Object... objArr) {
        this.name = Util.format(str, objArr);
    }

    public final void run() {
        String name2 = Thread.currentThread().getName();
        Thread.currentThread().setName(this.name);
        try {
            execute();
        } finally {
            Thread.currentThread().setName(name2);
        }
    }
}
