package com.crashlytics.android.core;

import io.a.a.a.a.b.i;
import io.a.a.a.c;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

class MetaDataStore {
    private static final String KEYDATA_SUFFIX = "keys";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NAME = "userName";
    private static final String METADATA_EXT = ".meta";
    private static final String USERDATA_SUFFIX = "user";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final File filesDir;

    public MetaDataStore(File file) {
        this.filesDir = file;
    }

    public void writeUserData(String str, UserMetaData userMetaData) {
        Throwable th;
        Exception e;
        File userDataFileForSession = getUserDataFileForSession(str);
        BufferedWriter bufferedWriter = null;
        try {
            String userDataToJson = userDataToJson(userMetaData);
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userDataFileForSession), UTF_8));
            try {
                bufferedWriter2.write(userDataToJson);
                bufferedWriter2.flush();
                i.a((Closeable) bufferedWriter2, "Failed to close user metadata file.");
            } catch (Exception e2) {
                e = e2;
                bufferedWriter = bufferedWriter2;
                try {
                    c.g().e(CrashlyticsCore.TAG, "Error serializing user metadata.", e);
                    i.a((Closeable) bufferedWriter, "Failed to close user metadata file.");
                } catch (Throwable th2) {
                    th = th2;
                    i.a((Closeable) bufferedWriter, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = bufferedWriter2;
                i.a((Closeable) bufferedWriter, "Failed to close user metadata file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            c.g().e(CrashlyticsCore.TAG, "Error serializing user metadata.", e);
            i.a((Closeable) bufferedWriter, "Failed to close user metadata file.");
        }
    }

    public UserMetaData readUserData(String str) {
        Throwable th;
        Exception e;
        File userDataFileForSession = getUserDataFileForSession(str);
        if (!userDataFileForSession.exists()) {
            return UserMetaData.EMPTY;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(userDataFileForSession);
            try {
                UserMetaData jsonToUserData = jsonToUserData(i.a((InputStream) fileInputStream2));
                i.a((Closeable) fileInputStream2, "Failed to close user metadata file.");
                return jsonToUserData;
            } catch (Exception e2) {
                e = e2;
                fileInputStream = fileInputStream2;
                try {
                    c.g().e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
                    i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    return UserMetaData.EMPTY;
                } catch (Throwable th2) {
                    th = th2;
                    i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            c.g().e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
            i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
            return UserMetaData.EMPTY;
        }
    }

    public void writeKeyData(String str, Map<String, String> map) {
        Throwable th;
        Exception e;
        File keysFileForSession = getKeysFileForSession(str);
        BufferedWriter bufferedWriter = null;
        try {
            String keysDataToJson = keysDataToJson(map);
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(keysFileForSession), UTF_8));
            try {
                bufferedWriter2.write(keysDataToJson);
                bufferedWriter2.flush();
                i.a((Closeable) bufferedWriter2, "Failed to close key/value metadata file.");
            } catch (Exception e2) {
                e = e2;
                bufferedWriter = bufferedWriter2;
                try {
                    c.g().e(CrashlyticsCore.TAG, "Error serializing key/value metadata.", e);
                    i.a((Closeable) bufferedWriter, "Failed to close key/value metadata file.");
                } catch (Throwable th2) {
                    th = th2;
                    i.a((Closeable) bufferedWriter, "Failed to close key/value metadata file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = bufferedWriter2;
                i.a((Closeable) bufferedWriter, "Failed to close key/value metadata file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            c.g().e(CrashlyticsCore.TAG, "Error serializing key/value metadata.", e);
            i.a((Closeable) bufferedWriter, "Failed to close key/value metadata file.");
        }
    }

    public Map<String, String> readKeyData(String str) {
        Throwable th;
        Exception e;
        File keysFileForSession = getKeysFileForSession(str);
        if (!keysFileForSession.exists()) {
            return Collections.emptyMap();
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(keysFileForSession);
            try {
                Map<String, String> jsonToKeysData = jsonToKeysData(i.a((InputStream) fileInputStream2));
                i.a((Closeable) fileInputStream2, "Failed to close user metadata file.");
                return jsonToKeysData;
            } catch (Exception e2) {
                e = e2;
                fileInputStream = fileInputStream2;
                try {
                    c.g().e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
                    i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    return Collections.emptyMap();
                } catch (Throwable th2) {
                    th = th2;
                    i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            c.g().e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
            i.a((Closeable) fileInputStream, "Failed to close user metadata file.");
            return Collections.emptyMap();
        }
    }

    public File getUserDataFileForSession(String str) {
        File file = this.filesDir;
        return new File(file, str + USERDATA_SUFFIX + METADATA_EXT);
    }

    public File getKeysFileForSession(String str) {
        File file = this.filesDir;
        return new File(file, str + KEYDATA_SUFFIX + METADATA_EXT);
    }

    private static UserMetaData jsonToUserData(String str) {
        JSONObject jSONObject = new JSONObject(str);
        return new UserMetaData(valueOrNull(jSONObject, KEY_USER_ID), valueOrNull(jSONObject, KEY_USER_NAME), valueOrNull(jSONObject, KEY_USER_EMAIL));
    }

    private static String userDataToJson(final UserMetaData userMetaData) {
        return new JSONObject() {
            /* class com.crashlytics.android.core.MetaDataStore.AnonymousClass1 */

            {
                put(MetaDataStore.KEY_USER_ID, userMetaData.id);
                put(MetaDataStore.KEY_USER_NAME, userMetaData.name);
                put(MetaDataStore.KEY_USER_EMAIL, userMetaData.email);
            }
        }.toString();
    }

    private static Map<String, String> jsonToKeysData(String str) {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, valueOrNull(jSONObject, next));
        }
        return hashMap;
    }

    private static String keysDataToJson(Map<String, String> map) {
        return new JSONObject(map).toString();
    }

    private static String valueOrNull(JSONObject jSONObject, String str) {
        if (!jSONObject.isNull(str)) {
            return jSONObject.optString(str, null);
        }
        return null;
    }
}
