package com.facebook.react.modules.debug;

import android.widget.Toast;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.a.a;
import com.facebook.react.modules.debug.b;
import java.util.Locale;

@a(a = AnimationsDebugModule.NAME)
public class AnimationsDebugModule extends ReactContextBaseJavaModule {
    protected static final String NAME = "AnimationsDebugModule";
    private final com.facebook.react.modules.debug.a.a mCatalystSettings;
    private b mFrameCallback;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public AnimationsDebugModule(ReactApplicationContext reactApplicationContext, com.facebook.react.modules.debug.a.a aVar) {
        super(reactApplicationContext);
        this.mCatalystSettings = aVar;
    }

    @ReactMethod
    public void startRecordingFps() {
        com.facebook.react.modules.debug.a.a aVar = this.mCatalystSettings;
        if (aVar != null && aVar.a()) {
            if (this.mFrameCallback == null) {
                this.mFrameCallback = new b(getReactApplicationContext());
                this.mFrameCallback.c();
                return;
            }
            throw new JSApplicationCausedNativeException("Already recording FPS!");
        }
    }

    @ReactMethod
    public void stopRecordingFps(double d2) {
        b bVar = this.mFrameCallback;
        if (bVar != null) {
            bVar.d();
            b.a a2 = this.mFrameCallback.a((long) d2);
            if (a2 == null) {
                Toast.makeText(getReactApplicationContext(), "Unable to get FPS info", 1);
            } else {
                String str = String.format(Locale.US, "FPS: %.2f, %d frames (%d expected)", Double.valueOf(a2.e), Integer.valueOf(a2.f2862a), Integer.valueOf(a2.f2864c)) + "\n" + String.format(Locale.US, "JS FPS: %.2f, %d frames (%d expected)", Double.valueOf(a2.f), Integer.valueOf(a2.f2863b), Integer.valueOf(a2.f2864c)) + "\nTotal Time MS: " + String.format(Locale.US, "%d", Integer.valueOf(a2.g));
                com.facebook.common.e.a.a("ReactNative", str);
                Toast.makeText(getReactApplicationContext(), str, 1).show();
            }
            this.mFrameCallback = null;
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        b bVar = this.mFrameCallback;
        if (bVar != null) {
            bVar.d();
            this.mFrameCallback = null;
        }
    }
}
