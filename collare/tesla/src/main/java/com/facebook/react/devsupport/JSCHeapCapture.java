package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.File;

@com.facebook.react.module.a.a(a = "JSCHeapCapture", c = true)
public class JSCHeapCapture extends ReactContextBaseJavaModule {
    private a mCaptureInProgress = null;

    public interface HeapCapture extends JavaScriptModule {
        void captureHeap(String str);
    }

    public interface a {
        void a(b bVar);

        void a(File file);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JSCHeapCapture";
    }

    public static class b extends Exception {
        b(String str) {
            super(str);
        }
    }

    public JSCHeapCapture(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public synchronized void captureHeap(String str, a aVar) {
        if (this.mCaptureInProgress != null) {
            aVar.a(new b("Heap capture already in progress."));
            return;
        }
        File file = new File(str + "/capture.json");
        file.delete();
        HeapCapture heapCapture = (HeapCapture) getReactApplicationContext().getJSModule(HeapCapture.class);
        if (heapCapture == null) {
            aVar.a(new b("Heap capture js module not registered."));
            return;
        }
        this.mCaptureInProgress = aVar;
        heapCapture.captureHeap(file.getPath());
    }

    @ReactMethod
    public synchronized void captureComplete(String str, String str2) {
        if (this.mCaptureInProgress != null) {
            if (str2 == null) {
                this.mCaptureInProgress.a(new File(str));
            } else {
                this.mCaptureInProgress.a(new b(str2));
            }
            this.mCaptureInProgress = null;
        }
    }
}
