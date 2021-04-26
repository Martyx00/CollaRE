package com.sha256lib;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public class Sha256Module extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "sha256Lib";
    }

    public Sha256Module(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    /* access modifiers changed from: package-private */
    public String buildHash(String str, String str2, Integer num) {
        MessageDigest instance = MessageDigest.getInstance(str2);
        instance.update(str.getBytes("UTF-8"));
        byte[] digest = instance.digest();
        return String.format("%0" + num.toString() + "x", new BigInteger(1, digest));
    }

    @ReactMethod
    public void sha256(String str, Promise promise) {
        try {
            promise.resolve(buildHash(str, McElieceCCA2KeyGenParameterSpec.SHA256, 64));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            promise.reject("sha256", e.getMessage());
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            promise.reject("sha256", e2.getMessage());
        }
    }

    @ReactMethod
    public void sha1(String str, Promise promise) {
        try {
            promise.resolve(buildHash(str, McElieceCCA2KeyGenParameterSpec.SHA1, 40));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            promise.reject("sha1", e.getMessage());
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            promise.reject("sha1", e2.getMessage());
        }
    }
}
