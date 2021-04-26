package com.facebook.react.modules.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.f;
import com.facebook.react.modules.dialog.DialogModule;

/* compiled from: SupportAlertFragment */
public class b extends f implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private final DialogModule.a f2878a;

    public b() {
        this.f2878a = null;
    }

    @SuppressLint({"ValidFragment"})
    public b(DialogModule.a aVar, Bundle bundle) {
        this.f2878a = aVar;
        setArguments(bundle);
    }

    @Override // android.support.v4.app.f
    public Dialog onCreateDialog(Bundle bundle) {
        return a.a(getActivity(), getArguments(), this);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DialogModule.a aVar = this.f2878a;
        if (aVar != null) {
            aVar.onClick(dialogInterface, i);
        }
    }

    @Override // android.support.v4.app.f
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogModule.a aVar = this.f2878a;
        if (aVar != null) {
            aVar.onDismiss(dialogInterface);
        }
    }
}
