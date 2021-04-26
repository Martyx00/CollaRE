package com.facebook.react.views.textinput;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

/* compiled from: ReactEditTextInputConnectionWrapper */
class d extends InputConnectionWrapper {

    /* renamed from: a  reason: collision with root package name */
    private c f3519a;

    /* renamed from: b  reason: collision with root package name */
    private com.facebook.react.uimanager.events.d f3520b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3521c;

    /* renamed from: d  reason: collision with root package name */
    private String f3522d = null;

    public d(InputConnection inputConnection, ReactContext reactContext, c cVar) {
        super(inputConnection, false);
        this.f3520b = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.f3519a = cVar;
    }

    public boolean beginBatchEdit() {
        this.f3521c = true;
        return super.beginBatchEdit();
    }

    public boolean endBatchEdit() {
        this.f3521c = false;
        String str = this.f3522d;
        if (str != null) {
            b(str);
            this.f3522d = null;
        }
        return super.endBatchEdit();
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        int selectionStart = this.f3519a.getSelectionStart();
        int selectionEnd = this.f3519a.getSelectionEnd();
        boolean composingText = super.setComposingText(charSequence, i);
        int selectionStart2 = this.f3519a.getSelectionStart();
        boolean z = false;
        boolean z2 = selectionStart == selectionEnd;
        boolean z3 = selectionStart2 == selectionStart;
        if (selectionStart2 < selectionStart || selectionStart2 <= 0) {
            z = true;
        }
        a((z || (!z2 && z3)) ? "Backspace" : String.valueOf(this.f3519a.getText().charAt(selectionStart2 - 1)));
        return composingText;
    }

    public boolean commitText(CharSequence charSequence, int i) {
        String charSequence2 = charSequence.toString();
        if (charSequence2.length() <= 1) {
            if (charSequence2.equals("")) {
                charSequence2 = "Backspace";
            }
            a(charSequence2);
        }
        return super.commitText(charSequence, i);
    }

    public boolean deleteSurroundingText(int i, int i2) {
        b("Backspace");
        return super.deleteSurroundingText(i, i2);
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            if (keyEvent.getKeyCode() == 67) {
                b("Backspace");
            } else if (keyEvent.getKeyCode() == 66) {
                b("Enter");
            }
        }
        return super.sendKeyEvent(keyEvent);
    }

    private void a(String str) {
        if (this.f3521c) {
            this.f3522d = str;
        } else {
            b(str);
        }
    }

    private void b(String str) {
        if (str.equals("\n")) {
            str = "Enter";
        }
        this.f3520b.a(new j(this.f3519a.getId(), str));
    }
}
