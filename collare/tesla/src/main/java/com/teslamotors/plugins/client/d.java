package com.teslamotors.plugins.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: OwnerAPIError */
public enum d {
    OWNERAPI_ERROR_UNAUTHORIZED("UNAUTHORIZED"),
    OWNERAPI_ERROR_NOT_FOUND("NOT_FOUND"),
    OWNERAPI_ERROR_MOBILE_ACCESS_DISABLED("MOBILE_ACCESS_DISABLED"),
    OWNERAPI_ERROR_VEHICLE_IN_SERVICE("VEHICLE_IN_SERVICE"),
    OWNERAPI_ERROR_VEHICLE_UNAVAILABLE("VEHICLE_UNAVAILABLE"),
    OWNERAPI_ERROR_ACCOUNT_LOCKED("ACCOUNT_LOCKED"),
    OWNERAPI_ERROR_TOO_MANY_REQUESTS("TOO_MANY_REQUESTS"),
    OWNERAPI_ERROR_SERVER_ERROR("SERVER_ERROR"),
    OWNERAPI_ERROR_SERVICE_MAINTENANCE("SERVICE_MAINTENANCE"),
    OWNERAPI_ERROR_EMPTY_RESPONSE("EMPTY_RESPONSE"),
    OWNERAPI_ERROR_BAD_RESPONSE("BAD_RESPONSE"),
    OWNERAPI_ERROR_UNKNOWN_ERROR("UNKNOWN_ERROR");
    
    static final HashMap<String, d> m = new HashMap<>();
    public static final HashSet<d> n = new HashSet<>(Arrays.asList(OWNERAPI_ERROR_UNAUTHORIZED, OWNERAPI_ERROR_ACCOUNT_LOCKED, OWNERAPI_ERROR_SERVICE_MAINTENANCE, OWNERAPI_ERROR_MOBILE_ACCESS_DISABLED, OWNERAPI_ERROR_NOT_FOUND, OWNERAPI_ERROR_TOO_MANY_REQUESTS));
    public static final HashSet<String> o = new HashSet<>(Arrays.asList(OWNERAPI_ERROR_UNAUTHORIZED.a(), OWNERAPI_ERROR_ACCOUNT_LOCKED.a(), OWNERAPI_ERROR_SERVICE_MAINTENANCE.a(), OWNERAPI_ERROR_MOBILE_ACCESS_DISABLED.a(), OWNERAPI_ERROR_NOT_FOUND.a(), OWNERAPI_ERROR_TOO_MANY_REQUESTS.a()));
    private String p;

    static {
        d[] values = values();
        for (d dVar : values) {
            m.put(dVar.p, dVar);
        }
    }

    private d(String str) {
        this.p = str;
    }

    public String a() {
        return this.p;
    }

    public static d a(String str) {
        if (m.containsKey(str)) {
            return m.get(str);
        }
        return OWNERAPI_ERROR_UNKNOWN_ERROR;
    }
}
