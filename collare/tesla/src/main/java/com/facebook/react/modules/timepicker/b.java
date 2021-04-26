package com.facebook.react.modules.timepicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.f;
import android.text.format.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/* compiled from: TimePickerDialogFragment */
public class b extends f {

    /* renamed from: a  reason: collision with root package name */
    private TimePickerDialog.OnTimeSetListener f3018a;

    /* renamed from: b  reason: collision with root package name */
    private DialogInterface.OnDismissListener f3019b;

    @Override // android.support.v4.app.f
    public Dialog onCreateDialog(Bundle bundle) {
        return a(getArguments(), getActivity(), this.f3018a);
    }

    static Dialog a(Bundle bundle, Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int i2 = instance.get(12);
        boolean is24HourFormat = DateFormat.is24HourFormat(context);
        c cVar = c.DEFAULT;
        if (!(bundle == null || bundle.getString("mode", null) == null)) {
            cVar = c.valueOf(bundle.getString("mode").toUpperCase(Locale.US));
        }
        if (bundle != null) {
            i = bundle.getInt("hour", instance.get(11));
            i2 = bundle.getInt("minute", instance.get(12));
            is24HourFormat = bundle.getBoolean("is24Hour", DateFormat.is24HourFormat(context));
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (cVar == c.CLOCK) {
                return new a(context, context.getResources().getIdentifier("ClockTimePickerDialog", "style", context.getPackageName()), onTimeSetListener, i, i2, is24HourFormat);
            }
            if (cVar == c.SPINNER) {
                return new a(context, context.getResources().getIdentifier("SpinnerTimePickerDialog", "style", context.getPackageName()), onTimeSetListener, i, i2, is24HourFormat);
            }
        }
        return new a(context, onTimeSetListener, i, i2, is24HourFormat);
    }

    @Override // android.support.v4.app.f
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.f3019b;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    public void a(DialogInterface.OnDismissListener onDismissListener) {
        this.f3019b = onDismissListener;
    }

    public void a(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        this.f3018a = onTimeSetListener;
    }
}
