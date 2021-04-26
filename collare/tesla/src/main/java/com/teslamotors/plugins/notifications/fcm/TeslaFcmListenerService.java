package com.teslamotors.plugins.notifications.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.d;
import com.teslamotors.plugins.notifications.b;

public class TeslaFcmListenerService extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void a(d dVar) {
        b.a(getApplicationContext()).a(dVar.a());
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void b(String str) {
        b.a(getApplicationContext()).a(str);
    }
}
