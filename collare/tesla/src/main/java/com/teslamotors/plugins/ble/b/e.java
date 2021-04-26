package com.teslamotors.plugins.ble.b;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: MessageType */
public enum e {
    SetVin,
    SetAccountEmail,
    ScanForPeripherals,
    ClearPeripherals,
    ResetCommandPeripheral,
    AddToWhitelist,
    RemoveFromWhitelist,
    GetWhiteslistUpdate,
    GetStatus,
    GetPublicKeyInfo,
    StartMonitoringRSSI,
    StopMonitoringRSSI,
    SetConnectionBehaviorWhenUnauthorized,
    AuthResponse,
    UnlockCar,
    LockCar,
    OpenTrunk,
    OpenFrunk,
    OpenChargePort,
    CloseChargePort,
    ScanForPeripheralsResult,
    ClearPeripheralsResult,
    PublicKeyInfoResult,
    CommandResult,
    StatusMessage,
    LogMessage,
    SetEventSubscriptionsReady,
    Register,
    Unregister,
    RegisterComplete,
    PromoteServiceToForeground,
    NFCGetPublicKeyBytes,
    NFCGetPublicKeyBytesInfo,
    NFCEncryptWithSharedSecret,
    NFCEncryptWithSharedSecretResponse,
    NFCProtoMessage,
    SwitchProductMessage;
    
    private static final Map<Integer, e> L = new HashMap();

    public String b() {
        return "data";
    }

    static {
        Iterator it = EnumSet.allOf(e.class).iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            L.put(Integer.valueOf(eVar.ordinal()), eVar);
        }
    }

    public int a() {
        return ordinal();
    }

    public static e a(int i) {
        return L.get(Integer.valueOf(i));
    }

    public static String b(int i) {
        String format = String.format("Unknown - [%d]", Integer.valueOf(i));
        e a2 = a(i);
        return a2 != null ? a2.name() : format;
    }
}
