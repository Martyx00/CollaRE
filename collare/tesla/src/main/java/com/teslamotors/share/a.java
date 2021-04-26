package com.teslamotors.share;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.measurement.AppMeasurement;
import com.teslamotors.share.b;

/* compiled from: PopupDialog */
public class a extends DialogFragment {

    /* renamed from: a  reason: collision with root package name */
    ImageView f5657a;

    /* renamed from: b  reason: collision with root package name */
    ProgressBar f5658b;

    /* renamed from: c  reason: collision with root package name */
    TextView f5659c;

    /* renamed from: d  reason: collision with root package name */
    TextView f5660d;
    String e;
    int f;
    int g;
    Handler h = new Handler();
    Runnable i;

    /* access modifiers changed from: package-private */
    /* renamed from: com.teslamotors.share.a$a  reason: collision with other inner class name */
    /* compiled from: PopupDialog */
    public enum EnumC0152a {
        Alert,
        Error,
        Success,
        Processing,
        Timeout
    }

    static void a(FragmentManager fragmentManager, EnumC0152a aVar, String str, int i2) {
        a aVar2 = (a) fragmentManager.findFragmentByTag("popup_dialog");
        if (aVar2 == null || !aVar2.isResumed()) {
            a aVar3 = new a();
            Bundle bundle = new Bundle();
            bundle.putInt(AppMeasurement.Param.TYPE, aVar.ordinal());
            bundle.putString("msg", str);
            bundle.putInt("timeouts", i2);
            aVar3.setArguments(bundle);
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.addToBackStack(null);
            aVar3.show(beginTransaction, "popup_dialog");
            return;
        }
        aVar2.a(aVar.ordinal(), str, i2);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f = arguments.getInt(AppMeasurement.Param.TYPE);
            this.e = arguments.getString("msg");
            this.g = arguments.getInt("timeouts");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(b.c.share_popup, viewGroup);
        getDialog().requestWindowFeature(1);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setStyle(2, 16973829);
        this.f5657a = (ImageView) inflate.findViewById(b.C0153b.image);
        this.f5658b = (ProgressBar) inflate.findViewById(b.C0153b.spinner);
        this.f5659c = (TextView) inflate.findViewById(b.C0153b.title);
        this.f5660d = (TextView) inflate.findViewById(b.C0153b.text);
        a(this.f, this.e, this.g);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void a(int i2, String str, int i3) {
        a(i2);
        this.f5660d.setText(str);
        this.f5660d.setVisibility(str.equals("") ? 8 : 0);
        if (i3 != 0) {
            this.i = new Runnable() {
                /* class com.teslamotors.share.a.AnonymousClass1 */

                public void run() {
                    if (a.this.isResumed() && !a.this.isRemoving()) {
                        a.this.dismiss();
                        a.this.getActivity().finish();
                        a.this.i = null;
                    }
                }
            };
            this.h.postDelayed(this.i, (long) (i3 * 1000));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Runnable runnable = this.i;
        if (runnable != null) {
            this.h.removeCallbacks(runnable);
        }
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            getActivity().finish();
        }
    }

    @SuppressLint({"ConstantConditions"})
    private void a(int i2) {
        if (i2 == EnumC0152a.Alert.ordinal()) {
            this.f5657a.setImageResource(b.a.ic_error);
            this.f5657a.setVisibility(0);
            this.f5658b.setVisibility(8);
            this.f5659c.setText(b.d.share_popup_title_info);
        } else if (i2 == EnumC0152a.Error.ordinal()) {
            this.f5657a.setImageResource(b.a.ic_error);
            this.f5657a.setVisibility(0);
            this.f5658b.setVisibility(8);
            this.f5659c.setText(b.d.share_popup_title_error);
        } else if (i2 == EnumC0152a.Success.ordinal()) {
            this.f5657a.setImageResource(b.a.ic_success);
            this.f5657a.setVisibility(0);
            this.f5658b.setVisibility(8);
            this.f5659c.setText(b.d.share_popup_title_success);
        } else if (i2 == EnumC0152a.Processing.ordinal()) {
            this.f5658b.setVisibility(0);
            this.f5657a.setVisibility(8);
            this.f5659c.setText(b.d.share_popup_title_sending);
        } else if (i2 == EnumC0152a.Timeout.ordinal()) {
            this.f5657a.setImageResource(b.a.ic_error);
            this.f5657a.setVisibility(0);
            this.f5658b.setVisibility(8);
            this.f5659c.setText(b.d.share_popup_title_error);
        }
    }
}
