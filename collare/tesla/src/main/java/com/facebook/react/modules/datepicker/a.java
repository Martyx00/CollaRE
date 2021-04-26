package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.f;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Locale;

@SuppressLint({"ValidFragment"})
/* compiled from: DatePickerDialogFragment */
public class a extends f {

    /* renamed from: a  reason: collision with root package name */
    private DatePickerDialog.OnDateSetListener f2845a;

    /* renamed from: b  reason: collision with root package name */
    private DialogInterface.OnDismissListener f2846b;

    @Override // android.support.v4.app.f
    public Dialog onCreateDialog(Bundle bundle) {
        return a(getArguments(), getActivity(), this.f2845a);
    }

    static Dialog a(Bundle bundle, Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {
        c cVar;
        Calendar instance = Calendar.getInstance();
        if (bundle != null && bundle.containsKey("date")) {
            instance.setTimeInMillis(bundle.getLong("date"));
        }
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        b valueOf = (bundle == null || bundle.getString("mode", null) == null) ? b.DEFAULT : b.valueOf(bundle.getString("mode").toUpperCase(Locale.US));
        if (Build.VERSION.SDK_INT >= 21) {
            switch (valueOf) {
                case CALENDAR:
                    cVar = new c(context, context.getResources().getIdentifier("CalendarDatePickerDialog", "style", context.getPackageName()), onDateSetListener, i, i2, i3);
                    break;
                case SPINNER:
                    cVar = new c(context, context.getResources().getIdentifier("SpinnerDatePickerDialog", "style", context.getPackageName()), onDateSetListener, i, i2, i3);
                    break;
                case DEFAULT:
                    cVar = new c(context, onDateSetListener, i, i2, i3);
                    break;
                default:
                    cVar = null;
                    break;
            }
        } else {
            c cVar2 = new c(context, onDateSetListener, i, i2, i3);
            switch (valueOf) {
                case CALENDAR:
                    cVar2.getDatePicker().setCalendarViewShown(true);
                    cVar2.getDatePicker().setSpinnersShown(false);
                    break;
                case SPINNER:
                    cVar2.getDatePicker().setCalendarViewShown(false);
                    break;
            }
            cVar = cVar2;
        }
        DatePicker datePicker = cVar.getDatePicker();
        if (bundle == null || !bundle.containsKey("minDate")) {
            datePicker.setMinDate(-2208988800001L);
        } else {
            instance.setTimeInMillis(bundle.getLong("minDate"));
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            datePicker.setMinDate(instance.getTimeInMillis());
        }
        if (bundle != null && bundle.containsKey("maxDate")) {
            instance.setTimeInMillis(bundle.getLong("maxDate"));
            instance.set(11, 23);
            instance.set(12, 59);
            instance.set(13, 59);
            instance.set(14, 999);
            datePicker.setMaxDate(instance.getTimeInMillis());
        }
        return cVar;
    }

    @Override // android.support.v4.app.f
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.f2846b;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.f2845a = onDateSetListener;
    }

    /* access modifiers changed from: package-private */
    public void a(DialogInterface.OnDismissListener onDismissListener) {
        this.f2846b = onDismissListener;
    }
}
