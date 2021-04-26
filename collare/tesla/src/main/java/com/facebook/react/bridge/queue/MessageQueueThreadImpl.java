package com.facebook.react.bridge.queue;

import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Pair;
import com.facebook.j.a.a;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@a
public class MessageQueueThreadImpl implements MessageQueueThread {
    private final String mAssertionErrorMessage;
    private final MessageQueueThreadHandler mHandler;
    private volatile boolean mIsFinished;
    private final Looper mLooper;
    private final String mName;
    private MessageQueueThreadPerfStats mPerfStats;

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        this(str, looper, queueThreadExceptionHandler, null);
    }

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler, MessageQueueThreadPerfStats messageQueueThreadPerfStats) {
        this.mIsFinished = false;
        this.mName = str;
        this.mLooper = looper;
        this.mHandler = new MessageQueueThreadHandler(looper, queueThreadExceptionHandler);
        this.mPerfStats = messageQueueThreadPerfStats;
        this.mAssertionErrorMessage = "Expected to be called from the '" + getName() + "' thread!";
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public void runOnQueue(Runnable runnable) {
        if (this.mIsFinished) {
            com.facebook.common.e.a.c("ReactNative", "Tried to enqueue runnable on already finished thread: '" + getName() + "... dropping Runnable.");
        }
        this.mHandler.post(runnable);
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public <T> Future<T> callOnQueue(final Callable<T> callable) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        runOnQueue(new Runnable() {
            /* class com.facebook.react.bridge.queue.MessageQueueThreadImpl.AnonymousClass1 */

            public void run() {
                try {
                    simpleSettableFuture.a(callable.call());
                } catch (Exception e) {
                    simpleSettableFuture.a(e);
                }
            }
        });
        return simpleSettableFuture;
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public void assertIsOnThread() {
        SoftAssertions.assertCondition(isOnThread(), this.mAssertionErrorMessage);
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public void assertIsOnThread(String str) {
        boolean isOnThread = isOnThread();
        SoftAssertions.assertCondition(isOnThread, this.mAssertionErrorMessage + " " + str);
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() != Thread.currentThread()) {
            try {
                this.mLooper.getThread().join();
            } catch (InterruptedException unused) {
                throw new RuntimeException("Got interrupted waiting to join thread " + this.mName);
            }
        }
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public MessageQueueThreadPerfStats getPerfStats() {
        return this.mPerfStats;
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    @a
    public void resetPerfStats() {
        assignToPerfStats(this.mPerfStats, -1, -1);
        runOnQueue(new Runnable() {
            /* class com.facebook.react.bridge.queue.MessageQueueThreadImpl.AnonymousClass2 */

            public void run() {
                MessageQueueThreadImpl.assignToPerfStats(MessageQueueThreadImpl.this.mPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
            }
        });
    }

    /* access modifiers changed from: private */
    public static void assignToPerfStats(MessageQueueThreadPerfStats messageQueueThreadPerfStats, long j, long j2) {
        messageQueueThreadPerfStats.wallTime = j;
        messageQueueThreadPerfStats.cpuTime = j2;
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public String getName() {
        return this.mName;
    }

    public static MessageQueueThreadImpl create(MessageQueueThreadSpec messageQueueThreadSpec, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        switch (messageQueueThreadSpec.getThreadType()) {
            case MAIN_UI:
                return createForMainThread(messageQueueThreadSpec.getName(), queueThreadExceptionHandler);
            case NEW_BACKGROUND:
                return startNewBackgroundThread(messageQueueThreadSpec.getName(), messageQueueThreadSpec.getStackSize(), queueThreadExceptionHandler);
            default:
                throw new RuntimeException("Unknown thread type: " + messageQueueThreadSpec.getThreadType());
        }
    }

    private static MessageQueueThreadImpl createForMainThread(String str, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        MessageQueueThreadImpl messageQueueThreadImpl = new MessageQueueThreadImpl(str, Looper.getMainLooper(), queueThreadExceptionHandler);
        if (UiThreadUtil.isOnUiThread()) {
            Process.setThreadPriority(-4);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() {
                /* class com.facebook.react.bridge.queue.MessageQueueThreadImpl.AnonymousClass3 */

                public void run() {
                    Process.setThreadPriority(-4);
                }
            });
        }
        return messageQueueThreadImpl;
    }

    private static MessageQueueThreadImpl startNewBackgroundThread(String str, long j, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        AnonymousClass4 r3 = new Runnable() {
            /* class com.facebook.react.bridge.queue.MessageQueueThreadImpl.AnonymousClass4 */

            public void run() {
                Process.setThreadPriority(-4);
                Looper.prepare();
                MessageQueueThreadPerfStats messageQueueThreadPerfStats = new MessageQueueThreadPerfStats();
                MessageQueueThreadImpl.assignToPerfStats(messageQueueThreadPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
                simpleSettableFuture.a(new Pair(Looper.myLooper(), messageQueueThreadPerfStats));
                Looper.loop();
            }
        };
        new Thread(null, r3, "mqt_" + str, j).start();
        Pair pair = (Pair) simpleSettableFuture.a();
        return new MessageQueueThreadImpl(str, (Looper) pair.first, queueThreadExceptionHandler, (MessageQueueThreadPerfStats) pair.second);
    }
}
