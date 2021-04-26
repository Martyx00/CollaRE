package com.facebook.react.bridge.queue;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MessageQueueThreadHandler extends Handler {
    private final QueueThreadExceptionHandler mExceptionHandler;

    public MessageQueueThreadHandler(Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        super(looper);
        this.mExceptionHandler = queueThreadExceptionHandler;
    }

    public void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Exception e) {
            this.mExceptionHandler.handleException(e);
        }
    }
}
