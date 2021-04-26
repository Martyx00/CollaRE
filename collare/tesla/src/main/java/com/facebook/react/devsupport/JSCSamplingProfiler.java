package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@com.facebook.react.module.a.a(a = "JSCSamplingProfiler", c = true)
public class JSCSamplingProfiler extends ReactContextBaseJavaModule {
    private static final HashSet<JSCSamplingProfiler> sRegisteredDumpers = new HashSet<>();
    private String mOperationError = null;
    private boolean mOperationInProgress = false;
    private int mOperationToken = 0;
    private SamplingProfiler mSamplingProfiler = null;
    private String mSamplingProfilerResult = null;

    public interface SamplingProfiler extends JavaScriptModule {
        void poke(int i);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JSCSamplingProfiler";
    }

    public static class a extends Exception {
        a(String str) {
            super(str);
        }
    }

    private static synchronized void registerSamplingProfiler(JSCSamplingProfiler jSCSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            if (!sRegisteredDumpers.contains(jSCSamplingProfiler)) {
                sRegisteredDumpers.add(jSCSamplingProfiler);
            } else {
                throw new RuntimeException("a JSCSamplingProfiler registered more than once");
            }
        }
    }

    private static synchronized void unregisterSamplingProfiler(JSCSamplingProfiler jSCSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            sRegisteredDumpers.remove(jSCSamplingProfiler);
        }
    }

    public static synchronized List<String> poke(long j) {
        LinkedList linkedList;
        synchronized (JSCSamplingProfiler.class) {
            linkedList = new LinkedList();
            if (!sRegisteredDumpers.isEmpty()) {
                Iterator<JSCSamplingProfiler> it = sRegisteredDumpers.iterator();
                while (it.hasNext()) {
                    JSCSamplingProfiler next = it.next();
                    next.pokeHelper(j);
                    linkedList.add(next.mSamplingProfilerResult);
                }
            } else {
                throw new a("No JSC registered");
            }
        }
        return linkedList;
    }

    public JSCSamplingProfiler(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private synchronized void pokeHelper(long j) {
        if (this.mSamplingProfiler != null) {
            this.mSamplingProfiler.poke(getOperationToken());
            waitForOperation(j);
        } else {
            throw new a("SamplingProfiler.js module not connected");
        }
    }

    private int getOperationToken() {
        if (!this.mOperationInProgress) {
            this.mOperationInProgress = true;
            int i = this.mOperationToken + 1;
            this.mOperationToken = i;
            return i;
        }
        throw new a("Another operation already in progress.");
    }

    private void waitForOperation(long j) {
        try {
            wait(j);
            if (!this.mOperationInProgress) {
                String str = this.mOperationError;
                if (str != null) {
                    throw new a(str);
                }
                return;
            }
            this.mOperationInProgress = false;
            throw new a("heap capture timed out.");
        } catch (InterruptedException e) {
            throw new a("Waiting for heap capture failed: " + e.getMessage());
        }
    }

    @ReactMethod
    public synchronized void operationComplete(int i, String str, String str2) {
        if (i == this.mOperationToken) {
            this.mOperationInProgress = false;
            this.mSamplingProfilerResult = str;
            this.mOperationError = str2;
            notify();
        } else {
            throw new RuntimeException("Completed operation is not in progress.");
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        this.mSamplingProfiler = (SamplingProfiler) getReactApplicationContext().getJSModule(SamplingProfiler.class);
        registerSamplingProfiler(this);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        unregisterSamplingProfiler(this);
        this.mSamplingProfiler = null;
    }
}
