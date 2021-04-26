package com.idehub.GoogleAnalyticsBridge;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GoogleAnalyticsBridge extends ReactContextBaseJavaModule {
    private final boolean _shouldTrack;
    private final String _trackingId;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "GoogleAnalyticsBridge";
    }

    public GoogleAnalyticsBridge(ReactApplicationContext reactApplicationContext, String str) {
        super(reactApplicationContext);
        this._trackingId = str;
        this._shouldTrack = new Random().nextInt(10) >= 1 ? false : true;
    }

    /* access modifiers changed from: package-private */
    public synchronized Tracker getTracker(String str) {
        return a.a(getReactApplicationContext()).a(str);
    }

    /* access modifiers changed from: package-private */
    public synchronized GoogleAnalytics getAnalyticsInstance() {
        return GoogleAnalytics.getInstance(getReactApplicationContext());
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("nativeTrackerId", this._trackingId);
        return hashMap;
    }

    @ReactMethod
    public void trackScreenView(String str, String str2) {
        a.a(getReactApplicationContext()).a(str, str2);
    }

    @ReactMethod
    public void trackEvent(String str, String str2, String str3, ReadableMap readableMap) {
        Integer num;
        String str4;
        if (this._shouldTrack) {
            Integer num2 = null;
            if (readableMap != null) {
                String string = readableMap.hasKey("label") ? readableMap.getString("label") : null;
                if (readableMap.hasKey(FirebaseAnalytics.b.VALUE)) {
                    num2 = Integer.valueOf(readableMap.getInt(FirebaseAnalytics.b.VALUE));
                }
                num = num2;
                str4 = string;
            } else {
                str4 = null;
                num = null;
            }
            a.a(getReactApplicationContext()).a(str, str2, str3, str4, num);
        }
    }

    @ReactMethod
    public void setCustomDimensionValue(String str, int i, String str2) {
        a.a(getReactApplicationContext()).a(str, i, str2);
    }

    @ReactMethod
    public void trackTiming(String str, String str2, Double d2, ReadableMap readableMap) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            HitBuilders.TimingBuilder value = new HitBuilders.TimingBuilder().setCategory(str2).setValue(d2.longValue());
            if (readableMap.hasKey("name")) {
                value.setVariable(readableMap.getString("name"));
            }
            if (readableMap.hasKey("label")) {
                value.setLabel(readableMap.getString("label"));
            }
            tracker.send(value.build());
        }
    }

    @ReactMethod
    public void trackPurchaseEvent(String str, ReadableMap readableMap, ReadableMap readableMap2, String str2, String str3) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.send(((HitBuilders.EventBuilder) ((HitBuilders.EventBuilder) new HitBuilders.EventBuilder().addProduct(getPurchaseProduct(readableMap))).setProductAction(getPurchaseTransaction(readableMap2))).setCategory(str2).setAction(str3).build());
        }
    }

    @ReactMethod
    public void trackMultiProductsPurchaseEvent(String str, ReadableArray readableArray, ReadableMap readableMap, String str2, String str3) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            HitBuilders.EventBuilder action = ((HitBuilders.EventBuilder) new HitBuilders.EventBuilder().setProductAction(getPurchaseTransaction(readableMap))).setCategory(str2).setAction(str3);
            for (int i = 0; i < readableArray.size(); i++) {
                action.addProduct(getPurchaseProduct(readableArray.getMap(i)));
            }
            tracker.send(action.build());
        }
    }

    private ProductAction getPurchaseTransaction(ReadableMap readableMap) {
        return new ProductAction(ProductAction.ACTION_PURCHASE).setTransactionId(readableMap.getString("id")).setTransactionTax(readableMap.getDouble(FirebaseAnalytics.b.TAX)).setTransactionRevenue(readableMap.getDouble("revenue")).setTransactionShipping(readableMap.getDouble(FirebaseAnalytics.b.SHIPPING)).setTransactionCouponCode(readableMap.getString("couponCode")).setTransactionAffiliation(readableMap.getString(FirebaseAnalytics.b.AFFILIATION));
    }

    private Product getPurchaseProduct(ReadableMap readableMap) {
        Product category = new Product().setId(readableMap.getString("id")).setName(readableMap.getString("name")).setBrand(readableMap.getString("brand")).setPrice(readableMap.getDouble(FirebaseAnalytics.b.PRICE)).setQuantity(readableMap.getInt(FirebaseAnalytics.b.QUANTITY)).setVariant(readableMap.getString("variant")).setCategory(readableMap.getString("category"));
        if (readableMap.hasKey("couponCode")) {
            category.setCouponCode(readableMap.getString("couponCode"));
        }
        return category;
    }

    @ReactMethod
    public void trackException(String str, String str2, Boolean bool) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.send(new HitBuilders.ExceptionBuilder().setDescription(str2).setFatal(bool.booleanValue()).build());
        }
    }

    @ReactMethod
    public void setUser(String str, String str2) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.set("&uid", str2);
        }
    }

    @ReactMethod
    public void allowIDFA(String str, Boolean bool) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.enableAdvertisingIdCollection(bool.booleanValue());
        }
    }

    @ReactMethod
    public void trackSocialInteraction(String str, String str2, String str3, String str4) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.send(new HitBuilders.SocialBuilder().setNetwork(str2).setAction(str3).setTarget(str4).build());
        }
    }

    @ReactMethod
    public void trackScreenViewWithCustomDimensionValues(String str, String str2, ReadableMap readableMap) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.setScreenName(str2);
            HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                screenViewBuilder.setCustomDimension(Integer.parseInt(nextKey), readableMap.getString(nextKey));
            }
            tracker.send(screenViewBuilder.build());
        }
    }

    @ReactMethod
    public void trackEventWithCustomDimensionValues(String str, String str2, String str3, ReadableMap readableMap, ReadableMap readableMap2) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            HitBuilders.EventBuilder action = new HitBuilders.EventBuilder().setCategory(str2).setAction(str3);
            if (readableMap.hasKey("label")) {
                action.setLabel(readableMap.getString("label"));
            }
            if (readableMap.hasKey(FirebaseAnalytics.b.VALUE)) {
                action.setValue((long) readableMap.getInt(FirebaseAnalytics.b.VALUE));
            }
            ReadableMapKeySetIterator keySetIterator = readableMap2.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                action.setCustomDimension(Integer.parseInt(nextKey), readableMap2.getString(nextKey));
            }
            tracker.send(action.build());
        }
    }

    @ReactMethod
    public void setSamplingRate(String str, Double d2) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.setSampleRate(d2.doubleValue());
        }
    }

    @ReactMethod
    public void setDryRun(Boolean bool) {
        GoogleAnalytics analyticsInstance = getAnalyticsInstance();
        if (analyticsInstance != null) {
            analyticsInstance.setDryRun(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setDispatchInterval(Integer num) {
        GoogleAnalytics analyticsInstance = getAnalyticsInstance();
        if (analyticsInstance != null) {
            analyticsInstance.setLocalDispatchPeriod(num.intValue());
        }
    }

    @ReactMethod
    public void setTrackUncaughtExceptions(String str, Boolean bool) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.enableExceptionReporting(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setAnonymizeIp(String str, Boolean bool) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.setAnonymizeIp(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setOptOut(Boolean bool) {
        GoogleAnalytics analyticsInstance = getAnalyticsInstance();
        if (analyticsInstance != null) {
            analyticsInstance.setAppOptOut(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setAppName(String str, String str2) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.setAppName(str2);
        }
    }

    @ReactMethod
    public void setAppVersion(String str, String str2) {
        Tracker tracker = getTracker(str);
        if (tracker != null) {
            tracker.setAppVersion(str2);
        }
    }
}
