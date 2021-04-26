package com.teslamotors.plugins.client.d;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSONHelper */
public class b {
    public static Map<String, Object> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, a(jSONObject.get(next)));
        }
        return hashMap;
    }

    public static List a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.get(i)));
        }
        return arrayList;
    }

    private static Object a(Object obj) {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj);
        }
        return obj instanceof JSONArray ? a((JSONArray) obj) : obj;
    }

    public static JSONObject a(ReadableMap readableMap) {
        JSONObject jSONObject = new JSONObject();
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        if (!keySetIterator.hasNextKey()) {
            return null;
        }
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            try {
                switch (readableMap.getType(nextKey)) {
                    case Null:
                        jSONObject.put(nextKey, (Object) null);
                        continue;
                    case Boolean:
                        jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
                        continue;
                    case Number:
                        jSONObject.put(nextKey, (long) readableMap.getDouble(nextKey));
                        continue;
                    case String:
                        jSONObject.put(nextKey, readableMap.getString(nextKey));
                        continue;
                    case Map:
                        jSONObject.put(nextKey, a(readableMap.getMap(nextKey)));
                        continue;
                    case Array:
                        jSONObject.put(nextKey, readableMap.getArray(nextKey));
                        continue;
                    default:
                        continue;
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    public static WritableMap b(JSONObject jSONObject) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (jSONObject == null) {
            return null;
        }
        Iterator<String> keys = jSONObject.keys();
        if (!keys.hasNext()) {
            return null;
        }
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj != null) {
                    if (!obj.toString().equals("null")) {
                        if (obj instanceof Boolean) {
                            writableNativeMap.putBoolean(next, ((Boolean) obj).booleanValue());
                        } else if (obj instanceof Integer) {
                            writableNativeMap.putInt(next, ((Integer) obj).intValue());
                        } else if (obj instanceof Double) {
                            writableNativeMap.putDouble(next, ((Double) obj).doubleValue());
                        } else if (obj instanceof String) {
                            writableNativeMap.putString(next, (String) obj);
                        } else if (obj instanceof JSONObject) {
                            writableNativeMap.putMap(next, b((JSONObject) obj));
                        } else if (obj instanceof JSONArray) {
                            writableNativeMap.putArray(next, b((JSONArray) obj));
                        }
                    }
                }
                writableNativeMap.putNull(next);
            } catch (JSONException unused) {
            }
        }
        return writableNativeMap;
    }

    public static WritableArray b(JSONArray jSONArray) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object obj = jSONArray.get(i);
                if (obj == null) {
                    writableNativeArray.pushNull();
                } else if (obj instanceof Boolean) {
                    writableNativeArray.pushBoolean(((Boolean) obj).booleanValue());
                } else if (obj instanceof Integer) {
                    writableNativeArray.pushInt(((Integer) obj).intValue());
                } else if (obj instanceof Double) {
                    writableNativeArray.pushDouble(((Double) obj).doubleValue());
                } else if (obj instanceof String) {
                    writableNativeArray.pushString((String) obj);
                } else if (obj instanceof JSONObject) {
                    writableNativeArray.pushMap(b((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    writableNativeArray.pushArray(b((JSONArray) obj));
                }
            } catch (JSONException unused) {
            }
        }
        return writableNativeArray;
    }
}
