package a;

/* compiled from: ExecutorException */
public class g extends RuntimeException {
    public g(Exception exc) {
        super("An exception was thrown by an Executor", exc);
    }
}
