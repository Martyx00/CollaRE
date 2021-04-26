package com.teslamotors.plugins.alert;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.f;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.teslamotors.plugins.alert.AlertPromptModule;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import d.a.a.a;
import java.util.Arrays;
import java.util.List;

@SuppressLint({"ValidFragment"})
/* compiled from: AlertFragment */
public class a extends f implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final List<String> f5323a = Arrays.asList("login-password", "secure-text");

    /* renamed from: b  reason: collision with root package name */
    private final AlertPromptModule.a f5324b;

    public a() {
        this.f5324b = null;
    }

    @SuppressLint({"ValidFragment"})
    public a(AlertPromptModule.a aVar, Bundle bundle) {
        this.f5324b = aVar;
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
        if (bundle.containsKey("alert_type")) {
            View inflate = LayoutInflater.from(context).inflate(a.b.alert_prompt_dialog_fragment, (ViewGroup) null);
            boolean contains = f5323a.contains(bundle.getString("alert_type", "default"));
            EditText editText = (EditText) inflate.findViewById(a.C0154a.inputValue);
            if (contains) {
                editText.setInputType(129);
                editText.setSelection(editText.getText().length());
            }
            if (bundle.containsKey("default_value")) {
                editText.setHint(bundle.getString("default_value"));
            }
            title.setView(inflate);
        }
        return title.create();
    }

    @Override // android.support.v4.app.f
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog a2 = a(getActivity(), getArguments(), this);
        a2.getWindow().setSoftInputMode(4);
        return a2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f5324b != null) {
            EditText editText = (EditText) ((AlertDialog) dialogInterface).findViewById(a.C0154a.inputValue);
            String str = null;
            if (editText != null) {
                str = editText.getText().toString();
            }
            this.f5324b.a(dialogInterface, i, str);
        }
    }

    @Override // android.support.v4.app.f
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        AlertPromptModule.a aVar = this.f5324b;
        if (aVar != null) {
            aVar.onDismiss(dialogInterface);
        }
    }
}
