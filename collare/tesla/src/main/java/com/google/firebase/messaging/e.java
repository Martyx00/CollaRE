package com.google.firebase.messaging;

import java.util.Locale;

public final class e extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private final int f3967a;

    e(String str) {
        super(str);
        int i = 4;
        if (str != null) {
            String lowerCase = str.toLowerCase(Locale.US);
            char c2 = 65535;
            switch (lowerCase.hashCode()) {
                case -1743242157:
                    if (lowerCase.equals("service_not_available")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -1290953729:
                    if (lowerCase.equals("toomanymessages")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -920906446:
                    if (lowerCase.equals("invalid_parameters")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -617027085:
                    if (lowerCase.equals("messagetoobig")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -95047692:
                    if (lowerCase.equals("missing_to")) {
                        c2 = 1;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                    i = 1;
                    break;
                case 2:
                    i = 2;
                    break;
                case 3:
                    i = 3;
                    break;
            }
            this.f3967a = i;
        }
        i = 0;
        this.f3967a = i;
    }
}
