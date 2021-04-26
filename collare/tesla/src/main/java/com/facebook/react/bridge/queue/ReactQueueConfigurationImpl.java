package com.facebook.react.bridge.queue;

import android.os.Looper;
import com.facebook.react.common.e;
import java.util.HashMap;

public class ReactQueueConfigurationImpl implements ReactQueueConfiguration {
    private final MessageQueueThreadImpl mJSQueueThread;
    private final MessageQueueThreadImpl mNativeModulesQueueThread;
    private final MessageQueueThreadImpl mUIQueueThread;

    private ReactQueueConfigurationImpl(MessageQueueThreadImpl messageQueueThreadImpl, MessageQueueThreadImpl messageQueueThreadImpl2, MessageQueueThreadImpl messageQueueThreadImpl3) {
        this.mUIQueueThread = messageQueueThreadImpl;
        this.mNativeModulesQueueThread = messageQueueThreadImpl2;
        this.mJSQueueThread = messageQueueThreadImpl3;
    }

    @Override // com.facebook.react.bridge.queue.ReactQueueConfiguration
    public MessageQueueThread getUIQueueThread() {
        return this.mUIQueueThread;
    }

    @Override // com.facebook.react.bridge.queue.ReactQueueConfiguration
    public MessageQueueThread getNativeModulesQueueThread() {
        return this.mNativeModulesQueueThread;
    }

    @Override // com.facebook.react.bridge.queue.ReactQueueConfiguration
    public MessageQueueThread getJSQueueThread() {
        return this.mJSQueueThread;
    }

    @Override // com.facebook.react.bridge.queue.ReactQueueConfiguration
    public void destroy() {
        if (this.mNativeModulesQueueThread.getLooper() != Looper.getMainLooper()) {
            this.mNativeModulesQueueThread.quitSynchronous();
        }
        if (this.mJSQueueThread.getLooper() != Looper.getMainLooper()) {
            this.mJSQueueThread.quitSynchronous();
        }
    }

    public static ReactQueueConfigurationImpl create(ReactQueueConfigurationSpec reactQueueConfigurationSpec, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        HashMap a2 = e.a();
        MessageQueueThreadSpec mainThreadSpec = MessageQueueThreadSpec.mainThreadSpec();
        MessageQueueThreadImpl create = MessageQueueThreadImpl.create(mainThreadSpec, queueThreadExceptionHandler);
        a2.put(mainThreadSpec, create);
        MessageQueueThreadImpl messageQueueThreadImpl = (MessageQueueThreadImpl) a2.get(reactQueueConfigurationSpec.getJSQueueThreadSpec());
        if (messageQueueThreadImpl == null) {
            messageQueueThreadImpl = MessageQueueThreadImpl.create(reactQueueConfigurationSpec.getJSQueueThreadSpec(), queueThreadExceptionHandler);
        }
        MessageQueueThreadImpl messageQueueThreadImpl2 = (MessageQueueThreadImpl) a2.get(reactQueueConfigurationSpec.getNativeModulesQueueThreadSpec());
        if (messageQueueThreadImpl2 == null) {
            messageQueueThreadImpl2 = MessageQueueThreadImpl.create(reactQueueConfigurationSpec.getNativeModulesQueueThreadSpec(), queueThreadExceptionHandler);
        }
        return new ReactQueueConfigurationImpl(create, messageQueueThreadImpl2, messageQueueThreadImpl);
    }
}
