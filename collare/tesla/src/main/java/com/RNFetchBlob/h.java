package com.RNFetchBlob;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.tagmanager.DataLayer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

/* compiled from: RNFetchBlobUtils */
public class h {
    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", Integer.valueOf(digest[i] & 255)));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable unused) {
        }
        return null;
    }

    public static void b(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(DataLayer.EVENT_KEY, "warn");
        createMap.putString(ProductAction.ACTION_DETAIL, str);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) RNFetchBlob.RCTContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("RNFetchBlobMessage", createMap);
    }

    public static OkHttpClient.Builder a(OkHttpClient okHttpClient) {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() {
                /* class com.RNFetchBlob.h.AnonymousClass1 */

                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            newBuilder.sslSocketFactory(socketFactory);
            newBuilder.hostnameVerifier(new HostnameVerifier() {
                /* class com.RNFetchBlob.h.AnonymousClass2 */

                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
            return newBuilder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
