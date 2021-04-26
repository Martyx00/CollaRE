package com.crashlytics.android.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ScrollView;
import android.widget.TextView;
import io.a.a.a.a.g.o;
import java.util.concurrent.CountDownLatch;

class CrashPromptDialog {
    private final AlertDialog.Builder dialog;
    private final OptInLatch latch;

    interface AlwaysSendCallback {
        void sendUserReportsWithoutPrompting(boolean z);
    }

    private static int dipsToPixels(float f, int i) {
        return (int) (f * ((float) i));
    }

    private static class OptInLatch {
        private final CountDownLatch latch;
        private boolean send;

        private OptInLatch() {
            this.send = false;
            this.latch = new CountDownLatch(1);
        }

        /* access modifiers changed from: package-private */
        public void setOptIn(boolean z) {
            this.send = z;
            this.latch.countDown();
        }

        /* access modifiers changed from: package-private */
        public boolean getOptIn() {
            return this.send;
        }

        /* access modifiers changed from: package-private */
        public void await() {
            try {
                this.latch.await();
            } catch (InterruptedException unused) {
            }
        }
    }

    public static CrashPromptDialog create(Activity activity, o oVar, final AlwaysSendCallback alwaysSendCallback) {
        final OptInLatch optInLatch = new OptInLatch();
        DialogStringResolver dialogStringResolver = new DialogStringResolver(activity, oVar);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        ScrollView createDialogView = createDialogView(activity, dialogStringResolver.getMessage());
        builder.setView(createDialogView).setTitle(dialogStringResolver.getTitle()).setCancelable(false).setNeutralButton(dialogStringResolver.getSendButtonTitle(), new DialogInterface.OnClickListener() {
            /* class com.crashlytics.android.core.CrashPromptDialog.AnonymousClass1 */

            public void onClick(DialogInterface dialogInterface, int i) {
                optInLatch.setOptIn(true);
                dialogInterface.dismiss();
            }
        });
        if (oVar.f6055d) {
            builder.setNegativeButton(dialogStringResolver.getCancelButtonTitle(), new DialogInterface.OnClickListener() {
                /* class com.crashlytics.android.core.CrashPromptDialog.AnonymousClass2 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    optInLatch.setOptIn(false);
                    dialogInterface.dismiss();
                }
            });
        }
        if (oVar.f) {
            builder.setPositiveButton(dialogStringResolver.getAlwaysSendButtonTitle(), new DialogInterface.OnClickListener() {
                /* class com.crashlytics.android.core.CrashPromptDialog.AnonymousClass3 */

                public void onClick(DialogInterface dialogInterface, int i) {
                    alwaysSendCallback.sendUserReportsWithoutPrompting(true);
                    optInLatch.setOptIn(true);
                    dialogInterface.dismiss();
                }
            });
        }
        return new CrashPromptDialog(builder, optInLatch);
    }

    private static ScrollView createDialogView(Activity activity, String str) {
        float f = activity.getResources().getDisplayMetrics().density;
        int dipsToPixels = dipsToPixels(f, 5);
        TextView textView = new TextView(activity);
        textView.setAutoLinkMask(15);
        textView.setText(str);
        textView.setTextAppearance(activity, 16973892);
        textView.setPadding(dipsToPixels, dipsToPixels, dipsToPixels, dipsToPixels);
        textView.setFocusable(false);
        ScrollView scrollView = new ScrollView(activity);
        scrollView.setPadding(dipsToPixels(f, 14), dipsToPixels(f, 2), dipsToPixels(f, 10), dipsToPixels(f, 12));
        scrollView.addView(textView);
        return scrollView;
    }

    private CrashPromptDialog(AlertDialog.Builder builder, OptInLatch optInLatch) {
        this.latch = optInLatch;
        this.dialog = builder;
    }

    public void show() {
        this.dialog.show();
    }

    public void await() {
        this.latch.await();
    }

    public boolean getOptIn() {
        return this.latch.getOptIn();
    }
}
