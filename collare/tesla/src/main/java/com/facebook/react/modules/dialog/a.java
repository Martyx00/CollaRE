package com.facebook.react.modules.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.facebook.react.modules.dialog.DialogModule;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;

/* compiled from: AlertFragment */
public class a extends DialogFragment implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private final DialogModule.a f2877a;

    public a() {
        this.f2877a = null;
    }

    @SuppressLint({"ValidFragment"})
    public a(DialogModule.a aVar, Bundle bundle) {
        this.f2877a = aVar;
        setArguments(bundle);
    }

    public static Dialog a(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder title = new AlertDialog.Builder(context).setTitle(bundle.getString("title"));
        if (bundle.containsKey("button_positive")) {
            title.setPositiveButton(bundle.getString("button_positive"), onClickListener);
        }
        if (bundle.containsKey("button_negative")) {
            title.setNegativeButton(bundle.getString("button_negative"), onClickListener);
        }
        if (bundle.containsKey("button_neutral")) {
            title.setNeutralButton(bundle.getString("button_neutral"), onClickListener);
        }
        if (bundle.containsKey(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE)) {
            title.setMessage(bundle.getString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE));
        }
        if (bundle.containsKey("items")) {
            title.setItems(bundle.getCharSequenceArray("items"), onClickListener);
        }
        return title.create();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return a(getActivity(), getArguments(), this);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DialogModule.a aVar = this.f2877a;
        if (aVar != null) {
            aVar.onClick(dialogInterface, i);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogModule.a aVar = this.f2877a;
        if (aVar != null) {
            aVar.onDismiss(dialogInterface);
        }
    }
}
