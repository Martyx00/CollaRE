package com.teslamotors.plugins.ble.b;

import com.teslamotors.a.a;

/* compiled from: CommandStatus */
public enum b {
    OK,
    WAIT,
    ERROR;

    public static b a(a.bl blVar) {
        switch (blVar) {
            case OPERATIONSTATUS_OK:
                return OK;
            case OPERATIONSTATUS_WAIT:
                return WAIT;
            default:
                return ERROR;
        }
    }

    public static String a(a.ce ceVar) {
        switch (ceVar) {
            case SIGNEDMESSAGE_INFORMATION_NONE:
                return "PHONE_KEY_COMMAND_NO_ERROR";
            case SIGNEDMESSAGE_INFORMATION_FAULT_NOT_ON_WHITELIST:
                return "PHONE_KEY_NOT_ON_WHITELIST";
            default:
                return "PHONE_KEY_COMMAND_FAILURE";
        }
    }
}
