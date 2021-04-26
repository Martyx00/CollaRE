package com.facebook.react;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.c;
import android.view.KeyEvent;
import com.facebook.react.modules.core.b;
import com.facebook.react.modules.core.d;

/* compiled from: ReactActivity */
public abstract class g extends c implements b, com.facebook.react.modules.core.c {
    private final h k = j();

    /* access modifiers changed from: protected */
    public String i() {
        return null;
    }

    protected g() {
    }

    /* access modifiers changed from: protected */
    public h j() {
        return new h(this, i());
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.ag, android.support.v4.app.h, android.support.v7.app.c
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k.a(bundle);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.h
    public void onPause() {
        super.onPause();
        this.k.e();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.h
    public void onResume() {
        super.onResume();
        this.k.f();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.h, android.support.v7.app.c
    public void onDestroy() {
        super.onDestroy();
        this.k.g();
    }

    @Override // android.support.v4.app.h
    public void onActivityResult(int i, int i2, Intent intent) {
        this.k.a(i, i2, intent);
    }

    @Override // android.support.v7.app.c
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.k.a(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.k.b(i, keyEvent) || super.onKeyUp(i, keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return this.k.c(i, keyEvent) || super.onKeyLongPress(i, keyEvent);
    }

    @Override // android.support.v4.app.h
    public void onBackPressed() {
        if (!this.k.h()) {
            super.onBackPressed();
        }
    }

    @Override // com.facebook.react.modules.core.b
    public void k() {
        super.onBackPressed();
    }

    @Override // android.support.v4.app.h
    public void onNewIntent(Intent intent) {
        if (!this.k.a(intent)) {
            super.onNewIntent(intent);
        }
    }

    @Override // com.facebook.react.modules.core.c
    public void a(String[] strArr, int i, d dVar) {
        this.k.a(strArr, i, dVar);
    }

    @Override // android.support.v4.app.h, android.support.v4.app.a.AbstractC0004a
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.k.a(i, strArr, iArr);
    }
}
