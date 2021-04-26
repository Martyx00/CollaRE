package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaJSExecutor;

@a
public class ProxyJavaScriptExecutor extends JavaScriptExecutor {
    private JavaJSExecutor mJavaJSExecutor;

    private static native HybridData initHybrid(JavaJSExecutor javaJSExecutor);

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        return "ProxyJavaScriptExecutor";
    }

    public static class Factory implements JavaScriptExecutorFactory {
        private final JavaJSExecutor.Factory mJavaJSExecutorFactory;

        public Factory(JavaJSExecutor.Factory factory) {
            this.mJavaJSExecutorFactory = factory;
        }

        @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
        public JavaScriptExecutor create() {
            return new ProxyJavaScriptExecutor(this.mJavaJSExecutorFactory.create());
        }
    }

    static {
        ReactBridge.staticInit();
    }

    public ProxyJavaScriptExecutor(JavaJSExecutor javaJSExecutor) {
        super(initHybrid(javaJSExecutor));
        this.mJavaJSExecutor = javaJSExecutor;
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public void close() {
        JavaJSExecutor javaJSExecutor = this.mJavaJSExecutor;
        if (javaJSExecutor != null) {
            javaJSExecutor.close();
            this.mJavaJSExecutor = null;
        }
    }
}
